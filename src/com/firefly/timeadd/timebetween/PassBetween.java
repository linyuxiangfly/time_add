package com.firefly.timeadd.timebetween;

/**
 * 通行时间范围
 */
public interface PassBetween {
    /**
     * 是否允许通行
     * @return
     */
    boolean isAllow();

    /**
     * 时间范围类型
     * @return
     */
    TimeBetweenType getType();
}
