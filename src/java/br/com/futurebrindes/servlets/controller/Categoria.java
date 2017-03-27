/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.futurebrindes.servlets.controller;

import br.com.futurebrindes.dao.CategoriaDao;
import br.com.futurebrindes.javabean.model.CategoriaModel;
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

/**
 *
 * @author davis_9n3g2
 */
public class Categoria extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher rd = null;
        
        String catCodigo = request.getParameter("catcodigo");
        String catDescricao = request.getParameter("catdescricao");
        
        CategoriaModel categoria = new CategoriaModel();
        
        if (catCodigo != null)
            categoria.setCatCodigo(Integer.parseInt(catCodigo));
        
        categoria.setCatDescricao(catDescricao);
       
        CategoriaDao categoriaDao = new CategoriaDao();
       
        //verificar qual é a acao
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listarCategoria";
        }

        if (acao.equals("alterar")) {
            categoriaDao.alteraCategoria(categoria);
            rd = request.getRequestDispatcher("/Categoria?acao=listarCategoria");
        } else if (acao.equals("excluir")) {
            categoriaDao.excluiCategoria(categoria);
            rd = request.getRequestDispatcher("/Categoria?acao=listarCategoria");
        } else if (acao.equals("listarCategoria")) {
            int numPagina = 1;
            if (request.getParameter("numpagina") != null) {
                numPagina = Integer.parseInt(request.getParameter("numpagina"));
            }
            try {
                String ordenacao = request.getParameter("ordenacao");
                if (ordenacao == null)
                    ordenacao = "catDescricao";
                
                String pesquisa = request.getParameter("pesquisa");
                if (pesquisa == null)
                    pesquisa = "";
                
                String campoapesquisar = request.getParameter("campoapesquisar");
                if (campoapesquisar == null)
                    campoapesquisar = "catDescricao";
      
                List listaCategorias = categoriaDao.getListaCategoriaPaginada(numPagina, ordenacao, pesquisa, campoapesquisar);
                String qtdTotalRegistros = categoriaDao.totalRegistros(pesquisa, campoapesquisar);
                request.setAttribute("sessaoListaCategoriaPaginada", listaCategorias);
                request.setAttribute("sessaoqtdTotalRegistros", qtdTotalRegistros);
                rd = request.getRequestDispatcher("/listacategorias.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.equals("novo")) {
            categoriaDao.novaCategoria(categoria);
            rd = request.getRequestDispatcher("/Categoria?acao=listarCategoria");
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
            Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
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
