/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.futurebrindes.dao;

import br.com.futurebrindes.factory.ConnectionFactory;
import br.com.futurebrindes.javabean.model.BairroModel;
import br.com.futurebrindes.javabean.model.CategoriaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davis_9n3g2
 */
public class CategoriaDao {
    
    private Connection connection;
    
    public CategoriaDao() {
        this.connection = new ConnectionFactory().getConnetion();
    }
    
    public List getListaCategoriaCombo() throws SQLException {

        String sql = "select * from categoria order by catdescricao";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<CategoriaModel> listaCategoria = new ArrayList<CategoriaModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                CategoriaModel categoriaModel = new CategoriaModel();
                categoriaModel.setCatCodigo(resultSet.getInt("catcodigo"));
                categoriaModel.setCatDescricao(resultSet.getString("catdescricao"));
                listaCategoria.add(categoriaModel);
            }
            return listaCategoria;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
        }
        return null;
    }
    //---------------------------------------------------------------------------
    public List getListaCategoriaPaginada(int pagina, String ordenacao, String pesquisa, String campoapesquisar) throws SQLException {
        int limite = 10;
        int offset = (pagina * limite) - limite;
        String sql = "";  
          
        if (campoapesquisar.equals("catcodigo")) 
            if (pesquisa.equals(""))
                sql = "SELECT * FROM categoria WHERE " + campoapesquisar + " > 0 ORDER BY " + ordenacao + " LIMIT " + offset + "," + limite;
            else
                sql = "SELECT * FROM categoria WHERE " + campoapesquisar + " = " + pesquisa + " ORDER BY " + ordenacao+ " LIMIT " + offset + "," + limite;
        else
             sql = "SELECT * FROM categoria WHERE " + campoapesquisar + " LIKE '%" + pesquisa + "%' ORDER BY " + ordenacao + " LIMIT " + offset + "," + limite;        

        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<CategoriaModel> listaCategoria = new ArrayList<CategoriaModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                CategoriaModel categoria = new CategoriaModel();
                categoria.setCatCodigo(resultSet.getInt("catCodigo"));
                categoria.setCatDescricao(resultSet.getString("catDescricao"));
                listaCategoria.add(categoria);
            }
            return listaCategoria;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //connection.close();
            //ps.close();
            //resultSet.close();
        }
        return null;
    }
    //---------------------------------------------------------------------------
    //metodo para retornar o numero total de registros para paginacao
    public String totalRegistros(String pesquisa, String campoapesquisar) throws SQLException {
        PreparedStatement psConta = null;
        ResultSet resultSetConta = null;     
        String sqlConta = "";
        try {
            if (campoapesquisar.equals("catcodigo"))
                if (pesquisa.equals(""))
                    sqlConta = "select count(*) AS contaRegistros from categoria where "+campoapesquisar+"> 0";
                else
                   sqlConta = "select count(*) AS contaRegistros from categoria where "+campoapesquisar+" = "+pesquisa;
            
            else     
                sqlConta = "select count(*) AS contaRegistros from categoria where "+campoapesquisar+" like '%"+pesquisa+"%'";
            
            psConta = connection.prepareStatement(sqlConta);
            resultSetConta = psConta.executeQuery();
            resultSetConta.next();
            String qtdTotalRegistros = resultSetConta.getString("contaRegistros");
            return qtdTotalRegistros;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            psConta.close();
            resultSetConta.close();
        }
        return "TotalReg com erro";
    }
    //---------------------------------------------------------------------------
     //excluir bairro usando Model CategoriaModel
    public boolean excluiCategoria(CategoriaModel categoria) throws SQLException {
        String sql = "delete from categoria where catcodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, categoria.getCatCodigo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }

        return false;
    }
    //---------------------------------------------------------------------------
    public void alteraCategoria(CategoriaModel categoria) throws SQLException {
        String sql = "UPDATE categoria SET catdescricao=? where catcodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, categoria.getCatDescricao());
            ps.setInt(2, categoria.getCatCodigo());
           
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }
    //---------------------------------------------------------------------------
    public void novaCategoria(CategoriaModel categoria) throws SQLException {
        String sql = "insert into categoria (catdescricao) values (?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, categoria.getCatDescricao());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }
    
}
