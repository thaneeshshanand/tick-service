package com.solactive.tickservice.controller;

import com.solactive.tickservice.dto.AckDto;
import com.solactive.tickservice.dto.ExportTicksDto;
import com.solactive.tickservice.dto.TickDto;
import com.solactive.tickservice.service.TickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticks")
public class TickController {

    @Autowired
    private TickService tickService;

    @GetMapping
    public List<TickDto> getTicks(@RequestParam(value = "ric") String ric) {
        return tickService.listTicks(ric);
    }

    @PostMapping("/consume")
    public void consumeTicks(@RequestBody List<String> ticks) {
        tickService.consumeTicks(ticks);
    }

    @PostMapping("/export")
    public ExportTicksDto exportTicks() {
        return tickService.exportTicks();
    }

    @PostMapping("/export/ack")
    public void acknowledgeExportTicks(@RequestBody AckDto ack) {
        tickService.acknowledgeExportTicks(ack);
    }

}