package com.finances.Finances.V1.util;

import com.finances.Finances.V1.model.Billing;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NextBilling {
    public static Billing getNextBillingList(List<Billing> billingList){
        Billing nextBilling = new Billing();
        LocalDate today = LocalDate.now();
        long minDifference = Long.MAX_VALUE;

        for(Billing billing : billingList){
            long difference = ChronoUnit.DAYS.between(today,billing.getDate());

            if(difference >= 0 && difference < minDifference){
                minDifference = difference;
                nextBilling = billing;
            }
        }
        return nextBilling;
    }
}
