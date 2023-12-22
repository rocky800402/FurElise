package com.furelise.config;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.furelise.common.model.BigDecimalSerializer;

@Configuration
public class JacksonConfig {
    @Bean
    @Primary  //请加上这个注解
    public ObjectMapper bigDecimalObjectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder){
        jackson2ObjectMapperBuilder.serializerByType(BigDecimal.class,new BigDecimalSerializer());
        jackson2ObjectMapperBuilder.deserializerByType(BigDecimal.class,new NumberDeserializers.BigDecimalDeserializer());
        return jackson2ObjectMapperBuilder.build();
    }
}