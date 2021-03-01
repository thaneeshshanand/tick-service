package com.solactive.exportservice.resource;

import com.solactive.exportservice.service.CsvExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Service
@Path("/csv-export")
public class CsvExportResource {

    @Autowired
    private CsvExportService csvExportService;

    @GET
    public Response exportCsv(@QueryParam("ric") String ric) {
        var zipFile = csvExportService.exportCsv(ric);
        var response = ok(zipFile.getFile())
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"tick-data.zip\"")
                .build();
        return response;
    }

}