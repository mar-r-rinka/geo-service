import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceImplTest {
    LocalizationServiceImpl localizationService= new LocalizationServiceImpl();
    @ParameterizedTest
    @MethodSource("sourceLocale")
    public void localeTest (Country country, String expected){
        String result = localizationService.locale(country);
        Assertions.assertEquals(expected,result);
    }
    public static Stream<Arguments> sourceLocale(){
        return Stream.of(Arguments.of("RUSSIA", "Добро пожаловать"),
                Arguments.of("USA", "Welcome"),
                Arguments.of("GERMANY", "Welcome"),
                Arguments.of("BRAZIL", "Welcome"));
    }
}
