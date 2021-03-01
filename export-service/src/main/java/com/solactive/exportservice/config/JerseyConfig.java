package com.solactive.exportservice.config;

import com.solactive.exportservice.resource.CsvExportResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CsvExportResource.class);
    }

}