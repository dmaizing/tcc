<%@tag body-content="empty" %>
<jsp:useBean id="beanCliente" class="br.com.futurebrindes.dao.ClienteDao"></jsp:useBean>
<%
    String selected="";
    java.util.List listaDeCliente = (java.util.List) beanCliente.getListaClienteParaSelectOption();
    //out.println("<option value='baicodigo' >Código</option>");
    for (java.util.Iterator iterator = listaDeCliente.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.ClienteModel clienteModel = (br.com.futurebrindes.javabean.model.ClienteModel) iterator.next();
        int cliCodigo = clienteModel.getCliCodigo();
        String cliNome = clienteModel.getCliNome();
        out.println("<option value="+cliCodigo+">" + cliNome + "</option>");		
    }
%>