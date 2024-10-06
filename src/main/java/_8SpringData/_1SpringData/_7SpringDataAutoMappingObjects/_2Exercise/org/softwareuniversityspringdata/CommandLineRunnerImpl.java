package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.GameService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.ShoppingCartService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.UserService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.CartItemDTO;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.GameAddDTO;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.LoginUserDTO;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.UserRegisterDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;
    private final ShoppingCartService shoppingCartService;

    public CommandLineRunnerImpl(UserService userService, GameService gameService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.gameService = gameService;
        this.shoppingCartService = shoppingCartService;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.equals("END")) {
            String[] inputValues = input.split("\\|");
            String command = "";
            if (inputValues[0].equals("RegisterUser")) {
                String email = inputValues[1];
                String password = inputValues[2];
                String confirmPassword = inputValues[3];
                String fullName = inputValues[4];
                UserRegisterDTO registerUserDTO = new UserRegisterDTO(email, password, confirmPassword, fullName);
                command = this.userService.registerUser(registerUserDTO);
            } else if (inputValues[0].equals("LoginUser")) {
                String email = inputValues[1];
                String password = inputValues[2];
                LoginUserDTO loginUserDTO = new LoginUserDTO(email, password);
                command = this.userService.loginUser(loginUserDTO);
            } else if (inputValues[0].equals("Logout")) {
                command = this.userService.logout();
            } else if (inputValues[0].equals("AddGame")) {
                String title = inputValues[1];
                String trailer = inputValues[4];
                String thumbnail = inputValues[5];
                double size = Double.parseDouble(inputValues[3]);;
                double price = Double.parseDouble(inputValues[2]);
                String description = inputValues[6];
                LocalDate releaseDate = LocalDate.parse(inputValues[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                GameAddDTO gameAddDTO = new GameAddDTO(title, trailer, thumbnail, size, price, description, releaseDate);
                command = this.gameService.addGame(gameAddDTO);
            } else if (inputValues[0].equals("EditGame")) {
                long id = Long.parseLong(inputValues[1]);
                Map<String, String> map = Arrays.stream(inputValues).skip(2)
                        .map(value -> value.split("="))
                        .collect(Collectors.toMap(value -> value[0], value -> value[1]));
                command = this.gameService.editGame(id, map);
            } else if (inputValues[0].equals("DeleteGame")) {
                long id = Long.parseLong(inputValues[1]);
                command = this.gameService.deleteGame(id);
            } else if (inputValues[0].equals("AllGames")) {
                command = this.gameService.getAllGames();
            } else if (inputValues[0].equals("DetailGame")) {
                String title = inputValues[1];
                command = this.gameService.getDetailsForGameWithGivenTitle(title);
            } else if (inputValues[0].equals("OwnedGames")) {
                command = this.gameService.getOwnedGames();
            } else if (inputValues[0].equals("AddItem")) {
                String title = inputValues[1];
                CartItemDTO cartItemDTO = new CartItemDTO(title);
                command = this.shoppingCartService.addItem(cartItemDTO);
            } else if (inputValues[0].equals("RemoveItem")) {
                String title = inputValues[1];
                CartItemDTO cartItemDTO = new CartItemDTO(title);
                command = this.shoppingCartService.deleteItem(cartItemDTO);
            } else if (inputValues[0].equals("BuyItem")) {
                command = this.shoppingCartService.buyItem();
            }
            System.out.printf("%s%n", command);

            input = scanner.nextLine();
        }

    }
}
