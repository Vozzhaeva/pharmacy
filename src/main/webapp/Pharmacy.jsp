<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 22.03.2017
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Model.Pharmacy" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    <%@include file='main.css' %>
</style>



<nav class="top-menu">
    <ul class="menu-main">
        <li><a href="/list_ph">Аптеки</a></li>
        <li><a href="/list_Medicament">Список препаратов</a></li>
        <li><a href="/list_Realization">Препараты в наличии</a></li>
        <li><a href="/list_Manufacturer">Производители</a></li>
    </ul>
</nav>


<html>
<head>
    <title>Аптеки</title>
</head>
<body>

<div align="center">
    <table border="10" cellpadding="10">
        <caption><h1>Аптеки</h1></caption>
        <tr>
            <th>ID</th>
            <th>Наименование</th>
            <th>Адрес</th>
            <th>Номер телефона</th>
            <th>Сайт</th>
            <th>Действия</th>
        </tr>

        <c:forEach var="pharmacy" items="${listPharmacy}">
            <tr>
                <td><c:out value="${pharmacy.id_ph}"/></td>
                <td><c:out value="${pharmacy.name_ph}"/></td>
                <td><c:out value="${pharmacy.address_ph}"/></td>
                <td><c:out value="${pharmacy.phone_ph}"/></td>
                <td><c:out value="${pharmacy.site_ph}"/></td>
                <td>
                    <a href="/edit_ph?id_ph=<c:out value='${pharmacy.id_ph}' />" class="s1">Изменить</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete_ph?id_ph=<c:out value='${pharmacy.id_ph}' />" class="s1">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <nav class="top-menu">
        <ul class="menu-main">
            <li>  <a href="/new_ph" >Добавить аптеку</a> </li>

        </ul>
    </nav>


</div>
</body>
</html>