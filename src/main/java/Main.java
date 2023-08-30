import entity.Owner;

public class Main {
    public static void main(String[] args) {
        SampleWorkingWithOwnerTable sampleWorkingWithOwnerTable = new SampleWorkingWithOwnerTable();

        Owner createdOwner = sampleWorkingWithOwnerTable.createOwner(new Owner("Zino", 2, "zino@home.com"));

        System.out.println(sampleWorkingWithOwnerTable.createOwner(new Owner("Sam", 94, "sam@home.com")));
        System.out.println(sampleWorkingWithOwnerTable.createOwner(new Owner("Juliet", 45, "juliet@home.com")));

        System.out.println(createdOwner);

    }
}
