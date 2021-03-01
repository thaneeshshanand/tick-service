package com.solactive.tickservice.config;

import com.solactive.tickservice.exception.ApiExceptionMapper;
import com.solactive.tickservice.resource.TickResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(TickResource.class);
        packages(ApiExceptionMapper.class.getPackageName());
    }

}