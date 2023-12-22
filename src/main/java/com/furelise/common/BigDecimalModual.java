package com.furelise.common;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.furelise.common.model.BigDecimalDeSerializer;
import com.furelise.common.model.BigDecimalSerializer;

@Component
public class BigDecimalModual extends SimpleModule {
    public BigDecimalModual(){
        addSerializer(BigDecimal.class,new BigDecimalSerializer());
        addDeserializer(BigDecimal.class,new BigDecimalDeSerializer());
    }
}