package com.contact.contactservice.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
public class ContactServiceUtil {

    public static enum ValidRequestParamsForSearchUserContactByEmailPhone {
        email, phone;
    }

    public static enum ValidRequestParamsForSearchAllContactByAddress {
        state, city, page, size;
    }

    public boolean isRequestParamsValid(Map<String, String> options, Class<?> enumClass){
        if(options.isEmpty()) return true;
        for (Map.Entry<String, String> entry : options.entrySet()) {
            boolean tmp = isInEnum(entry.getKey(), enumClass);
            if(!tmp)
                return false;
        }
        return true;
    }

    public boolean isInEnum(String value, Class<?> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> ((Enum<?>) e).name().equals(value));
    }
}
