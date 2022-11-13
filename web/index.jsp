<%@page import="java.util.Iterator, java.util.List, javax.naming.InitialContext,
ejb.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
private static ShoppingCart cart;
public void jspInit() {
try {
InitialContext ic = new InitialContext();
cart = (ShoppingCart) ic.lookup("java:global/Prac6CShoppingCartApp/ShoppingCart");
} catch (Exception ex) {
System.out.println("Could not create cart bean." + ex.getMessage());
}
}
%>
<%
if(request.getParameter("txtCustomerName") != null) {
cart.initialize(request.getParameter("txtCustomerName"));
} else {
cart.initialize("Guest");
}
if (request.getParameter("btnRmvBook") != null) {
String books[] = request.getParameterValues("chkBook");
if (books != null) {
for (int i=0; i<books.length; i++) {
cart.removeBook(books[i]);
}
}
}
if (request.getParameter("btnAddBook") != null) {
String books[] = request.getParameterValues("chkBook");
if (books != null) {
    for (int i=0; i<books.length; i++) {
cart.addBook(books[i]);
}
}
}
%>