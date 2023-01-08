import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    @ParameterizedTest
    @MethodSource("sourceFindLocationByIp")
    public void findLocationByIp(String ip, Location expected) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> sourceFindLocationByIp() {
        return Stream.of(Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.*", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.*", new Location("New York", Country.USA, null, 0)),
                Arguments.of("255.*", null));

    }

    @ParameterizedTest
    @MethodSource("sourceByCoordinates")
    public void locationByCoordinates_Exception(double latitude, double longitude) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(latitude, longitude));
    }

    public static Stream<Arguments> sourceByCoordinates() {
        return Stream.of(Arguments.of(45.5, 82.6),
                Arguments.of(120.5, 90.3));
    }
}
