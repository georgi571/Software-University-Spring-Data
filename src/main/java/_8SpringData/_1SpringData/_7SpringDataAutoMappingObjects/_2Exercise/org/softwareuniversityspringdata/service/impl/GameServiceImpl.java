package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.GameAllDTO;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.GameDetailsDTO;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.GameOwnedDTO;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.entities.Game;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.repositories.GameRepository;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.GameService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.UserService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.GameAddDTO;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.util.ValidatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ValidatorService validatorService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, ValidatorService validatorService, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.validatorService = validatorService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String addGame(GameAddDTO gameAddDTO) {
        User user = this.userService.getLoggedIn();
        if (user == null) {
            return "No user is logged in";
        }

        if (!user.isAdmin()) {
            return "You have no rights to add games";
        }

        if (!this.validatorService.isValid(gameAddDTO)) {
            return this.validatorService.validate(gameAddDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }

        Optional<Game> optionalGame = this.gameRepository.findByTitle(gameAddDTO.getTitle());
        if (optionalGame.isPresent()) {
            return "Game with this title already exists.";
        }

        Game game = this.modelMapper.map(gameAddDTO, Game.class);
        this.gameRepository.saveAndFlush(game);
        return String.format("Added %s", game.getTitle());
    }

    @Override
    public String editGame(long id, Map<String, String> map) {
        User user = this.userService.getLoggedIn();
        if (user == null) {
            return "No user is logged in";
        }

        if (!user.isAdmin()) {
            return "You have no rights to edit games";
        }

        Optional<Game> optionalGame = this.gameRepository.findById(id);
        if (optionalGame.isEmpty()) {
            return "No such game exists with given id";
        }

        Game game = optionalGame.get();
        String title = game.getTitle();
        editGameInformation(map, game);
        this.gameRepository.saveAndFlush(game);

        return String.format("Edited %s", title);
    }

    @Override
    public String deleteGame(long id) {
        User user = this.userService.getLoggedIn();
        if (user == null) {
            return "No user is logged in";
        }

        if (!user.isAdmin()) {
            return "You have no rights to delete games";
        }

        Optional<Game> optionalGame = this.gameRepository.findById(id);
        if (optionalGame.isEmpty()) {
            return "No such game exists with given id";
        }
        Game game = optionalGame.get();
        String title = game.getTitle();
        this.gameRepository.delete(game);

        return String.format("Deleted %s", title);
    }

    @Override
    public String getAllGames() {
        return this.gameRepository.findAll().stream()
                .map(game -> this.modelMapper.map(game, GameAllDTO.class).toString())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String getDetailsForGameWithGivenTitle(String title) {
        Optional<Game> optionalGame = this.gameRepository.findByTitle(title);
        if (optionalGame.isEmpty()) {
            return "Game with this title doesn't exists";
        }

        return this.modelMapper.map(optionalGame, GameDetailsDTO.class).toString();
    }

    @Override
    public String getOwnedGames() {
        User user = this.userService.getLoggedIn();
        if (user == null) {
            return "No user is logged in";
        }

        return user.getGames()
                .stream()
                .map(game -> this.modelMapper.map(game, GameOwnedDTO.class).toString())
                .collect(Collectors.joining("\n"));
    }

    private static void editGameInformation(Map<String, String> map, Game game) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("title")) {
                game.setTitle(entry.getValue());
            } else if (entry.getKey().equals("trailer")) {
                game.setTrailer(entry.getValue());
            } else if (entry.getKey().equals("thumbnail")) {
                game.setThumbnail(entry.getValue());
            } else if (entry.getKey().equals("size")) {
                game.setSize(Double.parseDouble(entry.getValue()));
            } else if (entry.getKey().equals("price")) {
                game.setPrice(Double.parseDouble(entry.getValue()));
            } else if (entry.getKey().equals("description")) {
                game.setDescription(entry.getValue());
            } else if (entry.getKey().equals("releaseDate")) {
                game.setReleaseDate(LocalDate.parse(entry.getValue(),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }
        }
    }
}
