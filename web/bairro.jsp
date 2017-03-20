<%-- 
    Videoaulas Neri www.informaticon.com.br   email:videoaulaneri@gmail.com
--%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>

<form id="formBairro" method="get" action="Bairro">
    <c:choose>
        <c:when test="${param.acao eq 'novo'}">
                <h1>Novo Bairro</h1><br>                    
                <div class="campos">
                    <label for="baidescricao">Descrição..:</label>
                    <input type="text" name="baidescricao"  id="baidescricao" required="true" size="30" maxlength="30"/>
                </div><br>
                <input type="submit" name="acao" value="novo"/>
            </c:when>
            <c:otherwise>
                <h1>Alteração de Bairro</h1><br>
                 <div class="campos">
                    <label for="baicodigo">Código..:</label>
                    <input type="text" value="${param.baicodigo}" name="baicodigo"  id="baicodigo" required="true" size="5" readonly="true"/>
                </div><br>
                    <div class="campos">
                    <label for="baidescricao">Descrição..:</label>
                    <input type="text" value="${param.baidescricao}" name="baidescricao"  id="baidescricao" required="true" size="30" maxlength="30"/>
                </div><br>
                <input type="submit" name="acao" value="alterar"/>
    </c:otherwise>
</c:choose>
</form>
<c:import url="rodape.jsp"/>