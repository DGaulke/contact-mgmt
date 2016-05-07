
package contacts.model;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import contacts.util.*;

public class EmailDB {

	public static void insert(Contact contact, EmailAddress emailAddress) 
			throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		int emailTypeId = EmailDB.insertEmailType(emailAddress.getEmailType());
		int emailId = EmailDB.insertEmail(emailAddress.getEmailAddress());

		try {
			String query = "insert into contact_email " +
				"(contact_id, email_id, email_type_id) " +
				"values (?, ?, ?);";
			
			ps = connection.prepareStatement(query);
			ps.setInt(1, contact.getContactId());
			ps.setInt(2, emailId);
			ps.setInt(3, emailTypeId);
			ps.executeUpdate();
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}

	private static int insertEmailType(String emailType) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
	
		try {
			String query = "select email_type_id from email_type " + 
					"where email_type_desc = ?;";

			ps = connection.prepareStatement(query);
			ps.setString(1, emailType);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt("email_type_id");

			query = "insert into email_type (email_type_desc) " +
					"values (?);";

			ps = connection.prepareStatement(query, 
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, emailType);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);
			throw new SQLException("Insert Email Type unsucessful.");
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}

	private static int insertEmail(String emailAddress) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
	
		try {
			String query = "select email_id from email " + 
				"where email_addr = ?;";

			ps = connection.prepareStatement(query);
			ps.setString(1, emailAddress);
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				return rs.getInt("email_id");

			query = "insert into email (email_addr) " +
					"values (?);";

			ps = connection.prepareStatement(query, 
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, emailAddress);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);
			throw new SQLException("Insert Email Address unsuccessful.");
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}
	
	public static EmailAddress[] load(Contact contact) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		try {
			List<EmailAddress> emailAddresses = new ArrayList<EmailAddress>();
			String query = "select et.email_type_desc, e.email_addr " + 
					"from contact_email ce inner join email e " + 
					"on ce.email_id = e.email_id " +
					"inner join email_type et " + 
					"on ce.email_type_id = et.email_type_id " +
					"where ce.contact_id = ?;";
			ps = connection.prepareStatement(query);
			ps.setInt(1, contact.getContactId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				EmailAddress ea = new EmailAddress();
				ea.setEmailType(rs.getString("email_type_desc"));
				ea.setEmailAddress(rs.getString("email_addr"));
				emailAddresses.add(ea);
			}
			if (emailAddresses.size() == 0)
				return null;
			EmailAddress[] emailAddressArray = 
					new EmailAddress[emailAddresses.size()];
			return emailAddresses.toArray(emailAddressArray);
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
			String query = "delete from contact_email where contact_id = ?;";
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

