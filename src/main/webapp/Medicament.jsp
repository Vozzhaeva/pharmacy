<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 19.03.2017
  Time: 20:06
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
        <li><a href="/list_Pharmacy">Аптеки</a></li>
        <li><a href="/list_med">Список препаратов</a></li>
        <li><a href="/listRealization">Препараты в наличии</a></li>
        <li><a href="/listManufacturer">Производители</a></li>
        <li></li>
    </ul>
</nav>





<html>
<head>
    <title>Список препаратов</title>
</head>
<body>


<div align="center">
    <table border="10" cellpadding="10">
        <caption><h1>Препараты</h1></caption>
        <tr>
            <th>ID</th>
            <th>Наименование</th>
            <th>Наличие рецепта</th>
            <th>Дата производства</th>
            <th>Срок годности</th>
            <th>Название производителя</th>
            <th>Действия</th>
        </tr>

        <c:forEach var="medicament" items="${listMedicament}">
            <tr>
                <td><c:out value="${medicament.id_med}"/></td>
                <td><c:out value="${medicament.name_med}"/></td>
                <td><c:if test="${medicament.recipe == 'true'}">
                    <c:out value="Требуется"/>
                </c:if>
                    <c:if test="${medicament.recipe == 'false'}">
                        <c:out value="Не требуется"/>
                    </c:if>
                </td>
                <td><c:out value="${medicament.date_med}"/></td>
                <td><c:out value="${medicament.limitation}"/></td>
                <td>


                    <c:forEach var="manufacturer" items="${listManufacturer}">
                        <c:if test="${manufacturer.id_m == medicament.id_m}">
                            <c:out value="${manufacturer.name_m}"/>
                        </c:if>
                    </c:forEach>

                </td>
                <td>


                    <a href="/edit_med?id_med=<c:out value='${medicament.id_med}' />"  class="s1">Изменить</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete_med?id_med=<c:out value='${medicament.id_med}' />"  class="s1">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>


    <nav class="top-menu">
        <ul class="menu-main">
            <li> <a href="/new_med" >Добавить препарат</a></li>

        </ul>
    </nav>






</div>
</body>
</html>