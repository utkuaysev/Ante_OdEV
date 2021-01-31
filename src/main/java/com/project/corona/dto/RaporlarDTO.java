package com.project.corona.dto;
import com.project.corona.entity.Rapor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class RaporlarDTO {
    private List<RaporDTO> dataPoints;
    private String name;

    public RaporlarDTO(List<RaporDTO> dataPoints, String name) {
        this.dataPoints = dataPoints;
        this.name = name;
    }
}
