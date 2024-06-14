package com.consultoriaTi.gestao.util.mapper;

import com.consultoriaTi.gestao.dto.AllocationCreateDTO;
import com.consultoriaTi.gestao.dto.AllocationDTO;
import com.consultoriaTi.gestao.dto.AllocationUpdateDTO;
import com.consultoriaTi.gestao.entity.Allocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AllocationMapper {

    @Mapping(target = "id", ignore = true)
    Allocation buildAllocation(AllocationCreateDTO createDTO);

    AllocationDTO buildAllocationDTO(Allocation allocation);

    @Mapping(target = "id", ignore = true)
    Allocation buildAllocation(AllocationUpdateDTO updateDTO);
}
