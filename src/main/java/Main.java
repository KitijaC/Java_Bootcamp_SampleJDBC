import owner.OwnerController;
import pet.PetController;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private OwnerController ownerController = new OwnerController();
    private PetController petController = new PetController();
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
        String mainMenuChoice = scanner.nextLine();

        if (mainMenuChoice.equals("9")) System.exit(0);

        System.out.println(this.getMenuText(mainMenuChoice));
        String subMenuChoice = scanner.nextLine();

        if (mainMenuChoice.equals("9")) System.exit(0);

        if (Objects.equals(mainMenuChoice,"2"))
            this.handleOwnerChoice(subMenuChoice);
        else
            this.handlePetChoice(subMenuChoice);

        this.showMenu();
    }

    private String getMenuText(String userChoice) {
        String menuText = "Please choose what you would like to do \n";

        if (Objects.equals(userChoice, "1")) menuText += this.getPetMenu();
        else if (Objects.equals(userChoice, "2")) menuText += this.getOwnerMenu();
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
                this.petController.createPet();
                break;
            case "2":
                this.petController.displayPets(null);
                break;
            case "3":
                this.petController.findPet();
                break;
            case "4":
                this.petController.filterPet();
                break;
            case "5":
                this.petController.updatePet();
                break;
            case "6":
                this.petController.deletePet();
                break;
        }
    }

    private void handleOwnerChoice(String userChoice) {
        switch (userChoice) {
            case "1":
                this.ownerController.createOwner();
                break;
            case "2":
                this.ownerController.displayOwners(null);
                break;
            case "3":
                this.ownerController.findOwner();
                break;
            case "4":
                this.ownerController.filterOwner();
                break;
            case "5":
                this.ownerController.updateOwner();
                break;
            case "6":
                this.ownerController.deleteOwner();
                break;
        }
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
