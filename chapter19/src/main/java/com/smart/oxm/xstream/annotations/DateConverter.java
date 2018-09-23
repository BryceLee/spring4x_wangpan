package com.smart.oxm.xstream.annotations;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class DateConverter implements SingleValueConverter {

    public DateConverter() {}

    @Override
    public String toString(Object obj) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setLenient(true);
            return dateFormat.format(((Date) obj));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object fromString(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setLenient(true);
            return dateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Date.class);
    }

}
