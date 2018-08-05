package cn.habitdiary.form.utils;

import java.util.UUID;

/**
 * UUID生成类
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
