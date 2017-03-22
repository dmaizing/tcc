<%@tag body-content="empty" %>
<%
    request.setCharacterEncoding("UTF8");
    
    int limite = 10;
    String numPagina = request.getParameter("numpagina");
    if (numPagina == null) {
        numPagina = "1";
    }


    java.util.List listaDeLogradouros = (java.util.List) request.getAttribute("sessaoListaLogradouroPaginada");
    
    String ordenacao = request.getParameter("ordenacao");
    if (ordenacao == null)
        ordenacao = "logdescricao";
    
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa == null)
        pesquisa = "";
    String campoapesquisar = request.getParameter("campoapesquisar");
    if (campoapesquisar == null)
        campoapesquisar = "logdescricao"; 
    
    //out.println("campopesquisar = "+campoapesquisar);
    out.println("<table style='margin-top:5px; font-family:Arial;font-size:13px;'>");
    out.println("<form action='Logradouro' method='get' >");
    out.println("<tr><td colspan='4'>Localizar por: ");
    out.println("<select name='campoapesquisar'>");
    if (campoapesquisar.equals("logdescricao"))
        out.println("<option value='logdescricao' selected='selected'>Descrição</option>");
    else 
        out.println("<option value='logdescricao'>Decrição</option>");
    
    if (campoapesquisar.equals("logcodigo"))
        out.println("<option value='logcodigo' selected='selected'>Código</option>");
    else
        out.println("<option value='logcodigo' >Código</option>");
    
   
    out.println("</select>");
    out.println("<input type='text' name='pesquisa' value='"+pesquisa+"'/>");
    out.println("<input type='hidden' name='acao' value='listarLogradouro'/>");
    out.println("<input type='image' src='imagens/localizar.png'/></td></tr>");
    out.println("</form>");
    out.println("<tr><td colspan='2'><a href='logradouro.jsp?acao=novo'><b>Novo Registro</b></a></td><td colspan='2'>Ordenado por "+ordenacao+"</td></tr>");
    out.println("<tr class='linhaespecialTable'>");
    out.println("<td><a href='Logradouro?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarLogradouro&ordenacao=logcodigo&numpagina=" +Integer.parseInt(numPagina)+"'>Código</a></td><td><a href='Logradouro?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarLogradouro&ordenacao=logdescricao&numpagina=" +Integer.parseInt(numPagina)+"'>Descrição</a></td><td>Alterar</td><td>Excluir</td>");
    out.println("</tr>");
    int contador = 0;
    
    for (java.util.Iterator iterator = listaDeLogradouros.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.LogradouroModel logradouro = (br.com.futurebrindes.javabean.model.LogradouroModel) iterator.next();
        if (contador % 2 == 0) {
            out.println("<tr style='background: yellow'>");
        } else {
            out.println("<tr>");
        }
        int logcodigo = logradouro.getLogcodigo();
        String logdescricao = logradouro.getLogdescricao();
        out.println("<td>" + logcodigo + "</td>");
        out.println("<td>" + logdescricao + "</td>");
        out.println("<td><div align='center'><a href='logradouro.jsp?acao=alterar&logcodigo=" + logcodigo +"&logdescricao=" + logdescricao + "'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        out.println("<td><div align='center'><a href='Logradouro?&numpagina="+Integer.parseInt(numPagina)+"&campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&acao=excluir&logcodigo=" + logcodigo + "'><img src='imagens/delete.png' alt='Excluir'/></a></div></td>");
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
        out.println("<a href=Logradouro?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarLogradouro&numpagina=" + pagAnterior + ">Anterior</a>");
    }

    for (int i = 1; i <= totalPaginas; i++) {
        if (i == Integer.parseInt(numPagina)) {
            out.println("<b>" + i + "</b>");
        } else {
            out.println("<a href=Logradouro?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarLogradouro&numpagina=" + i + ">" + i + "</a>");
        }
    }

    int proximaPag;
    if ((Integer.parseInt(totalRegistros) - (Integer.parseInt(numPagina) * limite)) > 0) {
        proximaPag = Integer.parseInt(numPagina) + 1;
        out.println("<a href=Logradouro?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarLogradouro&numpagina=" + proximaPag + ">Proxima</a>");
    }
    out.println(". Total de registros: " + totalRegistros + ". Total de Pag: " + totalPaginas + "</td></tr>");
    out.println("</table>");

%>