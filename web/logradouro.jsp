<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>

<form id="formLogradouro" method="get" action="Logradouro">
    <c:choose>
        <c:when test="${param.acao eq 'novo'}">
                <h1>Novo Logradouro</h1><br>                    
                <div class="campos">
                    <label for="logdescricao">Descrição..:</label>
                    <input type="text" name="logdescricao"  id="logdescricao" required="true" size="30" maxlength="30"/>
                </div><br>
                <input type="submit" name="acao" value="novo"/>
            </c:when>
            <c:otherwise>
                <h1>Alteração de Logradouro</h1><br>
                 <div class="campos">
                    <label for="logcodigo">Código..:</label>
                    <input type="text" value="${param.logcodigo}" name="logcodigo"  id="logcodigo" required="true" size="5" readonly="true"/>
                </div><br>
                    <div class="campos">
                    <label for="logdescricao">Descrição..:</label>
                    <input type="text" value="${param.logdescricao}" name="logdescricao"  id="logdescricao" required="true" size="30" maxlength="30"/>
                </div><br>
                <input type="submit" name="acao" value="alterar"/>
    </c:otherwise>
</c:choose>
</form>
<c:import url="rodape.jsp"/>
