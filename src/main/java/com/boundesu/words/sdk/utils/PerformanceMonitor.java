package com.boundesu.words.sdk.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 性能监控工具类
 * 用于监控文档处理操作的性能指标
 *
 * @author Boundesu Team
 * @version 1.0.0
 */
public final class PerformanceMonitor {

    private static final Logger logger = Logger.getLogger(PerformanceMonitor.class.getName());

    // 操作计数器
    private static final ConcurrentHashMap<String, AtomicLong> operationCounts = new ConcurrentHashMap<>();

    // 操作总耗时
    private static final ConcurrentHashMap<String, AtomicLong> operationTotalTime = new ConcurrentHashMap<>();

    // 当前正在执行的操作
    private static final ThreadLocal<OperationContext> currentOperation = new ThreadLocal<>();

    // 防止实例化
    private PerformanceMonitor() {
        throw new UnsupportedOperationException("工具类不能被实例化");
    }

    /**
     * 开始监控操作
     *
     * @param operationName 操作名称
     * @return 操作上下文
     */
    public static OperationContext startOperation(String operationName) {
        OperationContext context = new OperationContext(operationName, Instant.now());
        currentOperation.set(context);

        logger.fine("开始操作: " + operationName);
        return context;
    }

    /**
     * 结束监控操作
     *
     * @param context 操作上下文
     */
    public static void endOperation(OperationContext context) {
        if (context == null) {
            return;
        }

        Instant endTime = Instant.now();
        Duration duration = Duration.between(context.getStartTime(), endTime);
        long durationMillis = duration.toMillis();

        String operationName = context.getOperationName();

        // 更新计数器
        operationCounts.computeIfAbsent(operationName, k -> new AtomicLong(0)).incrementAndGet();
        operationTotalTime.computeIfAbsent(operationName, k -> new AtomicLong(0)).addAndGet(durationMillis);

        logger.fine(String.format("操作完成: %s, 耗时: %d ms", operationName, durationMillis));

        // 清理线程本地变量
        currentOperation.remove();
    }

    /**
     * 监控代码块执行
     *
     * @param operationName 操作名称
     * @param operation     要执行的操作
     * @param <T>           返回类型
     * @return 操作结果
     * @throws Exception 操作异常
     */
    public static <T> T monitor(String operationName, MonitoredOperation<T> operation) throws Exception {
        OperationContext context = startOperation(operationName);
        try {
            return operation.execute();
        } finally {
            endOperation(context);
        }
    }

    /**
     * 监控无返回值的代码块执行
     *
     * @param operationName 操作名称
     * @param operation     要执行的操作
     * @throws Exception 操作异常
     */
    public static void monitor(String operationName, MonitoredVoidOperation operation) throws Exception {
        OperationContext context = startOperation(operationName);
        try {
            operation.execute();
        } finally {
            endOperation(context);
        }
    }

    /**
     * 获取操作统计信息
     *
     * @param operationName 操作名称
     * @return 统计信息
     */
    public static OperationStats getOperationStats(String operationName) {
        long count = operationCounts.getOrDefault(operationName, new AtomicLong(0)).get();
        long totalTime = operationTotalTime.getOrDefault(operationName, new AtomicLong(0)).get();

        double averageTime = count > 0 ? (double) totalTime / count : 0.0;

        return new OperationStats(operationName, count, totalTime, averageTime);
    }

    /**
     * 打印所有操作的统计信息
     */
    public static void printAllStats() {
        System.out.println("=== 性能监控统计 ===");
        System.out.printf("%-30s %10s %15s %15s%n", "操作名称", "执行次数", "总耗时(ms)", "平均耗时(ms)");
        StringBuilder dashLine = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            dashLine.append("-");
        }
        System.out.println(dashLine.toString());

        operationCounts.keySet().stream()
                .sorted()
                .forEach(operationName -> {
                    OperationStats stats = getOperationStats(operationName);
                    System.out.printf("%-30s %10d %15d %15.2f%n",
                            stats.getOperationName(),
                            stats.getCount(),
                            stats.getTotalTime(),
                            stats.getAverageTime()
                    );
                });

        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            separator.append("=");
        }
        System.out.println(separator.toString());
    }

    /**
     * 清除所有统计数据
     */
    public static void clearStats() {
        operationCounts.clear();
        operationTotalTime.clear();
        logger.info("性能监控统计数据已清除");
    }

    /**
     * 获取当前正在执行的操作
     *
     * @return 当前操作上下文
     */
    public static OperationContext getCurrentOperation() {
        return currentOperation.get();
    }

    /**
     * 操作上下文类
     */
    public static class OperationContext {
        private final String operationName;
        private final Instant startTime;

        public OperationContext(String operationName, Instant startTime) {
            this.operationName = operationName;
            this.startTime = startTime;
        }

        public String getOperationName() {
            return operationName;
        }

        public Instant getStartTime() {
            return startTime;
        }

        public Duration getElapsedTime() {
            return Duration.between(startTime, Instant.now());
        }
    }

    /**
     * 操作统计信息类
     */
    public static class OperationStats {
        private final String operationName;
        private final long count;
        private final long totalTime;
        private final double averageTime;

        public OperationStats(String operationName, long count, long totalTime, double averageTime) {
            this.operationName = operationName;
            this.count = count;
            this.totalTime = totalTime;
            this.averageTime = averageTime;
        }

        public String getOperationName() {
            return operationName;
        }

        public long getCount() {
            return count;
        }

        public long getTotalTime() {
            return totalTime;
        }

        public double getAverageTime() {
            return averageTime;
        }

        @Override
        public String toString() {
            return String.format("OperationStats{name='%s', count=%d, totalTime=%d ms, avgTime=%.2f ms}",
                    operationName, count, totalTime, averageTime);
        }
    }

    /**
     * 被监控的操作接口（有返回值）
     */
    @FunctionalInterface
    public interface MonitoredOperation<T> {
        T execute() throws Exception;
    }

    /**
     * 被监控的操作接口（无返回值）
     */
    @FunctionalInterface
    public interface MonitoredVoidOperation {
        void execute() throws Exception;
    }
}