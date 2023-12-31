package kioskDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kioskDTO.CustomerDto;
import kioskDTO.OracleUtility;

public class CustomerDAO2 {

private static CustomerDAO2 dao = new CustomerDAO2();
private CustomerDAO2() {}
public static CustomerDAO2 getDao() {
	return dao;
}
	
	
	public int insert(CustomerDto custom) throws SQLException {
		
		Connection conn = OracleUtility.getConnection();
		String sql = "insert into Customer values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, custom.getCustNo());
		ps.setString(2, custom.getCustName());
		ps.setString(3, custom.getCustPhone());
		ps.setInt(4, custom.getCustPoint());
		
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result;
	}
	
	   public int update(CustomerDto cdto) throws SQLException{
	       Connection conn = OracleUtility.getConnection();
	       String sql = "update customer set custname = ?, custpoint = ?, custphone = ? where CustNo = ?";
	       PreparedStatement ps = conn.prepareStatement(sql);
	       ps.setString(1, cdto.getCustName());
	       ps.setInt(2, cdto.getCustPoint());
	       ps.setString(3, cdto.getCustPhone());
	       ps.setInt(4, cdto.getCustNo());
	       int result = ps.executeUpdate();
	       
	       ps.close();
	       conn.close();
	       return result;
	   }

	
	public int delete(int custno) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "delete from Customer where CustNo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, custno);
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result;
	}
	
	public List<CustomerDto> customerslectall() throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from Customer ";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<CustomerDto> result =new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			result.add(new CustomerDto(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4)));
		
		
		}
		return result;
	
	}

}
