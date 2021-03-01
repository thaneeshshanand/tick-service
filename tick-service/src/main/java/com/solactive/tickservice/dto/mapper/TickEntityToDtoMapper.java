package com.solactive.tickservice.dto.mapper;

import com.solactive.tickservice.dto.TickDto;
import com.solactive.tickservice.entity.Tick;
import org.mapstruct.Mapper;

@Mapper
public interface TickEntityToDtoMapper {

    TickDto entityToDto(Tick entity);

}