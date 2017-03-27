/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.futurebrindes.servlets.controller;

import br.com.futurebrindes.dao.FornecedorDao;
import br.com.futurebrindes.javabean.model.FornecedorModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Davis-W10
 */
public class Fornecedor extends HttpServlet {

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
            throws ServletException, IOException, ParseException, SQLException {
        
        RequestDispatcher rd = null;
        
        response.setContentType("text/html;charset=UTF-8");
        
        String forCodigo= request.getParameter("for_codigo");
        String forNome= request.getParameter("for_nome");
        String forFantasia = request.getParameter("for_fantasia");
        String logCodigo= request.getParameter("log_codigo");
        String baiCodigo= request.getParameter("bai_codigo");
        String cidCodigo= request.getParameter("cid_codigo");
        String forNumero= request.getParameter("for_numero");
        String forComplemento= request.getParameter("for_complemento");
        String forCep= request.getParameter("for_cep");
        String forCNPJ= request.getParameter("for_cnpj");
        String forInscEst= request.getParameter("for_inscest");
        String forDataCadastro= request.getParameter("for_datacadastro");
        String forFoneCel= request.getParameter("for_fonecel");
        String forFone2= request.getParameter("for_fone2");
        String forEmail= request.getParameter("for_email");
        String forObs= request.getParameter("for_obs");    
        
        FornecedorModel fornecedorModel = new FornecedorModel();
        if (forCodigo != null)
            fornecedorModel.setForCodigo(Integer.parseInt(forCodigo));
        
        fornecedorModel.setForNome(forNome);
        fornecedorModel.setForFantasia(forFantasia);
        
        if (logCodigo != null)
        	fornecedorModel.setLogCodigo(Integer.parseInt(logCodigo));
        
        if (baiCodigo != null)
        	fornecedorModel.setBaiCodigo(Integer.parseInt(baiCodigo));
        
        if (cidCodigo != null)
        	fornecedorModel.setCidCodigo(Integer.parseInt(cidCodigo));
        
        fornecedorModel.setForNumero(forNumero);
        fornecedorModel.setForComplemento(forComplemento);
        fornecedorModel.setForCep(forCep);
        fornecedorModel.setForCNPJ(forCNPJ);
        fornecedorModel.setForInscEst(forInscEst);
        DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");

        if (forDataCadastro != null) {
            Date forDataCadastroFormatada = formatoData.parse(forDataCadastro);
            fornecedorModel.setForDataCadastro(forDataCadastroFormatada);
        }
        
        fornecedorModel.setForFoneCel(forFoneCel);
        fornecedorModel.setForFone2(forFone2);
        fornecedorModel.setForEmail(forEmail);
        fornecedorModel.setForObs(forObs);
        
        FornecedorDao fornecedorDao = new FornecedorDao();
       
        //verificar qual é a acao
        String acao = request.getParameter("acao");
        
        System.out.println("acao = " + acao);
        
        if (acao == null) {
            acao = "listarFornecedor";
        }

        if (acao.equals("alterar")) {
            fornecedorDao.alteraFornecedor(fornecedorModel);
            rd = request.getRequestDispatcher("/Fornecedor?acao=listarFornecedor");
        } else if (acao.equals("excluir")) {
            fornecedorDao.excluiFornecedor(fornecedorModel);
            rd = request.getRequestDispatcher("/Fornecedor?acao=listarFornecedor");
        } else if (acao.equals("listarFornecedor")) {
            int numPagina = 1;
            if (request.getParameter("numpagina") != null) {
                numPagina = Integer.parseInt(request.getParameter("numpagina"));
            }
            try {
                String ordenacao = request.getParameter("ordenacao");
                if (ordenacao == null)
                    ordenacao = "for_nome";
                
                String pesquisa = request.getParameter("pesquisa");
                if (pesquisa == null)
                    pesquisa = "";
                
                String campoapesquisar = request.getParameter("campoapesquisar");
                if (campoapesquisar == null)
                    campoapesquisar = "for_nome";
      
                List listaFornecedores = fornecedorDao.getListaFornecedorPaginada(numPagina, ordenacao, pesquisa, campoapesquisar);
                
                String qtdTotalRegistros = fornecedorDao.totalRegistros(pesquisa, campoapesquisar);
                
                request.setAttribute("sessaoListaFornecedorPaginada", listaFornecedores);
                request.setAttribute("sessaoqtdTotalRegistros", qtdTotalRegistros);
                rd = request.getRequestDispatcher("/listaFornecedores.jsp");
                
            } catch (SQLException ex) {
                Logger.getLogger(Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.equals("novo")) {
            fornecedorDao.novoFornecedor(fornecedorModel);
            rd = request.getRequestDispatcher("/Fornecedor?acao=listarFornecedor");
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
        } catch (ParseException ex) {
            Logger.getLogger(Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
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
