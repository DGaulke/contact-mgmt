package contacts.model;

public class Anniversary {
	private int anniversaryId;
	private String anniversaryType;
	private String anniversary;

	public int getAnniversaryId(){
		return this.anniversaryId;
	}
	public void setAnniversaryId(int anniversaryId){
		this.anniversaryId = anniversaryId;
	}
	public String getAnniversaryType(){
		return this.anniversaryType;
	}
	public void setAnniversaryType(String anniversaryType){
		this.anniversaryType = anniversaryType;
	}
	public String getAnniversary(){
		return this.anniversary;
	}	
	public void setAnniversary(String anniversary){
		this.anniversary = anniversary;
	}

}
