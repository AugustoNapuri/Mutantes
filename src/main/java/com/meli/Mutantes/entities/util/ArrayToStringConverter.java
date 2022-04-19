package com.meli.Mutantes.entities.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ArrayToStringConverter implements AttributeConverter<String[], String> {

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        if (attribute == null || attribute.length == 0) {
            return "";
        }
        return String.join(",", attribute);
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().length() == 0) {
            return new String[]{};
        }
        return dbData.split(",");
    }
}
