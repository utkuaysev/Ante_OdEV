package com.project.corona;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import com.project.corona.entity.Haber;
import com.project.corona.service.HaberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.project.corona.service.*")
public class HaberServiceTests {

    @InjectMocks
    private HaberService HaberService;

    @Test
    public void testSave() throws Exception {
        HaberService spy = PowerMockito.spy(HaberService);
        //When
        String haber_id = "6015d4caf78f8154ad9bd23b";
        doReturn(haber_id).when(spy, "save",ArgumentMatchers.any());
        //Then
        String response = spy.save(new Haber("22.04.2020 tarihinde Ankara da Korona virüs" +
                "salgınında yapılan testlerde 33 yeni vaka bulundu. 10 kişi korona dan vefat etti. 12 kişide taburcu oldu"));
        System.out.println("response:" + response);
        assertEquals(haber_id, response);
    }

}
