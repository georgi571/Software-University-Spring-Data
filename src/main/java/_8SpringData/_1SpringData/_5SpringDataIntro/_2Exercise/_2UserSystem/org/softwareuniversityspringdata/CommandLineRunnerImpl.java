package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.*;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CountryService countryService;
    private final TownService townService;
    private final UserService userService;
    private final AlbumService albumService;
    private final PictureService pictureService;

    public CommandLineRunnerImpl(CountryService countryService, TownService townService, UserService userService, AlbumService albumService, PictureService pictureService) {
        this.countryService = countryService;
        this.townService = townService;
        this.userService = userService;
        this.albumService = albumService;
        this.pictureService = pictureService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        Long idFirstUser = 2L;
        Long idSecondUser = 6L;
        String countryName = "Egypt";
        String townName = "Cairo";
        String username = "nakov123";
        String password = "Random_password-500";
        String email = "nakov@mail.bg";
        int age = 40;
        String bornTown = "Varna";
        String currentlyLivingTown = "Sofia";
        String firstName = "Svetlin";
        String lastName = "Nakov";
        String albumName = "Study Spring Data";
        String backgroundColor = "Blue";
        String pictureTitle = "Spring Data";
        String pictureCaption = "Study hard";
        String picturePath = "path500";
        Town townInWhatUserWasBorn = this.townService.getTownByName(bornTown);
        Town townInWhatUserCurrentlyLive = this.townService.getTownByName(currentlyLivingTown);

        addFriend(idFirstUser, idSecondUser);
        createCountry(countryName);
        createTown(countryName, townName);
        createUser(username, password, email, age, townInWhatUserWasBorn, townInWhatUserCurrentlyLive, firstName, lastName);
        creaeAlbum(idFirstUser, albumName, backgroundColor);
        createPicture(pictureTitle, pictureCaption, picturePath, albumName);

    }

    private void createPicture(String pictureTitle, String pictureCaption, String picturePath, String albumName) {
        Picture picture = new Picture(pictureTitle, pictureCaption, picturePath);
        Album album = this.albumService.getAlbumByName(albumName);
        this.pictureService.createPicture(picture, album);
    }

    private void creaeAlbum(Long idFirstUser, String albumName, String backgroundColor) {
        User user = this.userService.getUserById(idFirstUser);
        Album album = new Album(albumName, backgroundColor, true,  user);
        this.albumService.createAlbum(album);
    }

    private void createTown(String countryName, String townName) {
        Country country = this.countryService.getCountryName(countryName);
        Town town = new Town(townName, country);
        this.townService.createTown(town);
    }

    private void createCountry(String countryName) {
        Country country = new Country(countryName);
        this.countryService.createCountry(country);
    }

    private void createUser(String username, String password, String email, int age, Town townInWhatUserWasBorn, Town townInWhatUserCurrentlyLive, String firstName, String lastName) {
        User user = new User(username, password, email, LocalDateTime.now(), LocalDateTime.now(), age, townInWhatUserWasBorn, townInWhatUserCurrentlyLive, firstName, lastName);
        this.userService.createUser(user);
    }

    private void addFriend(Long idFirstUser, Long idSecondUser) {
        User user = this.userService.getUserById(idFirstUser);
        User user1 = this.userService.getUserById(idSecondUser);
        this.userService.addFriends(user, user1);
    }

    private void seedData() throws IOException {
        this.countryService.seedCountries();
        this.townService.seedTowns();
        this.userService.seedUsers();
        this.albumService.seedAlbums();
        this.pictureService.seedPictures();
    }
}
