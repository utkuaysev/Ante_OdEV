package com.project.corona.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "Rapor")
public class Rapor {
    @Id
    private String _id;
    private final String date;
    private final String tur;
    private final String sehir;
    private final int  sayi;
    private final String haber_id;

    public Rapor(String date, String tur, String sehir, int sayi, String haber_id) {
        this.date = date;
        this.tur = tur;
        this.sehir = sehir;
        this.sayi = sayi;
        this.haber_id = haber_id;
    }
    public String toString(){
        return "date:" + date + " tur:" + tur + " sehir:" + sehir + " sayi:" + sayi;
    }
}