package com.project.corona.service;

import com.project.corona.entity.Rapor;
import org.springframework.stereotype.Service;
import zemberek.tokenization.Token;
import zemberek.tokenization.TurkishSentenceExtractor;
import zemberek.tokenization.TurkishTokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class HaberRecognitor {
    private TurkishSentenceExtractor extractor;
    private TurkishTokenizer tokenizer;
    private HashSet<String> sehirler;
    private HashSet<String> turler;
    private static HashMap<String,Integer> turSayi;
    public HashSet<String> getTurler() {
        return turler;
    }

    private HaberRecognitor(){
        extractor = TurkishSentenceExtractor.DEFAULT;
        tokenizer = TurkishTokenizer.ALL;
        sehirler = new HashSet<>();
        turler = new HashSet<>();
        hashSetCreatorFromFile(sehirler, "./src/main/resources/dict/sehir");
        hashSetCreatorFromFile(turler, ".//src/main/resources/dict/tur");
    }

    public void hashSetCreatorFromFile(HashSet<String> set, String filename){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
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
    public Rapor[] getRaporlarFromHaber(String haber,String haber_id) {
        String date = null, sehir  = null;
        turSayi = new HashMap<>();
        List<String> sentences = extractor.fromDocument(haber);
        for (String sentence : sentences) {
            List<Token> tokens = tokenizer.tokenize(sentence);
            int val = -1;
            String tur = "";
            for(Token token: tokens){
                String tokenType = token.getType().name();
                switch (tokenType){
                    case "Date":
                        date = token.getText();
                        break;
                    case "Number":
                        String str = token.getText();
                        str = str.replace(".","");
                        val = Integer.parseInt(str);
                        break;
                    case "Word":
                        String word = token.getText();
                        if (sehirler.contains(word))
                            sehir = word;
                        else if (turler.contains(word))
                            tur = word;
                }
            }
            if(val != -1 && !tur.equals(""))
                turSayi.put(tur, val);
        }
        boolean check = checkTurSayi(date,sehir);
        if(!check){
            return null;
        }
        Rapor[] raporlar = new Rapor[turler.size()];
        int i = 0;
        for (Iterator<String> it = turler.iterator(); it.hasNext(); ) {
            String tur = it.next();
            raporlar[i] = new Rapor(date,tur,sehir,turSayi.get(tur), haber_id);
            i++;
        }
        return raporlar;
    }

    private boolean checkTurSayi(String date,String sehir) {
        boolean turCheck = false;
        for (String tur:turler) {
            turCheck = turCheck || !turSayi.containsKey(tur);
        }
        if(date == null || sehir == null || turCheck){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        HaberRecognitor test = new HaberRecognitor();
        Rapor[] rapor = (test.getRaporlarFromHaber("19.04.2020 tarihinde İstanbul  için korona virüs ile ilgili yeni bir açıklama yapıldı. " +
                "Korona virüs salgınında yapılan testlerde 20 yeni vaka tespit edildi. " +
                "taburcu sayısı ise 7 oldu.  3 kişin de vefat ettiği öğrenildi.  ","1"));
        System.out.println("test");
    }
}
