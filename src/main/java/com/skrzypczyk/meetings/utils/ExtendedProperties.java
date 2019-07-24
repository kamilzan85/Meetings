package com.skrzypczyk.meetings.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@PropertySource("classpath:config.properties")
public  class ExtendedProperties {
    public static ExtendedProperties INSTANCE;

    @Value("${google.api.key}")
    private String googleApiKey;

    @PostConstruct
    public void init(){
        INSTANCE = this;
    }

    public String getGoogleApiKey(){
        return googleApiKey;
    }
}
