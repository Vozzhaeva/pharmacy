<%@ page import="Model.Manufacturer" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    <%@include file='main.css' %>
</style>



<nav class="top-menu">
    <ul class="menu-main">
        <li><a href="/listPharmacy">Аптеки</a></li>
        <li><a href="/listMedicament">Список препаратов</a></li>
        <li><a href="/listtRealization">Препараты в наличии</a></li>
        <li><a href="/list">Производители</a></li>
        <li></li>
    </ul>
</nav>


<html>
<head>
    <title>Производители</title>
</head>
<body>
<center>

</center>
<div align="center">
    <table border="10" cellpadding="10">
        <caption><h1>Производители</h1></caption>
        <tr>
            <th>ID</th>
            <th>Наименование</th>
            <th>Адрес</th>
            <th>Номер телефона</th>
            <th>E-mail</th>
            <th>Действия</th>
        </tr>

        <c:forEach var="manufacturer" items="${listManufacturer}">
            <tr>
                <td><c:out value="${manufacturer.id_m}"/></td>
                <td><c:out value="${manufacturer.name_m}"/></td>
                <td><c:out value="${manufacturer.address_m}"/></td>
                <td><c:out value="${manufacturer.phone_m}"/></td>
                <td><c:out value="${manufacturer.email_m}"/></td>
                <td>
                    <a href="/edit?id_m=<c:out value='${manufacturer.id_m}' />" class="s1">Изменить</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id_m=<c:out value='${manufacturer.id_m}' />" class="s1">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>


    <nav class="top-menu">
        <ul class="menu-main">
            <li> <a href="/new">Добавить производителя</a> </li>

        </ul>
    </nav>

</div>
</body>
</html>