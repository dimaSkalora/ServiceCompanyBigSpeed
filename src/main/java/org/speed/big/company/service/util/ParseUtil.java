package org.speed.big.company.service.util;

import org.springframework.util.StringUtils;

public class ParseUtil {
    public static Integer parseInteger(String str) {
        return StringUtils.isEmpty(str) ? null : Integer.parseInt(str);
    }

    public static String parseString(String str) {
        return StringUtils.isEmpty(str) ? null : str;
    }

    public static Boolean parseBoolean(String str) {
        return StringUtils.isEmpty(str) ? null : Boolean.parseBoolean(str);
    }
}
