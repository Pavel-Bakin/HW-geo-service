import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LocalizationServiceImplTest {
    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    void testRussianLocale() {
        Country russia = Country.RUSSIA;

        String locale = localizationService.locale(russia);

        assertEquals("Добро пожаловать", locale);
    }

    @Test
    void testUsaLocale() {
        Country usa = Country.USA;

        String locale = localizationService.locale(usa);

        assertEquals("Welcome", locale);
    }

    @Test
    void testBrazilLocale() {
        Country brazil = Country.BRAZIL;

        String locale = localizationService.locale(brazil);

        assertEquals("Welcome", locale);
    }

    @Test
    void testGermanyLocale() {
        Country germany = Country.GERMANY;

        String locale = localizationService.locale(germany);

        assertEquals("Welcome", locale);
    }
}
