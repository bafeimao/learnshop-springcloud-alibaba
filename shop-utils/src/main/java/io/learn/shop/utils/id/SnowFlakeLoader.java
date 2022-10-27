package io.learn.shop.utils.id;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @projectName: shop
 * @package: io.learn.shop.utils.id
 * @className: SnowFlakeLoader
 * @author: ycd20
 * @description: define loader params.properties
 * @date: 2022/10/27 21:25
 * @version: 1.0
 */
public class SnowFlakeLoader {
    public static final String DATA_CENTER_ID = "data.center.id";
    public static final String MACHINE_ID = "machine.id";

    private volatile static Properties instance;

    static {
        InputStream in =
                SnowFlakeLoader.class.getClassLoader().getResourceAsStream("snowflake/snowflake.properties");
        instance = new Properties();
        try {
            instance.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getStringValue(String key) {
        if (instance == null) {
            return "";
        }
        return instance.getProperty(key, "");
    }

    private static Long getLongValue(String key) {
        String v = getStringValue(key);
        return (v == null || v.trim().isEmpty() ? 0 : Long.parseLong(v));
    }

    public static Long getDataCenterId() {
        return getLongValue(DATA_CENTER_ID);
    }

    public static Long getMachineId() {
        return getLongValue(MACHINE_ID);
    }

}
