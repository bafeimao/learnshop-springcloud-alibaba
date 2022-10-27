package io.learn.shop.utils.id;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @projectName: shop
 * @package: io.learn.shop.utils.id
 * @className: SnowFlakeFactory
 * @author: ycd20
 * @description: snow flake factory
 * @date: 2022/10/27 21:09
 * @version: 1.0
 */
public class SnowFlakeFactory {
    /**
     * default datacenterId
     */
    private static final long DEFAULT_DATACENTER_ID = 1;

    /**
     * default machineId
     */
    private static final long DEFAULT_MACHINE_ID = 1;

    /**
     * default snow flake handle
     */
    private static final String DEFAULT_SNOW_FLAKE = "snow_flake";

    /**
     * cache snow flake object
     */
    private static ConcurrentMap<String, SnowFlake> snowFlakeCache = new ConcurrentHashMap<>(2);

    public static SnowFlake getSnowFlake(long datacenterId, long machineId) {
        return new SnowFlake(datacenterId, machineId);
    }

    public static SnowFlake getSnowFlake() {
        return new SnowFlake(DEFAULT_DATACENTER_ID, DEFAULT_MACHINE_ID);
    }

    public static SnowFlake getSnowFlakeCache() {
        SnowFlake snowFlake = snowFlakeCache.get(DEFAULT_SNOW_FLAKE);
        if (snowFlake == null) {
            snowFlake = new SnowFlake(DEFAULT_DATACENTER_ID, DEFAULT_MACHINE_ID);
            snowFlakeCache.put(DEFAULT_SNOW_FLAKE, snowFlake);
        }
        return snowFlake;
    }

    /**
     * according dataCenterId and machineId from cache get globalId
     *
     * @param dataCenterId: 1~31
     * @param machineId:    1~31
     */
    public static SnowFlake getSnowFlakeByDataCenterIdAndMachineIdFromCache(Long dataCenterId, Long machineId) {
        if (dataCenterId > SnowFlake.getMaxDataCenterNum() || dataCenterId < 0) {
            throw new IllegalArgumentException("datacenterId can not be greater than max datacenter num or less than " +
                    "zero");
        }
        if (machineId > SnowFlake.getMaxMachineNum() || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than max machine num or less than zero");
        }
        String key =
                DEFAULT_SNOW_FLAKE.concat("_").concat(String.valueOf(dataCenterId)).concat("_")
                        .concat(String.valueOf(machineId));
        SnowFlake snowFlake = snowFlakeCache.get(key);
        if (snowFlake == null) {
            snowFlake = new SnowFlake(dataCenterId, machineId);
            snowFlakeCache.put(key, snowFlake);
        }
        return snowFlake;
    }

}
