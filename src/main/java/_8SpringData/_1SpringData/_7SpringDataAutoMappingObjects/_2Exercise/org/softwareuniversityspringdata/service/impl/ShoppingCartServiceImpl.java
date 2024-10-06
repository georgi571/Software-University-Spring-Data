package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.entities.Game;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.entities.Order;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.repositories.GameRepository;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.repositories.OrderRepository;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.repositories.UserRepository;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.ShoppingCartService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.UserService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.CartItemDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final OrderRepository orderRepository;
    private Set<Game> cart;

    public ShoppingCartServiceImpl(UserService userService, UserRepository userRepository, GameRepository gameRepository, OrderRepository orderRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
        this.cart = new HashSet<>();
    }

    @Override
    public String addItem(CartItemDTO item) {
        User user = this.userService.getLoggedIn();
        if (user == null) {
            return "No user is logged in";
        }

        Optional<Game> optionalGame = this.gameRepository.findByTitle(item.getTitle());
        if (optionalGame.isEmpty()) {
            return "No such game exists";
        }

        Game game = optionalGame.get();
        this.cart.add(game);
        return String.format("%s added to cart", game.getTitle());

    }

    @Override
    public String deleteItem(CartItemDTO item) {
        User user = this.userService.getLoggedIn();
        if (user == null) {
            return "No user is logged in";
        }

        Optional<Game> optionalGame = this.gameRepository.findByTitle(item.getTitle());
        if (optionalGame.isEmpty()) {
            return "No such game exists";
        }
        Game game = optionalGame.get();
        this.cart.remove(game);
        return String.format("%s removed from cart", game.getTitle());
    }

    @Override
    public String buyItem() {
        User user = this.userService.getLoggedIn();
        if (user == null) {
            return "No user is logged in";
        }

        user.getGames().addAll(this.cart);
        this.userRepository.saveAndFlush(user);
        Order order = new Order(LocalDateTime.now(), user, this.cart);
        this.orderRepository.saveAndFlush(order);

        String output = String.format("Successfully bought games:%n%s", this.cart.stream().map(game -> String.format(" -%s", game.getTitle())).collect(Collectors.joining("\n")));
        this.cart = new HashSet<>();
        return output;
    }
}
