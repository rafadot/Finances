package com.finances.Finances.V1.util;

import java.math.BigDecimal;

public class DotToComma {
    public static String convert(BigDecimal bigDecimal){
        String s = bigDecimal.setScale(2).toString();
        return s.replace('.',',');
    }
}
