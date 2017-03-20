<%@tag body-content="empty" %>
<%
int limite = 10;
    String numPagina = request.getParameter("numPagina");
    if (numPagina == null) {
        numPagina = "1";
    }
    java.util.List listaDeUsuarios = (java.util.List) request.getAttribute("sessaoListaUsuarioPaginada");
    
    String ordenacao = request.getParameter("ordenacao");
    if(ordenacao == null)
       ordenacao = "nomecompleto";
 
    String pesquisa = request.getParameter("pesquisa");
    if (pesquisa == null)
        pesquisa = "";
    
    String campoapesquisar = request.getParameter("campoapesquisar");
    if (campoapesquisar == null)
        campoapesquisar = "nomecompleto"; 
    
    out.println("<table style='margin-top:12px;'>");
    
    out.println("<form action='Usuario' method='get' >");
    out.println("<tr><td colspan='6'>Localizar por: ");
    out.println("<select name='campoapesquisar'>");
    if (campoapesquisar.equals("nomecompleto"))
        out.println("<option value='nomecompleto' selected='selected'>Nome Completo</option>");
    else 
        out.println("<option value='nomecompleto'>Nome Completo</option>");
    
    if (campoapesquisar.equals("usuario"))
        out.println("<option value='usuario' selected='selected'>Usuário</option>");
    else
        out.println("<option value='usuario' >Usuário</option>");
    
    if (campoapesquisar.equals("nivel"))
        out.println("<option value='nivel' selected='selected'>Nivel</option>");
    else
        out.println("<option value='nivel'>Nivel</option>");
    out.println("</select>");
    out.println("<input type='text' name='pesquisa' value='"+pesquisa+"'/>");
    
    out.println("<input type='hidden' name='acao' value='listarUsuario'/>");
    out.println("<input type='image' src='imagens/localizar.png'/></td></tr>");
    out.println("</form>");
    
    out.println("<tr><td colspan='3'><a href='usuario.jsp?acao=novo'><b>Novo Registro</b></a></td><td colspan='3'>Ordenado por: "+ordenacao+"</td></tr>");
    out.println("<tr class='linhaespecialTable'>");
        
    out.println("<td><a href='Usuario?pesquisa="+pesquisa+"&campoapesquisar="+campoapesquisar+"&acao=listarUsuario&ordenacao=usuario&numPagina="+Integer.parseInt(numPagina)+"'>Usuário</a></td>"
            + "<td>Senha</td>"
            + "<td><a href='Usuario?acao=listarUsuario&ordenacao=nivel&numPagina="+Integer.parseInt(numPagina)+"'>Nível</a></td>"
            + "<td><a href='Usuario?acao=listarUsuario&ordenacao=nomecompleto&numPagina="+Integer.parseInt(numPagina)+"'>Nome Completo</a></td>"
            + "<td>Alterar</td>"
            + "<td>Excluir</td>");
    out.println("</tr>");
    int contador = 0;
    for (java.util.Iterator iterator = listaDeUsuarios.iterator(); iterator.hasNext();) {
        br.com.futurebrindes.javabean.model.UsuarioModel usuarios = (br.com.futurebrindes.javabean.model.UsuarioModel) iterator.next();
    //    out.println("Usuario:"+resultSet.getString("usuario")+", senha:"+resultSet.getString("senha")+
        //            ", sivel:"+resultSet.getString("nivel")+", nome Completo:"+resultSet.getString("nomecompleto")+"<br>");
        if (contador % 2 == 0) {
            out.println("<tr style='background: yellow'>");
        } else {
            out.println("<tr>");
        }
        String usuario1 = usuarios.getUsuario();
        String senha1 = usuarios.getSenha();
        String nivel = String.valueOf(usuarios.getNivel());
        String nomeCompleto = usuarios.getNomeCompleto();
        out.println("<td>" + usuario1 + "</td>");
        out.println("<td>" + senha1 + "</td>");
        out.println("<td>" + nivel + "</td>");
        out.println("<td>" + nomeCompleto + "</td>");
        //out.println("<td><div align='center'><a href='alterausuario.jsp?usuario=" + usuario1 + "&senha=" + senha1 + "&nivel=" + nivel + "&nomecompleto=" + nomeCompleto + "'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        out.println("<td><div align='center'><a href='usuario.jsp?acao=alterar&usuario=" + usuario1 + "&senha=" + senha1 + "&nivel=" + nivel + "&nomecompleto=" + nomeCompleto + "'><img src='imagens/altera.png' alt='Altera'/></a></div></td>");
        //out.println("<td><div align='center'><a href='Usuario?acao=excluir&usuario=" + usuario1 + "'><img src='imagens/delete.png' alt='Excluir'/></a></div></td>");
        out.println("<td><div align='center'><a href='Usuario?&numPagina="+Integer.parseInt(numPagina)+"&campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&acao=excluir&usuario=" + usuario1 + "'><img src='imagens/delete.png' alt='Excluir'/></a></div></td>");
        out.println("</tr>");
        contador++;
    }

    String totalRegistros = (String) request.getAttribute("sessaoqtdTotalRegistros");
    
    int totalPaginas = Integer.parseInt(totalRegistros) / limite;
    if (Integer.parseInt(totalRegistros) % limite != 0) {
        totalPaginas++;
    }
    out.println("<tr class='linhaEspecialTable'><td colspan='6'>");
    int pagAnterior;
    if (Integer.parseInt(numPagina) > 1) {
        pagAnterior = Integer.parseInt(numPagina) - 1;
        //out.println("<a href=Usuario?acao=listarUsuario&ordenacao="+ordenacao+"&numPagina=" + pagAnterior + ">Anterior</a>");
        out.println("<a href=Usuario?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarUsuario&numPagina=" + pagAnterior + ">Anterior</a>");
    }

    for (int i = 1; i <= totalPaginas; i++) {
        if (i == Integer.parseInt(numPagina)) {
            out.println("<b>" + i + "</b>");
        } else {
            //out.println("<a href=Usuario?acao=listarUsuario&ordenacao="+ordenacao+"&numPagina=" + i + ">" + i + "</a>");
            out.println("<a href=Usuario?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarUsuario&numPagina=" + i + ">" + i + "</a>");
        }
    }

    int proximaPag;
    if ((Integer.parseInt(totalRegistros) - (Integer.parseInt(numPagina) * limite)) > 0) {
        proximaPag = Integer.parseInt(numPagina) + 1;
        //out.println("<a href=Usuario?acao=listarUsuario&ordenacao="+ordenacao+"&numPagina=" + proximaPag + ">Proxima</a>");
        out.println("<a href=Usuario?campoapesquisar="+campoapesquisar+"&pesquisa="+pesquisa+"&ordenacao="+ordenacao+"&acao=listarUsuario&numPagina=" + proximaPag + ">Proxima</a>");
    }
    out.println(". Total de registros: " + totalRegistros + ". Total de Pag: " + totalPaginas + "</td></tr>");
    out.println("</table>");  

%>