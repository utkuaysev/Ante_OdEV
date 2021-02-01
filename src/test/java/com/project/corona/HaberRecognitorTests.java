package com.project.corona;


import com.project.corona.entity.Rapor;
import com.project.corona.service.HaberRecognitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import zemberek.tokenization.Token;
import zemberek.tokenization.TurkishSentenceExtractor;
import zemberek.tokenization.TurkishTokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;


@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.project.corona.service.*")
public class HaberRecognitorTests {

    @Mock
    private HaberRecognitor haberRecognitor;

    @Mock
    private TurkishSentenceExtractor extractor;

    @Mock
    private TurkishTokenizer tokenizer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRaporlarFromHaber() throws Exception {
        HaberRecognitor spy = PowerMockito.spy(haberRecognitor);


        List<String> sentences = new ArrayList<>(Arrays.asList
                (new String[]{"Korona virüs salgınında yapılan " +
                        "testlerde 19.04.2020 tarihinde  İstanbul da 30 yeni vaka tespit edil", "" +
                        "İstanbul da taburcu sayısı 7 kişi ",
                        " 3 vefat"}));
        String haber = "Korona virüs salgınında yapılan testlerde 19.04.2020 tarihinde  İstanbul da 30 yeni vaka tespit edil. " +
                "İstanbul da taburcu sayısı 7 kişi . 3 vefat";
        doReturn(sentences).when(extractor).fromDocument(haber);

        List<Token> tokens = new ArrayList<>(Arrays.asList(new Token[]{new Token("3", Token.Type.Number,0,1),
                new Token(" ",Token.Type.SpaceTab,1,2),new Token("vefat",Token.Type.Word,2,6)}));
        doReturn(tokens).when(tokenizer).tokenize(" 3 vefat");
        //When
        Rapor[] raporlar = new Rapor[]{new Rapor("19.04.2020","taburcu","İstanbul",7,"6015b78f50912f37e775ffbf"),
                new Rapor("19.04.2020","vefat","İstanbul",3,"6015b78f50912f37e775ffbf"),
                new Rapor("19.04.2020","vaka","İstanbul",30,"6015b78f50912f37e775ffbf")};

        doReturn(raporlar).when(spy).getRaporlarFromHaber(haber,"6015b78f50912f37e775ffbf");
        //Then
        Rapor[] response = spy.getRaporlarFromHaber(haber,"6015b78f50912f37e775ffbf");
         System.out.println("response:" + response);
        assertEquals(raporlar, response);


    }

}

