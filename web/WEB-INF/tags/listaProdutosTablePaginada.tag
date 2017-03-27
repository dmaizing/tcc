<%@tag body-content="empty" import="java.util.*, java.text.*" %>
<%
    
    request.setCharacterEncoding("UTF8");
    
    int limite = 10;
    String numPagina = request.getParameter("numpagina");
    if (numPagina == null) {
        numPagina = "1";
    }


    List listaDeProdutos = (List) request.getAttribute("sessaoListaProdutoPaginada");
    
    String ordenacao = request.getParameter("ordenacao");
    if (ordenacao == null)
        ordenacao = "prodescricao";
    
    String pesquisa = request.getParameter("pesquisa");
    
    if (pesquisa == null)
        pesquisa = "";
    
    String campoapesquisar = request.getParameter("campoapesquisar");
    
    if (campoapesquisar == null)
        campoapesquisar = "prodescricao"; 
    
    
    out.println("<table style='margin-top:5px; font-family:Arial;font-size:13px;'>");
    out.println("<form action='Produto' method='get' >");
    out.println("<tr><td colspan='4'>Localizar por: ");
    
    out.println("<select name='campoapesquisar'>");
        if (campoapesquisar.equals("prodescricao"))
            out.println("<option value='prodescricao' selected='selected'>Descrição</option>");
        else 
            out.println("<option value='prodescricao'>Decrição</option>");
    
        if (campoapesquisar.equals("procodigo"))
            out.println("<option value='procodigo' selected='selected'>Código</option>");
        else
            out.println("<option value='procodigo' >Código</option>");
    out.println("</select>");
    
    out.println("<input type='text' name='pesquisa' value='"+pesquisa+"'/>");
    out.println("<input type='hidden' name='acao' value='listarProduto'/>");
    out.println("<input type='image' src='imagens/localizar.png'/></td></tr>");
    out.println("</form>");
    
    out.println("<tr class='linhaNovoRegistro'><td colspan='2'><a href='produto.jsp?acao=novo'><b>Novo Registro</b></a></td><td colspan='2'>Ordenado por "+ordenacao+"</td></tr>");
    out.println("<tr class='linhaEspecialTable'>");
  //out.println("<td><a href='Produto?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarProduto&ordenacao=clicodigo&numpagina=" +Integer.parseInt(numPagina)+"'>Código</a></td><td><a href='Cliente?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarCliente&ordenacao=clinome&numpagina=" +Integer.parseInt(numPagina)+"'>Descrição</a></td><td>CPF</td><td>RG</td><td>Data Nascimento</td><td>Data Cadastro</td><td>Telefone</td><td>Email<td>Sexo</td></td><td>Alterar</td><td>Excluir</td>");
    out.println("<td><a href='Produto?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarProduto&ordenacao=procodigo&numpagina=" +Integer.parseInt(numPagina)+"'>Código</a></td><td><a href='Produto?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarProduto&ordenacao=prodescricao&numpagina=" +Integer.parseInt(numPagina)+"'>Descrição</a></td><td>QTD ESTOQUE</td><td>R$ CUSTO</td><td>R$ VENDA</td><td>Data Cadastro</td><td>Data Ult. Venda</td><td>Alterar</td><td>Excluir</td>");
    out.println("</tr>");
    int contador = 0;
    
    //if( listaDeClientes != null ){
    
    for (Iterator iterator = listaDeProdutos.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.ProdutoModel produtos = (br.com.futurebrindes.javabean.model.ProdutoModel) iterator.next();
        if (contador % 2 == 0) {
            out.println("<tr style='background: yellow'>");
        } else {
            out.println("<tr>");
        }
        int proCodigo         = produtos.getProCodigo();
        int catCodigo         = produtos.getCatCodigo();
        int forCodigo         = produtos.getForCodigo();
        String proDescricao   = produtos.getProDescricao();
        Double proQtdEstoque  = produtos.getProQtdEstoque();
        Double proPrecoCusto  = produtos.getProPrecoCusto();
        int proPercLucro      = produtos.getProPercLucro();
        Double proPrecoVenda  = produtos.getProPrecoVenda();
        
        //------------------datas
        Date proDataCadastro  = produtos.getProDataCadastro();
        Date proDataUltVenda  = produtos.getProDataUltVenda();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
        
        String dataCadastroFormatada   = dataFormatada.format(proDataCadastro);
        
        String dataUltVendaFormatada = null;
        if( proDataUltVenda != null ){
            dataUltVendaFormatada   = dataFormatada.format(proDataUltVenda);
        }else{
            dataUltVendaFormatada = "00-00-0000";
        }
        
        //--------------------------
        String proObs         = produtos.getProObs();
        
        out.println("<td>" + proCodigo + "</td>");
        out.println("<td>" + proDescricao + "</td>");
        out.println("<td>" + proQtdEstoque + "</td>");
        out.println("<td>" + proPrecoCusto + "</td>");
        out.println("<td>" + proPrecoVenda + "</td>");
        out.println("<td>" + dataCadastroFormatada + "</td>");
        out.println("<td>" + dataUltVendaFormatada + "</td>");
        
        out.println("<td><div align='center'><a href='produto.jsp?acao=alterar&procodigo="  + proCodigo + "&catcodigo=" + catCodigo + "&for_codigo=" + forCodigo + "&prodescricao="+proDescricao+"&proqtdestoque="+proQtdEstoque+"&proprecocusto="+proPrecoCusto+"&properclucro="+proPercLucro+"&proprecovenda="+proPrecoVenda+"&prodatacadastro="+dataCadastroFormatada+"&prodataultvenda="+dataUltVendaFormatada+"&pro_obs="+proObs+"'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        //out.println("<td><div align='center'><a href='produto.jsp?acao=alterar&procodigo="  + proCodigo + "&catcodigo=" + catCodigo + "&for_codigo=" + forCodigo + "&prodescricao="+proDescricao+"&proqtdestoque="+proQtdEstoque+"&proprecocusto="+proPrecoCusto+"&properclucro="+proPercLucro+"&proprecovenda="+proPrecoVenda+"&prodatacadastro="+dataCadastroFormatada+"&pro_obs="+proObs+"'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        out.println("<td><div align='center'><a href='Produto?&numpagina="+Integer.parseInt(numPagina)+"&campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&acao=excluir&procodigo=" + proCodigo + "'><img src='imagens/delete.png' alt='Excluir'/></a></div></td>");
        out.println("</tr>");
        contador++;
    }

    //}

    String totalRegistros = (String) request.getAttribute("sessaoqtdTotalRegistros");
   
    
    int totalPaginas = Integer.parseInt(totalRegistros) / limite;
    if (Integer.parseInt(totalRegistros) % limite != 0) {
        totalPaginas++;
    }
   
    out.println("<tr class='linhaEspecialTable'><td colspan='4'>");
    int pagAnterior;
    if (Integer.parseInt(numPagina) > 1) {
        pagAnterior = Integer.parseInt(numPagina) - 1;
        out.println("<a href=Produto?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarProduto&numpagina=" + pagAnterior + ">Anterior</a>");
    }

    for (int i = 1; i <= totalPaginas; i++) {
        if (i == Integer.parseInt(numPagina)) {
            out.println("<b>" + i + "</b>");
        } else {
            out.println("<a href=Produto?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarProduto&numpagina=" + i + ">" + i + "</a>");
        }
    }

    int proximaPag;
    if ((Integer.parseInt(totalRegistros) - (Integer.parseInt(numPagina) * limite)) > 0) {
        proximaPag = Integer.parseInt(numPagina) + 1;
        out.println("<a href=Produto?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarProduto&numpagina=" + proximaPag + ">Proxima</a>");
    }
    out.println(". Total de registros: " + totalRegistros + ". Total de Pag: " + totalPaginas + "</td></tr>");
    out.println("</table>");

%>