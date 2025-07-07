package com.oteller.rezervin.gateway.configuration;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.optionals.OptionalDecoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.beans.factory.ObjectFactory;

@Configuration
public class FeignSupportConfig {

    @Bean
    Encoder feignEncoder() {
        ObjectFactory<HttpMessageConverters> messageConverters = () ->
                new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
        return new SpringEncoder(messageConverters);
    }

    @SuppressWarnings("deprecation")
	@Bean
    Decoder feignDecoder() {
        ObjectFactory<HttpMessageConverters> messageConverters = () ->
                new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
        return new OptionalDecoder(new SpringDecoder(messageConverters));
    }
}
