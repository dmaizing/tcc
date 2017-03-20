<%@tag body-content="empty" import="java.util.*, java.text.*" %>
<%
    int limite = 10;
    String numPagina = request.getParameter("numpagina");
    if (numPagina == null) {
        numPagina = "1";
    }


    List listaDeClientes = (List) request.getAttribute("sessaoListaClientePaginada");
    String ordenacao = request.getParameter("ordenacao");
    if (ordenacao == null)
        ordenacao = "clinome";
    
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa == null)
        pesquisa = "";
    String campoapesquisar = request.getParameter("campoapesquisar");
    if (campoapesquisar == null)
        campoapesquisar = "clinome"; 
    
    //out.println("campopesquisar = "+campoapesquisar);
    out.println("<table style='margin-top:5px; font-family:Arial;font-size:13px;'>");
    out.println("<form action='Cliente' method='get' >");
    out.println("<tr><td colspan='4'>Localizar por: ");
    out.println("<select name='campoapesquisar'>");
    if (campoapesquisar.equals("clinome"))
        out.println("<option value='clinome' selected='selected'>Nome</option>");
    else 
        out.println("<option value='clinome'>Decrição</option>");
    
    if (campoapesquisar.equals("clicodigo"))
        out.println("<option value='clicodigo' selected='selected'>Código</option>");
    else
        out.println("<option value='clicodigo' >Código</option>");
    
   
    out.println("</select>");
    out.println("<input type='text' name='pesquisa' value='"+pesquisa+"'/>");
    out.println("<input type='hidden' name='acao' value='listarCliente'/>");
    out.println("<input type='image' src='imagens/localizar.png'/></td></tr>");
    out.println("</form>");
    out.println("<tr class='linhaNovoRegistro'><td colspan='2'><a href='cliente.jsp?acao=novo'><b>Novo Registro</b></a></td><td colspan='2'>Ordenado por "+ordenacao+"</td></tr>");
    out.println("<tr class='linhaEspecialTable'>");
    out.println("<td><a href='Cliente?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarCliente&ordenacao=clicodigo&numpagina=" +Integer.parseInt(numPagina)+"'>Código</a></td><td><a href='Cliente?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarCliente&ordenacao=clinome&numpagina=" +Integer.parseInt(numPagina)+"'>Descrição</a></td><td>CPF</td><td>RG</td><td>Data Nascimento</td><td>Data Cadastro</td><td>Telefone</td><td>Email<td>Sexo</td></td><td>Alterar</td><td>Excluir</td>");
    out.println("</tr>");
    int contador = 0;
    
    //if( listaDeClientes != null ){
    
    for (Iterator iterator = listaDeClientes.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.ClienteModel clientes = (br.com.futurebrindes.javabean.model.ClienteModel) iterator.next();
        if (contador % 2 == 0) {
            out.println("<tr style='background: yellow'>");
        } else {
            out.println("<tr>");
        }
        int cliCodigo         = clientes.getCliCodigo();
        String cliNome        = clientes.getCliNome();
        int logCodigo         = clientes.getLogCodigo();
        int baiCodigo         = clientes.getBaiCodigo();
        int cidCodigo         = clientes.getCidCodigo();
        String cliNumero      = clientes.getCliNumero();
        String cliComplemento = clientes.getCliComplemento();
        String cliCep         = clientes.getCliCep();
        String cliRg          = clientes.getCliRg();
        String cliCpf         = clientes.getCliCpf();
        //------------------data
        Date cliDataNasc               = clientes.getCliDataNasc();
        Date cliDataCadastro           = clientes.getCliDataCadastro();
        //SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
        String dataNascFormatada       = dataFormatada.format(cliDataNasc);
        String dataCadastroFormatada   = dataFormatada.format(cliDataCadastro);
        //--------------------------
                
        String cliFoneCel = clientes.getCliFoneCel();
        String cliFone2   = clientes.getCliFone2();
        String cliEmail   = clientes.getCliEmail();
        String cliFoto    = clientes.getCliFoto();
        String cliSexo    = clientes.getCliSexo();
        String cliObs     = clientes.getCliObs();
        
        
        out.println("<td>" + cliCodigo + "</td>");
        out.println("<td>" + cliNome + "</td>");
        out.println("<td>" + cliCpf + "</td>");
        out.println("<td>" + cliRg + "</td>");
        out.println("<td>" + dataNascFormatada + "</td>");
        out.println("<td>" + dataCadastroFormatada + "</td>");
        out.println("<td>" + cliFoneCel + "</td>");
        out.println("<td>" + cliEmail + "</td>");
        out.println("<td>" + cliSexo + "</td>");
        
        out.println("<td><div align='center'><a href='cliente.jsp?acao=alterar&clicodigo=" + cliCodigo +"&clinome=" + cliNome + "&logcodigo=" + logCodigo + "&baicodigo=" + baiCodigo + "&cidcodigo=" + cidCodigo + "&clinumero="+cliNumero+"&clicomplemento="+cliComplemento+"&clicep="+cliCep+"&clirg="+cliRg+"&clicpf="+cliCpf+"&clidatanasc="+dataNascFormatada+"&clidatacadastro="+dataCadastroFormatada+"&clifonecel="+cliFoneCel+"&clifone2="+cliFone2+"&cliemail="+cliEmail+"&clifoto="+cliFoto+"&clisexo="+cliSexo+"&cliobs="+cliObs+"'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        out.println("<td><div align='center'><a href='Cliente?&numpagina="+Integer.parseInt(numPagina)+"&campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&acao=excluir&clicodigo=" + cliCodigo + "'><img src='imagens/delete.png' alt='Excluir'/></a></div></td>");
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
        out.println("<a href=Cliente?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarCliente&numpagina=" + pagAnterior + ">Anterior</a>");
    }

    for (int i = 1; i <= totalPaginas; i++) {
        if (i == Integer.parseInt(numPagina)) {
            out.println("<b>" + i + "</b>");
        } else {
            out.println("<a href=Cliente?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarCliente&numpagina=" + i + ">" + i + "</a>");
        }
    }

    int proximaPag;
    if ((Integer.parseInt(totalRegistros) - (Integer.parseInt(numPagina) * limite)) > 0) {
        proximaPag = Integer.parseInt(numPagina) + 1;
        out.println("<a href=Cliente?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarCliente&numpagina=" + proximaPag + ">Proxima</a>");
    }
    out.println(". Total de registros: " + totalRegistros + ". Total de Pag: " + totalPaginas + "</td></tr>");
    out.println("</table>");

%>