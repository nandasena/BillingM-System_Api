package com.createvision.sivilima.service.impl;

import org.springframework.stereotype.Repository;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Repository("commonFunctionsImpl")
public class CommonFunctionsImpl {

    public enum PaymentType {
        INCOME("INCOME", 1),
        COST("COST", 2);

        String name;
        int id;

        PaymentType(java.lang.String name, int id) {
            this.name = name;
            this.id = id;
        }

        public java.lang.String getName() {
            return name;
        }

        public java.lang.Integer getId() {
            return id;
        }
    }

    public Date getCurrentDateAndTimeByTimeZone(String timeZone) throws Exception {
        try {
            LocalDateTime today = LocalDateTime.now();
            ZoneId id = ZoneId.of(timeZone);  //Create timezone
            ZonedDateTime zonedDateTime = ZonedDateTime.of(today, id);  //Add timezone to
            String formattedDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(zonedDateTime);
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(formattedDateTime);
            return date;
        } catch (Exception e) {
            throw e;
        }

    }

    public Date getDateTimeByDateString(String dateString) throws Exception {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e) {
            throw e;
        }
        return date;
    }

    public String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(date);
    }

    public double DecimalFormat(double value) {
        DecimalFormat df2 = new DecimalFormat("#.##");
        df2.setRoundingMode(RoundingMode.DOWN);
        return Double.valueOf(df2.format(value));
    }

    public static PaymentType getPaymentType(int id) {
        for (PaymentType e : PaymentType.values()) {
            if (id == e.getId()) return e;
            break;
        }
        return null;
    }


}
