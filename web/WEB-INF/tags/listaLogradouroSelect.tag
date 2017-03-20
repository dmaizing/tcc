<%@tag body-content="empty" %>
<<jsp:useBean id="beanLogradouro" class="br.com.futurebrindes.dao.LogradouroDao"></jsp:useBean>
<%
	String selected="";
    java.util.List listaDeLogradouro = (java.util.List) beanLogradouro.getListaLogradouroCombo();
    //out.println("<option value='baicodigo' >Código</option>");
    for (java.util.Iterator iterator = listaDeLogradouro.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.LogradouroModel logradouro = (br.com.futurebrindes.javabean.model.LogradouroModel) iterator.next();
        int logCodigo = logradouro.getLogcodigo();
        String logDescricao = logradouro.getLogdescricao();
        if (request.getParameter("logcodigo") != null) {
        	if (logCodigo == Integer.parseInt(request.getParameter("logcodigo")))
        		selected="selected";
        	else
        		selected="";
        }
        out.println("<option value="+logCodigo+" "+selected+">" + logDescricao + "</option>");
		
    }

%>