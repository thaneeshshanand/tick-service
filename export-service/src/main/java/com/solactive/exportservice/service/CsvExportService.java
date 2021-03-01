package com.solactive.exportservice.service;

import com.solactive.exportservice.handler.CsvFilesHandler;
import net.lingala.zip4j.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvExportService {

    @Autowired
    private CsvFilesHandler csvFilesHandler;

    public ZipFile exportCsv(String ric) {
        return csvFilesHandler.getTicksZip(ric);
    }

}