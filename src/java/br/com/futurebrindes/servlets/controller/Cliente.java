/*
 * Prof Neri Neitzke - Mais de 100 palestras em 7 países
 * Videoaulas Neri wwww.informaticon.com.br
 * email: videoaulaneri@gmail.com
 */

package br.com.futurebrindes.servlets.controller;

import br.com.futurebrindes.dao.ClienteDao;
import br.com.futurebrindes.javabean.model.ClienteModel;

import java.io.IOException;
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
 * @author User
 */
public class Cliente extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        RequestDispatcher rd = null;
        
        
        System.out.println("request.getParameter(clidatanasc) = " + request.getParameter("clidatanasc"));
        System.out.println("request.getParameter(clidatacadastro) = " + request.getParameter("clidatacadastro"));
        
        String cliCodigo= request.getParameter("clicodigo");
        String cliNome= request.getParameter("clinome");
        String logCodigo= request.getParameter("logcodigo");
        String baiCodigo= request.getParameter("baicodigo");
        String cidCodigo= request.getParameter("cidcodigo");
        String cliNumero= request.getParameter("clinumero");
        String cliComplemento= request.getParameter("clicomplemento");
        String cliCep= request.getParameter("clicep");
        String cliRg= request.getParameter("clirg");
        String cliCpf= request.getParameter("clicpf");
        String cliDataNasc= request.getParameter("clidatanasc");
        String cliDataCadastro= request.getParameter("clidatacadastro");
        String cliFoneCel= request.getParameter("clifonecel");
        String cliFone2= request.getParameter("clifone2");
        String cliEmail= request.getParameter("cliemail");
        String cliFoto= request.getParameter("clifoto");
        String cliSexo= request.getParameter("clisexo");
        String cliObs= request.getParameter("cliobs");       
        
        ClienteModel clienteModel = new ClienteModel();
        if (cliCodigo != null)
            clienteModel.setCliCodigo(Integer.parseInt(cliCodigo));
        clienteModel.setCliNome(cliNome);
        if (logCodigo != null)
        	clienteModel.setLogCodigo(Integer.parseInt(logCodigo));
        if (baiCodigo != null)
        	clienteModel.setBaiCodigo(Integer.parseInt(baiCodigo));
        if (cidCodigo != null)
        	clienteModel.setCidCodigo(Integer.parseInt(cidCodigo));
        clienteModel.setCliNumero(cliNumero);
        clienteModel.setCliComplemento(cliComplemento);
        clienteModel.setCliCep(cliCep);
        clienteModel.setCliRg(cliRg);
        clienteModel.setCliCpf(cliCpf);
        
        //DateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        
        if (cliDataNasc != null) {
            Date cliDataNascFormatada = formatoData.parse(cliDataNasc);
            clienteModel.setCliDataNasc(cliDataNascFormatada);
        }

        if (cliDataCadastro != null) {
            Date cliDataCadastroFormatada = formatoData.parse(cliDataCadastro);
            clienteModel.setCliDataCadastro(cliDataCadastroFormatada);
        }
        
        clienteModel.setCliFoneCel(cliFoneCel);
        clienteModel.setCliFone2(cliFone2);
        clienteModel.setCliEmail(cliEmail);
        clienteModel.setCliFoto(cliFoto);
        clienteModel.setCliSexo(cliSexo);
        clienteModel.setCliObs(cliObs);
        
        ClienteDao clienteDao = new ClienteDao();
       
        //verificar qual é a acao
        String acao = request.getParameter("acao");
        
        System.out.println("acao = " + acao);
        
        if (acao == null) {
            acao = "listarCliente";
        }

        if (acao.equals("alterar")) {
            clienteDao.alteraCliente(clienteModel);
            rd = request.getRequestDispatcher("/Cliente?acao=listarCliente");
        } else if (acao.equals("excluir")) {
            clienteDao.excluiCliente(clienteModel);
            rd = request.getRequestDispatcher("/Cliente?acao=listarCliente");
        } else if (acao.equals("listarCliente")) {
            int numPagina = 1;
            if (request.getParameter("numpagina") != null) {
                numPagina = Integer.parseInt(request.getParameter("numpagina"));
            }
            try {
                String ordenacao = request.getParameter("ordenacao");
                if (ordenacao == null)
                    ordenacao = "clinome";
                
                String pesquisa = request.getParameter("pesquisa");
                if (pesquisa == null)
                    pesquisa = "";
                
                String campoapesquisar = request.getParameter("campoapesquisar");
                if (campoapesquisar == null)
                    campoapesquisar = "clinome";
      
                List listaClientes = clienteDao.getListaClientePaginada(numPagina, ordenacao, pesquisa, campoapesquisar);
                
                String qtdTotalRegistros = clienteDao.totalRegistros(pesquisa, campoapesquisar);
                
                request.setAttribute("sessaoListaClientePaginada", listaClientes);
                request.setAttribute("sessaoqtdTotalRegistros", qtdTotalRegistros);
                rd = request.getRequestDispatcher("/listaclientes.jsp");
                
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (acao.equals("novo")) {
            clienteDao.novoCliente(clienteModel);
            rd = request.getRequestDispatcher("/Cliente?acao=listarCliente");
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
            try {
				processRequest(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
            try {
				processRequest(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
