package contacts.model;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import contacts.util.*;

public class AnniversaryDB {

	public static void insert(Contact contact, Anniversary anniversary) 
			throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		int anniversaryTypeId = AnniversaryDB.insertAnniversaryType(
				anniversary.getAnniversaryType());

		try {
			String query = "insert into contact_anniversary " + 
				"(contact_id, date_val, anniversary_type_id) " +
				"values (?, ?, ?);";
			
			ps = connection.prepareStatement(query);
			ps.setInt(1, contact.getContactId());
			ps.setString(2, anniversary.getAnniversary());
			ps.setInt(3, anniversaryTypeId);
			ps.executeUpdate();
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}

	}

	private static int insertAnniversaryType(String anniversaryType) 
			throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
	
		try {
			String query = "select anniversary_type_id from anniversary_type " +
			   "where anniversary_type_desc = ?;";

			ps = connection.prepareStatement(query);
			ps.setString(1, anniversaryType);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt("anniversary_type_id");

			query = "insert into anniversary_type (anniversary_type_desc) " +
					"values (?);";

			ps = connection.prepareStatement(query, 
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, anniversaryType);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);
			throw new SQLException("Anniversary type insert unsuccessful.");
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	
	}
	

	public static Anniversary[] load(Contact contact) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		try {
			List<Anniversary> anniversaries = new ArrayList<Anniversary>();
			String query = "select ca.anniversary_id, " + 
					"at.anniversary_type_desc, ca.date_val " + 
					"from contact_anniversary ca " +
					"inner join anniversary_type at " + 
					"on ca.anniversary_type_id = at.anniversary_type_id " +
					"where ca.contact_id = ?;";
			ps = connection.prepareStatement(query);
			ps.setInt(1, contact.getContactId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Anniversary a = new Anniversary();
				a.setAnniversaryId(rs.getInt("anniversary_id"));
				a.setAnniversaryType(rs.getString("anniversary_type_desc"));
				a.setAnniversary(rs.getString("date_val"));
				anniversaries.add(a);
			}
			if (anniversaries.size() == 0)
				return null;
			Anniversary[] anniversaryArray = 
					new Anniversary[anniversaries.size()];
			return anniversaries.toArray(anniversaryArray);
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}
	
	 public static void delete(Contact contact) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		
		try {
			String query = "delete from contact_anniversary " +
					"where contact_id = ?;";
			ps = connection.prepareStatement(query);
			ps.setInt(1, contact.getContactId());
			ps.executeUpdate();
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
		 
	 }
}
