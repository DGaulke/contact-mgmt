package contacts.model;

public class Contact implements Comparable<Contact> {
	private int contactId; private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private PhoneNumber[] phoneNumbers;
	private EmailAddress[] emailAddresses;
	private Anniversary[] anniversaries;

	public void	setContactId(int contactId){
		this.contactId = contactId;
	}
	public int getContactId(){
		return this.contactId;
	}

	public void setFirstName(String name){
		this.firstName = name;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public void setLastName(String name){
		this.lastName = name;
	}
	public String getLastName(){
		return this.lastName;
	}
	public void setStreetAddress(String streetAddress){
		this.streetAddress = streetAddress;
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

	public PhoneNumber[] getPhoneNumbers(){
		return this.phoneNumbers;
	} 
	public void setPhoneNumbers(PhoneNumber[] phoneNumbers){
		this.phoneNumbers = phoneNumbers;
	}
	public EmailAddress[] getEmailAddresses(){
		return this.emailAddresses;
	}
	public void setEmailAddresses(EmailAddress[] emailAddresses){
		this.emailAddresses = emailAddresses;
	}
	public Anniversary[] getAnniversaries(){
		return this.anniversaries;
	}
	public void setAnniversaries(Anniversary[] anniversaries){
		this.anniversaries = anniversaries;
	}

	public int compareTo(Contact contact){
		if (this.contactId < contact.contactId)
			return -1;
		else if (this.contactId == contact.contactId)
			return 0;
		else
			return 1;
	}
}
