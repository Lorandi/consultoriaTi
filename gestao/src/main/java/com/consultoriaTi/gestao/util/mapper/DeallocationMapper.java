package com.consultoriaTi.gestao.util.mapper;

import com.consultoriaTi.gestao.dto.DeallocationCreateDTO;
import com.consultoriaTi.gestao.dto.DeallocationDTO;
import com.consultoriaTi.gestao.entity.Deallocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DeallocationMapper {

    @Mapping(target = "id", ignore = true)
    Deallocation builDeallocation(DeallocationCreateDTO createDTO);

    DeallocationDTO buildDeallocationDTO(Deallocation deallocation);

}
