<%-- 
    Document   : shoppinglist
    Created on : 14-Oct-2022, 10:09:49 PM
    Author     : Eric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${user}</p>
        <p><a href="<c:url value='ShoppingList?action=logout'/>">Log out</a></p>

        <h2>List</h2>
        <form action="" method="post">
            Add item: <input type="text" name="item" required>
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>

        <form action="" method="post">
            <c:forEach var="item" items="${list}">
                <input type="radio" name="item" value="${item}">${item}<br>
            </c:forEach>
            <br>
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete">
        </form>

    </body>
</html>
