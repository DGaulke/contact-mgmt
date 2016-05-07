package contacts.model;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import contacts.util.*;
public class ContactDB {

	public static void insert(Contact contact, String login) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		try {
			User user = UserDB.load(login);

			int addressId = getAddressId(connection, ps, 
					contact.getStreetAddress(), contact.getCity(), 
					contact.getState(), contact.getZip());

			String query = "insert into contact (user_id, first_name, " + 
					"last_name, address_id) values (?, ?, ?, ?);";
			ps = connection.prepareStatement(query, 
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, user.getUserId());
			ps.setString(2, contact.getFirstName());
			ps.setString(3, contact.getLastName());
			ps.setInt(4, addressId);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (!rs.next())
				throw new SQLException("Insert Contact unsuccessful.");

			int contactId = rs.getInt(1);
			contact.setContactId(contactId);
			
			if (contact.getPhoneNumbers() != null)
				for (PhoneNumber pn : contact.getPhoneNumbers())
					PhoneDB.insert(contact, pn);
			
			if (contact.getEmailAddresses() != null)
				for (EmailAddress ea : contact.getEmailAddresses())
					EmailDB.insert(contact, ea);
			
			if (contact.getAnniversaries() != null)
				for (Anniversary a : contact.getAnniversaries())
					AnniversaryDB.insert(contact, a);

		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}
	
	private static int getAddressId(Connection cn, PreparedStatement ps,
			String streetAddress, String city, String state, String zip) 
			throws SQLException {

		ResultSet rs = null;
		String query = "select address_id from address " +
				"where street_address = ? and city = ? " + 
				"and state_abrv = ? and zip = ?;";

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
		throw new SQLException("Insert Address unsuccessful.");
	}

	public static Contact load(int contactId, String login) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "select c.contact_id, c.first_name, c.last_name, " +
					"a.street_address, a.city, a.state_abrv, a.zip " + 
					"from contact c " + 
					"inner join address a " +
					"on c.address_id = a.address_id " + 
					"inner join user u " +
					"on c.user_id = u.user_id " +
					"where c.contact_id = ? and u.username = ?";

			ps = connection.prepareStatement(query);
			ps.setInt(1, contactId);
			ps.setString(2, login);
			ResultSet rs = ps.executeQuery();

			if (!rs.next())
				throw new SQLException("Load Contact unsuccessful.");

			Contact c = new Contact();
			c.setContactId(rs.getInt("contact_id"));
			c.setFirstName(rs.getString("first_name"));
			c.setLastName(rs.getString("last_name"));
			c.setStreetAddress(rs.getString("street_address"));
			c.setCity(rs.getString("city"));
			c.setState(rs.getString("state_abrv"));
			c.setZip(rs.getString("zip"));

			c.setPhoneNumbers(PhoneDB.load(c));
			c.setEmailAddresses(EmailDB.load(c));
			c.setAnniversaries(AnniversaryDB.load(c));
			return c;

		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}

	public static Contact[] loadAll(String login) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		try {
			List<Contact> contacts = new ArrayList<Contact>();

			String query = "select c.contact_id, c.first_name, c.last_name, " +
					"a.street_address, a.city, a.state_abrv, a.zip " + 
					"from contact c " + 
					"inner join address a " +
					"on c.address_id = a.address_id " + 
					"inner join user u " +
					"on c.user_id = u.user_id " + 
					"where u.username = ?";

			ps = connection.prepareStatement(query);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				Contact c = new Contact();
				c.setContactId(rs.getInt("contact_id"));
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
				c.setStreetAddress(rs.getString("street_address"));
				c.setCity(rs.getString("city"));
				c.setState(rs.getString("state_abrv"));
				c.setZip(rs.getString("zip"));

				c.setPhoneNumbers(PhoneDB.load(c));
				c.setEmailAddresses(EmailDB.load(c));
				c.setAnniversaries(AnniversaryDB.load(c));
				contacts.add(c);
			}
			if (contacts.size() == 0)
				return null;

			Contact[] contactArray = new Contact[contacts.size()];
			contactArray = contacts.toArray(contactArray);
			Arrays.sort(contactArray);
			return contactArray;

		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}

	public static void delete(int contactId, String login) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
	
		try {
			Contact contact = ContactDB.load(contactId, login);
			PhoneDB.delete(contact);
			EmailDB.delete(contact);
			AnniversaryDB.delete(contact);

			String query = "delete from contact where contact_id = ?;";
			ps = connection.prepareStatement(query);
			ps.setInt(1, contactId);
			ps.executeUpdate();
		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}

	}

	public static void update(Contact contact) throws SQLException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;

		try {
			int addressId = getAddressId(connection, ps, 
					contact.getStreetAddress(), contact.getCity(), 
					contact.getState(), contact.getZip());

			String query = "update contact " +
					"set first_name = ?, last_name = ?, address_id = ? " +
					"where contact_id = ?;";
			ps = connection.prepareStatement(query, 
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.setInt(3, addressId);
			ps.setInt(4, contact.getContactId());
			ps.executeUpdate();
	
			PhoneDB.delete(contact);
			EmailDB.delete(contact);
			AnniversaryDB.delete(contact);

			if (contact.getPhoneNumbers() != null)
				for (PhoneNumber pn : contact.getPhoneNumbers())
					PhoneDB.insert(contact, pn);
			
			if (contact.getEmailAddresses() != null)
				for (EmailAddress ea : contact.getEmailAddresses())
					EmailDB.insert(contact, ea);
			
			if (contact.getAnniversaries() != null)
				for (Anniversary a : contact.getAnniversaries())
					AnniversaryDB.insert(contact, a);

		} catch (SQLException sqle){
			throw sqle;
		} finally {
			AppUtil.cleanup(ps, pool, connection);
		}
	}

}
