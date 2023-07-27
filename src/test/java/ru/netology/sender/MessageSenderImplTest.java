package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {

    @Test
    void testSendRussianMessageWhenRussianIp() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String russianIp = "172.50.70.100"; // Russian IP address
        Location russiaLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Mockito.when(geoService.byIp(russianIp)).thenReturn(russiaLocation);

        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, russianIp);
        String result = messageSender.send(headers);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("Добро пожаловать"));

        Mockito.verify(geoService, Mockito.times(1)).byIp(russianIp);
        Mockito.verify(localizationService, Mockito.times(2)).locale(Country.RUSSIA);
    }

    @Test
    void testSendUSAMessageWhenUSAIp() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String usaIp = "96.44.183.149"; // USA IP address
        Location usaLocation = new Location("New York", Country.USA, "10th Avenue", 32);
        Mockito.when(geoService.byIp(usaIp)).thenReturn(usaLocation);

        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, usaIp);
        String result = messageSender.send(headers);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("Welcome"));

        Mockito.verify(geoService, Mockito.times(1)).byIp(usaIp);
        Mockito.verify(localizationService, Mockito.times(2)).locale(Country.USA);
    }
}