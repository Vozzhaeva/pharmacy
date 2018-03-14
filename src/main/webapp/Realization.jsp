<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 22.03.2017
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Model.Medicament" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    <%@include file='main.css' %>
</style>


<nav class="top-menu">
    <ul class="menu-main">
        <li><a href="/listtPharmacy">Аптеки</a></li>
        <li><a href="/listtMedicament">Список препаратов</a></li>
        <li><a href="/list_r">Препараты в наличии</a></li>
        <li><a href="/listtManufacturer">Производители</a></li>

    </ul>
</nav>




<html>
<head>
    <title>Препараты в наличии</title>
</head>
<body>

<div align="center">
    <table border="10"  cellpadding="10">
        <caption><h1>Препараты в наличии</h1></caption>
        <tr>
            <th>Название аптеки</th>
            <th>Название препарата</th>
            <th>Дата поступления</th>
            <th>Стоимость, руб.</th>
            <th>Действия</th>
        </tr>

        <c:forEach var="realization" items="${listRealization}">
            <tr>
                <td>
                    <c:forEach var="pharmacy" items="${listPharmacy}">
                        <c:if test="${pharmacy.id_ph == realization.id_ph}">
                            <c:out value="${pharmacy.name_ph}"/>
                        </c:if>
                    </c:forEach>



                </td>
                <td>

                    <c:forEach var="medicament" items="${listMedicament}">
                    <c:if test="${medicament.id_med == realization.id_med}">
                        <c:out value="${medicament.name_med}"/>
                    </c:if>
                </c:forEach>

                </td>
                <td><c:out value="${realization.date_r}" /></td>
                <td><c:out value="${realization.price}" /></td>

                <td>




                    <a href="/delete_r?id_ph=<c:out value='${realization.id_ph}'/>&id_med=<c:out value='${realization.id_med}'/> " class="s1">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>


    <nav class="top-menu">
        <ul class="menu-main">
            <li><a href="/new_r"  >Добавить</a> </li>

        </ul>
    </nav>

</div>
</body>
</html>