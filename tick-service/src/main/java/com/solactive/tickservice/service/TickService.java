package com.solactive.tickservice.service;

import com.solactive.tickservice.dto.AckDto;
import com.solactive.tickservice.dto.ExportTicksDto;
import com.solactive.tickservice.dto.TickDto;
import com.solactive.tickservice.dto.mapper.TickEntityToDtoMapper;
import com.solactive.tickservice.entity.ExportIndex;
import com.solactive.tickservice.repository.ExportIndexRepository;
import com.solactive.tickservice.repository.TickRepository;
import com.solactive.tickservice.util.CommonUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.solactive.tickservice.entity.ExportStatus.*;

@Service
@Transactional
public class TickService {

    @Autowired
    private TickRepository tickRepository;

    @Autowired
    private ExportIndexRepository indexRepository;

    private TickEntityToDtoMapper mapper = Mappers.getMapper(TickEntityToDtoMapper.class);

    public List<TickDto> listTicks(String ric) {
        return tickRepository
                .findByRic(ric)
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    public void consumeTicks(List<String> tickStrs) {
        var tickList = CommonUtil.parseTicks(tickStrs);
        tickList.forEach(tick -> {
            var tickEntity = tickRepository.save(tick);
            if (tick.getClosePrice() > 0) {
                var prevExportIndex = indexRepository.findFirstByRicOrderByIdDesc(tick.getRic());
                long prevIndex = prevExportIndex.isPresent() ? prevExportIndex.get().getClosingPriceIndex() + 1 : 1;
                var exportIndex = new ExportIndex(
                        tickEntity.getRic(), tickEntity.getId(),
                        prevIndex, READY);
                indexRepository.save(exportIndex);
            }
        });
    }

    public ExportTicksDto exportTicks() {
        var exportTicksDto = new ExportTicksDto();
        var exportIndexOptional = indexRepository.findFirstByStatus(READY);
        exportIndexOptional.ifPresent(exportIndex -> {
            exportTicksDto.setExportIndexId(exportIndex.getId());
            exportTicksDto.setRic(exportIndex.getRic());
            exportIndex.setStatus(IN_PROGRESS);
            indexRepository.save(exportIndex);
            var tickEntities = tickRepository.findByIdBetweenAndRic(
                    exportIndex.getPreviousIndex(), exportIndex.getClosingPriceIndex(), exportIndex.getRic());
            var tickList =  tickEntities
                    .stream()
                    .map(mapper::entityToDto)
                    .collect(Collectors.toList());
            exportTicksDto.setTicks(tickList);
        });
        return exportTicksDto;
    }

    public void acknowledgeExportTicks(AckDto ack) {
        indexRepository
                .findById(ack.getExportIndexId())
                .ifPresent(exportIndex -> {
                    exportIndex.setStatus(ack.isAck() ? SUCCESS : FAILED);
                    indexRepository.save(exportIndex);
                });
    }

}