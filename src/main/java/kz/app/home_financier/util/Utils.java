package kz.app.home_financier.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    private Utils() {
    }

    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    public static boolean notNullOrEmptyStr(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static BigDecimal toPercentageOf(BigDecimal value, BigDecimal total) {
        return value.divide(total, 2, RoundingMode.DOWN).multiply(ONE_HUNDRED);
    }
}
