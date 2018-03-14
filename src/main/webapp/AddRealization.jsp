<%@ page import="Model.Pharmacy" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Medicament" %><%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 22.03.2017
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>

<style>
    <%@include file='main.css' %>
</style>


<nav class="top-menu">
    <ul class="menu-main">
        <li><a href="/listtPharmacy">Аптеки</a></li>
        <li><a href="/listtMedicament">Список препаратов</a></li>
        <li><a href="/list_r">Препараты в наличии</a></li>
        <li><a href="/listtManufacturer">Производители</a></li>
        <li></li>
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


    <c:if test="${realization == null}">
    <form action="insert_r"  onsubmit="return send();"  method="post">
        </c:if>

        <table border="10" cellpadding="10">
            <caption>
                <h1>


                    <c:if test="${realization == null}">
                        Добавить новую продажу
                    </c:if>
                </h1>
            </caption>


            <tr>
                <th>Название аптеки:</th>
                <td>

                    <select class ="list" name ="id_ph">

                        <%
                            List<Pharmacy> pharmacys = (List<Pharmacy>) session.getAttribute("listPharmacy");
                            for(int i=0;i<pharmacys.size();i++) {
                        %>
                        <option
                                selected  value="<%=pharmacys.get(i).getId_ph()%>">
                            <%=pharmacys.get(i).getName_ph()%></option>
                        <%}%>

                    </select>




                    <%--<input type="text" name="id_ph" size="45"--%>
                           <%--value="<c:out value='${realization.id_ph}' />"--%>
                    <%--/>--%>
                </td>
            </tr>
            <tr>
                <th>Наименование прапарата:</th>
                <td>

                    <select class ="list" name ="id_med">

                        <%
                            List<Medicament> medicaments = (List<Medicament>) session.getAttribute("listMedicament");
                            for(int i=0;i<medicaments.size();i++) {
                        %>
                        <option
                                selected  value="<%=medicaments.get(i).getId_med()%>">
                            <%=medicaments.get(i).getName_med()%></option>
                        <%}%>

                    </select>


                    <%--<input type="text" name="id_med" size="45"--%>
                           <%--value="<c:out value='${realization.id_med}'/>"--%>
                    <%--/>--%>
                </td>
            </tr>
            <tr>
                <th>Дата поступления yyyy-MM-dd:</th>
                <td>
                    <input type="date" name="date_r" size="45"
                           value="<c:out value='${realization.date_r}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Стоимость:</th>
                <td>
                    <input type="text" name="price" size="45"
                           value="<c:out value='${realization.price}' />"
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
                    <li><a href="/list_r">Все продажи</a> </li>

                </ul>
            </nav>
    </form>
</div>
</body>
</html>
