package com.edanrh.apiong.dto.converts;

import com.edanrh.apiong.dto.HeadquarterDTO;
import com.edanrh.apiong.dto.HeadquarterReportDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HeadReportDTOConvert {

    private ModelMapper modelMapper;

    public HeadquarterReportDTO toDTO(HeadquarterDTO headReport) {
        return modelMapper.map(headReport, HeadquarterReportDTO.class);
    }
}
