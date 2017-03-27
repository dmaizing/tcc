<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>

<form id="formCidade" method="get" action="Cidade">
    <c:choose>
        <c:when test="${param.acao eq 'novo'}">
                <h1>Nova Cidade</h1><br>                    
                <div class="campos">
                    <label for="ciddescricao">Descrição..:</label>
                    <input type="text" name="ciddescricao"  id="ciddescricao" required="true" size="30" maxlength="30"/>
                    <br /><br />
                    <label for="selectuf">UF..:</label>
                    <select name="selectuf" id="selectUF">
                        <tagsTCC:listaUFSelect/>
                    </select>
                </div>
                <br />
                <input type="submit" name="acao" value="novo"/>
            </c:when>
            <c:otherwise>
                <h1>Alteração de Cidade</h1><br>
                 <div class="campos">
                    <label for="cidcodigo">Código..:</label>
                    <input type="text" value="${param.cidcodigo}" name="cidcodigo"  id="cidcodigo" required="true" size="5" readonly="true"/>
                    <br /><br /> 
                    <label for="baidescricao">Descrição..:</label>
                    <input type="text" value="${param.baidescricao}" name="baidescricao"  id="baidescricao" required="true" size="30" maxlength="30"/>
                    <br /><br />
                    <label for="selectuf">UF..:</label>
        
                    <select name="selectuf" id="selectUF">
                        
                    </select>
                 </div>   
                 <br />
                <input type="submit" name="acao" value="alterar"/>
    </c:otherwise>
</c:choose>
</form>
<c:import url="rodape.jsp"/>