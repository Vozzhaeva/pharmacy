<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 22.03.2017
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>


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


<script>
    function  send() {
        var valid = true;
        var elems = document.forms[0].elements;
        for (var i=0; i<document.forms[0].length;i++){
            if(elems[i].getAttribute('type')=='text'||
                elems[i].tagName=='TEXTAREA'){
                if(elems[i].value==''){
                    elems[i].style.border='2px solid red';
                    valid=false;
                }
            }
        }
        if(!valid){
            alert('Все поля должны быть заполнены.');
            return false;
        }
        else  return true;
    }
</script>
<div align="center">
    <c:if test="${pharmacy != null}">
    <form action="update_ph" onsubmit="return send();" method="post">
        </c:if>
        <c:if test="${pharmacy == null}">
        <form action="insert_ph" onsubmit="return send();" method="post">
            </c:if>
            <table border="10" cellpadding="10">
                <caption>
                    <h1>
                        <c:if test="${pharmacy != null}">
                            Изменить данные об аптеке
                        </c:if>
                        <c:if test="${pharmacy == null}">
                            Новая аптека
                        </c:if>
                    </h1>
                </caption>
                <c:if test="${pharmacy != null}">
                    <%
                        String idPharmacy = request.getParameter("id_ph");
                    %>
                    <input type="hidden" name="id_ph" value="<%=idPharmacy%>"/>
                </c:if>
                <tr>
                    <th>Наименование:</th>
                    <td>
                        <input type="text" name="name_ph" size="45"
                               value="<c:out value='${pharmacy.name_ph}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Адрес:</th>
                    <td>
                        <input type="text" name="address_ph" size="45"
                               value="<c:out value='${pharmacy.address_ph}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Номер телефона:</th>
                    <td>
                        <input type="text" name="phone_ph" size="45"
                               value="<c:out value='${pharmacy.phone_ph}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Сайт:</th>
                    <td>
                        <input type="text" name="site_ph" size="45"
                               value="<c:out value='${pharmacy.site_ph}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Сохранить"/>
                    </td>
                </tr>
            </table>

                <nav class="top-menu">
                    <ul class="menu-main">
                        <li> <a href="/list_ph">Все аптеки</a> </li>

                    </ul>
                </nav>
        </form>
</div>
</body>
</html>