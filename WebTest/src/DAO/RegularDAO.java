package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegularDAO {
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
	public String getTime(DTO.regularDTO regDto) {
		String time = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT end_time FROM regular WHERE car_num = ? ORDER BY end_time DESC LIMIT 1";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, regDto.getCar_num());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//DTO.regularDTO regDto = new DTO.regularDTO();
				regDto.setEnd_time(rs.getString(1));
				
				time = regDto.getEnd_time();
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
		return time;
	}
	
    public boolean insert(DTO.regularDTO regDto ) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "INSERT INTO regular (car_num, end_time, rec_id) VALUES (?, DATE_ADD(now(), INTERVAL 30 DAY), ?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, regDto.getCar_num());
            pstmt.setString(2, regDto.getRec_id());
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
