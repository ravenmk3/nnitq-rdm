package raven.nnitq.restdm.core.common;

/**
 * @author Raven
 */
public final class Guard {

    private Guard() {
        throw new UnsupportedOperationException();
    }

    /**
     * 检查参数值是否为Null
     *
     * @param value   参数值
     * @param name    参数名
     * @param message 错误消息格式
     * @return 参数值
     */
    public static <TValue> TValue notNull(final TValue value, final String name, final String message) {
        if (value == null) {
            throw new IllegalArgumentException(String.format(message, name));
        }
        return value;
    }

    /**
     * 检查参数值是否为Null
     *
     * @param value 参数值
     * @param name  参数名
     * @return 参数值
     */
    public static <TValue> TValue notNull(final TValue value, final String name) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("Argument '%s' must not be null.", name));
        }
        return value;
    }

    /**
     * 检查参数值是否为Null
     *
     * @param value 参数值
     * @return 参数值
     */
    public static <TValue> TValue notNull(final TValue value) {
        if (value == null) {
            throw new IllegalArgumentException("Argument must not be null.");
        }
        return value;
    }

    /**
     * 检查参数值是否为Null或空字符串
     *
     * @param value   参数值
     * @param name    参数名
     * @param message 错误消息格式
     * @return 参数值
     */
    public static String notEmpty(final String value, final String name, final String message) {
        if (value == null || value.length() < 1) {
            throw new IllegalArgumentException(String.format(message, name));
        }
        return value;
    }

    /**
     * 检查参数值是否为Null或空字符串
     *
     * @param value 参数值
     * @param name  参数名
     * @return 参数值
     */
    public static String notEmpty(final String value, final String name) {
        if (value == null || value.length() < 1) {
            throw new IllegalArgumentException(String.format("Argument '%s' must not be empty.", name));
        }
        return value;
    }

    /**
     * s
     * 检查参数值是否为Null或空字符串
     *
     * @param value 参数值
     * @return 参数值
     */
    public static String notEmpty(final String value) {
        if (value == null || value.length() < 1) {
            throw new IllegalArgumentException("Argument must not be empty.");
        }
        return value;
    }

}
