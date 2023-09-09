package pet;



import owner.Owner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PetController {

    private final PetRepository petRepository;

    public PetController() {
        this.petRepository = new PetRepository();
    }

    public void createPet() {
        try {
            Pet pet = this.collectPetInfo();

            if (this.petRepository.createPet(pet) == null) {
                throw new Exception("Creating pet with name " + pet.getPetName() + " failed");
            }
            System.out.println("Pet created successfully");

        } catch (Exception e) {
            System.out.println("Error while creating pet: " + e.getMessage());
        }
    }

    private Pet collectPetInfo() {
        Pet pet = new Pet();
        pet.setPetName(this.getUserInput("Enter pet name: "));
        pet.setAge(Integer.parseInt(this.getUserInput("Enter age of the pet: ")));
        pet.setWeight(Float.parseFloat(this.getUserInput("Enter weight of the pet: ")));
        pet.setPetTypeId(Integer.parseInt(this.getUserInput("Enter ID of the Pet Type: ")));
        pet.setOwnerId(Integer.parseInt(this.getUserInput("Enter ID of the Owner: ")));

        return pet;
    }

    private String getUserInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    public void displayPets(ArrayList<Pet> pets) {
        try {
            System.out.println("==== List of Pets (ID, pet name, age, weight, pet type ID, owner ID) ====");
            if (pets == null) pets = this.petRepository.findAllPets();
            if (pets.isEmpty()) System.out.println("No pets to display at this time");
            pets.forEach(pet -> System.out.println(pet.getId() + " - " + pet.getPetName() + " - " + pet.getAge()
            + " - " + pet.getWeight() + " - " + pet.getPetTypeId() + " - " + pet.getOwnerId()));

        } catch (Exception e) {
            System.out.println("Unable to display pets at this time: " + e.getMessage());
        }
    }

    public void findPet() {
        try {
            int idOfPetToFind = Integer.parseInt(this.getUserInput("Please enter the pet id"));
            Pet pet = this.petRepository.findPetById(idOfPetToFind);
            System.out.println(pet);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void filterPet() {
        try {
            String filterBy = this.getPetFilterByChoice();
            String informationToFind = this.getUserInput("Enter the info to search: e.g. dino");

            ArrayList<Pet> foundPets = this.petRepository.filterPetsBy(filterBy, informationToFind);

            this.displayPets(foundPets);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private String getPetFilterByChoice() {
        Map<String, String> filterOptions = new HashMap<>();
        filterOptions.put("1", "petName");
        filterOptions.put("2", "age");
        filterOptions.put("3", "weight");

        String filterBy = this.getUserInput("""
                    Enter the field to filter with:
                    1. for name
                    2. for age
                    3. for weight
                   """

        );
        return filterOptions.get(filterBy);
    }

    public void updatePet() {
        try {
            int idOfPetToUpdate = Integer.parseInt(this.getUserInput("Please enter the ID of the pet you want to update: "));
            Pet existingPet = this.petRepository.findPetById(idOfPetToUpdate);

            if (existingPet == null) {
                System.out.println("Pet not found with ID: " + idOfPetToUpdate);
            }

            Pet updatedPet = this.collectPetInfo();
            updatedPet.setId(idOfPetToUpdate);

            Pet updatedPetFromDb = this.petRepository.updatePet(updatedPet);

            System.out.println("Pet updated successfully");
            System.out.println(updatedPetFromDb);

        } catch (Exception exception) {
            System.out.println("Error while updating pet: " + exception.getMessage());
        }
    }

    public void deletePet() {
        try {
            int idOfPetToDelete = Integer.parseInt(this.getUserInput("Please enter the ID of the pet you want to delete: "));
            this.petRepository.deletePet(idOfPetToDelete);

            System.out.println("Pet with ID " + idOfPetToDelete + " has been deleted successfully");
        } catch (Exception exception) {
            System.out.println("Error while deleting pet: " + exception.getMessage());
        }
    }
}
