package com.project.corona.service;

import com.project.corona.entity.Rapor;
import org.springframework.stereotype.Service;
import zemberek.tokenization.Token;
import zemberek.tokenization.TurkishSentenceExtractor;
import zemberek.tokenization.TurkishTokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;

@Service
public class HaberRecognitor {
    private TurkishSentenceExtractor extractor;
    private TurkishTokenizer tokenizer;
    private HashSet<String> sehirler;
    private HashSet<String> turler;
    private static HashMap<String, Integer> turSayi;

    public HashSet<String> getTurler() {
        return turler;
    }

    private HaberRecognitor() {
        extractor = TurkishSentenceExtractor.DEFAULT;
        tokenizer = TurkishTokenizer.ALL;
        sehirler = new HashSet<>();
        turler = new HashSet<>();
        hashSetCreatorFromFile(sehirler, ".//src/main/resources/dict/sehir");
        hashSetCreatorFromFile(turler, ".//src/main/resources/dict/tur");
    }

    // parse isleminde veri turlerinin belirlenmesi icin kullanilacak olan hashsetleri olusturur
    public void hashSetCreatorFromFile(HashSet<String> set, String filename) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj, "UTF-8");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                set.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    //sehir isimleri set'te ilk harfi buyuk olarak tutulmaktadır.Bu method aldigi datanin ilk harfini buyultur
    private String capitalizeFirstChar(String data) {
        return data.substring(0, 1).toUpperCase() + data.substring(1);
    }

    // verilen haber parse edilerek ona uygun raporlar donulur
    public Rapor[] getRaporlarFromHaber(String haber, String haber_id) {
        String date = null, sehir = null;
        turSayi = new HashMap<>();
        List<String> sentences = extractor.fromDocument(haber);
        for (String sentence : sentences) {
            List<Token> tokens = tokenizer.tokenize(sentence);
            int val = -1;
            String tur = "";
            for (Token token : tokens) {
                String tokenType = token.getType().name();
                String data = token.getText();
                switch (tokenType) {
                    case "Date":
                        date = data;
                        break;
                    case "Number":
                        data = data.replace(".", "").replace(",", "");
                        val = Integer.parseInt(data);
                        break;
                    case "Word":
                        data = data.replace(".", "").replace(",", "");
                        if (turler.contains(data.toLowerCase()))
                            tur = data.toLowerCase();
                        else if (sehirler.contains(capitalizeFirstChar(data)))
                            sehir = capitalizeFirstChar(data);
                }
            }
            if (val != -1 && !tur.equals(""))
                turSayi.put(tur, val);
        }
        boolean check = checkTurSayi(date, sehir);
        if (!check) {
            return null;
        }
        Rapor[] raporlar = new Rapor[turler.size()];
        int i = 0;
        for (Iterator<String> it = turler.iterator(); it.hasNext(); ) {
            String tur = it.next();
            raporlar[i] = new Rapor(date, tur, sehir, turSayi.get(tur), haber_id);
            i++;
        }
        return raporlar;
    }

    // backendde her anahtar kelime yoksa verilecek error'u saglar
    private boolean checkTurSayi(String date, String sehir) {
        boolean turCheck = false;
        for (String tur : turler) {
            turCheck = turCheck || !turSayi.containsKey(tur);
        }
        if (date == null || sehir == null || turCheck) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HaberRecognitor test = new HaberRecognitor();
        Rapor[] rapor = (test.getRaporlarFromHaber("19.04.2020 tarihinde İstanbul  için korona virüs ile ilgili yeni bir açıklama yapıldı. " +
                "Korona virüs salgınında yapılan testlerde 20 yeni vaka tespit edildi. " +
                "taburcu sayısı ise 7 oldu.  3 kişin de vefat ettiği öğrenildi.  ", "1"));
        System.out.println("test");
    }
}
