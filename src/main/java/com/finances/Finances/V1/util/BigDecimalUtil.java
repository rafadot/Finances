package com.finances.Finances.V1.util;

import com.finances.Finances.V1.model.TypeSpent;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BigDecimalUtil {
    public static String convert(BigDecimal bigDecimal){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt","BR")));
        return decimalFormat.format(bigDecimal);
    }

    public static List<String> graphicLineCalculate(List<TypeSpent> typeSpentValueList){
        BigDecimal maxValue = new BigDecimal(0);

        for(TypeSpent type : typeSpentValueList){
            if(type.getTotalSpent().compareTo(maxValue) > 0)
                maxValue = type.getTotalSpent();
        }

        if(maxValue.compareTo(new BigDecimal(0)) == 0)
            return null;

        ArrayList<String> response = new ArrayList<>();
        response.add(BigDecimalUtil.convert(maxValue));
        response.add(BigDecimalUtil.convert(maxValue.multiply(new BigDecimal("0.75"))));
        response.add(BigDecimalUtil.convert(maxValue.multiply(new BigDecimal("0.50"))));
        response.add(BigDecimalUtil.convert(maxValue.multiply(new BigDecimal("0.25"))));

        return response;
    }
}
