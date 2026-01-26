package dev.parkingApp;

import dev.parkingApp.dtos.request.BookingRequest;
import dev.parkingApp.dtos.response.CoordinatesResponse;
import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.auth.SignInRequest;
import dev.parkingApp.services.BookingService;
import dev.parkingApp.services.SpotService;
import dev.parkingApp.services.auth.AuthUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final AuthUserDetailsService authUserDetailsService;
    private final SpotService spotService;
    private final BookingService bookingService;

    public void initial() {

        authUserDetailsService.createUser(new SignInRequest("+7 952 235 53 62", "password", "Biba", "Max", "sobaka@mail.ru"));
        authUserDetailsService.createUser(new SignInRequest("+7 920 513 12 94", "password1", "Boba", "Grisha", "sile1@gmail.com"));
        authUserDetailsService.createUser(new SignInRequest("+7 999 777 51 12", "password2", "Bibi", "Roma", "dksdg2@gmail.com"));
        authUserDetailsService.createUser(new SignInRequest("1", "1", "1", "1", "1"));

        spotService.addSpot(new SpotRequest(null, "Норм парковка",
                "пл. Ленина д.142",
                new BigDecimal(4),
                new BigDecimal(150),
                LocalDateTime.of(2025,6,20,11,30),
                new CoordinatesResponse(null, "30.124125", "41.2154125"),
                1L,
                null));

        spotService.addSpot(new SpotRequest(null, "Парковка в самом центре, проезд всегда без проблем",
                "ул. Московский проспект д.62",
                new BigDecimal("4.9"),
                new BigDecimal(250),
                LocalDateTime.of(2025,3,11,19,19),
                new CoordinatesResponse(null, "60.1515125", "71.9456635"),
                1L,
                null));

        spotService.addSpot(new SpotRequest(null, "Крытая парковка ТЦ Галерея Чижова",
                "Московский проспект, 129/1",
                new BigDecimal("4.8"), new BigDecimal(200),
                LocalDateTime.of(2024, 12, 10, 14, 20),
                new CoordinatesResponse(null, "51.681667", "39.211944"),
                2L, null));

        spotService.addSpot(new SpotRequest(null, "Подземная парковка у цирка",
                "ул. 20-летия Октября, 121",
                new BigDecimal("4.5"), new BigDecimal(180),
                LocalDateTime.of(2024, 11, 25, 9, 15),
                new CoordinatesResponse(null, "51.669722", "39.210278"),
                2L, null));


        spotService.addSpot(new SpotRequest(null, "Парковка бизнес-центра на Никитинской",
                "ул. Никитинская, 8А",
                new BigDecimal("4.9"), new BigDecimal(250),
                LocalDateTime.of(2024, 10, 5, 16, 45),
                new CoordinatesResponse(null, "51.666111", "39.200000"),
                3L, null));

        spotService.addSpot(new SpotRequest(null, "Охраняемая парковка у Кольцовского сквера",
                "пл. Ленина, 11",
                new BigDecimal("4.7"), new BigDecimal(170),
                LocalDateTime.of(2024, 9, 18, 12, 30),
                new CoordinatesResponse(null, "51.660556", "39.200833"),
                3L, null));

        spotService.addSpot(new SpotRequest(null, "Ночная парковка у реки Воронеж",
                "Адмиралтейская пл., 1",
                new BigDecimal("4.3"), new BigDecimal(120),
                LocalDateTime.of(2024, 8, 30, 20, 10),
                new CoordinatesResponse(null, "51.658889", "39.208611"),
                3L, null));


        spotService.addSpot(new SpotRequest(null, "Парковка у ВГУ с видеонаблюдением",
                "Университетская пл., 1",
                new BigDecimal("4.6"), new BigDecimal(160),
                LocalDateTime.of(2024, 7, 22, 8, 0),
                new CoordinatesResponse(null, "51.658056", "39.194722"),
                4L, null));

        spotService.addSpot(new SpotRequest(null, "Парковка ТРЦ Максимир",
                "ул. Хользунова, 35",
                new BigDecimal("4.4"), new BigDecimal(140),
                LocalDateTime.of(2024, 6, 15, 13, 25),
                new CoordinatesResponse(null, "51.642500", "39.169444"),
                4L, null));

        spotService.addSpot(new SpotRequest(null, "Парковка у Северного рынка",
                "Ленинский проспект, 174",
                new BigDecimal("4.2"), new BigDecimal(100),
                LocalDateTime.of(2024, 5, 8, 10, 50),
                new CoordinatesResponse(null, "51.683611", "39.165833"),
                4L, null));

        spotService.addSpot(new SpotRequest(null, "Ул. Плехановская 12",
                "ул. Плехановская, д.12",
                new BigDecimal("4.2"),
                new BigDecimal(120),
                LocalDateTime.of(2025, 3, 15, 14, 20),
                new CoordinatesResponse(null, "39.2001", "51.6720"),
                2L, null));

        spotService.addSpot(new SpotRequest(null, "ТРК Арена",
                "ул. Кольцовская, д.35",
                new BigDecimal("4.5"),
                new BigDecimal(200),
                LocalDateTime.of(2025, 7, 10, 9, 45),
                new CoordinatesResponse(null, "39.2075", "51.6678"),
                3L, null));

        spotService.addSpot(new SpotRequest(null, "Парковка у театра",
                "пр. Революции, д.55",
                new BigDecimal("3.8"),
                new BigDecimal(100),
                LocalDateTime.of(2025, 4, 22, 16, 30),
                new CoordinatesResponse(null, "39.2043", "51.6602"),
                1L, null));

        spotService.addSpot(new SpotRequest(null, "Ж/д вокзал",
                "пл. Черняховского, д.1",
                new BigDecimal("4.0"),
                new BigDecimal(180),
                LocalDateTime.of(2025, 8, 5, 12, 15),
                new CoordinatesResponse(null, "39.2105", "51.6712"),
                4L, null));

        spotService.addSpot(new SpotRequest(null, "ТЦ Галерея Чижова",
                "Московский пр-т, д.129/1",
                new BigDecimal("4.7"),
                new BigDecimal(250),
                LocalDateTime.of(2025, 5, 30, 10, 0),
                new CoordinatesResponse(null, "39.2298", "51.6273"),
                2L, null));

        spotService.addSpot(new SpotRequest(null, "Ул. 20-летия Октября",
                "ул. 20-летия Октября, д.88",
                new BigDecimal("3.5"),
                new BigDecimal(90),
                LocalDateTime.of(2025, 11, 12, 13, 45),
                new CoordinatesResponse(null, "39.2320", "51.6415"),
                1L, null));

        spotService.addSpot(new SpotRequest(null, "БЦ на Лизюкова",
                "ул. Генерала Лизюкова, д.66",
                new BigDecimal("4.3"),
                new BigDecimal(170),
                LocalDateTime.of(2026, 1, 18, 8, 30),
                new CoordinatesResponse(null, "39.2247", "51.6332"),
                3L, null));

        spotService.addSpot(new SpotRequest(null, "Парковка у цирка",
                "ул. 20-летия ВЛКСМ, д.54",
                new BigDecimal("3.9"),
                new BigDecimal(110),
                LocalDateTime.of(2026, 2, 25, 15, 20),
                new CoordinatesResponse(null, "39.2391", "51.6468"),
                2L, null));

        spotService.addSpot(new SpotRequest(null, "ТЦ Московский",
                "Московский пр-т, д.201",
                new BigDecimal("4.6"),
                new BigDecimal(220),
                LocalDateTime.of(2025, 9, 14, 11, 10),
                new CoordinatesResponse(null, "39.2415", "51.6220"),
                4L, null));

        spotService.addSpot(new SpotRequest(null, "Университетская площадь",
                "Университетская пл., д.1",
                new BigDecimal("4.1"),
                new BigDecimal(130),
                LocalDateTime.of(2025, 12, 3, 14, 50),
                new CoordinatesResponse(null, "39.2138", "51.6660"),
                1L, null));

        spotService.addSpot(new SpotRequest(null, "Парковка на Ленинском",
                "Ленинский пр-т, д.77",
                new BigDecimal("3.7"),
                new BigDecimal(95),
                LocalDateTime.of(2026, 3, 8, 17, 25),
                new CoordinatesResponse(null, "39.2172", "51.6584"),
                3L, null));

        spotService.addSpot(new SpotRequest(null, "ТРК Сити-Парк",
                "ул. Парковая, д.3",
                new BigDecimal("4.8"),
                new BigDecimal(300),
                LocalDateTime.of(2025, 10, 29, 10, 45),
                new CoordinatesResponse(null, "39.1350", "51.6280"),
                2L, null));

        spotService.addSpot(new SpotRequest(null, "Ул. Домостроителей",
                "ул. Домостроителей, д.24",
                new BigDecimal("3.6"),
                new BigDecimal(85),
                LocalDateTime.of(2026, 4, 17, 12, 30),
                new CoordinatesResponse(null, "39.2463", "51.6382"),
                1L, null));

        spotService.addSpot(new SpotRequest(null, "БЦ на Кольцовской",
                "ул. Кольцовская, д.68",
                new BigDecimal("4.4"),
                new BigDecimal(190),
                LocalDateTime.of(2025, 6, 25, 9, 15),
                new CoordinatesResponse(null, "39.2090", "51.6651"),
                4L, null));

        spotService.addSpot(new SpotRequest(null, "Парковка у стадиона",
                "ул. Карла Маркса, д.67",
                new BigDecimal("4.0"),
                new BigDecimal(140),
                LocalDateTime.of(2026, 5, 22, 16, 40),
                new CoordinatesResponse(null, "39.2214", "51.6523"),
                2L, null));

        spotService.addSpot(new SpotRequest(null, "ТЦ Европа",
                "Ленинский пр-т, д.25",
                new BigDecimal("4.5"),
                new BigDecimal(210),
                LocalDateTime.of(2025, 8, 30, 13, 20),
                new CoordinatesResponse(null, "39.2067", "51.6645"),
                3L, null));

        spotService.addSpot(new SpotRequest(null, "Ул. Матросова",
                "ул. Матросова, д.15",
                new BigDecimal("3.8"),
                new BigDecimal(105),
                LocalDateTime.of(2025, 2, 14, 18, 10),
                new CoordinatesResponse(null, "39.1985", "51.6689"),
                1L, null));

        spotService.addSpot(new SpotRequest(null, "Парковка у реки",
                "наб. Адмиралатейская, д.12",
                new BigDecimal("4.2"),
                new BigDecimal(160),
                LocalDateTime.of(2026, 6, 10, 11, 55),
                new CoordinatesResponse(null, "39.2189", "51.6598"),
                4L, null));

        spotService.addSpot(new SpotRequest(null, "БЦ на Мира",
                "ул. Мира, д.43",
                new BigDecimal("4.3"),
                new BigDecimal(175),
                LocalDateTime.of(2025, 7, 28, 8, 45),
                new CoordinatesResponse(null, "39.2014", "51.6627"),
                2L, null));

        spotService.addSpot(new SpotRequest(null, "ТЦ на Плехановской",
                "ул. Плехановская, д.6",
                new BigDecimal("4.1"),
                new BigDecimal(145),
                LocalDateTime.of(2026, 7, 19, 14, 15),
                new CoordinatesResponse(null, "39.1968", "51.6732"),
                3L, null));

        // БРОНИ

        bookingService.createBooking(new BookingRequest(
                null,
                LocalDateTime.of(2025, 1, 15, 9, 30),
                LocalDateTime.of(2027, 1, 16, 10, 0),
                LocalDateTime.of(2027, 1, 16, 14, 0),
                new BigDecimal(680),
                5L,
                4L));

        bookingService.createBooking(new BookingRequest(
                null,
                LocalDateTime.of(2025, 1, 12, 14, 0),
                LocalDateTime.of(2026, 1, 15, 18, 0),
                LocalDateTime.of(2026, 1, 15, 23, 0),
                new BigDecimal(850),
                8L,
                4L
        ));

        bookingService.createBooking(new BookingRequest(
                null,
                LocalDateTime.of(2025, 1, 10, 9, 15),
                LocalDateTime.of(2026, 2, 20, 10, 0),
                LocalDateTime.of(2026, 2, 22, 18, 0),
                new BigDecimal(8400),
                9L,
                4L
        ));

        bookingService.createBooking(new BookingRequest(
                null,
                LocalDateTime.of(2025, 1, 14, 20, 30),
                LocalDateTime.of(2026, 1, 14, 22, 0),
                LocalDateTime.of(2026, 1, 19, 8, 0),
                new BigDecimal(2200),
                10L,
                4L
        ));

        bookingService.createBooking(new BookingRequest(
                null,
                LocalDateTime.of(2025, 1, 16, 8, 45),
                LocalDateTime.of(2026, 1, 15, 1, 30),
                LocalDateTime.of(2026, 1, 20, 1, 30),
                new BigDecimal(3300),
                6L,
                4L
        ));

        bookingService.createBooking(new BookingRequest(
                null,
                LocalDateTime.of(2025, 1, 18, 17, 20),
                LocalDateTime.of(2026, 1, 10, 7, 0),
                LocalDateTime.of(2026, 1, 24, 12, 0),
                new BigDecimal(9625),
                7L,
                4L
        ));

        bookingService.createBooking(new BookingRequest(
                null,
                LocalDateTime.of(2025, 1, 18, 17, 20),
                LocalDateTime.of(2025, 12, 10, 7, 0),
                LocalDateTime.of(2025, 12, 24, 12, 0),
                new BigDecimal(8260),
                7L,
                4L
        ));

        bookingService.createBooking(new BookingRequest(
                null,
                LocalDateTime.of(2025, 1, 18, 17, 20),
                LocalDateTime.of(2025, 7, 9, 7, 0),
                LocalDateTime.of(2025, 7, 10, 12, 0),
                new BigDecimal(1100),
                9L,
                4L
        ));
    }
}
