<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>
<h1>Lista de Logradouros</h1>
<tagsTCC:listaLogradouroTablePaginada/>
<c:import url="rodape.jsp"/>
