package com.finances.Finances.V1.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BigDecimalUtil {
    public static String bigDecimalToReal(BigDecimal bigDecimal){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt","BR")));
        return decimalFormat.format(bigDecimal);
    }

    public static List<String> graphicLines(BigDecimal maxValue){
        ArrayList<String> response = new ArrayList<>();
        response.add(BigDecimalUtil.bigDecimalToReal(maxValue));
        response.add(BigDecimalUtil.bigDecimalToReal(maxValue.multiply(new BigDecimal("0.75"))));
        response.add(BigDecimalUtil.bigDecimalToReal(maxValue.multiply(new BigDecimal("0.50"))));
        response.add(BigDecimalUtil.bigDecimalToReal(maxValue.multiply(new BigDecimal("0.25"))));
        return response;
    }

    public static BigDecimal realToBigDecimal(String value){
        if(value.contains(".")){
            value = value.replace(".","") .replace(",",".");
            return new BigDecimal(value);
        }
        return new BigDecimal(value.replace(",","."));
    }
}
