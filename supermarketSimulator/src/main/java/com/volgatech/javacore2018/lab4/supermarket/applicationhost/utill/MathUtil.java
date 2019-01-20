package com.volgatech.javacore2018.lab4.supermarket.applicationhost.utill;

import java.math.BigDecimal;

public class MathUtil {
    private MathUtil() {
    }

    public static final BigDecimal ZERO = BigDecimal.valueOf(0);

    public static boolean less(BigDecimal lhs, BigDecimal rhs) {
        return lhs.compareTo(rhs) < 0;
    }

    public static boolean lessThanZero(BigDecimal number) {
        return less(number, ZERO);
    }
}
