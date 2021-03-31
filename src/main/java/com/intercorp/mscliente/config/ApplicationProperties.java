package com.intercorp.mscliente.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@RefreshScope
@Configuration
@Getter
@Setter
public class ApplicationProperties {

    @Value("${cloud.prefix:default}")
    private String prefix;
    
}
