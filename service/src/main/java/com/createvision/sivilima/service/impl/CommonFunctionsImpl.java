package com.createvision.sivilima.service.impl;

import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Repository("commonFunctionsImpl")
public class CommonFunctionsImpl {

    public Date getCorrentDateAndTimeByTimeZone(String timeZone) throws Exception {
        LocalDateTime today = LocalDateTime.now();
        ZoneId id = ZoneId.of(timeZone);  //Create timezone
        ZonedDateTime zonedDateTime = ZonedDateTime.of(today, id);  //Add timezone to
        String formattedDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(zonedDateTime);
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(formattedDateTime);
        return date;
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


}
