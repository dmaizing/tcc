<%@tag body-content="empty" %>
<%
    int limite = 10;
    String numPagina = request.getParameter("numpagina");
    if (numPagina == null) {
        numPagina = "1";
    }


    java.util.List listaDeBairros = (java.util.List) request.getAttribute("sessaoListaBairroPaginada");
    String ordenacao = request.getParameter("ordenacao");
    if (ordenacao == null)
        ordenacao = "baidescricao";
    
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa == null)
        pesquisa = "";
    String campoapesquisar = request.getParameter("campoapesquisar");
    if (campoapesquisar == null)
        campoapesquisar = "baidescricao"; 
    
    //out.println("campopesquisar = "+campoapesquisar);
    out.println("<table style='margin-top:12px;'>");
    out.println("<form action='Bairro' method='get' >");
    out.println("<tr><td colspan='4'>Localizar por: ");
    out.println("<select name='campoapesquisar'>");
    if (campoapesquisar.equals("baidescricao"))
        out.println("<option value='baidescricao' selected='selected'>Descrição</option>");
    else 
        out.println("<option value='baidescricao'>Decrição</option>");
    
    if (campoapesquisar.equals("baicodigo"))
        out.println("<option value='baicodigo' selected='selected'>Código</option>");
    else
        out.println("<option value='baicodigo' >Código</option>");
    
   
    out.println("</select>");
    out.println("<input type='text' name='pesquisa' value='"+pesquisa+"'/>");
    out.println("<input type='hidden' name='acao' value='listarBairro'/>");
    out.println("<input type='image' src='imagens/localizar.png'/></td></tr>");
    out.println("</form>");
    out.println("<tr><td colspan='2'><a href='bairro.jsp?acao=novo'><b>Novo Registro</b></a></td><td colspan='2'>Ordenado por "+ordenacao+"</td></tr>");
    out.println("<tr class='linhaespecialTable'>");
    out.println("<td><a href='Bairro?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarBairro&ordenacao=baiCodigo&numpagina=" +Integer.parseInt(numPagina)+"'>Código</a></td><td><a href='Bairro?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarBairro&ordenacao=baiDescricao&numpagina=" +Integer.parseInt(numPagina)+"'>Descrição</a></td><td>Alterar</td><td>Excluir</td>");
    out.println("</tr>");
    int contador = 0;
    for (java.util.Iterator iterator = listaDeBairros.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.BairroModel bairros = (br.com.futurebrindes.javabean.model.BairroModel) iterator.next();
        if (contador % 2 == 0) {
            out.println("<tr style='background: yellow'>");
        } else {
            out.println("<tr>");
        }
        int baiCodigo = bairros.getBaiCodigo();
        String baiDescricao = bairros.getBaiDescricao();
        out.println("<td>" + baiCodigo + "</td>");
        out.println("<td>" + baiDescricao + "</td>");
        out.println("<td><div align='center'><a href='bairro.jsp?acao=alterar&baicodigo=" + baiCodigo +"&baidescricao=" + baiDescricao + "'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        out.println("<td><div align='center'><a href='Bairro?&numpagina="+Integer.parseInt(numPagina)+"&campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&acao=excluir&baicodigo=" + baiCodigo + "'><img src='imagens/delete.png' alt='Excluir'/></a></div></td>");
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
        out.println("<a href=Bairro?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarBairro&numpagina=" + pagAnterior + ">Anterior</a>");
    }

    for (int i = 1; i <= totalPaginas; i++) {
        if (i == Integer.parseInt(numPagina)) {
            out.println("<b>" + i + "</b>");
        } else {
            out.println("<a href=Bairro?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarBairro&numpagina=" + i + ">" + i + "</a>");
        }
    }

    int proximaPag;
    if ((Integer.parseInt(totalRegistros) - (Integer.parseInt(numPagina) * limite)) > 0) {
        proximaPag = Integer.parseInt(numPagina) + 1;
        out.println("<a href=Bairro?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarBairro&numpagina=" + proximaPag + ">Proxima</a>");
    }
    out.println(". Total de registros: " + totalRegistros + ". Total de Pag: " + totalPaginas + "</td></tr>");
    out.println("</table>");

%>