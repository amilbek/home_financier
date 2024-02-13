package kz.app.home_financier.util;

public class Utils {

    public static boolean notNullOrEmptyStr(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
