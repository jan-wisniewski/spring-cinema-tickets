package com.app.mappers;

import com.app.model.*;
import com.app.dto.*;
import com.app.model.thymeleaf.localDateAsString.MovieLocalDateAsString;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Mapper {
    static User fromCreateUserDtoToUser(CreateUserDto userDto) {
        return User
                .builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    static Reservation fromCreateReservationDtoToReservation(CreateReservationDto reservationDto) {
        return Reservation
                .builder()
                .seanceId(reservationDto.getSeanceId())
                .seatId(reservationDto.getSeatId())
                .userId(reservationDto.getUserId())
                .build();
    }

    static Ticket fromCreateTicketDtoToTicket(CreateTicketDto ticketDto) {
        return Ticket
                .builder()
                .discount(ticketDto.getDiscount())
                .price(ticketDto.getPrice())
                .seanceId(ticketDto.getSeanceId())
                .seatId(ticketDto.getSeatId())
                .userId(ticketDto.getUserId())
                .build();
    }

    static Cinema fromCinemaDtoToCinema(CreateCinemaDto cinemaDto) {
        return Cinema
                .builder()
                .cityId(cinemaDto.getCityId())
                .name(cinemaDto.getName())
                .build();
    }

    static CinemaRoom fromCinemaRoomDtoToCinemaRoom(CreateCinemaRoomDto cinemaRoomDto) {
        return CinemaRoom
                .builder()
                .cinemaId(cinemaRoomDto.getCinemaId())
                .name(cinemaRoomDto.getName())
                .places(cinemaRoomDto.getPlaces())
                .rowsNumber(cinemaRoomDto.getRows())
                .build();
    }

    static Seance fromSeanceDtoToSeance(CreateSeanceDto seanceDto) {
        return Seance
                .builder()
                .dateTime(seanceDto.getDateTime())
                .movieId(seanceDto.getMovieId())
                .cinemaRoomId(seanceDto.getCinemaRoomId())
                .price(seanceDto.getPrice())
                .build();
    }

    static Movie fromMovieDtoToMovie(CreateMovieDto movieDto) {
        return Movie
                .builder()
                .genre(movieDto.getGenre())
                .description(movieDto.getDescription())
                .dateFrom(movieDto.getDateFrom())
                .dateTo(movieDto.getDateTo())
                .title(movieDto.getTitle())
                .build();
    }

    static Movie fromMovieLocalDateAsStringToMovie(MovieLocalDateAsString movie){
        return Movie
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .dateFrom(LocalDateTime.parse(movie.getDateFrom()))
                .dateTo(LocalDateTime.parse(movie.getDateTo()))
                .genre(movie.getGenre())
                .build();
    }

    static CreateMovieDto fromMovieLocalDateAsStringToMovieDto(MovieLocalDateAsString movie){
        return CreateMovieDto
                .builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .dateFrom(LocalDateTime.parse(movie.getDateFrom()))
                .dateTo(LocalDateTime.parse(movie.getDateTo()))
                .genre(movie.getGenre())
                .build();
    }

    static MovieLocalDateAsString fromMovieToMovieLocalDateString (Movie movie){
        return MovieLocalDateAsString
                .builder()
                .title(movie.getTitle())
                .id(movie.getId())
                .description(movie.getDescription())
                .dateFrom(movie.getDateFrom().toString())
                .dateTo(movie.getDateTo().toString())
                .genre(movie.getGenre())
                .build();
    }


    static EditProfileDto fromUserToEditProfileDto (User user){
        return EditProfileDto
                .builder()
                .email(user.getEmail())
                .id(user.getId())
                .password(user.getPassword())
                .newPassword(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    static User fromEditProfileDtoToUser (EditProfileDto editProfileDto){
        return User
                .builder()
                .username(editProfileDto.getUsername())
                .surname(editProfileDto.getSurname())
                .name(editProfileDto.getName())
                .role(editProfileDto.getRole())
                .password(editProfileDto.getNewPassword())
                .email(editProfileDto.getEmail())
                .id(editProfileDto.getId())
                .build();
    }

}
