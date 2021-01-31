package com.project.corona.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "Haber")
public class Haber {
    @Id
    private String _id;
    private final String icerik;

    public Haber(String icerik) {
        this.icerik = icerik;
    }
}
