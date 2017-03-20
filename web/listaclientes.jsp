<%-- 
    Videoaulas Neri www.informaticon.com.br   email:videoaulaneri@gmail.com
--%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>
<h1>Lista de Clientes</h1>
<tagsTCC:listaClientesTablePaginada />
<c:import url="rodape.jsp"/>
