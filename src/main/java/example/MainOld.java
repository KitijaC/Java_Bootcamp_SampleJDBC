package example;

import owner.Owner;

import java.util.ArrayList;

public class MainOld {
    public static void main(String[] args) {
        SampleWorkingWithOwnerTable sampleWorkingWithOwnerTable = new SampleWorkingWithOwnerTable();

        // creating item to database
        Owner createdOwner = sampleWorkingWithOwnerTable.createOwner(new Owner("Zino", 2, "zino@home.com"));
        System.out.println(createdOwner);
        System.out.println(sampleWorkingWithOwnerTable.createOwner(new Owner("Sam", 94, "sam@home.com")));
        System.out.println(sampleWorkingWithOwnerTable.createOwner(new Owner("Juliet", 45, "juliet@home.com")));

        // get single item out of database
        sampleWorkingWithOwnerTable.getOwnerById(20);
        sampleWorkingWithOwnerTable.getOwnerById(createdOwner.getId());

        // get all items out of database
        ArrayList<Owner> owners = sampleWorkingWithOwnerTable.getAllOwners();
        System.out.println(owners);

        // update items in database
        Owner ownerToUpdate = sampleWorkingWithOwnerTable.createOwner(new Owner("Zino", 2, "zino@home.com"));
        ownerToUpdate = owners.get(0);
        ownerToUpdate.setOwnerName("Updated Zino");
        ownerToUpdate.setAge(143);

        sampleWorkingWithOwnerTable.updateOwner(ownerToUpdate);
        System.out.println(
                "After update " +
                sampleWorkingWithOwnerTable.getOwnerById(ownerToUpdate.getId())
        );

        // delete item from database
        Owner ownerToDelete = sampleWorkingWithOwnerTable.createOwner(new Owner("owner that you will not see", 2, "zino@home.com"));
        sampleWorkingWithOwnerTable.deleteOwner(ownerToDelete.getId());
        // after deleting it should not exist in database anymore
        System.out.println("found owner after deleting with id: " + ownerToDelete.getId() + " " + sampleWorkingWithOwnerTable.getOwnerById(ownerToDelete.getId()));

        Owner anotherOwnerToDelete = sampleWorkingWithOwnerTable.getOwnerById(createdOwner.getId());
        sampleWorkingWithOwnerTable.deleteOwner(anotherOwnerToDelete.getId());

        // task: make CRUD for petTypes table
    }
}
