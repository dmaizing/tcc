<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>
<h1>Lista de Categorias</h1>
<tagsTCC:listaCategoriasTablePaginada/>
<c:import url="rodape.jsp"/>
