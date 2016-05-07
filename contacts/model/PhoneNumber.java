package contacts.model;

public class PhoneNumber{
	private String phoneType;
	private String phoneNumber;

	public String getPhoneType(){
		return this.phoneType;
	}
	public void setPhoneType(String phoneType){
		this.phoneType = phoneType;
	}
	public String getPhoneNumber(){
		return this.phoneNumber;
	}	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

}
