package com.project.corona;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import com.project.corona.dto.RaporDTO;
import com.project.corona.dto.RaporlarDTO;
import com.project.corona.entity.Rapor;
import com.project.corona.repository.RaporRepository;
import com.project.corona.service.HaberRecognitor;
import com.project.corona.service.RaporService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.project.corona.service.*")
public class RaporServiceTests {

    @InjectMocks
    private RaporService raporService;

    @Mock
    private RaporRepository raporRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testgetListRaporlarDTO() throws Exception {
        RaporService spy = PowerMockito.spy(raporService);
        // When
        List<RaporlarDTO> raporlarDTOList = new ArrayList<>();
        RaporDTO rapor1 = new RaporDTO("21.04.2021", 12);
        List<RaporDTO> list1 = new ArrayList<>(Arrays.asList(new RaporDTO[]{rapor1}));
        RaporDTO rapor2 = new RaporDTO("21.04.2021", 21);
        List<RaporDTO> list2 = new ArrayList<>(Arrays.asList(new RaporDTO[]{rapor2}));
        RaporDTO rapor3 = new RaporDTO("21.04.2021", 12);
        List<RaporDTO> list3 = new ArrayList<>(Arrays.asList(new RaporDTO[]{rapor3}));
        raporlarDTOList.add(new RaporlarDTO(list1, "vaka"));
        raporlarDTOList.add(new RaporlarDTO(list2, "vefat"));
        raporlarDTOList.add(new RaporlarDTO(list3, "taburcu"));
        ResponseEntity<List<RaporlarDTO>> responseEntity = new ResponseEntity<List<RaporlarDTO>>(raporlarDTOList, HttpStatus.OK);
        doReturn(responseEntity).when(spy, "getListRaporlarDTO", ArgumentMatchers.any());
        // Then
        ResponseEntity<List<RaporlarDTO>> response = new ResponseEntity<>(spy.getListRaporlarDTO("Adana"),HttpStatus.OK);
        assertEquals(responseEntity, response);
    }

    @Test
    public void testfindDistinctSehirler() throws Exception {
        RaporService spy = PowerMockito.spy(raporService);
        //When
        List<String> sehirList = new ArrayList<String>(Arrays.asList(new String[]{"Adana", "Ankara", "İstanbul"}));
        doReturn(sehirList).when(raporRepository).findDistinctSehirler();
        //Then
        List<String> response = spy.findDistinctSehirler();
        System.out.println("response:" + response);
        assertEquals(sehirList, response);
    }

    @Test
    public void testSaveAll() throws Exception {
        RaporService spy = PowerMockito.spy(raporService);
        //When
        List<Rapor> raporList = new ArrayList<Rapor>
                (Arrays.asList(
                        new Rapor[]
                                {new Rapor("22.10.2020", "vaka", "Adana", 12, "1"),
                                new Rapor("22.10.2020", "vefat", "Adana", 15, "1"),
                                new Rapor("22.10.2020", "taburcu", "Adana", 23, "1")}));
        doReturn(raporList).when(raporRepository).saveAll(raporList);
        //Then
        List<Rapor> response = spy.saveAll(raporList);
        System.out.println("response:" + response);
        assertEquals(raporList, response);
    }
}
