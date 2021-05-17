package com.qzy.boot.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    private final SimpleDateFormat smf =  new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date convert(String s) {
        if ("".equals(s) || null == s) {
            return null;
        }

        try {
            Date parse = smf.parse(s);
            return parse;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
