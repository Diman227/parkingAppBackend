package dev.parkingApp;

import dev.parkingApp.dtos.BookingDTO;
import dev.parkingApp.dtos.CoordinatesDTO;
import dev.parkingApp.dtos.SpotDTO;
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

        spotService.addSpot(new SpotDTO(null, "Норм парковка",
                "пл. Ленина д.142",
                new BigDecimal(4),
                new BigDecimal(150),
                LocalDateTime.of(2025,6,20,11,30),
                new CoordinatesDTO(null, "30.124125", "41.2154125"),
                1L,
                null));

        spotService.addSpot(new SpotDTO(null, "Парковка в самом центре, проезд всегда без проблем",
                "ул. Московский проспект д.62",
                new BigDecimal("4.9"),
                new BigDecimal(250),
                LocalDateTime.of(2025,3,11,19,19),
                new CoordinatesDTO(null, "60.1515125", "71.9456635"),
                1L,
                null));

        spotService.addSpot(new SpotDTO(null, "Крытая парковка ТЦ Галерея Чижова",
                "Московский проспект, 129/1",
                new BigDecimal("4.8"), new BigDecimal(200),
                LocalDateTime.of(2024, 12, 10, 14, 20),
                new CoordinatesDTO(null, "51.681667", "39.211944"),
                2L, null));

        spotService.addSpot(new SpotDTO(null, "Подземная парковка у цирка",
                "ул. 20-летия Октября, 121",
                new BigDecimal("4.5"), new BigDecimal(180),
                LocalDateTime.of(2024, 11, 25, 9, 15),
                new CoordinatesDTO(null, "51.669722", "39.210278"),
                2L, null));


        spotService.addSpot(new SpotDTO(null, "Парковка бизнес-центра на Никитинской",
                "ул. Никитинская, 8А",
                new BigDecimal("4.9"), new BigDecimal(250),
                LocalDateTime.of(2024, 10, 5, 16, 45),
                new CoordinatesDTO(null, "51.666111", "39.200000"),
                3L, null));

        spotService.addSpot(new SpotDTO(null, "Охраняемая парковка у Кольцовского сквера",
                "пл. Ленина, 11",
                new BigDecimal("4.7"), new BigDecimal(170),
                LocalDateTime.of(2024, 9, 18, 12, 30),
                new CoordinatesDTO(null, "51.660556", "39.200833"),
                3L, null));

        spotService.addSpot(new SpotDTO(null, "Ночная парковка у реки Воронеж",
                "Адмиралтейская пл., 1",
                new BigDecimal("4.3"), new BigDecimal(120),
                LocalDateTime.of(2024, 8, 30, 20, 10),
                new CoordinatesDTO(null, "51.658889", "39.208611"),
                3L, null));


        spotService.addSpot(new SpotDTO(null, "Парковка у ВГУ с видеонаблюдением",
                "Университетская пл., 1",
                new BigDecimal("4.6"), new BigDecimal(160),
                LocalDateTime.of(2024, 7, 22, 8, 0),
                new CoordinatesDTO(null, "51.658056", "39.194722"),
                4L, null));

        spotService.addSpot(new SpotDTO(null, "Парковка ТРЦ Максимир",
                "ул. Хользунова, 35",
                new BigDecimal("4.4"), new BigDecimal(140),
                LocalDateTime.of(2024, 6, 15, 13, 25),
                new CoordinatesDTO(null, "51.642500", "39.169444"),
                4L, null));

        spotService.addSpot(new SpotDTO(null, "Парковка у Северного рынка",
                "Ленинский проспект, 174",
                new BigDecimal("4.2"), new BigDecimal(100),
                LocalDateTime.of(2024, 5, 8, 10, 50),
                new CoordinatesDTO(null, "51.683611", "39.165833"),
                4L, null));

        // БРОНИ

        bookingService.createBooking(new BookingDTO(
                null,
                LocalDateTime.of(2025, 1, 15, 9, 30),
                LocalDateTime.of(2027, 1, 16, 10, 0),
                LocalDateTime.of(2027, 1, 16, 14, 0),
                new BigDecimal(680),
                5L,
                4L));

        bookingService.createBooking(new BookingDTO(
                null,
                LocalDateTime.of(2025, 1, 12, 14, 0),
                LocalDateTime.of(2026, 1, 15, 18, 0),
                LocalDateTime.of(2026, 1, 15, 23, 0),
                new BigDecimal(850),
                8L,
                4L
        ));

        bookingService.createBooking(new BookingDTO(
                null,
                LocalDateTime.of(2025, 1, 10, 9, 15),
                LocalDateTime.of(2026, 2, 20, 10, 0),
                LocalDateTime.of(2026, 2, 22, 18, 0),
                new BigDecimal(8400),
                9L,
                4L
        ));

        bookingService.createBooking(new BookingDTO(
                null,
                LocalDateTime.of(2025, 1, 14, 20, 30),
                LocalDateTime.of(2026, 1, 14, 22, 0),
                LocalDateTime.of(2026, 1, 19, 8, 0),
                new BigDecimal(2200),
                10L,
                4L
        ));

        bookingService.createBooking(new BookingDTO(
                null,
                LocalDateTime.of(2025, 1, 16, 8, 45),
                LocalDateTime.of(2026, 1, 15, 1, 30),
                LocalDateTime.of(2026, 1, 20, 1, 30),
                new BigDecimal(3300),
                6L,
                4L
        ));

        bookingService.createBooking(new BookingDTO(
                null,
                LocalDateTime.of(2025, 1, 18, 17, 20),
                LocalDateTime.of(2026, 1, 10, 7, 0),
                LocalDateTime.of(2026, 1, 24, 12, 0),
                new BigDecimal(9625),
                7L,
                4L
        ));

        bookingService.createBooking(new BookingDTO(
                null,
                LocalDateTime.of(2025, 1, 18, 17, 20),
                LocalDateTime.of(2025, 12, 10, 7, 0),
                LocalDateTime.of(2025, 12, 24, 12, 0),
                new BigDecimal(8260),
                7L,
                4L
        ));

        bookingService.createBooking(new BookingDTO(
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
