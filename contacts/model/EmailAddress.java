
package contacts.model;

public class EmailAddress {
	private String emailType;
	private String emailAddress;

	public String getEmailType(){
		return this.emailType;
	}
	public void setEmailType(String emailType){
		this.emailType = emailType;
	}
	public String getEmailAddress(){
		return this.emailAddress;
	}	
	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}

}
