<%@tag body-content="empty" %>
<jsp:useBean id="beanFornecedor" class="br.com.futurebrindes.dao.FornecedorDao"></jsp:useBean>
<%
    String selected="";
    java.util.List listaDeFornecedor = (java.util.List) beanFornecedor.getListaFornecedorParaSelectOption();
    //out.println("<option value='baicodigo' >Código</option>");
    for (java.util.Iterator iterator = listaDeFornecedor.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.FornecedorModel fornecedor = (br.com.futurebrindes.javabean.model.FornecedorModel) iterator.next();
        int fornCodigo = fornecedor.getForCodigo();
        String fornNome = fornecedor.getForNome();
        if (request.getParameter("for_codigo") != null) {
        	if (fornCodigo == Integer.parseInt(request.getParameter("for_codigo")))
        		selected="selected";
        	else
        		selected="";
        }
        out.println("<option value="+fornCodigo+" "+selected+">" + fornNome + "</option>");
		
    }

%>