package ru.netology.sender.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    private final GeoService geoService = new GeoServiceImpl();

    @Test
    void testValidRussianIp() {
        String russianIp = "172.0.32.11";

        Location location = geoService.byIp(russianIp);

        assertNotNull(location);
        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuiling());
    }

    @Test
    void testValidUSAIp() {
        String usaIp = "96.44.183.149";

        Location location = geoService.byIp(usaIp);

        assertNotNull(location);
        assertEquals("New York", location.getCity());
        assertEquals(Country.USA, location.getCountry());
        assertEquals(" 10th Avenue", location.getStreet());
        assertEquals(32, location.getBuiling());
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
