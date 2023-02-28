package com.finances.Finances.V1.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BigDecimalUtil {
    public static String convert(BigDecimal bigDecimal){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt","BR")));
        return decimalFormat.format(bigDecimal);
    }
}
