import entity.Owner;

import java.sql.*;

public class SampleWorkingWithOwnerTable {

    public Owner createOwner(Owner owner) {
        try {
            // create a connection from java to mysql by providing username and password and url
            String url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            String username = "Java_35_36";
            String password = "valdemarpils2606";
            Connection connection = DriverManager.getConnection(url, username, password);

            // create the sql command
            String query = "INSERT INTO owners(ownerName, age, email) VALUES(?,?,?)";
            // using prepared statement, we can fill in the owner information using the set methods
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, owner.getOwnerName());
            statement.setInt(2, owner.getAge());
            statement.setString(3, owner.getEmail());

            // execute the command
            statement.executeUpdate();

            // select the last created id
            ResultSet result = statement.getGeneratedKeys();
            // we are asking the next available information what was returned by our query
            if (result.next()) {
                int ownerId = result.getInt(1);
                // get the last created owner object by the id
                return this.getOwnerById(ownerId);
            } else {
                throw new Exception("Unable to find newly added owner");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());;
        }
        return null;
    }

    private Owner getOwnerById(int ownerId) {
        try {
            String url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            String username = "Java_35_36";
            String password = "valdemarpils2606";
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM owners WHERE owners.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ownerId);
            ResultSet result = preparedStatement.executeQuery(); // because we get back some results, we use executeQuery

            // process the result
            if (result.next()) {
                return new Owner(
                    result.getInt("id"),
                    result.getString("ownerName"),
                    result.getInt("age"),
                    result.getString("email"),
                    result.getTimestamp("createdAt"),
                    result.getTimestamp("lastUpdated")
                );
            } else {
                throw new Exception("Unable to find owner with id " + ownerId);
            }

            // return the result as java objects
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        return null;
    }
}
