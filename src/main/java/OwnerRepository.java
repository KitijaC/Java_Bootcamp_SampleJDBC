import entity.Owner;

import java.sql.*;
import java.util.ArrayList;

public class OwnerRepository {
    private String dbUsername;
    private String dbPassword;
    private Connection connection;
    private String url;

    public OwnerRepository() {
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

    public Owner createOwner(Owner owner) throws SQLException, OwnerRepositoryActionFailedException {

        String query = "INSERT INTO owners(ownerName, age, email) VALUES(?,?,?)";

        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, owner.getOwnerName());
        statement.setInt(2, owner.getAge());
        statement.setString(3, owner.getEmail());

        statement.executeUpdate();

        ResultSet result = statement.getGeneratedKeys();

        if (result.next()) {
            int ownerId = result.getInt(1);

            return this.getOwnerById(ownerId);
        }

        throw new OwnerRepositoryActionFailedException("Problem occurred while adding new owner with name: " + owner.getOwnerName());
    }

    public Owner getOwnerById(int ownerId) throws SQLException, OwnerRepositoryActionFailedException {

        String query = "SELECT * FROM owners WHERE owners.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ownerId);
        ResultSet result = preparedStatement.executeQuery(); // because we get back some results, we use executeQuery

        if (result.next()) {
            return this.convertResultSetToOwner(result);
        }

        throw new OwnerRepositoryActionFailedException("Unable to find owner with id " + ownerId);
    }

    public ArrayList<Owner> getAllOwners() throws SQLException {
        ArrayList<Owner> owners = new ArrayList<>();

        String query = "SELECT * FROM owners";

        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            owners.add(this.convertResultSetToOwner(resultSet));
        }
        return owners;
    }

    private Owner convertResultSetToOwner(ResultSet resultSet) throws SQLException {
        return new Owner(
                resultSet.getInt("id"),
                resultSet.getString("ownerName"),
                resultSet.getInt("age"),
                resultSet.getString("email"),
                resultSet.getTimestamp("createdAt"),
                resultSet.getTimestamp("lastUpdated")
        );
    }
}
