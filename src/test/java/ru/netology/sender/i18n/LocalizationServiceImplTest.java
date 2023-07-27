package ru.netology.sender.geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LocalizationServiceImplTest {
    private final LocalizationService localizationService = new LocalizationServiceImpl();
    private final String WELCOME_MESSAGE = "Welcome";

    @Test
    void testRussianLocale() {
        Country russia = Country.RUSSIA;

        String locale = localizationService.locale(russia);

        assertEquals("Добро пожаловать", locale);
    }

    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"USA", "BRAZIL", "GERMANY"})
    void testWelcomeLocale(Country country) {
        String locale = localizationService.locale(country);
        assertEquals(WELCOME_MESSAGE, locale);
    }
}
