<%@tag body-content="empty" %>
<jsp:useBean id="beanCliente" class="br.com.futurebrindes.dao.ProdutoDao"></jsp:useBean>
<%
    String selected="";
    java.util.List listaDeProduto = (java.util.List) beanCliente.getListaProdutoParaSelectOption();
    //out.println("<option value='baicodigo' >Código</option>");
    for (java.util.Iterator iterator = listaDeProduto.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.ProdutoModel produtoModel = (br.com.futurebrindes.javabean.model.ProdutoModel) iterator.next();
        int proCodigo = produtoModel.getProCodigo();
        String proDescricao = produtoModel.getProDescricao();
        out.println("<option value="+proCodigo+">" + proDescricao + "</option>");		
    }
%>