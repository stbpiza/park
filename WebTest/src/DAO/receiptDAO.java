package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class receiptDAO {
	private Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/park_test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            conn = DriverManager.getConnection(url, "root", "");
        }
        catch (ClassNotFoundException e) {
            System.out.println(" 드라이버 로딩 실패 ");
        }

        return conn;
    }
	
    public boolean insert(DTO.receiptDTO recDto ) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "INSERT INTO receipt (pay_time, pay_price, regular_non, gate_id) VALUES (now(), ?, ?, ?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, recDto.getPay_price());
            pstmt.setString(2, recDto.getRegular_non());
            pstmt.setInt(3, Integer.parseInt(recDto.getGate_id()));
            int count = pstmt.executeUpdate();

            result = (count == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( conn != null ) {
                    conn.close();
                }
                if( pstmt != null ) {
                    pstmt.close();
                }
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
