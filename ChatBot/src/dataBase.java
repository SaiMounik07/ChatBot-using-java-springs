import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dataBase {
	
	
	// Method to get Reply
	public String getResponse(String ques)throws Exception
	{
		String url="jdbc:mysql://127.0.0.1:3305/piggy";
	    String uname="root";
	    String pass="mig20@home";
	    String query="select ans from queries where LOWER(questions)='"+ques+"'";
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection con=DriverManager.getConnection(url,uname,pass);
	    Statement st=con.createStatement();
	    try 
	    {
	    	ResultSet res = st.executeQuery(query);
	        res.next();
	    	return (String)res.getString(1);
	    }
	    catch(Exception e){}
	    return "Could not get you..";
	}
	
	
	//Method to check the eligibility of User in Database
	public boolean isEligible(String accNum) throws Exception{
		String url="jdbc:mysql://127.0.0.1:3305/piggy";
	    String uname="root";
	    String pass="mig20@home";
	    String query="select Eligible from BankDetails where Account_num='"+accNum+"'";
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection con=DriverManager.getConnection(url,uname,pass);
	    Statement st=con.createStatement();
	    try {
	    	 ResultSet res = st.executeQuery(query);
	         res.next();
	   
	    	if(res==null ||(res.getString(1)).equals("No")) {
	    		return false;
	    	}
	    	return true;
	    }catch(Exception e){
	    	
	    }
	    return false;
	}
	
	
	//Store the questions and answers in the Database
	public void Record(String ques,String ans)throws Exception
	{
		String url="jdbc:mysql://127.0.0.1:3305/piggy";
	    String uname="root";
	    String pass="mig20@home";
	    String query="insert into record values('"+ques+"',"+"'"+ans+"')";
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection con=DriverManager.getConnection(url,uname,pass);
	    Statement st=con.createStatement();
	    st.executeUpdate(query);
	         
	}
	
	// Validating user credentials
	public userBean checkLogin(String userName,String password) throws Exception
	{
		String url="jdbc:mysql://127.0.0.1:3305/piggy";
	    String uname="root";
	    String pass="mig20@home";
	    String query="select Password,Account_num,Balance,Phone,Name from BankDetails where UserName='"+userName+"';";
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection con=DriverManager.getConnection(url,uname,pass);
	    Statement st=con.createStatement();
	    ResultSet res=st.executeQuery(query);
	    if(!res.next())
	    	return null;
	    userBean obj= new userBean();
	    obj.setAccBal(res.getString(3));
	    obj.setAccNum(res.getString(2));
	    obj.setName(res.getString(5));
	    obj.setPhNum(res.getString(4));
	    obj.setPassword(res.getString(1));
	    obj.setUsername(userName);
		return obj;
		
	}
}
