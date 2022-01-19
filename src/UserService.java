import java.sql.*;
import java.sql.Driver;
//import "com.mysql.jdbc.Driver";
public class UserService {
    private Connection conn;
    private Statement stmt;
    private Statement pstmt;


    {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_users", "root","bestcoder123@!");
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void insertUser(int uId, String names, float amount) throws SQLException {
        String sqlInsertQuery = "insert into users (uId, names, amount) values(?,?,?)";
        PreparedStatement psmt = conn.prepareStatement(sqlInsertQuery);
        psmt.setInt(1, uId);
        psmt.setString(2, names);
        psmt.setFloat(3, amount);
        boolean rowAffected = psmt.execute();
        if(rowAffected){
            System.out.println("Added new record successfully!");
        }

    }

}
