package com.app.controller;

import com.app.dto.*;
import com.app.enums.Genre;
import com.app.enums.Role;
import com.app.mappers.Mapper;
import com.app.model.*;
import com.app.model.thymeleaf.*;
import com.app.model.thymeleaf.localDateAsString.MovieLocalDateAsString;
import com.app.model.thymeleaf.localDateAsString.NewsLocalDateAsString;
import com.app.model.thymeleaf.localDateAsString.SeanceLocalDateAsString;
import com.app.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private CinemaService cinemaService;
    private CinemaRoomService cinemaRoomService;
    private CityService cityService;
    private MovieService movieService;
    private SeanceService seanceService;
    private SeatService seatService;
    private TicketService ticketService;
    private UserService userService;
    private NewsService newsService;

    @GetMapping("")
    public String index() {
        return "admin";
    }

    //--------------[NEWS]-----------------------------------

    @GetMapping("/news")
    public String news(Model model) {
        model.addAttribute("newsAll", newsService.findAll());
        model.addAttribute("news",new CreateNewsDto());
        return "admin_news";
    }

    @GetMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable Integer id, Model model) {
        model.addAttribute("status", (newsService.deleteById(id) == 1) ? "News deleted!" : "Can't delete news.");
        return "admin_operation";
    }

    @PostMapping("news/add")
    public String addNews(@ModelAttribute CreateNewsDto newsDto, Model model) {
        model.addAttribute("status", (newsService.add(newsDto) > 0) ? "News added!" : "Can't add news.");
        return "admin_operation";
    }

    @PostMapping("news/edit")
    public String saveEditedNews(@ModelAttribute NewsLocalDateAsString newsLocalDateAsString, Model model) {
        News news = Mapper.fromNewsLocalDateAsStringToNews(newsLocalDateAsString);
        System.out.println(news);
        model.addAttribute("status", (newsService.editNews(news).getId().equals(news.getId())) ? "News edited!" : "Cant' edit news");
        return "admin_operation";
    }

    @GetMapping("news/edit/{id}")
    public String editNews(@PathVariable Integer id, Model model) {
        model.addAttribute("news", Mapper.fromNewsToNewsLocalDateAsString(newsService.findById(id)));
        return "admin_news_edit";
    }


    //--------------[CINEMA]-----------------------------------

    @GetMapping("/cinema")
    public String cinemas(Model model) {
        List<CinemaWithObj> cinemaWithObjs =
                cinemaService
                        .getAll()
                        .stream()
                        .map(cinema -> CinemaWithObj
                                .builder()
                                .id(cinema.getId())
                                .city(cityService.findCityById(cinema.getCityId()))
                                .name(cinema.getName())
                                .img(cinema.getImg())
                                .build())
                        .collect(Collectors.toList());
        model.addAttribute("cinemas", cinemaWithObjs);
        model.addAttribute("newCinema", new Cinema());
        List<City> cities = cityService.getAll();
        model.addAttribute("getAllCities", cities);
        return "admin_cinemas";
    }

    @GetMapping("/cinema/delete/{id}")
    public String deleteCinema(@PathVariable Integer id, Model model) {
        model.addAttribute("status", (cinemaService.deleteCinema(id) == 1) ? "Cinema deleted!" : "Cant' delete cinema. You can't have any active seances!");
        return "admin_operation";
    }

    @PostMapping("cinema/add")
    public String addCinema(@ModelAttribute Cinema cinema, Model model) {
        var cinemaDto = CreateCinemaDto.builder().name(cinema.getName()).cityId(cinema.getCityId()).img(cinema.getImg()).build();
        model.addAttribute("status", (cinemaService.addCinema(cinemaDto) == 1) ? "Cinema added!" : "Cant' add Cinema. Duplicate name");
        System.out.println(cinemaDto);
        return "admin_operation";
    }

    @PostMapping("cinema/edit")
    public String saveEditedCinema(@ModelAttribute Cinema cinema, Model model) {
        model.addAttribute("status", (cinemaService.editCinema(cinema).getId().equals(cinema.getId())) ? "Cinema edited!" : "Cant' edit cinema");
        return "admin_operation";
    }

    @GetMapping("cinema/edit/{id}")
    public String editCinema(@PathVariable Integer id, Model model) {
        model.addAttribute("cinema", cinemaService.findCinemaById(id));
        List<City> cities = cityService.getAll();
        model.addAttribute("getAllCities", cities);
        model.addAttribute("currentCity", cinemaService.findCinemaById(id).getCityId().toString());
        return "admin_cinema_edit";
    }

    //--------------[CINEMA ROOM]-----------------------------------

    @GetMapping("/cinemaRoom")
    public String cinemaRooms(Model model) {
        List<CinemaRoomWithObj> cinemaRoomWithObjs =
                cinemaRoomService
                        .getAll()
                        .stream()
                        .map(cinemaRoom -> CinemaRoomWithObj
                                .builder()
                                .id(cinemaRoom.getId())
                                .name(cinemaRoom.getName())
                                .places(cinemaRoom.getPlaces())
                                .rowsNumber(cinemaRoom.getRowsNumber())
                                .cinema(cinemaService.findCinemaById(cinemaRoom.getCinemaId()))
                                .build())
                        .collect(Collectors.toList());
        model.addAttribute("cinemasRooms", cinemaRoomWithObjs);
        model.addAttribute("newCinemaRoom", new CinemaRoom());
        List<City> cities = cityService.getAll();
        model.addAttribute("getAllCities", cities);
        List<Cinema> cinemas = cinemaService.getAll();
        model.addAttribute("getAllCinemas", cinemas);
        return "admin_cinema_rooms";
    }


    @GetMapping("/cinemaRoom/delete/{id}")
    public String deleteRoom(@PathVariable Integer id, Model model) {
        model.addAttribute("status", (cinemaRoomService.deleteCinemaRoom(id) == 1) ? "Cinema room deleted!" : "Cant' delete cinema room. You can't have any active seances!");
        return "admin_operation";
    }

    @PostMapping("cinemaRoom/add")
    public String addCinemaRoom(@ModelAttribute CinemaRoom cinemaRoom, Model model) {
        model.addAttribute("status", (cinemaRoomService.addCinemaRoom(new CreateCinemaRoomDto(cinemaRoom.getName(), cinemaRoom.getCinemaId(), cinemaRoom.getRowsNumber(), cinemaRoom.getPlaces())) == 1) ? "Cinema room added!" : "Cant' add Cinema room. Duplicate name");
        System.out.println(cinemaRoom.toString());
        return "admin_operation";
    }

    @PostMapping("cinemaRoom/edit")
    public String saveEditedCinemaRoom(@ModelAttribute CinemaRoom cinemaRoom, Model model) {
        model.addAttribute("status", (cinemaRoomService.editCinemaRoom(cinemaRoom).getId().equals(cinemaRoom.getId())) ? "Cinema room edited!" : "Cant' edit cinema room");
        return "admin_operation";
    }

    @GetMapping("cinemaRoom/edit/{id}")
    public String editCinemaRoom(@PathVariable Integer id, Model model) {
        model.addAttribute("cinemaRoom", cinemaRoomService.findById(id));
        List<Cinema> cinemas = cinemaService.getAll();
        model.addAttribute("getAllCinemas", cinemas);
        model.addAttribute("currentCinema", cinemaRoomService.findById(id).getCinemaId().toString());
        return "admin_cinema_room_edit";
    }

    //--------------[CITY]-----------------------------------


    @GetMapping("/city")
    public String cities(Model model) {
        List<CityWithObj> cityWithObjs =
                cityService
                        .getAll()
                        .stream()
                        .map(city -> CityWithObj
                                .builder()
                                .id(city.getId())
                                .name(city.getName())
                                .build())
                        .collect(Collectors.toList());
        model.addAttribute("cities", cityWithObjs);
        model.addAttribute("newCity", new City());
        return "admin_cities";
    }

    @GetMapping("/city/delete/{id}")
    public String deleteCity(@PathVariable Integer id, Model model) {
        model.addAttribute("status", (cityService.deleteCity(id) == 1) ? "City deleted!" : "Cant' delete city. You need to remove all cinemas first");
        return "admin_operation";
    }

    @PostMapping("/city/add")
    public String deleteCity(@ModelAttribute City city, Model model) {
        var cityDto = CreateCityDto.builder().name(city.getName()).img(city.getImg()).build();
        model.addAttribute("status", (cityService.addCity(cityDto) == 1) ? "City added!" : "Cant' add city. Duplicate name");
        return "admin_operation";
    }

    @PostMapping("city/edit")
    public String saveEditedCity(@ModelAttribute City city, Model model) {
        model.addAttribute("status", (cityService.editCity(city).getId().equals(city.getId())) ? "City edited!" : "Cant' edit city");
        return "admin_operation";
    }

    @GetMapping("city/edit/{id}")
    public String editCity(@PathVariable Integer id, Model model) {
        model.addAttribute("city", cityService.findCityById(id));
        return "admin_city_edit";
    }

    //--------------[Movie]-----------------------------------

    @GetMapping("/movie")
    public String movies(Model model) {
        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("newMovie", new CreateMovieDto());
        model.addAttribute("getAllGenres", Arrays.asList(Genre.values()));
        return "admin_movies";
    }

    @GetMapping("/movie/delete/{id}")
    public String deleteMovie(@PathVariable Integer id, Model model) {
        model.addAttribute("status", (movieService.deleteMovie(id) == 1) ? "Movie deleted!" : "Cant' delete movie. You already have seances planned");
        return "admin_operation";
    }


    @PostMapping("movie/add")
    public String addMovie(@ModelAttribute MovieLocalDateAsString movie, Model model) {
        var movieDto = Mapper.fromMovieLocalDateAsStringToMovieDto(movie);
        model.addAttribute("status", (movieService.addMovie(movieDto) > 0) ? "Movie added!" : "Cant' add Movie");
        return "admin_operation";
    }

    @PostMapping("movie/edit")
    public String saveEditedMovie(@ModelAttribute MovieLocalDateAsString movieLocalDateAsString, Model model) {
        var movie = Mapper.fromMovieLocalDateAsStringToMovie(movieLocalDateAsString);
        model.addAttribute("status", (movieService.editMovie(movie).getId().equals(movie.getId())) ? "Movie edited!" : "Cant' edit movie");
        return "admin_operation";
    }

    @GetMapping("movie/edit/{id}")
    public String editMovie(@PathVariable Integer id, Model model) {
        var movie = Mapper.fromMovieToMovieLocalDateString(movieService.findById(id));
        model.addAttribute("getAllGenres", Arrays.asList(Genre.values()));
        model.addAttribute("movie", movie);
        return "admin_movie_edit";
    }

    //--------------[SEANCE]-----------------------------------

    @PostMapping("seance/edit")
    public String saveEditedSeance(@ModelAttribute SeanceLocalDateAsString seanceLDS, Model model) {
        var seance = Mapper.fromSeanceLocalDateAsStringToSeance(seanceLDS);
        model.addAttribute("status", (seanceService.editSeance(seance).getId().equals(seance.getId())) ? "Seance edited!" : "Can't edit seance");
        return "admin_operation";
    }

    @GetMapping("seance/edit/{id}")
    public String editSeance(@PathVariable Integer id, Model model) {
        var seance = Mapper.fromSeanceToSeanceLocalDateAsString(seanceService.findById(id));
        Map<Integer, String> cinemaRoomAndCinema = cinemaRoomService
                .getAll()
                .stream()
                .collect(Collectors.toMap(
                        CinemaRoom::getId,
                        room -> cinemaService.getById(room.getId()).getName() + " (" +
                                cityService.showNameByCityId(cinemaService.getById(room.getId()).getCityId()) + ") - "
                                + cinemaRoomService.findById(room.getId()).getName()
                ));
        model.addAttribute("seance", seance);
        model.addAttribute("rooms", cinemaRoomAndCinema);
        model.addAttribute("movies", movieService.getAllWithFutureDate());
        return "admin_seance_edit";
    }

    @PostMapping("seance/add")
    public String addSeance(@ModelAttribute CreateSeanceDto seanceDto, Model model) {
        model.addAttribute("status", (seanceService.addSeance(seanceDto) > 0) ? "Seance added!" : "Cant' add Seance");
        return "admin_operation";
    }

    @GetMapping("/seance")
    public String seances(Model model) {
        List<SeanceWithObj> seanceWithObjs =
                seanceService
                        .getAll()
                        .stream()
                        .map(seance -> SeanceWithObj
                                .builder()
                                .id(seance.getId())
                                .cinemaRoom(cinemaRoomService.findById(seance.getCinemaRoomId()))
                                .movie(movieService.findById(seance.getMovieId()))
                                .dateTime(seance.getDateTime())
                                .price(seance.getPrice())
                                .build()
                        )
                        .collect(Collectors.toList());
        Map<Integer, String> cinemaRoomAndCinema = cinemaRoomService
                .getAll()
                .stream()
                .collect(Collectors.toMap(
                        CinemaRoom::getId,
                        room -> cinemaService.getById(room.getId()).getName() + " (" +
                                cityService.showNameByCityId(cinemaService.getById(room.getId()).getCityId()) + ") - "
                                + cinemaRoomService.findById(room.getId()).getName()
                ));
        model.addAttribute("seances", seanceWithObjs);
        model.addAttribute("newSeance", new Seance());
        model.addAttribute("futureMovies", movieService.getAllWithFutureDate());
        model.addAttribute("roomIdCinemaCity", cinemaRoomAndCinema);
        return "admin_seances";
    }

    @GetMapping("/seance/delete/{id}")
    public String deleteSeance(@PathVariable Integer id, Model model) {
        model.addAttribute("status", (seanceService.deleteSeance(id) == 1) ? "Seance deleted!" : "Cant' delete seance.");
        return "admin_operation";
    }

    //--------------[SEAT]-----------------------------------

    @GetMapping("/seat/delete/{id}")
    public Integer deleteSeat(@PathVariable Integer id) {
        return seatService.deleteById(id);
    }

    //--------------[USER]-----------------------------------

    @GetMapping("/user")
    public String users(Model model) {
        List<UsersWithObj> usersWithObjs =
                userService
                        .findAll()
                        .stream()
                        .map(user -> UsersWithObj
                                .builder()
                                .id(user.getId())
                                .name(user.getName())
                                .surname(user.getSurname())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .role(user.getRole())
                                .build())
                        .collect(Collectors.toList());
        model.addAttribute("users", usersWithObjs);

        return "admin_users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Integer id, Model model) {
        model.addAttribute("status", (userService.delete(id)) ? "User deleted!" : "Cant' delete user.");
        return "admin_operation";
    }
    @PostMapping("user/edit")
    public String saveEditedUser(@ModelAttribute User user, Model model) {
        model.addAttribute("status", (userService.edit(user).getId().equals(user.getId())) ? "User edited!" : "Cant' edit user");

        return "admin_operation";
    }

    @GetMapping("user/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("getAllRoles", Arrays.asList(Role.values()));
        model.addAttribute("currentRole", userService.findById(id).getRole().toString());
        return "admin_user_edit";
    }

}
