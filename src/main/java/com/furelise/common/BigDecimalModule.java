package com.furelise.common;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class BigDecimalModule extends SimpleModule {
    public BigDecimalModule(){
        addSerializer(BigDecimal.class,new BigDecimalSerializer());
        addDeserializer(BigDecimal.class,new BigDecimalDeSerializer());
    }
}