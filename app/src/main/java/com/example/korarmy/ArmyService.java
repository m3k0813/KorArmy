package com.example.korarmy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

// 군 복무 단축일 계산
public class ArmyService {
    public static int armyService(String enl) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date enlDate = dateFormat.parse(enl);
        long enlDate1 = enlDate.getTime();

        long date0310 = 0;
        long date0324 = 0;
        long date0407 = 0;
        long date0421 = 0;
        long date0505 = 0;
        long date0519 = 0;
        long date0602 = 0;
        try {
            date = dateFormat.parse("2020-03-10");
            date0310 = date.getTime();
            date = dateFormat.parse("2020-03-24");
            date0324 = date.getTime();
            date = dateFormat.parse("2020-04-07");
            date0407 = date.getTime();
            date = dateFormat.parse("2020-04-21");
            date0421 = date.getTime();
            date = dateFormat.parse("2020-05-05");
            date0505 = date.getTime();
            date = dateFormat.parse("2020-05-19");
            date0519 = date.getTime();
            date = dateFormat.parse("2020-06-02");
            date0602 = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (enlDate1 > date0602) {
            return 90;
        } else if (enlDate1 > date0519) {
            return 89;
        } else if (enlDate1 > date0505) {
            return 88;
        } else if (enlDate1 > date0421) {
            return 87;
        } else if (enlDate1 > date0407) {
            return 86;
        } else if (enlDate1 > date0324) {
            return 85;
        } else{
            return 84;
        }
    }
}
