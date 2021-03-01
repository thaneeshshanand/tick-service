package com.solactive.exportservice.handler;

import com.solactive.exportservice.dto.ExportTicksDto;
import lombok.extern.log4j.Log4j2;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Log4j2
@Component
public class CsvFilesHandler {

    @Value("${export.path}")
    private String basePath;
    private File baseDir;
    private final DateFormat fileFormat =  new SimpleDateFormat("yyyyMMddHHmmss'.csv'");
    private final String[] headers = {"TIMESTAMP","PRICE","CLOSE_PRICE","CURRENCY","RIC"};

    public void writeTicksToCsv(ExportTicksDto ticksDto) {
        initializeBaseDir();
        var targetDir = new File(baseDir, ticksDto.getRic());
        if (!targetDir.exists()) targetDir.mkdir();
        try (var fileWriter = new FileWriter(targetDir.getAbsoluteFile() + "/" + fileFormat.format(new Date()));
             var printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(headers))) {
            for (var tick : ticksDto.getTicks()) {
                printer.printRecord(
                        tick.getTimeStamp(),
                        tick.getPrice(),
                        tick.getClosePrice(),
                        tick.getCurrency(),
                        tick.getRic());
            }
        } catch (IOException e) {
            log.error("Error while writing csv", e);
        }
    }

    public ZipFile getTicksZip(String ric){
        initializeBaseDir();
        var targetDir = new File(baseDir, ric);
        var tickFiles = targetDir.listFiles();
        var zipFile = new ZipFile("tick-data.csv");
        try {
            zipFile.addFiles(Arrays.asList(tickFiles));
        } catch (IOException e) {
            log.error("Error while zipping csv", e);
        }
        return zipFile;
    }

    private void initializeBaseDir() {
        var ticDir = new File(new File(basePath), "tick-dir");
        if (!ticDir.exists()) ticDir.mkdir();
        baseDir = ticDir;
    }


}