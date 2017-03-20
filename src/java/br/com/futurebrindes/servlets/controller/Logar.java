package br.com.futurebrindes.servlets.controller;


import br.com.futurebrindes.dao.UsuarioDao;
import br.com.futurebrindes.javabean.model.UsuarioModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String usr = request.getParameter("usuario");
        String pwd = request.getParameter("senha");
        String status="";
        
        
        UsuarioDao usuarioDao = new UsuarioDao();
        UsuarioModel usuario = null;
        try {
            usuario = usuarioDao.getUsuario(usr, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(Logar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //usuario.setUsuario(usr);
        //usuario.setSenha(pwd);
        
        RequestDispatcher rd = null;
        //UsuarioDao usuarioDao = new UsuarioDao();
        if(usuario != null){
            HttpSession sessao = request.getSession();
            sessao.setAttribute("sessaoUsuario", usr);
            sessao.setAttribute("nomeCompleto", usuario.getNomeCompleto());
            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }else{
            request.setAttribute("mensagem", "Atenção! - Usuário ou Senha Inválido(s)");
            rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
