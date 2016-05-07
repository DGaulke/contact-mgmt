package contacts.util;

import java.sql.*;


public class AppUtil {

	public static String stateOption(){
		return AppUtil.stateOption("");
	}
	public static String stateOption(String selectedState){
		ConnectionPool pool = null;
		Connection cn = null;
		PreparedStatement ps = null;

		StringBuilder output = new StringBuilder();	
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			String query = "select state_abrv, state_name from state";
			ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			output.append("<label for='state'><span>State:</span></label>");
			output.append("<select name='state'> id='state' size='1'>");
			while (rs.next()){
				output.append(stateOption(rs.getString("state_abrv"), 
						rs.getString("state_name"), 
						rs.getString("state_abrv").equals(selectedState)));
			}
			output.append("</select>");
			return output.toString();

		} catch (SQLException sqle){
			sqle.printStackTrace();
			return null;
		} finally {
			AppUtil.cleanup(ps, pool, cn);
		}

	}
	private static String stateOption(String stateAbrv, String stateName, 
			boolean selected){
		return "<option value='" + stateAbrv + "'" + 
				(selected ? " selected" : "") + ">" + stateName + "</option>";
	}
	public static String phoneType(int number){
		return AppUtil.phoneType(number, "");	
	}	
	public static String phoneType(int number, String selectedType){
		ConnectionPool pool = null;
		Connection cn = null;
		PreparedStatement ps = null;

		StringBuilder output = new StringBuilder();	
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			String query = "select phone_type_desc from phone_type";
			ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			output.append("<label for='phoneType" + number + "'><span>Phone " + 
					(number + 1) + ":</span></label>");
			output.append("<br>");
			output.append("<select class='option' name='phoneType" + number + 
					"' id='phoneType" + number + "' size='1'>");
			while (rs.next()){
				output.append(phoneOption(rs.getString("phone_type_desc"), 
						rs.getString("phone_type_desc").equals(selectedType)));
			}
			output.append("</select>");
			return output.toString();

		} catch (SQLException sqle){
			sqle.printStackTrace();
			return null;
		} finally {
			AppUtil.cleanup(ps, pool, cn);
		}
	}
	private static String phoneOption(String phoneType, boolean selected){
		return "<option value='" + phoneType + "'" + 
				(selected ? " selected" : "") + ">" + phoneType + "</option>";
	}

	public static String emailType(int number){
		return AppUtil.emailType(number, "");	
	}	
	public static String emailType(int number, String selectedType){
		ConnectionPool pool = null;
		Connection cn = null;
		PreparedStatement ps = null;

		StringBuilder output = new StringBuilder();	
		try {
			pool = ConnectionPool.getInstance();
			cn = pool.getConnection();
			String query = "select email_type_desc from email_type";
			ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			output.append("<label for='emailType" + number + 
					"'><span>Email " + (number + 1) + ":</span></label>");
			output.append("<br>");
			output.append("<select class='option' name='emailType" + number +
				   	"' id='emailType" + number + "' size='1'>");
			while (rs.next()){
				output.append(emailOption(rs.getString("email_type_desc"), 
						rs.getString("email_type_desc").equals(selectedType)));
			}
			output.append("</select>");
			return output.toString();

		} catch (SQLException sqle){
			sqle.printStackTrace();
			return null;
		} finally {
			AppUtil.cleanup(ps, pool, cn);
		}
	}
	private static String emailOption(String emailType, boolean selected){
		return "<option value='" + emailType + "'" + 
				(selected ? " selected" : "") + ">" + emailType + "</option>";
	}

	public static void cleanup(PreparedStatement ps, ConnectionPool pool, 
			Connection cn){
		try {
			ps.close();
			pool.freeConnection(cn);
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}

}
