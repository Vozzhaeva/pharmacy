<%@ page import="java.util.List" %>
<%@ page import="Model.Manufacturer" %><%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 22.03.2017
  Time: 17:42
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
        <li><a href="/listPharmacy">Аптеки</a></li>
        <li><a href="/list_med">Список препаратов</a></li>
        <li><a href="/listRealization">Препараты в наличии</a></li>
        <li><a href="/listManufacturer">Производители</a></li>

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
    <c:if test="${medicament != null}">
    <form action="update_med" onsubmit="return send();" method="post">
        </c:if>
        <c:if test="${manufacturer == null}">
        <form action="insert_med" onsubmit="return send();" method="post">
            </c:if>


            <table border="10" cellpadding="10">
                <caption>
                    <h1>
                        <c:if test="${medicament != null}">
                            Изменить данные о препарате
                        </c:if>
                        <c:if test="${medicament == null}">
                            Новый препарат
                        </c:if>
                    </h1>
                </caption>
                <c:if test="${medicament != null}">
                    <%
                        String idMedicament = request.getParameter("id_med");
                    %>
                    <input type="hidden" name="id_med" value="<%=idMedicament%>"/>
                </c:if>
                <tr>
                    <th>Наименование:</th>
                    <td>
                        <input type="text" name="name_med" size="45"
                               value="<c:out value='${medicament.name_med}' />"
                        />

                    </td>
                </tr>
                <tr>
                    <th>Наличие рецепта:</th>
                    <td>
                        <select class ="list" name ="recipe">

                        <option value="true"> Требуется</option>
                        <option value="false">Не требуется</option>

                    </select>
                        <%--<input type="text" name="recipe" size="45"--%>
                               <%--value="<c:out value='${medicament.recipe}'/>"--%>
                        <%--/>--%>
                    </td>
                </tr>
                <tr>
                    <th>Дата производства yyyy-MM-dd:</th>
                    <td>
                        <input type="date" name="date_med" size="45"
                               value="<c:out value='${medicament.date_med}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Срок годности yyyy-MM-dd:</th>
                    <td>
                        <input type="date" name="limitation" size="45"
                               value="<c:out value='${medicament.limitation}' />"
                        />
                    </td>
                </tr>







                <tr>
                    <th>Производитель:</th>
                    <td>
                        <select class ="list" name ="id_m">

                            <%
                                List<Manufacturer> manufacturers = (List<Manufacturer>) session.getAttribute("listManufacturer");
                                for(int i=0;i<manufacturers.size();i++) {
                            %>
                            <option
                                    selected  value="<%=manufacturers.get(i).getId_m()%>">
                                <%=manufacturers.get(i).getName_m()%></option>
                            <%}%>

                        </select>

                        <%----%>
                        <%--<input type="text" name="id_m" size="45"--%>
                               <%--value="<c:out value='${medicament.id_m}' />"--%>
                        <%--/>--%>
                    </td>
                </tr>
                <tr>


                    <td colspan="2" align="center">
                        <input type="submit"  value="Сохранить "/>
                    </td>
                </tr>
            </table>
        </form>


            <nav class="top-menu">
                <ul class="menu-main">
                    <li>  <a href="/list_med">Все прапараты</a> </li>

                </ul>
            </nav>

        <br><br><br>
    </form>
</div>
</body>
</html>