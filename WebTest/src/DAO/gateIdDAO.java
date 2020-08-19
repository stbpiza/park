package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class gateIdDAO {
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
	
	public String getId(String car_num) {
		String gate_id = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT time FROM gate WHERE car_num = ? AND in_out = 0 ORDER BY time DESC LIMIT 1";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, car_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DTO.gateDTO gateDto = new DTO.gateDTO();
				gateDto.setGate_id(rs.getString(1));
				
				gate_id = gateDto.getGate_id();
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
		return gate_id;
	}
}
