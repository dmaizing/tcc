package br.com.futurebrindes.servlets.controller;

import br.com.futurebrindes.dao.ProdutoDao;
import br.com.futurebrindes.javabean.model.ProdutoModel;
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


public class Produto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = null;
        
        String proCodigo       = request.getParameter("procodigo");
        String catCodigo       = request.getParameter("catcodigo");
        String forCodigo       = request.getParameter("for_codigo");
        String proDescricao    = request.getParameter("prodescricao");
        String proQtdEstoque   = request.getParameter("proqtdestoque");
        String proPrecoCusto   = request.getParameter("proprecocusto");
        String proPercLucro    = request.getParameter("properclucro");
        String proPrecoVenda   = request.getParameter("proprecovenda");
        String proDataCadastro = request.getParameter("prodatacadastro");
        String proDataUltVenda = request.getParameter("prodataultvenda");
        String proObs          = request.getParameter("pro_obs");  
        
        
        
        
        ProdutoModel produtoModel = new ProdutoModel();
        
        if (proCodigo != null)
            produtoModel.setProCodigo(Integer.parseInt(proCodigo));
        
        if (catCodigo != null)
        	produtoModel.setCatCodigo(Integer.parseInt(catCodigo));
        
        if (forCodigo != null)
        	produtoModel.setForCodigo(Integer.parseInt(forCodigo));

        produtoModel.setProDescricao(proDescricao);
        
        if (proQtdEstoque != null)
            produtoModel.setProQtdEstoque(Double.parseDouble(proQtdEstoque));
        
        if (proPrecoCusto != null)
            produtoModel.setProPrecoCusto(Double.parseDouble(proPrecoCusto));
        
        if (proPercLucro != null)
            produtoModel.setProPercLucro(Integer.parseInt(proPercLucro));
        
        if (proPrecoVenda != null)
            produtoModel.setProPrecoVenda(Double.parseDouble(proPrecoVenda));
        
        
        
        DateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
        //DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");

        if (proDataCadastro != null) {
            Date proDataCadastroFormatada = formatoData.parse(proDataCadastro);
            produtoModel.setProDataCadastro(proDataCadastroFormatada);
        }
        if (proDataUltVenda != null) {
            Date proDataUltVendaFormatada = formatoData.parse(proDataUltVenda);
            produtoModel.setProDataUltVenda(proDataUltVendaFormatada);
        }

        produtoModel.setProObs(proObs);
        
        ProdutoDao produtoDao = new ProdutoDao();
       
        //verificar qual é a acao
        String acao = request.getParameter("acao");
        
        System.out.println("acao = " + acao);
        
        if (acao == null) {
            acao = "listarProduto";
        }

        if (acao.equals("alterar")) {
            produtoDao.alteraProduto(produtoModel);
            rd = request.getRequestDispatcher("/Produto?acao=listarProduto");
        } else if (acao.equals("excluir")) {
            produtoDao.excluiProduto(produtoModel);
            rd = request.getRequestDispatcher("/Produto?acao=listarProduto");
        } else if (acao.equals("listarProduto")) {
            int numPagina = 1;
            if (request.getParameter("numpagina") != null) {
                numPagina = Integer.parseInt(request.getParameter("numpagina"));
            }
            try {
                String ordenacao = request.getParameter("ordenacao");
                if (ordenacao == null)
                    ordenacao = "prodescricao";
                
                String pesquisa = request.getParameter("pesquisa");
                if (pesquisa == null)
                    pesquisa = "";
                
                String campoapesquisar = request.getParameter("campoapesquisar");
                if (campoapesquisar == null)
                    campoapesquisar = "prodescricao";
      
                List listaProdutos = produtoDao.getListaProdutoPaginada(numPagina, ordenacao, pesquisa, campoapesquisar);
                
                String qtdTotalRegistros = produtoDao.totalRegistros(pesquisa, campoapesquisar);
                
                request.setAttribute("sessaoListaProdutoPaginada", listaProdutos);
                request.setAttribute("sessaoqtdTotalRegistros", qtdTotalRegistros);
                rd = request.getRequestDispatcher("/listaprodutos.jsp");
                
            } catch (SQLException ex) {
                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.equals("novo")) {
            produtoDao.novoProduto(produtoModel);
            rd = request.getRequestDispatcher("/Produto?acao=listarProduto");
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
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
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
