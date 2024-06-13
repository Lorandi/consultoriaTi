package com.consultoriaTi.gestao.util.mapper;


import com.consultoriaTi.gestao.dto.ProfessionalCreateDTO;
import com.consultoriaTi.gestao.dto.ProfessionalDTO;
import com.consultoriaTi.gestao.entity.Professional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProfessionalMapper {


    @Mapping(target = "id", ignore = true)
    Professional buildEntity(ProfessionalCreateDTO requestDTO);

    ProfessionalDTO buildDTO(Professional professional);

}
