import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {
    String url = "jdbc:mysql://localhost:3306/textfile";
    String username = "root";
    String password = "password";
    Connection connection = null;

    public Connection settings() {
        if (connection == null);
        try {
            connection = DriverManager.getConnection(url, username, password);
        }catch(Exception e){

        }
        return connection;
    }
    public static void insert(String line) {
        PreparedStatement stmt=null;
        try {
            Query db = new Query();
            Connection conn = db.settings();
            stmt = conn.prepareStatement("insert into test(line)values(?)");
            stmt.setString(1, line);
            stmt.executeUpdate();
        }
        catch(Exception e){

        }
        finally {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
