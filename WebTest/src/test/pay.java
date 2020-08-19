package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import DTO.gateDTO;

public class pay {
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
	
    public boolean select(gateDTO dto ) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Date regularTime ;
        Date now = new Date();

        try {
            conn = getConnection();

            String sql = "SELECT end_time FROM regular WHERE car_num = ? ORDER BY end_time DESC LIMIT 1;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getCar_num());

            while(rs.next()) {
            	regularTime = rs.getDate("end_time");
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
        
        
    }
    
}
