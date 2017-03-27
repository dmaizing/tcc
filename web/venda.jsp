<%@page import="br.com.futurebrindes.javabean.model.ItensVendaModel"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTCC" %>
<tagsTCC:verificaSessao/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp"/>

<form id="formVenda" class="formVenda" method="get" action="Venda">
      <h1>Nova Venda de Mercadorias</h1><br>                    
      <label for="vencodigo">Codigo.:</label>
      <input type="text" name="vencodigo"  id="vencodigo" size="5" maxlength="" disabled="disabled"
         Value="
      	<%
      		if (request.getParameter("vencodigo") != null)
      			out.println(request.getParameter("vencodigo"));
      		else if (request.getAttribute("sessaoTotalRegistroVenda") != null)
      				out.println(request.getAttribute("sessaoTotalRegistroVenda"));
      	%>"
      />
      <label for="clicodigo">Cliente.:</label>
      <input type="text" name="clicodigo"  id="clicodigo" size="5"  value="${param.clicodigo}" />
      <select name="selectCliente" id="selectCliente" >
      	      <tagsTCC:listaClienteSelect/>
      </select>
      <label for="vendata">Data.:</label>
      <!--<input type="date" name="vendata"  id="vendata" size="10" value="${param.vendata}" />-->
      
      <input type="text" name="vendata"  id="vendata" size="10" value="${param.vendata}" />
      
      <label for="venvalortotal">Valor Total:</label>
      		<input type="text" name="venvalortotal"  id="venvalortotal" size="10" value="${param.venvalortotal}" />    
      
      
      <input type="hidden" name="acao" value="novo" />
      <input type="submit" value="Gerar Venda" />      
</form>
<form id="formItensVenda" class="formItensVenda" method="get" action="Venda" 
<%  if (request.getParameter("clicodigo") == null)
        out.println("hidden='true'");
        %>
        >

      <h1>Mercadorias da Venda</h1><br>                  
      <label for="vencodigo">Codigo.:</label>
      <input type="text" name="vencodigo" id="vencodigo" readonly="readonly" value="<%
    		  if (request.getParameter("vencodigo") != null)
        			out.println(request.getParameter("vencodigo"));
        		else if (request.getAttribute("sessaoTotalRegistroVenda") != null)
        				out.println(request.getAttribute("sessaoTotalRegistroVenda"));
      	%>" />
      
      <label for="codproduto">Produto.:</label>
      <input type="text" name="codproduto"  id="codproduto" size="5" maxlength="" disabled="disabled"/>
      <select name="selectProduto" id="selectProduto" >
      	<tagsTCC:listaProdutoSelect/>
      </select>
      <label for="quantidade">Quant.:</label>
      <input type="text" name="quantidade"  id="quantidade" size="10" />    
       <input type="hidden" name="clicodigo"  id="clicodigo" value="${param.clicodigo}" /> 
       
      <input type="hidden" name="vendata"  id="vendata" value="${param.vendata}" />
      <input type="hidden" name="venvalortotal"  id="venvalortotal" value="${param.venvalortotal}" /> 
      <input type="hidden" name="acao" value="novoItemVenda" />
      <input type="submit" value="Inserir" /> <br>
      
      <%
   		
      if(request.getAttribute("sessaolistaItensVenda") != null) {

    java.util.List listaDeItensVenda = (java.util.List) request.getAttribute("sessaolistaItensVenda");
   
    out.println("<table id='tabelaItensVenda'>");
    out.println("<tr><td>Código Venda</td><td>Código do Produto</td><td>Descrição do Produto</td><td>Quantidade</td><td>Valor Unitário</td><td>Valor Total</td><tr>");
    int contador = 0;
    double totalDaVenda=0;
    for (java.util.Iterator iterator = listaDeItensVenda.iterator(); iterator.hasNext();) {
        ItensVendaModel itensVendaModel = (ItensVendaModel) iterator.next();
        if (contador % 2 == 0) {
            out.println("<tr style='background: yellow'>");
        } else {
            out.println("<tr>");
        }
        int codigoVenda = itensVendaModel.getVencodigo();
        int codigoProduto = itensVendaModel.getProcodigo();
        int quantidade = itensVendaModel.getVenquantidade();
        String descricao = itensVendaModel.getProdescricao();
        double precoUnitario = itensVendaModel.getPrecounitario();
        //double valorTotal = itensVendaModel.getVentotal();
        double valorTotal = precoUnitario * quantidade;
        totalDaVenda+=valorTotal;
        out.println("<td>" + codigoVenda + "</td>");
        out.println("<td>" + codigoProduto + "</td>");
        out.println("<td>" + descricao + "</td>");
        out.println("<td align='center'>" + quantidade + "</td>");
        out.println("<td align='right'>" + precoUnitario + "</td>");
        out.println("<td align='right'>" + valorTotal + "</td>");
        out.println("</tr>");
        contador++;
    }
    out.println("<tr>");
    	out.println("<td align='right' colspan='5'>Valor Total desta Venda</td>");
    	out.println("<td align='right'>"+totalDaVenda+"<tr>");
    out.println("</tr>");
    out.println("</table>");
      }
%>
           
</form>

<script>
function atualizaCodigoCliente() {
	var getCliCodigo = document.forms['formVenda']['selectCliente'].value;
	document.forms['formVenda']['clicodigo'].value =getCliCodigo;
}
function atualizaCodigoProduto() {
	var getProCodigo = document.forms['formItensVenda']['selectProduto'].value;
	document.forms['formItensVenda']['codproduto'].value =getProCodigo;
}
window.onload = function() {
	document.forms['formVenda']['selectCliente'].onclick = atualizaCodigoCliente;
	document.forms['formItensVenda']['selectProduto'].onclick = atualizaCodigoProduto;

};
</script>
<c:import url="rodape.jsp"/>
