package com.solactive.exportservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solactive.exportservice.dto.AckDto;
import com.solactive.exportservice.dto.ExportTicksDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static java.net.http.HttpRequest.BodyPublishers.noBody;
import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.net.http.HttpResponse.BodyHandlers.ofInputStream;
import static java.net.http.HttpResponse.BodyHandlers.ofString;

@Log4j2
@Component
public class TickClient {

    @Value("${tickservice.endpoint}")
    private String tickEndpoint;
    private ObjectMapper objectMapper = new ObjectMapper();
    private HttpClient client = HttpClient.newHttpClient();

    public ExportTicksDto getTicks() {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(tickEndpoint))
                    .POST(noBody())
                    .build();
            var responseStream = client.send(request, ofInputStream()).body();
            return objectMapper.readValue(responseStream, ExportTicksDto.class);
        } catch (Exception e) {
            log.error("Error while exporting ticks");
        }
        return new ExportTicksDto();
    }

    public void acknowledgeTicks(long exportIndexId) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(tickEndpoint + "/ack"))
                    .header("Content-Type", "application/json")
                    .POST(ofString(objectMapper.writeValueAsString(new AckDto(exportIndexId, true))))
                    .build();
            client.send(request, ofString());
        } catch (Exception e) {
            log.error("Error while acknowledging ticks");
            e.printStackTrace();
        }
    }

}