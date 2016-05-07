package contacts.model;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import contacts.util.*;

public class PhoneDB {

	public static void insert(Contact contact, PhoneNumber phoneNumber) 
			throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";

		int phoneTypeId = PhoneDB.insertPhoneType(phoneNumber.getPhoneType());
		int phoneId = PhoneDB.insertPhone(phoneNumber.getPhoneNumber());

		try {
			query = "insert into contact_phone " +
				"(contact_id, phone_id, phone_type_id) " +
				"values (?, ?, ?);";
			
			ps = connection.prepareStatement(query);
			ps.setInt(1, contact.getContactId());
			ps.setInt(2, phoneId);
			ps.setInt(3, phoneTypeId);
			ps.executeUpdate();
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}

	private static int insertPhoneType(String phoneType) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
	
		try {
			query = "select phone_type_id from phone_type " +
				"where phone_type_desc = ?;";

			ps = connection.prepareStatement(query);
			ps.setString(1, phoneType);
			rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt("phone_type_id");

			query = "insert into phone_type (phone_type_desc) " +
					"values (?);";

			ps = connection.prepareStatement(query, 
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, phoneType);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);
			throw new SQLException("Insert Phone Type unsuccessful.");
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}

	private static int insertPhone(String phoneNumber) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
	
		try {
			query = "select phone_id from phone " +
				"where phone_nbr = ?;";

			ps = connection.prepareStatement(query);
			ps.setString(1, phoneNumber);
			rs = ps.executeQuery();

			if (rs.next())
				return rs.getInt("phone_id");

			query = "insert into phone (phone_nbr) " +
					"values (?);";

			ps = connection.prepareStatement(query, 
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, phoneNumber);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);
			throw new SQLException("Insert Phone unsuccessful");
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}
	
	public static PhoneNumber[] load(Contact contact) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		try {
			List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
			String query = "select pt.phone_type_desc, p.phone_nbr " + 
					"from contact_phone cp " + 
					"inner join phone p " + 
					"on cp.phone_id = p.phone_id " + 
					"inner join phone_type pt " + 
					"on cp.phone_type_id = pt.phone_type_id " + 
					"where cp.contact_id = ?;";

			ps = connection.prepareStatement(query);
			ps.setInt(1, contact.getContactId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				PhoneNumber pn = new PhoneNumber();
				pn.setPhoneType(rs.getString("phone_type_desc"));
				pn.setPhoneNumber(rs.getString("phone_nbr"));
				phoneNumbers.add(pn);
			}
			if (phoneNumbers.size() == 0)
				return null;
			PhoneNumber[] phoneNumberArray = 
					new PhoneNumber[phoneNumbers.size()];
			return phoneNumbers.toArray(phoneNumberArray);
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
		ResultSet rs = null;
		String query = "";	

		try {
			query = "delete from contact_phone where contact_id = ?;";
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
