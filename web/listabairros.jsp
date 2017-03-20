<%-- 
    Videoaulas Neri www.informaticon.com.br   email:videoaulaneri@gmail.com
--%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsNeri" %>
<tagsNeri:verificaSessao/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>
<h1>Lista de Bairros</h1>
<tagsNeri:listaBairrosTablePaginada/>
<c:import url="rodape.jsp"/>
