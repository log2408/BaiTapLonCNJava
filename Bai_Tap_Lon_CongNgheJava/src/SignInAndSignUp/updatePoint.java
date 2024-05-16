package SignInAndSignUp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class updatePoint {
	public void updatePoint(String name, long max) {
		String url = "jdbc:mySQL://localhost:3306/playergame";
		String user = "log2408";
		String password = "2408";
	    Connection con = null;
	    ResultSet rs = null;
	    PreparedStatement ps = null;
	    try {
			con = DriverManager.getConnection(url, user, password);
			String sql = "UPDATE game SET maxpoint = ? WHERE username = ?";
	        ps = con.prepareStatement(sql);
	        ps.setLong(1, max); 
            ps.setString(2, name); 
            ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	public void update(String name, long max) {
		this.updatePoint(name, max);
	}
}
