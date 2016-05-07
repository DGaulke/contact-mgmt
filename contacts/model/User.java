package contacts.model;
import java.security.*;
import java.util.*;
import javax.xml.bind.DatatypeConverter;

public class User {
	private int id;
	private String userName = "";
	private String password = "";
	private String salt = "";
	private String firstName = "";
	private String lastName = "";
	private String streetAddress = "";
	private String city = "";
	private String state = "";
	private String zip = "";

	public void setUserId(int id){
		this.id = id;
	}
	public int getUserId(){
		return this.id;
	}	
	public void setUserName(String userName){
		this.userName = userName;	
	}
	public String getUserName(){
		return this.userName;
	}
	public void setPassword(String password){
		if (password == null){
			this.password = null;
			return;
		}
		this.salt = User.generateRandomSalt();
		this.password = hashPassword(password, this.salt);
	}
	public void setPassword2(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public String getSalt(){
		return this.salt;
	}
	public void setSalt(String salt){
		this.salt = salt;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public void setStreetAddress(String address){
		this.streetAddress = address;
	}
	public String getStreetAddress(){
		return this.streetAddress;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getCity(){
		return this.city;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getState(){
		return this.state;
	}
	public void setZip(String zip){
		this.zip = zip;
	}
	public String getZip(){
		return this.zip;
	}
	
	private static String generateRandomSalt(){
		final Random r = new SecureRandom();
		byte[] salt = new byte[32];
		r.nextBytes(salt);
		return DatatypeConverter.printHexBinary(salt);
	}

	public static String hashPassword(String password, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(DatatypeConverter.parseHexBinary(salt));
			md.update(password.getBytes());
			return DatatypeConverter.printHexBinary(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
	    }
	}
	
	public boolean isValid(String password){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(DatatypeConverter.parseHexBinary(this.salt));
			md.update(password.getBytes());
			if (DatatypeConverter.printHexBinary(md.digest()).equals(this.password))
				return true;
			return false;
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
			return false;
		}
	}	
}
