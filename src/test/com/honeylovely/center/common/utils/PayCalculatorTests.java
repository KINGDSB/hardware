package com.airparking.apm.common.utils;

import com.airparking.apm.order.entity.Lease;
import com.airparking.apm.park.rentalprice.entity.RentalPrice;
import com.airparking.core.comm.utils.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 9/8/2016.
 */
public class PayCalculatorTests {
    @Test
    public void testCalculate() throws Exception {
        Lease lease = new Lease();
        lease.setActualStartDate(DateUtils.parseDate("2016-09-29 20:24:34", "yyyy-MM-dd HH:mm:ss"));
        lease.setActualEndDate(DateUtils.parseDate("2016-09-30 12:36:49", "yyyy-MM-dd HH:mm:ss"));

        List<RentalPrice> prices = new ArrayList<>();
        RentalPrice price = new RentalPrice();
        price.setUnitMinutes(15);
        price.setUnitPrice(75);
        price.setStartTime("08:00:00");
        price.setEndTime("00:00:00");
        price.setRestPeriod(false);
        price.setHighestPriceLimit(true);
        price.setHighestPrice(1500);
        prices.add(price);
        RentalPrice restPrice = new RentalPrice();
        restPrice.setUnitMinutes(15);
        restPrice.setUnitPrice(75);
        restPrice.setEndTime("08:00:00");
        restPrice.setStartTime("00:00:00");
        restPrice.setRestPeriod(true);
        restPrice.setHighestPriceLimit(true);
        restPrice.setHighestPrice(600);
        prices.add(restPrice);

        Long start = System.currentTimeMillis();
        System.out.println(PayCalculator.calculate(lease.getActualStartDate(), lease.getActualEndDate(), 1500, 15, prices));
        System.out.println("It takes " + (System.currentTimeMillis() - start ) + " ms");
        start = System.currentTimeMillis();
        System.out.println(PayCalculator.calculate(lease, prices));
        System.out.println("It takes " + (System.currentTimeMillis() - start ) + " ms");
    }

    @Test
    public void testCalculateAdditionalCharges() throws Exception {
        Lease lease = new Lease();
        lease.setActualStartDate(DateUtils.parseDate("2016-09-05 09:00:00", "yyyy-MM-dd HH:mm:ss"));
        lease.setActualEndDate(DateUtils.parseDate("2016-09-06 20:00:00", "yyyy-MM-dd HH:mm:ss"));

        List<RentalPrice> prices = new ArrayList<>();
        RentalPrice price = new RentalPrice();
        price.setUnitMinutes(15);
        price.setUnitPrice(150);
        price.setStartTime("08:00:00");
        price.setEndTime("22:00:00");
        price.setRestPeriod(false);
        price.setHighestPriceLimit(false);
        price.setHighestPrice(6000);
        prices.add(price);
        RentalPrice restPrice = new RentalPrice();
        restPrice.setUnitMinutes(15);
        restPrice.setUnitPrice(100);
        restPrice.setEndTime("08:00:00");
        restPrice.setStartTime("22:00:00");
        restPrice.setRestPeriod(true);
        restPrice.setHighestPriceLimit(false);
        restPrice.setHighestPrice(2500);
        prices.add(restPrice);

        Long start = System.currentTimeMillis();
        System.out.println(PayCalculator.calculate(lease.getActualStartDate(), lease.getActualEndDate(), 1500, 15, prices));
        System.out.println("It takes " + (System.currentTimeMillis() - start ) + " ms");
        start = System.currentTimeMillis();
        System.out.println(PayCalculator.calculate(lease, prices));
        System.out.println("It takes " + (System.currentTimeMillis() - start ) + " ms");
    }
}
