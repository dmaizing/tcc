/*
 * Prof Neri Neitzke - Mais de 100 palestras em 7 países
 * Videoaulas Neri wwww.informaticon.com.br
 * email: videoaulaneri@gmail.com
 */
package br.com.futurebrindes.servlets.controller;
 


import br.com.futurebrindes.dao.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import br.com.videoaulasneri.javabean.model.UsuarioModel;

/**
 *
 * @author User
 */
public class Usuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        RequestDispatcher rd = null;
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String nivel = request.getParameter("nivel");
        String nomeCompleto = request.getParameter("nomecompleto");

        br.com.futurebrindes.javabean.model.UsuarioModel usuarios = new br.com.futurebrindes.javabean.model.UsuarioModel();
        usuarios.setUsuario(usuario);
        usuarios.setSenha(senha);
        if(nivel != null)
            usuarios.setNivel(Integer.parseInt(nivel));
        usuarios.setNomeCompleto(nomeCompleto);

        UsuarioDao usuarioDao = new UsuarioDao();
       
        //verificar qual é a acao
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listarUsuario";
        }

        if (acao.equals("alterar")) {
            usuarioDao.alteraUsuario(usuarios);
            rd = request.getRequestDispatcher("/Usuario?acao=listarUsuario");
        } else if (acao.equals("excluir")) {
            usuarioDao.excluiUsuario(usuarios);
            rd = request.getRequestDispatcher("/Usuario?acao=listarUsuario");
        } else if (acao.equals("listarUsuario")) {
            if(request.getSession().getAttribute("sessaoUsuario") == null){
                response.sendRedirect("login.jsp");
                return;
            }
            int numPagina = 1;
            if (request.getParameter("numPagina") != null) {
                numPagina = Integer.parseInt(request.getParameter("numPagina"));
            }
            try {
                String ordenacao = request.getParameter("ordenacao");
                if(ordenacao == null)
                    ordenacao = "nomecompleto";
                
                String pesquisa = request.getParameter("pesquisa");
                if(pesquisa == null)
                    pesquisa = "";
                
                String campoapesquisar = request.getParameter("campoapesquisar");
                if (campoapesquisar == null)
                    campoapesquisar = "nomecompleto";
                
                List listaUsuarios = usuarioDao.getListaUsuariosPaginada(numPagina, ordenacao, pesquisa, campoapesquisar);
                String qtdTotalRegistros = usuarioDao.totalRegistros(pesquisa, campoapesquisar);
                request.setAttribute("sessaoListaUsuarioPaginada", listaUsuarios);
                request.setAttribute("sessaoqtdTotalRegistros", qtdTotalRegistros);
                rd = request.getRequestDispatcher("/listausuariospaginada.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.equals("novo")) {
            usuarioDao.novoUsuario(usuarios);
            rd = request.getRequestDispatcher("/Usuario?acao=listarUsuario");
        } 

        rd.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
