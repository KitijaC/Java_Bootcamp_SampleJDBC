import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.showMenu();
    }

    private void showMenu() {
        System.out.println("""
            Welcome to pet manager app, Please choose menu option:
            1. Pet options
            2. Owner options
            9. Exit
            """);

        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        System.out.println(this.getMenuText(userChoice));
        userChoice = scanner.nextLine();

        if (userChoice.equals("9")) System.exit(0);
        if (userChoice == "2") this.handleOwnerChoice(userChoice);
        else this.handlePetChoice(userChoice);

        this.showMenu();
    }

    private String getMenuText(String userChoice) {
        String menuText = "Please choose what you would like to do \n";

        if (Objects.equals(userChoice, "1")) menuText += this.getOwnerMenu();
        else if (Objects.equals(userChoice, "2")) menuText += this.getPetMenu();
        else System.out.println("Please choose one of the options from above");

        menuText += """
                8. Back to main menu
                9. Exit
                """;
        return menuText;
    }

    private void handlePetChoice(String userChoice) {
        switch (userChoice) {
            case "1":
                // create pet here
                break;
            case "2":
                // show all pets here
                break;
            case "3":
                // find pet
                break;
            case "4":
                // filter pets
                break;
            case "5":
                // update pet
                break;
            case "6":
                // delete pet
                break;
        }
    }

    private void handleOwnerChoice(String userChoice) {

    }

    private String getOwnerMenu() {
        return """
                1. create owner
                2. show all owners
                3. find owner
                4. filter owners
                5. update owner
                6. delete owner
                """;
    }

    private String getPetMenu() {
        return """
                1. create pet
                2. show all pets
                3. find pet
                4. filter pets
                5. update pet
                6. delete pet
                """;
    }
}
