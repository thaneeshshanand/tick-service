package com.solactive.tickservice.resource;

import com.solactive.tickservice.dto.AckDto;
import com.solactive.tickservice.exception.ApiException;
import com.solactive.tickservice.service.TickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.ok;

@Service
@Path("/ticks")
public class TickResource {

    @Autowired
    private TickService tickService;

    @GET
    @Produces(APPLICATION_JSON)
    public Response getTicks(@QueryParam("ric") String ric) throws ApiException {
        var tickList = tickService.listTicks(ric);
        return ok(tickList).build();
    }

    @POST
    @Path("/consume")
    @Consumes(APPLICATION_JSON)
    public Response consumeTicks(List<String> ticks) throws ApiException {
        tickService.consumeTicks(ticks);
        return Response.status(NO_CONTENT).build();
    }

    @POST
    @Path("/export")
    @Produces(APPLICATION_JSON)
    public Response exportTicks() throws ApiException {
        var exportTicks = tickService.exportTicks();
        return ok(exportTicks).build();
    }

    @POST
    @Path("/export/ack")
    @Consumes(APPLICATION_JSON)
    public Response acknowledgeExportTicks(AckDto ack) throws ApiException {
        tickService.acknowledgeExportTicks(ack);
        return ok().build();
    }

}