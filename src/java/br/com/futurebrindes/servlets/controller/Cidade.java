/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.futurebrindes.servlets.controller;

import br.com.futurebrindes.dao.CidadeDao;
import br.com.futurebrindes.javabean.model.CidadeModel;
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
public class Cidade extends HttpServlet {

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
        String cidCodigo = request.getParameter("cidcodigo");
        String cidDescricao = request.getParameter("ciddescricao");
        CidadeModel cidade = new CidadeModel();
        if (cidCodigo != null) {
            cidade.setCidCodigo(Integer.parseInt(cidCodigo));
        }

        cidade.setCidDescricao(cidDescricao);

        CidadeDao cidadeDao = new CidadeDao();

        //verificar qual é a acao
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listarCidade";
        }

        if (acao.equals("alterar")) {
            cidadeDao.alteraCidade(cidade);
            rd = request.getRequestDispatcher("/Cidade?acao=listarCidade");
        } else if (acao.equals("excluir")) {
            cidadeDao.excluiCidade(cidade);
            rd = request.getRequestDispatcher("/Cidade?acao=listarCidade");
        } else if (acao.equals("listarCidade")) {
            int numPagina = 1;
            if (request.getParameter("numpagina") != null) {
                numPagina = Integer.parseInt(request.getParameter("numpagina"));
            }
            try {
                String ordenacao = request.getParameter("ordenacao");
                if (ordenacao == null) {
                    ordenacao = "ciddescricao";
                }

                String pesquisa = request.getParameter("pesquisa");
                if (pesquisa == null) {
                    pesquisa = "";
                }

                String campoapesquisar = request.getParameter("campoapesquisar");
                if (campoapesquisar == null) {
                    campoapesquisar = "ciddescricao";
                }

                List listaCidades = cidadeDao.getListaCidadePaginada(numPagina, ordenacao, pesquisa, campoapesquisar);
                String qtdTotalRegistros = cidadeDao.totalRegistros(pesquisa, campoapesquisar);
                request.setAttribute("sessaoListaCidadePaginada", listaCidades);
                request.setAttribute("sessaoqtdTotalRegistros", qtdTotalRegistros);
                rd = request.getRequestDispatcher("/listacidades.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(Cidade.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.equals("novo")) {
            cidadeDao.novaCidade(cidade);
            rd = request.getRequestDispatcher("/Cidade?acao=listarCidade");
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
            Logger.getLogger(Cidade.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Cidade.class.getName()).log(Level.SEVERE, null, ex);
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
