<%@tag body-content="empty" %>
<jsp:useBean id="beanCategoria" class="br.com.futurebrindes.dao.CategoriaDao"></jsp:useBean>
<%
    String selected="";
    java.util.List listaDeCategoria = (java.util.List) beanCategoria.getListaCategoriaCombo();
    for (java.util.Iterator iterator = listaDeCategoria.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.CategoriaModel categoria = (br.com.futurebrindes.javabean.model.CategoriaModel) iterator.next();
        int catCodigo = categoria.getCatCodigo();
        String catDescricao = categoria.getCatDescricao();
        if (request.getParameter("catcodigo") != null) {
        	if (catCodigo == Integer.parseInt(request.getParameter("catcodigo")))
        		selected="selected";
        	else
        		selected="";
        }
        out.println("<option value="+catCodigo+" "+selected+">" + catDescricao + "</option>");
		
    }

%>