/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.futurebrindes.servlets.controller;

import br.com.futurebrindes.dao.LogradouroDao;
import br.com.futurebrindes.javabean.model.LogradouroModel;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manoel
 */
public class Logradouro extends HttpServlet {

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
        
        response.setContentType("text/html;charset=UTF-8");
        
        String logcodigo = request.getParameter("logcodigo");
        String logdescricao = request.getParameter("logdescricao");
        LogradouroModel logradouro = new LogradouroModel();
        
        if (logcodigo != null)
            logradouro.setLogcodigo(Integer.parseInt(logcodigo));
        
        logradouro.setLogdescricao(logdescricao);
        
       
        LogradouroDao logradouroDao = new LogradouroDao();
       
        //verificar qual é a acao
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listarLogradouro";
        }

        if (acao.equals("alterar")) {
            logradouroDao.alteraLogradouro(logradouro);
            rd = request.getRequestDispatcher("/Logradouro?acao=listarLogradouro");
        } else if (acao.equals("excluir")) {
            logradouroDao.excluiLogradouro(logradouro);
            rd = request.getRequestDispatcher("/Logradouro?acao=listarLogradouro");
        } else if (acao.equals("listarLogradouro")) {
            int numPagina = 1;
            if (request.getParameter("numpagina") != null) {
                numPagina = Integer.parseInt(request.getParameter("numpagina"));
            }
            try {
                String ordenacao = request.getParameter("ordenacao");
                if (ordenacao == null)
                    ordenacao = "logdescricao";
                
                String pesquisa = request.getParameter("pesquisa");
                if (pesquisa == null)
                    pesquisa = "";
                
                String campoapesquisar = request.getParameter("campoapesquisar");
                if (campoapesquisar == null)
                    campoapesquisar = "logdescricao";
      
                List listaLogradouro = logradouroDao.getListaLogradouroPaginada(numPagina, ordenacao, pesquisa, campoapesquisar);
                String qtdTotalRegistros = logradouroDao.totalRegistros(pesquisa, campoapesquisar);
                request.setAttribute("sessaoListaLogradouroPaginada", listaLogradouro);
                request.setAttribute("sessaoqtdTotalRegistros", qtdTotalRegistros);
                rd = request.getRequestDispatcher("/listalogradouro.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.equals("novo")) {
            logradouroDao.novoLogradouro(logradouro);
            rd = request.getRequestDispatcher("/Logradouro?acao=listarLogradouro");
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
            // TODO Auto-generated catch block
            
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
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
            // TODO Auto-generated catch block
            
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
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