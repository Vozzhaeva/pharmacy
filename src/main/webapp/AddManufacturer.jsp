<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 19.03.2017
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/javascript">
    <%@include file="/WEB-INF/valid.js"%>
</script>
<html>
<head>
</head>
<body>


<style>
    <%@include file='main.css' %>
</style>



<nav class="top-menu">
    <ul class="menu-main">
        <li><a href="/list_Pharmacy">Аптеки</a></li>
        <li><a href="/listMedicament">Список препаратов</a></li>
        <li><a href="/listtRealization">Препараты в наличии</a></li>
        <li><a href="/list">Производители</a></li>
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
    <c:if test="${manufacturer != null}">
    <form action="update" onsubmit="return send();" method="post">
        </c:if>
        <c:if test="${manufacturer == null}">
        <form action="insert"  onsubmit="return send();" method="post">
            </c:if>
            <table border="10" cellpadding="10">
                <caption>
                    <h1>
                        <c:if test="${manufacturer != null}">
                            Изменить данные о производителе
                        </c:if>
                        <c:if test="${manufacturer == null}">
                            Новый производитель
                        </c:if>
                    </h1>
                </caption>
                <c:if test="${manufacturer != null}">
                    <%
                        String idManufacturer = request.getParameter("id_m");
                    %>
                    <input type="hidden" name="id_m" value="<%=idManufacturer%>"/>
                </c:if>
                <tr>
                    <th>Наименование:</th>
                    <td>
                        <input type="text" name="name_m" size="45"
                               value="<c:out value='${manufacturer.name_m}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Адрес:</th>
                    <td>
                        <input type="text" name="address_m" size="45"
                               value="<c:out value='${manufacturer.address_m}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Номер телефона:</th>
                    <td>
                        <input type="text" name="phone_m" size="45"
                               value="<c:out value='${manufacturer.phone_m}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>E-mail:</th>
                    <td>
                        <input type="text" name="email_m" size="45"
                               value="<c:out value='${manufacturer.email_m}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input  type="submit" value="Сохранить"/>
                    </td>
                </tr>
            </table>


                <nav class="top-menu">
                    <ul class="menu-main">
                        <li><a href="/list">Все производители</a> </li>

                    </ul>
                </nav>
        </form>
</div>
</body>
</html>