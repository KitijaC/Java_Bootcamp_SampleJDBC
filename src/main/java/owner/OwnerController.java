package owner;

import pet.PetActionFailedException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OwnerController {
    private final OwnerRepository ownerRepository;

    public OwnerController() {
        this.ownerRepository = new OwnerRepository();
    }

    public void createOwner() {
        try {
            Owner owner = this.collectOwnerInfo();

            if (this.ownerRepository.createOwner(owner) == null) {
                throw new Exception("Creating owner with name " + owner.getOwnerName() + " failed");
            }
            System.out.println("Owner created successfully");

        } catch (Exception e) {
            System.out.println("Error while creating user: " + e.getMessage());
        }

    }
    private Owner collectOwnerInfo() {
        Owner owner = new Owner();
        owner.setOwnerName(this.getUserInput("Enter your name: "));
        owner.setEmail(this.getUserInput("Enter your email: "));
        owner.setAge(Integer.parseInt(this.getUserInput("Enter your age: ")));
        return owner;
    }

    private String getUserInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    public void displayOwners(ArrayList<Owner> owners) {
        try {
            System.out.println("==== List of Owners (ID, Name, email) ====");
            if (owners == null) owners = this.ownerRepository.getAllOwners();
            if (owners.isEmpty()) System.out.println("No owners to display at this time");
            owners.forEach(owner -> System.out.println(owner.getId() + " - " + owner.getOwnerName() + " - " + owner.getEmail()));

        } catch (Exception e) {
            System.out.println("Unable to display owners at this time: " + e.getMessage());
        }
    }

    public void findOwner() {
        try {
            String ownerIdOrEmail = this.getUserInput("Please enter the owner id or email");
            Owner owner = this.ownerRepository.findOwnerByIdOrEmail(ownerIdOrEmail);
            System.out.println(owner);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void filterOwner() {
        try {
            String filterBy = this.getUserFilterByChoice();
            String informationToFind = this.getUserInput("Enter the info to search: e.g. zino");

            ArrayList<Owner> foundOwners = this.ownerRepository.filterOwnersBy(filterBy, informationToFind);

            this.displayOwners(foundOwners);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private String getUserFilterByChoice() {
        Map<String, String> filterOptions = new HashMap<>();
        filterOptions.put("1", "ownerName");
        filterOptions.put("2", "email");
        filterOptions.put("3", "age");

        String filterBy = this.getUserInput("""
                    Enter the field to filter with:
                    1. for name
                    2. for email
                    3. for age
                   """

        );
        return filterOptions.get(filterBy);
    }

    public void updateOwner() {
        try {
            int idOfOwnerToUpdate = Integer.parseInt(this.getUserInput("Please enter the ID of the owner you want to update: "));
            Owner existingOwner = this.ownerRepository.getOwnerById(idOfOwnerToUpdate);

            if (existingOwner == null) {
                System.out.println("Owner not found with ID: " + idOfOwnerToUpdate);
            } else {
                Owner updatedOwner = this.collectOwnerInfo();
                updatedOwner.setId(idOfOwnerToUpdate);

                this.ownerRepository.updateOwner(updatedOwner);

                System.out.println("Owner updated successfully");
            }
        } catch (OwnerRepositoryActionFailedException exception) {
            System.out.println("Error while updating owner: " + exception.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input or unexpected error occurred");
        }
    }

    public void deleteOwner() {
        try {
            int id = Integer.parseInt(this.getUserInput("Please enter the ID of the owner you want to delete: "));
            this.ownerRepository.deleteOwner(id);

            System.out.println("Owner with ID " + id + " has been deleted successfully");

        } catch (Exception exception) {
            System.out.println("Error while deleting owner: " + exception.getMessage());
        }
    }
}
