<%@tag body-content="empty" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${nomeCompleto != null}">
        Usu&aacute;rio logado:${nomeCompleto}
    </c:when>
    <c:otherwise>
        Nenhum usu&aacute;rio logado
    </c:otherwise>
</c:choose>