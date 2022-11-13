/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;


@Stateless
public class ShoppingCart

{ List<String> contents;
String customerName;
private Connection conn = null;
private ResultSet rs;
private Statement stmt = null;
private String query = null;
public void initialize(String person)
{ if (person != null) {
customerName = person;
try {
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cartdb", "root",
"tiger");
} catch(ClassNotFoundException | IllegalAccessException | InstantiationException |
SQLException e) {
System.err.println("Sorry failed to connect to the Database." + e.getMessage());
}
}
contents = new ArrayList<>();
}
public void addBook(String title) {
try {
stmt = conn.createStatement();
query = "INSERT INTO cart VALUES('" + customerName + "','" + title + "')";
stmt.executeUpdate(query);
} catch(SQLException e) {
System.err.println("Sorry failed to insert values from the database table. " + e.getMessage());
}
}
public void removeBook(String title) {
try {
stmt = conn.createStatement();
query = "DELETE FROM cart WHERE UserName='" + customerName + "' AND
ItemName='" + title + "'";
stmt.executeUpdate(query);
} catch(SQLException e) {
System.err.println("Sorry failed to delete values from the database table. " + e.getMessage());

}
}
public List<String> getContents() {
try {
stmt = conn.createStatement();
query = "SELECT * FROM cart WHERE UserName='" + customerName + "'";
rs = stmt.executeQuery(query);
while(rs.next()) {
contents.add(rs.getString("ItemName"));
}
} catch(SQLException e) {
System.err.println("Sorry failed to select values from the database table. " + e.getMessage());
}
return contents;
}
@Remove()
public void remove() {
contents = null;
}
}
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart</title>
</head>
<body style="background-color: pink;">
<h1 style="text-align: center;">Books For Sale</h1><br>
<form method="post">
Customer Name: <input type="text" name="txtCustomerName" value=<%= request.getParameter("txtCustomerName")%> /><br>
<b>Book Titles</b><br>
<input type="checkbox" name="chkBook" value="Struts 2.0 For Beginners">Struts
2.0 For Beginners<br>
<input type="checkbox" name="chkBook" value="Oracle 11g For Professionals">
<input type="checkbox" name="chkBook" value="Hibernate 3 For Beginners">
<input type="checkbox" name="chkBook" value="Java Persistence API In EJB 3 For Beginners">
<br>
<input type='submit' value='Add To My Basket' name='btnAddBook'>
<input type='submit' value='Remove From My Basket'
name='btnRmvBook'><br><br><br>
<%
if(cart!=null)
{
out.print("<b>Basket</b><br>");
List <String> bookList = cart.get.contents();
Iterator iterator = bookList.iterator();
while (iterator.hasNext())
{
String title = (String) iterator.next();
%>
<%= title %><br>
<%
}
}
%>
</body>
</html>