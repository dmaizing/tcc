<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>

<form id="formCategoria" method="get" action="Categoria">
    <c:choose>
        <c:when test="${param.acao eq 'novo'}">
            <h1>Nova Categoria</h1><br />                    
            <div class="campos">
                <label for="catdescricao">Descrição..:</label>
                <input type="text" name="catdescricao"  id="catdescricao" required="true" size="30" maxlength="30"/>
            </div><br />
            <input type="submit" name="acao" value="novo" />
        </c:when>
        <c:otherwise>
            <h1>Alteração de Categoria</h1><br />
            <div class="campos">
                <label for="catcodigo">Código..:</label>
                <input type="text" value="${param.catcodigo}" name="catcodigo"  id="catcodigo" required="true" size="5" readonly="true"/>
            </div><br />
            <div class="campos">
                <label for="catdescricao">Descrição..:</label>
                <input type="text" value="${param.catdescricao}" name="catdescricao"  id="catdescricao" required="true" size="30" maxlength="30"/>
            </div><br />
            <input type="submit" name="acao" value="alterar" />
        </c:otherwise>
    </c:choose>
</form>
<c:import url="rodape.jsp"/>