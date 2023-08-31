package com.andersen.countrycity.mapper;

import com.andersen.countrycity.dto.CityDTO;
import com.andersen.countrycity.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CityMapper {

    @Mapping(target = "logo", expression = "java(imageServer + cityEntity.getLogo())")
    CityDTO mapToDTO(City cityEntity, String imageServer);
}
