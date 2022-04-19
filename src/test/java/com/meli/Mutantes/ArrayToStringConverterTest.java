package com.meli.Mutantes;

import com.meli.Mutantes.entities.util.ArrayToStringConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayToStringConverterTest extends MutantesApplicationTests{

    @Test
    public void testConverter() {
        ArrayToStringConverter converter = new ArrayToStringConverter();
        String simpleString = converter.convertToDatabaseColumn(new String[]{"1", "2", "3", "4"});
        String[] array = converter.convertToEntityAttribute("1,2,3,4");
        assertEquals("1,2,3,4", simpleString);
        assertEquals(array, new String[]{"1", "2", "3", "4"});
    }
}
