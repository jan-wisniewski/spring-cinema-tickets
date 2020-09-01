package com.app.mappers;

import com.app.dto.*;
import com.app.model.*;
import com.app.model.thymeleaf.localDateAsString.MovieLocalDateAsString;
import com.app.model.thymeleaf.localDateAsString.NewsLocalDateAsString;
import com.app.model.thymeleaf.localDateAsString.SeanceLocalDateAsString;

import java.time.LocalDateTime;

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
                .dateTime(LocalDateTime.parse(seanceDto.getDateTime()))
                .movieId(seanceDto.getMovieId())
                .cinemaRoomId(seanceDto.getCinemaRoomId())
                .price(seanceDto.getPrice())
                .build();
    }

    static SeanceLocalDateAsString fromSeanceToSeanceLocalDateAsString(Seance seance) {
        return SeanceLocalDateAsString
                .builder()
                .id(seance.getId())
                .dateTime(seance.getDateTime().toString())
                .movieId(seance.getMovieId())
                .cinemaRoomId(seance.getCinemaRoomId())
                .price(seance.getPrice())
                .build();
    }

    static CreateSeanceDto fromSeanceLocalDateAsStringToSeanceDto(SeanceLocalDateAsString seance) {
        return CreateSeanceDto
                .builder()
                .dateTime(seance.getDateTime())
                .movieId(seance.getMovieId())
                .cinemaRoomId(seance.getCinemaRoomId())
                .price(seance.getPrice())
                .build();
    }

    static Seance fromSeanceLocalDateAsStringToSeance(SeanceLocalDateAsString seance) {
        return Seance
                .builder()
                .id(seance.getId())
                .dateTime(LocalDateTime.parse(seance.getDateTime()))
                .movieId(seance.getMovieId())
                .cinemaRoomId(seance.getCinemaRoomId())
                .price(seance.getPrice())
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
                .img(movieDto.getImg())
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
                .img(movie.getImg())
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
                .img(movie.getImg())
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
                .img(movie.getImg())
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

    static News fromNewsDtoToNews(CreateNewsDto newsDto) {
        return News
                .builder()
                .author(newsDto.getAuthor())
                .content(newsDto.getContent())
                .description(newsDto.getDescription())
                .title(newsDto.getTitle())
                .date(LocalDateTime.parse(newsDto.getDate()))
                .img(newsDto.getImg())
                .build();
    }

    static NewsLocalDateAsString fromNewsToNewsLocalDateAsString(News news) {
        return NewsLocalDateAsString
                .builder()
                .id(news.getId())
                .author(news.getAuthor())
                .content(news.getContent())
                .description(news.getDescription())
                .title(news.getTitle())
                .date(news.getDate().toString())
                .img(news.getImg())
                .build();
    }

    static News fromNewsLocalDateAsStringToNews(NewsLocalDateAsString newsLocalDateAsString) {
        return News
                .builder()
                .id(newsLocalDateAsString.getId())
                .author(newsLocalDateAsString.getAuthor())
                .content(newsLocalDateAsString.getContent())
                .description(newsLocalDateAsString.getDescription())
                .title(newsLocalDateAsString.getTitle())
                .date(LocalDateTime.parse(newsLocalDateAsString.getDate()))
                .img(newsLocalDateAsString.getImg())
                .build();
    }
}
