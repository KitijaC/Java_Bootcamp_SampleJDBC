package pet;

import owner.Owner;

import java.sql.*;
import java.util.ArrayList;

public class PetRepository {
    private Connection connection;
    private String url;
    private String dbUsername;
    private String dbPassword;


    public PetRepository() {
        this.initializeConnection();
    }

    private void initializeConnection() {
        try {
            this.dbUsername = "Java_35_36";
            this.dbPassword = "valdemarpils2606";
            this.url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            this.connection = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public Pet createPet(Pet petToCreate) throws SQLException, PetActionFailedException {
        String query = "INSERT INTO pets(petName, age, weight, petTypeId, ownerId) VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, petToCreate.getPetName());
        preparedStatement.setInt(2, petToCreate.getAge());
        preparedStatement.setFloat(3, petToCreate.getWeight());
        preparedStatement.setInt(4, petToCreate.getPetTypeId());
        preparedStatement.setInt(5, petToCreate.getOwnerId());

        preparedStatement.executeUpdate(); // this does the command to insert the data

        ResultSet result = preparedStatement.getGeneratedKeys();
//        if (result.next()) return this.findPetById(result.getInt("id"));
        if (result.next()) {
            int generatedId = result.getInt(1);
            return this.findPetById(generatedId);
        }

        throw new PetActionFailedException("Problem creating new pet");
    }

    public Pet findPetById(int idOfPetToFind) throws SQLException, PetActionFailedException {
        String query =  "SELECT * FROM pets WHERE id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1, idOfPetToFind);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) return this.convertResultSetToPetObject(resultSet);

        throw new PetActionFailedException("Pet not found");
    }

    public ArrayList<Pet> findAllPets() throws SQLException {
        String query =  "SELECT * FROM pets";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Pet> pets = new ArrayList<>();
        while (resultSet.next()) pets.add(this.convertResultSetToPetObject(resultSet));

        return pets;
    }

    public Pet updatePet(Pet petToUpdate) throws SQLException, PetActionFailedException {
        String query = "UPDATE pets SET petName=?, age=?, weight=?, petTypeId=?, ownerId=? WHERE id=?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, petToUpdate.getPetName());
        preparedStatement.setInt(2, petToUpdate.getAge());
        preparedStatement.setFloat(3, petToUpdate.getWeight());
        preparedStatement.setInt(4, petToUpdate.getPetTypeId());
        preparedStatement.setInt(5, petToUpdate.getOwnerId());
        preparedStatement.setInt(6, petToUpdate.getId());

        preparedStatement.executeUpdate(); // this does the command to insert the data

        ResultSet result = preparedStatement.getGeneratedKeys();
        if (result.next()) {
            return this.findPetById(result.getInt("id"));
        }

        throw new PetActionFailedException("Problem updating pet with id " + petToUpdate.getId());
    }

    private Pet convertResultSetToPetObject(ResultSet resultSet) throws SQLException {
        return new Pet(
                resultSet.getInt("id"),
                resultSet.getString("petName"),
                resultSet.getInt("age"),
                resultSet.getFloat("weight"),
                resultSet.getInt("ownerId"),
                resultSet.getInt("petTypeId"),
                resultSet.getTimestamp("createdAt"),
                resultSet.getTimestamp("lastUpdated")
        );
    }

    public ArrayList<Pet> filterPetsBy(String filterBy, String informationToFind) throws SQLException {
        ArrayList<Pet> pets = new ArrayList<>();

        String query = "SELECT * FROM pets WHERE " + filterBy + " LIKE '%" + informationToFind + "%'";

        System.out.println(query);

        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pets.add(this.convertResultSetToPetObject(resultSet));
        }
        return pets;
    }
}
