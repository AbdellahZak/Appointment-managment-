package AbdoC195.DB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DbHelper {
    public static int insert(String userID, String userPassword) throws SQLException {
        String sql = "INSERT INTO USERS (User_Name, Password) VALUES (?,?)";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setString(1,userID);
        ps.setString(2,userPassword);
        int rowsAffected =ps.executeUpdate();
        return rowsAffected;
    }
}
