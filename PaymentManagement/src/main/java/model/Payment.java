package model;
import java.sql.*;
public class Payment
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/test", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertPayment(String billNo, String cardNo, String expiryDate, String securityNo)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into payments (`paymentID`,`billNo`,`cardNo`,`expiryDate`,`securityNo`)"
 + " values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, billNo);
 preparedStmt.setString(3, cardNo);
 preparedStmt.setString(4, expiryDate);
 preparedStmt.setString(5, securityNo);
 // execute the statement
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the payment.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readPayments()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>billNo</th><th>cardNo</th>" +
 "<th>expiryDate</th>" +
 "<th>securityNo</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from payments";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String paymentID = Integer.toString(rs.getInt("paymentID"));
 String billNo = rs.getString("billNo");
 String cardNo = rs.getString("cardNo");
 String expiryDate = Double.toString(rs.getDouble("expiryDate"));
 String securityNo = rs.getString("securityNo");
 // Add into the html table
 output += "<tr><td>" + billNo + "</td>";
 output += "<td>" + cardNo + "</td>";
 output += "<td>" + expiryDate + "</td>";
 output += "<td>" + securityNo + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='payments.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='paymentID' type='hidden' value='" + paymentID
 + "'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the payments.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updatePayment(String paymentID, String billNo, String cardNo, String expiryDate, String securityNo) 
{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE payments SET billNo=?,cardNo=?,expiryDate=?,securityNo=? WHERE paymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, billNo);
	 preparedStmt.setString(2, cardNo);
	 preparedStmt.setString(3, expiryDate);
	 preparedStmt.setString(4, securityNo);
	 preparedStmt.setInt(5, Integer.parseInt(paymentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deletePayment(String paymentID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from payments where paymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(paymentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	} 