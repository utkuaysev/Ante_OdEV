package com.project.corona.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaporDTO {
    private String date;
    private int y;

    public RaporDTO(String date, int y) {
        this.date = date;
        this.y = y;
    }
}
