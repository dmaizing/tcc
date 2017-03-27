<%@tag body-content="empty" %>
<%
    int limite = 10;
    String numPagina = request.getParameter("numpagina");
    if (numPagina == null) {
        numPagina = "1";
    }


    java.util.List listaDeCategorias = (java.util.List) request.getAttribute("sessaoListaCategoriaPaginada");
    String ordenacao = request.getParameter("ordenacao");
    if (ordenacao == null)
        ordenacao = "catdescricao";
    
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa == null)
        pesquisa = "";
    
    String campoapesquisar = request.getParameter("campoapesquisar");
    
    if (campoapesquisar == null)
        campoapesquisar = "catdescricao"; 
    
    //out.println("campopesquisar = "+campoapesquisar);
    out.println("<table style='margin-top:12px;'>");
    out.println("<form action='Categoria' method='get' >");
    out.println("<tr><td colspan='4'>Localizar por: ");
    out.println("<select name='campoapesquisar'>");
    if (campoapesquisar.equals("catdescricao"))
        out.println("<option value='catdescricao' selected='selected'>Descrição</option>");
    else 
        out.println("<option value='catdescricao'>Decrição</option>");
    
    if (campoapesquisar.equals("catcodigo"))
        out.println("<option value='catcodigo' selected='selected'>Código</option>");
    else
        out.println("<option value='catcodigo' >Código</option>");
    
   
    out.println("</select>");
    out.println("<input type='text' name='pesquisa' value='"+pesquisa+"'/>");
    out.println("<input type='hidden' name='acao' value='listarCategoria'/>");
    out.println("<input type='image' src='imagens/localizar.png'/></td></tr>");
    out.println("</form>");
    out.println("<tr><td colspan='2'><a href='categoria.jsp?acao=novo'><b>Novo Registro</b></a></td><td colspan='2'>Ordenado por "+ordenacao+"</td></tr>");
    out.println("<tr class='linhaespecialTable'>");
    out.println("<td><a href='Categoria?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarCategoria&ordenacao=catcodigo&numpagina=" +Integer.parseInt(numPagina)+"'>Código</a></td><td><a href='Categoria?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarCategoria&ordenacao=catdescricao&numpagina=" +Integer.parseInt(numPagina)+"'>Descrição</a></td><td>Alterar</td><td>Excluir</td>");
    out.println("</tr>");
    int contador = 0;
    for (java.util.Iterator iterator = listaDeCategorias.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.CategoriaModel categorias = (br.com.futurebrindes.javabean.model.CategoriaModel) iterator.next();
        if (contador % 2 == 0) {
            out.println("<tr style='background: yellow'>");
        } else {
            out.println("<tr>");
        }
        int catCodigo = categorias.getCatCodigo();
        String catDescricao = categorias.getCatDescricao();
        out.println("<td>" + catCodigo + "</td>");
        out.println("<td>" + catDescricao + "</td>");
        out.println("<td><div align='center'><a href='categoria.jsp?acao=alterar&catcodigo=" + catCodigo +"&catdescricao=" + catDescricao + "'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        out.println("<td><div align='center'><a href='Categoria?&numpagina="+Integer.parseInt(numPagina)+"&campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&acao=excluir&catcodigo=" + catCodigo + "'><img src='imagens/delete.png' alt='Excluir'/></a></div></td>");
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
        out.println("<a href=Categoria?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarCategoria&numpagina=" + pagAnterior + ">Anterior</a>");
    }

    for (int i = 1; i <= totalPaginas; i++) {
        if (i == Integer.parseInt(numPagina)) {
            out.println("<b>" + i + "</b>");
        } else {
            out.println("<a href=Categoria?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarCategoria&numpagina=" + i + ">" + i + "</a>");
        }
    }

    int proximaPag;
    if ((Integer.parseInt(totalRegistros) - (Integer.parseInt(numPagina) * limite)) > 0) {
        proximaPag = Integer.parseInt(numPagina) + 1;
        out.println("<a href=Categoria?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarCategoria&numpagina=" + proximaPag + ">Proxima</a>");
    }
    
    out.println(". Total de registros: " + totalRegistros + ". Total de Pag: " + totalPaginas + "</td></tr>");
    out.println("</table>");

%>