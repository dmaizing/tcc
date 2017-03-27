<%@tag body-content="empty" %>
<%
    int limite = 10;
    String numPagina = request.getParameter("numpagina");
    if (numPagina == null) {
        numPagina = "1";
    }


    java.util.List listaDeCidades = (java.util.List) request.getAttribute("sessaoListaCidadePaginada");
    String ordenacao = request.getParameter("ordenacao");
    if (ordenacao == null)
        ordenacao = "ciddescricao";
    
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa == null)
        pesquisa = "";
    
    String campoapesquisar = request.getParameter("campoapesquisar");
    
    if (campoapesquisar == null)
        campoapesquisar = "ciddescricao"; 
    
    //out.println("campopesquisar = "+campoapesquisar);
    out.println("<table style='margin-top:12px;'>");
    out.println("<form action='Cidade' method='get' >");
    out.println("<tr><td colspan='4'>Localizar por: ");
    out.println("<select name='campoapesquisar'>");
    if (campoapesquisar.equals("ciddescricao"))
        out.println("<option value='ciddescricao' selected='selected'>Descrição</option>");
    else 
        out.println("<option value='ciddescricao'>Decrição</option>");
    
    if (campoapesquisar.equals("cidcodigo"))
        out.println("<option value='cidcodigo' selected='selected'>Código</option>");
    else
        out.println("<option value='cidcodigo' >Código</option>");
    
   
    out.println("</select>");
    out.println("<input type='text' name='pesquisa' value='"+pesquisa+"'/>");
    out.println("<input type='hidden' name='acao' value='listarCidade'/>");
    out.println("<input type='image' src='imagens/localizar.png'/></td></tr>");
    out.println("</form>");
    out.println("<tr><td colspan='2'><a href='cidade.jsp?acao=novo'><b>Novo Registro</b></a></td><td colspan='2'>Ordenado por "+ordenacao+"</td></tr>");
    out.println("<tr class='linhaespecialTable'>");
    out.println("<td><a href='Cidade?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarCidade&ordenacao=cidcodigo&numpagina=" +Integer.parseInt(numPagina)+"'>Código</a></td><td><a href='Cidade?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarCidade&ordenacao=ciddescricao&numpagina=" +Integer.parseInt(numPagina)+"'>Descrição</a></td><td>Alterar</td><td>Excluir</td>");
    out.println("</tr>");
    int contador = 0;
    for (java.util.Iterator iterator = listaDeCidades.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.CidadeModel cidades = (br.com.futurebrindes.javabean.model.CidadeModel) iterator.next();
        if (contador % 2 == 0) {
            out.println("<tr style='background: yellow'>");
        } else {
            out.println("<tr>");
        }
        int cidCodigo = cidades.getCidCodigo();
        String cidDescricao = cidades.getCidDescricao();
        out.println("<td>" + cidCodigo + "</td>");
        out.println("<td>" + cidDescricao + "</td>");
        out.println("<td><div align='center'><a href='cidade.jsp?acao=alterar&cidcodigo=" + cidCodigo +"&ciddescricao=" + cidDescricao + "'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        out.println("<td><div align='center'><a href='Cidade?&numpagina="+Integer.parseInt(numPagina)+"&campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&acao=excluir&cidcodigo=" + cidCodigo + "'><img src='imagens/delete.png' alt='Excluir'/></a></div></td>");
        out.println("</tr>");
        contador++;
    }


    String totalRegistros = (String) request.getAttribute("sessaoqtdTotalRegistros");
   
    
    int totalPaginas = Integer.parseInt(totalRegistros) / limite;
    if (Integer.parseInt(totalRegistros) % limite != 0) {
        totalPaginas++;
    }
   
    out.println("<tr class='linhaespecialTable'><td colspan='4'>");
    int pagAnterior;
    if (Integer.parseInt(numPagina) > 1) {
        pagAnterior = Integer.parseInt(numPagina) - 1;
        out.println("<a href=Cidade?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarCidade&numpagina=" + pagAnterior + ">Anterior</a>");
    }

    for (int i = 1; i <= totalPaginas; i++) {
        if (i == Integer.parseInt(numPagina)) {
            out.println("<b>" + i + "</b>");
        } else {
            out.println("<a href=Cidade?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarCidade&numpagina=" + i + ">" + i + "</a>");
        }
    }

    int proximaPag;
    if ((Integer.parseInt(totalRegistros) - (Integer.parseInt(numPagina) * limite)) > 0) {
        proximaPag = Integer.parseInt(numPagina) + 1;
        out.println("<a href=Cidade?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarCidade&numpagina=" + proximaPag + ">Proxima</a>");
    }
    out.println(". Total de registros: " + totalRegistros + ". Total de Pag: " + totalPaginas + "</td></tr>");
    out.println("</table>");

%></h2>