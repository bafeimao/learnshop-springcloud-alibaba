package io.learn.shop.utils.id;

/**
 * @projectName: shop
 * @package: io.learn.shop.utils.id
 * @className: SnowFlake
 * @author: ycd20
 * @description: SnowFlake
 * @date: 2022/10/26 20:46
 * @version: 1.0
 */
public class SnowFlake {
    /**
     * start timestamp 2022-10-26 20:47:45
     */
    private final static long START_STAMP = 1649735805910L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12;

    private final static long MACHINE_BIT = 5;

    private final static long DATACENTER_BIT = 5;

    /**
     * every part max number
     */
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);

    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);

    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * move left
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;

    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    public static final long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private final long datacenterId;

    private final long machineId;

    private long sequence = 0L;

    private long lastStamp = -1L;

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can not greater than MAX_DATACENTER_NUM or less than " + "zero");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can not greater than MAX_MACHINE_ID or less than zero");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * generate next id
     */
    public synchronized long nextId() {
        long currentStamp = getNewStamp();
        if (currentStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.Refusing to generate id");
        }

        if (currentStamp == lastStamp) {
            //same nano seconds sequence increase
            sequence = (sequence + 1) & MAX_SEQUENCE;

            if (sequence == 0L) {
                currentStamp = getNextMill();
            }
        } else {
            //different nano seconds set sequence zero
            sequence = 0L;
        }
        lastStamp = currentStamp;

        return (currentStamp - START_STAMP) << TIMESTAMP_LEFT
                | datacenterId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }

    public static Long getMaxDataCenterNum() {
        return MAX_DATACENTER_NUM;
    }

    public static Long getMaxMachineNum() {
        return MAX_MACHINE_NUM;
    }

}
