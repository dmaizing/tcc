<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao /> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>
<h1>Lista de Usuários</h1>
<tagsTCC:listaUsuariosTablePaginada/> 
<c:import url="rodape.jsp"/>