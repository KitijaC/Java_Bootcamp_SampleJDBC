import entity.Owner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SampleWorkingWithOwnerTable {

    public Owner createOwner(Owner owner) {
        try {
            // create a connection from java to mysql by providing username and password and url
            String url = "jdbc:mysql://localhost:3306/java_35_36_pet_manager";
            String username = "java_35_36";
            String password = "valdemarpils";
            Connection connection = DriverManager.getConnection(url, username, password);

            // create the sql command
            String query = "INSERT INTO owners(ownerName, age, email) VALUES(?,?,?)";
            // using prepared statement, we can fill in the owner information using the set methods
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, owner.getOwnerName());
            statement.setInt(2, owner.getAge());
            statement.setString(3, owner.getEmail());

            // execute the command
            statement.executeUpdate();

            // select the last created id

            // get the last created owner object out

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }
}
