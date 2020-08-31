package com.adminService;

import com.app.dto.CreateCinemaRoomDto;
import com.app.mappers.Mapper;
import com.app.model.CinemaRoom;
import com.app.model.Seat;
import com.app.repository.CinemaRoomRepository;
import com.app.repository.SeatRepository;
import com.app.service.CinemaRoomService;
import com.app.service.CinemaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class AddCinemaRoomTests {

    private String exceptionMessage;

    @Mock
    CinemaRoomRepository cinemaRoomRepository;

    @Mock
    SeatRepository seatRepository;

    @InjectMocks
    CinemaRoomService cinemaRoomService;

    @Test
    @DisplayName("when cinemaRoomDto is null exception has been thrown")
    public void test1() {
        exceptionMessage = "";
        try {
            cinemaRoomService.addCinemaRoom(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            exceptionMessage = e.getMessage();
        }
        assertEquals("CinemaRoomDto is null", exceptionMessage);
    }

    @Test
    @DisplayName("If rows number is negative or equal zero exception has been thrown")
    public void test3() {
        var cinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .rows(0)
                .name("Room1")
                .places(8)
                .cinemaId(5)
                .build();
        exceptionMessage = "";
        try {
            cinemaRoomService.addCinemaRoom(cinemaRoomDto);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Add cinema room errors: Rows : Should be greater than 0", exceptionMessage);
    }

    @Test
    @DisplayName("if places number is negative or equal zero exception has been thrown")
    public void test4() {
        var cinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .rows(2)
                .name("Room1")
                .places(-1)
                .cinemaId(5)
                .build();
        exceptionMessage = "";
        try {
            cinemaRoomService.addCinemaRoom(cinemaRoomDto);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Add cinema room errors: Places : Should be greater than 0", exceptionMessage);
    }

    @Test
    @DisplayName("when name starts with lowercase exception has been thrown")
    public void test5() {
        var cinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .rows(2)
                .name("room")
                .places(3)
                .cinemaId(5)
                .build();
        exceptionMessage = "";
        try {
            cinemaRoomService.addCinemaRoom(cinemaRoomDto);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Add cinema room errors: Name : Should starts with uppercase", exceptionMessage);
    }

    @Test
    @DisplayName("when name length is equal 1 or 0 exception has been thrown")
    public void test6() {
        var cinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .rows(2)
                .name("r")
                .places(3)
                .cinemaId(5)
                .build();
        exceptionMessage = "";
        try {
            cinemaRoomService.addCinemaRoom(cinemaRoomDto);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Add cinema room errors: Name : Should be longer than one char", exceptionMessage);
    }

    //when is already on db
    @Test
    @DisplayName("when cinema room with this name and city id is already on db exception has been thrown")
    public void test7() {
        var cinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .rows(2)
                .name("Room1")
                .places(3)
                .cinemaId(5)
                .build();

        List<CinemaRoom> cinemasRoomListFromDB = List.of(
                CinemaRoom.builder().rowsNumber(5).places(2).name("Room1").cinemaId(5).build(),
                CinemaRoom.builder().rowsNumber(6).places(6).name("Room2").cinemaId(2).build()
        );

        Mockito
                .when(cinemaRoomRepository.findByNameAndCinemaId("Room1", 5))
                .thenReturn(Optional.of(cinemasRoomListFromDB.get(0)));

        exceptionMessage = "";
        try {
            cinemaRoomService.addCinemaRoom(cinemaRoomDto);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
        assertEquals("Cinema room with this name is already on this cinema id", exceptionMessage);
    }
}
