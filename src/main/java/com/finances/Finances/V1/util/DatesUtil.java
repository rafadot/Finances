package com.finances.Finances.V1.util;

import com.finances.Finances.V1.model.Billing;
import com.finances.Finances.V1.model.TypeSpent;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DatesUtil {
    public static List<Billing> getNextBillingList(List<Billing> billingList){
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

        List<Billing> response = new ArrayList<>();

        for(Billing billing : billingList){
            if(billing.getDate().equals(nextBilling.getDate()))
                response.add(billing);
        }

        return response;
    }

    public static List<TypeSpent> filterSpentDays(List<TypeSpent>typeSpentList, String dateRequest){
        LocalDate date = LocalDate.parse(dateRequest);
        long range = ChronoUnit.DAYS.between(date,LocalDate.now());
        List<TypeSpent> responseList = new ArrayList<>();

        log.info(String.valueOf(range));
        for(TypeSpent typeSpent : typeSpentList){
            typeSpent.setSpentList(typeSpent.getSpentList()
                    .stream()
                    .filter(spent -> ChronoUnit.DAYS.between(spent.getDate(),LocalDate.now()) <= range)
                    .collect(Collectors.toList()));
            responseList.add(typeSpent);
        }

        return responseList;
    }
}
