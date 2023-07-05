package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	//회원 등록하기(전화번호로 만드는게 제일 적합한거같아서 전화번호로)
	public int RegistrationCust(String custName,String custPhone) throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "INSERT INTO CUSTOMER VALUES(cusseq.nextval,?,?,0)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, custName);
			ps.setString(2, custPhone);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("회원등록 예외 : " + e.getMessage());
			System.out.println("회원등록을 취소합니다");
			try {
				conn.rollback();
			}catch(SQLException e1) {
				
			}
			}
				finally {
			        if (ps != null) {
			            ps.close();
			        }
			        if (conn != null) {
			            conn.close();
			        }
			    }
			    
			    return result;
	}
	//포인트 적립
	public void pointupdate(int custNo,int custPoint) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = OracleUtility.getConnection();
			String sql = "UPDATE CUSTOMER SET CUSTPOINT = CUSTPOINT + ? WHERE CUSTNO = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, custPoint);
			ps.setInt(2, custNo);
			
			ps.executeUpdate();
		}finally {
		        if (ps != null) {
		            ps.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    }
		    
	}
	public List<CustomerDTO> selectAll() throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from customer";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<CustomerDTO> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new CustomerDTO(rs.getInt(1), 
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4)));
		}
		ps.close();
		conn.close();
		return list;
	}
	public int update(CustomerDTO cdto) throws SQLException{
		Connection conn = OracleUtility.getConnection();
		String sql = "update customer set custname = ?, custphon = ?, custpoint = ? where custno = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cdto.custName);
		ps.setString(2, cdto.custPhone);
		ps.setInt(3, cdto.custPoint);
		ps.setInt(4, cdto.custNo);
		int result = ps.executeUpdate();
		
		ps.close();
		conn.close();
		return result;
	}
}


































