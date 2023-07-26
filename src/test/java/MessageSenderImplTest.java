import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessageSenderImplTest {
    private final GeoService geoService = mock(GeoService.class);
    private final LocalizationService localizationService = mock(LocalizationService.class);
    private final MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

    @Test
    void testSendRussianMessageWhenRussianIp() {
        String russianIp = "172.0.32.11";
        Location locationRussia = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        when(geoService.byIp(russianIp)).thenReturn(locationRussia);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать!");

        Map<String, String> headers = Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, russianIp);
        String result = messageSender.send(headers);

        assertNotNull(result);
        assertTrue(result.contains("Добро пожаловать!"));
        Mockito.verify(geoService, times(1)).byIp(russianIp);
        Mockito.verify(localizationService, times(2)).locale(Country.RUSSIA);
    }

    @Test
    void testSendUsaMessageWhenUsaIp() {
        String usaIp = "96.44.183.149";
        Location locationUsa = new Location("New York", Country.USA, " 10th Avenue", 32);
        when(geoService.byIp(usaIp)).thenReturn(locationUsa);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome!");

        Map<String, String> headers = Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, usaIp);
        String result = messageSender.send(headers);

        assertNotNull(result);
        assertTrue(result.contains("Welcome!"));
        Mockito.verify(geoService, times(1)).byIp(usaIp);
        Mockito.verify(localizationService, times(2)).locale(Country.USA);
    }

    @Test
    void testInvalidIp() {
        String invalidIp = "invalid_ip_address";

        Location location = geoService.byIp(invalidIp);

        assertNull(location);
    }

    @Test
    void testEmptyIp() {
        String emptyIp = "";

        Location location = geoService.byIp(emptyIp);

        assertNull(location);
    }
}
