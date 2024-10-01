package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.service.IngredientService;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._1Lab.com.example.advquerying.service.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public CommandLineRunnerImpl(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

            // 1st exercise
                // inputs :
                    // MEDIUM
        String sizeForEx1 = scanner.nextLine();
        this.shampooService.getAllShampooByGivenSize(sizeForEx1)
                .forEach(shampoo -> System.out.printf("%s%n", shampoo));

            // 2nd exercise
                // inputs :
                    // MEDIUM
                    // 10
        String sizeForEx2 = scanner.nextLine();
        long id = Long.parseLong(scanner.nextLine());
        this.shampooService.getAllShampooByGivenSizeOrLabel(sizeForEx2, id)
                .forEach(shampoo -> System.out.printf("%s%n", shampoo));

            // 3rd exercise
                // inputs :
                    // 5
        BigDecimal priceForEx3 = new BigDecimal(scanner.nextLine());
        this.shampooService.getAllShampooWithPriceHigherThan(priceForEx3)
                .forEach(shampoo -> System.out.printf("%s%n", shampoo));

            // 4th exercise
                // inputs :
                    // M
        String letters = scanner.nextLine();
        this.ingredientService.getAllIngredientStartWithLetters(letters)
                .forEach(ingredient -> System.out.printf("%s%n", ingredient));

            // 5th exercise
                // inputs :
                    // Lavender
                    // Herbs
                    // Apple
        List<String> namesForEx5 = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String ingredientName = scanner.nextLine();
            if (ingredientName.isEmpty()) {
                break;
            }
            namesForEx5.add(ingredientName);
        }
        this.ingredientService.getAllContainingIngredient(namesForEx5)
                .forEach(ingredient -> System.out.printf("%s%n", ingredient));


            // 6th exercise
                // inputs :
                    // 8.50
        BigDecimal priceForEx6 = new BigDecimal(scanner.nextLine());
        long count = this.shampooService.getCountOfAllShampooWithPriceLowerThan(priceForEx6);
        System.out.printf("%d%n", count);


            // 7th exercise
                // inputs:
                    // Berry
                    // Mineral-Collagen
        List<String> namesForEx7 = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String ingredientName = scanner.nextLine();
            if (ingredientName.isEmpty()) {
                break;
            }
            namesForEx7.add(ingredientName);
        }
        this.shampooService.getAllShampoosContainingIngredient(namesForEx7)
                .forEach(shampoo -> System.out.printf("%s%n", shampoo));


        // 8th exercise
            // inputs:
                // 2
        int number = Integer.parseInt(scanner.nextLine());
        this.shampooService.getAllShampooWithCountOfIngredientsBelowNumber(number)
                .forEach(shampoo -> System.out.printf("%s%n", shampoo));

        // 9th exercise
            // inputs:
                // Nettle
        String name = scanner.nextLine();
        int numberOfDeleteIngredients = this.ingredientService.deleteIngredientByName(name);
        System.out.printf("%d%n", numberOfDeleteIngredients);

        // 10th exercise
            // inputs:
                // 10
        double percentForEx10 = Double.parseDouble(scanner.nextLine());
        int numberOfUpdateIngredients = this.ingredientService.updateAllIngredientsPrices(percentForEx10);
        System.out.printf("%d%n", numberOfUpdateIngredients);

        // 11th exercise
            // inputs:
                // 10
                // Apple
                // Macadamia Oil
        double percentForEx11 = Double.parseDouble(scanner.nextLine());
        List<String> namesForEx11 = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String ingredientName = scanner.nextLine();
            if (ingredientName.isEmpty()) {
                break;
            }
            namesForEx11.add(ingredientName);
        }
        int numberOfUpdateIngredientsByNames = this.ingredientService.updateAllIngredientsPricesForGivenNames(percentForEx11, namesForEx11);
        System.out.printf("%d%n", numberOfUpdateIngredientsByNames);



    }
}
