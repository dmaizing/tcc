<%@tag body-content="empty" %>
<jsp:useBean id="beanBairro" class="br.com.futurebrindes.dao.BairroDao"></jsp:useBean>
<%
    String selected="";
    java.util.List listaDeBairro = (java.util.List) beanBairro.getListaBairroParaSelectOption();
    //out.println("<option value='baicodigo' >Código</option>");
    for (java.util.Iterator iterator = listaDeBairro.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.BairroModel bairro = (br.com.futurebrindes.javabean.model.BairroModel) iterator.next();
        int baiCodigo = bairro.getBaiCodigo();
        String baiDescricao = bairro.getBaiDescricao();
        if (request.getParameter("baicodigo") != null) {
        	if (baiCodigo == Integer.parseInt(request.getParameter("baicodigo")))
        		selected="selected";
        	else
        		selected="";
        }
        out.println("<option value="+baiCodigo+" "+selected+">" + baiDescricao + "</option>");
		
    }

%>