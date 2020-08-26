package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.gateDTO;

public class gateDAO {
	DAO.dbHandler dbH = new DAO.dbHandler();
	    public boolean inputInCarLog(gateDTO dto ) {
	        boolean result = false;
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = dbH.getConnection();

	            String sql = "INSERT INTO gate (car_num, time, in_out) VALUES (?, now(), 1);";
	            pstmt = conn.prepareStatement(sql);

	            pstmt.setString(1, dto.getCar_num());
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
	    
	    public boolean inputOutCarLog(gateDTO dto ) {
	        boolean result = false;
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = dbH.getConnection();


	            String sql = "INSERT INTO gate (car_num, time, in_out) VALUES (?, now(), 0);";
	            pstmt = conn.prepareStatement(sql);

	            pstmt.setString(1, dto.getCar_num());
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
	    
	    
		public String getId(DTO.gateDTO gateDto) {
			String gate_id = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = dbH.getConnection();
				
				String sql = "SELECT gate_id FROM gate WHERE car_num = ? AND in_out = 0 ORDER BY time DESC LIMIT 1";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, gateDto.getCar_num());
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					gateDto.setGate_id(rs.getString(1));
					
					gate_id = gateDto.getGate_id();
				}
			}
			catch (SQLException e){
				e.printStackTrace();
			}
			return gate_id;
		}
		
		public String getIn_out(DTO.gateDTO gateDto) {
			String in_out = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = dbH.getConnection();
				
				String sql = "SELECT in_out FROM gate WHERE car_num = ? ORDER BY time DESC LIMIT 1";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, gateDto.getCar_num());
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					gateDto.setIn_out(rs.getString(1));
					in_out = gateDto.getIn_out();
				}
			}
			catch (SQLException e){
				e.printStackTrace();
			}
			return in_out;
		}
		
		public String getTime(DTO.gateDTO gateDto, String in_out) {
			String Time = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = dbH.getConnection();
				
				String sql = "SELECT time FROM gate WHERE car_num = ? and in_out = ? ORDER BY time DESC LIMIT 1";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, gateDto.getCar_num());
				pstmt.setString(2, in_out);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					gateDto.setTime(rs.getString(1));
					Time = gateDto.getTime();
				}
			}
			catch (SQLException e){
				e.printStackTrace();
			}
			return Time;
		}
}
