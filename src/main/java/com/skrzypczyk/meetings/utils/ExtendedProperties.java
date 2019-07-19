package com.skrzypczyk.meetings.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class ExtendedProperties {
    @Value("${google.api.key}")
    private String googleApiKey;

    public String getGoogleApiKey(){
        return googleApiKey;
    }
}
