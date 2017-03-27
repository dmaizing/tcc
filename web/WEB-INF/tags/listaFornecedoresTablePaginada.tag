<%-- 
    Document   : listaFornecedoresTablePaginada
    Created on : 22/03/2017, 12:48:35
    Author     : Davis-W10
--%>
<%@tag body-content="empty" import="java.util.*, java.text.*" %>
<%@tag description="Lista dos fornecedores" pageEncoding="UTF-8"%>

<%
    
    request.setCharacterEncoding("UTF8");
    
    int limite = 10;
    String numPagina = request.getParameter("numpagina");
    if (numPagina == null) {
        numPagina = "1";
    }


    List listaDeFornecedores = (List) request.getAttribute("sessaoListaFornecedorPaginada");
    
    String ordenacao = request.getParameter("ordenacao");
    if (ordenacao == null)
        ordenacao = "for_nome";
    
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa == null)
        pesquisa = "";
    String campoapesquisar = request.getParameter("campoapesquisar");
    if (campoapesquisar == null)
        campoapesquisar = "for_nome"; 
    
    //out.println("campopesquisar = "+campoapesquisar);
    out.println("<table style='margin-top:5px; font-family:Arial;font-size:13px;'>");
    out.println("<form action='Fornecedor' method='get' >");
    out.println("<tr><td colspan='4'>Localizar por: ");
    out.println("<select name='campoapesquisar'>");
    if (campoapesquisar.equals("for_nome"))
        out.println("<option value='for_nome' selected='selected'>Nome</option>");
    else 
        out.println("<option value='for_nome'>Decrição</option>");
    
    if (campoapesquisar.equals("for_codigo"))
        out.println("<option value='for_codigo' selected='selected'>Código</option>");
    else
        out.println("<option value='for_codigo' >Código</option>");
    
   
    out.println("</select>");
    out.println("<input type='text' name='pesquisa' value='"+pesquisa+"'/>");
    out.println("<input type='hidden' name='acao' value='listarFornecedor'/>");
    out.println("<input type='image' src='imagens/localizar.png'/></td></tr>");
    out.println("</form>");
    out.println("<tr class='linhaNovoRegistro'><td colspan='2'><a href='fornecedor.jsp?acao=novo'><b>Novo Registro</b></a></td><td colspan='2'>Ordenado por "+ordenacao+"</td></tr>");
    out.println("<tr class='linhaEspecialTable'>");
    out.println("<td><a href='Fornecedor?pesquisa="+pesquisa+
            "&campoapesquisar="+campoapesquisar+
            "&acao=listarFornecedor&ordenacao=for_codigo&numpagina="+
            Integer.parseInt(numPagina)+"'>Código</a></td><td><a href='Fornecedor?pesquisa="+pesquisa+
            "&campoapesquisar="+campoapesquisar+"&acao=listarFornecedor&ordenacao=for_nome&numpagina="+
            Integer.parseInt(numPagina)+
            "'>Descrição</a></td><td>CNPJ</td><td>IE</td><td>Data Cad.</td><td>Telefone</td><td>Telefone</td><td>Email</td><td>Alterar</td><td>Excluir</td>");
    out.println("</tr>");
    int contador = 0;
    
    for (Iterator iterator = listaDeFornecedores.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.FornecedorModel fornecedores = (br.com.futurebrindes.javabean.model.FornecedorModel) iterator.next();
        if (contador % 2 == 0) {
            out.println("<tr style='background: yellow'>");
        } else {
            out.println("<tr>");
        }
        int forCodigo         = fornecedores.getForCodigo();
        String forNome        = fornecedores.getForNome();
        String forFantasia    = fornecedores.getForFantasia();
        int logCodigo         = fornecedores.getLogCodigo();
        int baiCodigo         = fornecedores.getBaiCodigo();
        int cidCodigo         = fornecedores.getCidCodigo();
        String forNumero      = fornecedores.getForNumero();
        String forComplemento = fornecedores.getForComplemento();
        String forCep         = fornecedores.getForCep();
        String forCNPJ        = fornecedores.getForCNPJ();
        String forInscEst     = fornecedores.getForInscEst();
        //------------------data
        
        Date forDataCadastro  = fornecedores.getForDataCadastro();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
        String dataCadastroFormatada   = dataFormatada.format(forDataCadastro);
        //--------------------------
        String forFoneCel = fornecedores.getForFoneCel();
        String forFone2   = fornecedores.getForFone2();
        String forEmail   = fornecedores.getForEmail();
        String forObs     = fornecedores.getForObs();
        
        
        out.println("<td class='linhaRegistroNormal'>" + forCodigo + "</td>");
        out.println("<td class='linhaRegistroNormal'>" + forNome + "</td>");
        out.println("<td class='linhaRegistroNormal'>" + forCNPJ + "</td>");
        out.println("<td class='linhaRegistroNormal'>" + forInscEst + "</td>");
        out.println("<td class='linhaRegistroNormal'>" + dataCadastroFormatada + "</td>");
        out.println("<td class='linhaRegistroNormal'>" + forFoneCel + "</td>");
        out.println("<td class='linhaRegistroNormal'>" + forFone2 + "</td>");
        out.println("<td class='linhaRegistroNormal'>" + forEmail + "</td>");
        
        out.println("<td><div align='center'><a href='fornecedor.jsp?acao=alterar&for_codigo=" + forCodigo +"&for_nome=" + forNome + "&for_fantasia="+forFantasia+"&log_codigo=" + logCodigo + "&bai_codigo=" + baiCodigo + "&cid_codigo=" + cidCodigo + "&for_numero="+forNumero+"&for_complemento="+forComplemento+"&for_cep="+forCep+"&for_cnpj="+forCNPJ+"&for_inscest="+forInscEst+"&for_datacadastro="+dataCadastroFormatada+"&for_fonecel="+forFoneCel+"&for_fone2="+forFone2+"&for_email="+forEmail+"&for_obs="+forObs+"'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        out.println("<td><div align='center'><a href='Fornecedor?&numpagina="+Integer.parseInt(numPagina)+"&campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&acao=excluir&for_codigo=" + forCodigo + "'><img src='imagens/delete.png' alt='Excluir'/></a></div></td>");
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
        out.println("<a href=Fornecedor?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarFornecedor&numpagina=" + pagAnterior + ">Anterior</a>");
    }

    for (int i = 1; i <= totalPaginas; i++) {
        if (i == Integer.parseInt(numPagina)) {
            out.println("<b>" + i + "</b>");
        } else {
            out.println("<a href=Fornecedor?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarFornecedor&numpagina=" + i + ">" + i + "</a>");
        }
    }

    int proximaPag;
    if ((Integer.parseInt(totalRegistros) - (Integer.parseInt(numPagina) * limite)) > 0) {
        proximaPag = Integer.parseInt(numPagina) + 1;
        out.println("<a href=Fornecedor?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarFornecedor&numpagina=" + proximaPag + ">Proxima</a>");
    }
    out.println(". Total de registros: " + totalRegistros + ". Total de Pag: " + totalPaginas + "</td></tr>");
    out.println("</table>");

%>