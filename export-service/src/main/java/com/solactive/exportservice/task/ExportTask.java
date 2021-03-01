package com.solactive.exportservice.task;

import com.solactive.exportservice.client.TickClient;
import com.solactive.exportservice.dto.ExportTicksDto;
import com.solactive.exportservice.handler.CsvFilesHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Component
public class ExportTask {

    @Autowired
    private TickClient tickClient;

    @Autowired
    private CsvFilesHandler csvFilesHandler;

    @Scheduled(fixedDelay = 5000)
    public void exportTicks() {
        var exportTicksDto = tickClient.getTicks();
        System.out.println(exportTicksDto);
        long exportIndex = exportTicksDto.getExportIndexId();
        if (exportIndex != 0) {
            exportTickToCsvFile(exportTicksDto);
            tickClient.acknowledgeTicks(exportIndex);
        }
    }

    private void exportTickToCsvFile(ExportTicksDto ticksDto) {
        log.info("****** Export to csv file");
        csvFilesHandler.writeTicksToCsv(ticksDto);
        log.info("****** Export to csv file Complete");
    }

}