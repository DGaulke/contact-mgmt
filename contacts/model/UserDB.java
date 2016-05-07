package contacts.model;

import java.sql.*;
import java.util.*;
import contacts.util.*;
import com.mysql.jdbc.exceptions.jdbc4.*;
public class UserDB {

	public static int insert(User user) 
			throws MySQLIntegrityConstraintViolationException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		try {
			int addressId = getAddressId(connection, ps, 
					user.getStreetAddress(), user.getCity(), user.getState(), 
					user.getZip());

			String query = "insert into user (first_name, last_name, address_id, " +
					" username, password, salt) " +
					"values (?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setInt(3, addressId);
			ps.setString(4, user.getUserName());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getSalt());
			return ps.executeUpdate();

		} catch (MySQLIntegrityConstraintViolationException ve){
			throw ve;

		} catch (SQLException sqle){
			sqle.printStackTrace();
			return 0;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}
	
	private static int getAddressId(Connection cn, PreparedStatement ps,
			String streetAddress, String city, String state, String zip) 
			throws SQLException {
		ResultSet rs = null;

		String query = "select address_id from address where " +
				"street_address = ? and city = ? and state_abrv = ? and zip = ?;";
		ps = cn.prepareStatement(query);
		ps.setString(1, streetAddress);
		ps.setString(2, city);
		ps.setString(3, state);
		ps.setString(4, zip);
		rs = ps.executeQuery();

		if (rs.next())
			return rs.getInt("address_id");

		query = "insert into address (street_address, city, state_abrv, zip) " +
				"values (?, ?, ?, ?);";

		ps = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, streetAddress);
		ps.setString(2, city);
		ps.setString(3, state);
		ps.setString(4, zip);
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
		if (rs.next())
			return rs.getInt(1);
			
		return 0;
	}
	public static User[] loadAll(){
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "select u.user_id, u.username, u.first_name, " +
					"u.last_name, a.street_address, a.city, " +
					"a.state_abrv, a.zip " + 
					"from user u " + 
					"inner join address a " + 
					"on u.address_id = a.address_id";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while(rs.next()){
				User u = new User();
				u.setUserId(rs.getInt("user_id"));
				u.setUserName(rs.getString("username"));
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));
				u.setStreetAddress(rs.getString("street_address"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state_abrv"));
				u.setZip(rs.getString("zip"));
				users.add(u);
			}
			User[] userArray = new User[users.size()];
			return users.toArray(userArray);

		} catch (SQLException sqle){
			sqle.printStackTrace();
			return null;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}

	public static User load(String userName){
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		try {
			String query = "select user_id, username, salt, password " +
				"from user " +
				"where username = ?;";
			ps = connection.prepareStatement(query);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (!rs.next())
				return null;

			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setUserName(rs.getString("username"));
			user.setSalt(rs.getString("salt"));
			user.setPassword2(rs.getString("password"));
			return user;
		} catch (SQLException sqle){
			sqle.printStackTrace();
			return null;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
		
	}
	
}
