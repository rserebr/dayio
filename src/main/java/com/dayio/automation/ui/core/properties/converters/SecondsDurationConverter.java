package com.dayio.automation.ui.core.properties.converters;

import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.time.Duration;

public class SecondsDurationConverter implements Converter<Duration> {

    @Override
    public Duration convert(Method method, String property) {
        return Duration.ofSeconds(Long.parseLong(property));
    }
}