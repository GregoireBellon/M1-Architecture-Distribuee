package com.ArchiDistribuee.VirtualCRM.CustomDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateDeserializer extends JsonDeserializer<Calendar> {

    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

    @Override
    public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String dateAsString = jsonParser.getText();
        try {
            SimpleDateFormat _format = new SimpleDateFormat(DATE_PATTERN);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(_format.parse(dateAsString));
            return calendar;
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + dateAsString, e);
        }
    }
}