package br.com.futurebrindes.dao;

import br.com.futurebrindes.factory.ConnectionFactory;
import br.com.futurebrindes.javabean.model.ProdutoModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutoDao {
    
    private Connection connection;

    public ProdutoDao() {
        this.connection = new ConnectionFactory().getConnetion();
    }
    
    public List getListaProdutoParaSelectOption() throws SQLException {
        
        String sql = "SELECT * FROM produto ORDER BY prodescricao";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<ProdutoModel> listaProduto = new ArrayList<ProdutoModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ProdutoModel produtoModel = new ProdutoModel();
                produtoModel.setProCodigo(resultSet.getInt("procodigo"));
                produtoModel.setProDescricao(resultSet.getString("prodescricao"));
                listaProduto.add(produtoModel);
            }
            return listaProduto;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
        }
        return null;
    }
    //---------------------------------------------------------------------------
    public List getListaProdutoPaginada(int pagina, String ordenacao, String pesquisa, String campoapesquisar) throws SQLException {
        int limite = 10;
        int offset = (pagina * limite) - limite;
        String sql = "";

        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
              
        if (campoapesquisar.equals("procodigo")) {
            if (pesquisa.equals("")) {
                sql = "SELECT * FROM produto WHERE " + campoapesquisar + " > 0 ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;
            } else {
                sql = "SELECT * FROM produto WHERE " + campoapesquisar + " = " + pesquisa + " ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;
            }
        } else {
            sql = "SELECT * FROM produto WHERE " + campoapesquisar + " LIKE '%" + pesquisa + "%' ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;
        }

        
        
        List<ProdutoModel> listaProduto = new ArrayList<ProdutoModel>();
        try {
            
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                ProdutoModel produtoModel = new ProdutoModel();
                produtoModel.setProCodigo(resultSet.getInt("procodigo"));
                produtoModel.setCatCodigo(resultSet.getInt("catcodigo"));
                produtoModel.setForCodigo(resultSet.getInt("for_codigo"));
                produtoModel.setProDescricao(resultSet.getString("prodescricao"));
                produtoModel.setProQtdEstoque(resultSet.getDouble("proqtdestoque"));
                produtoModel.setProPrecoCusto(resultSet.getDouble("proprecocusto"));
                produtoModel.setProPercLucro(resultSet.getInt("properclucro"));
                produtoModel.setProPrecoVenda(resultSet.getDouble("proprecovenda"));
                produtoModel.setProDataCadastro( resultSet.getDate("prodatacadastro"));
                //produtoModel.setProDataUltVenda(resultSet.getDate("prodataultvenda"));
                produtoModel.setProDataUltVenda(resultSet.getDate("prodataultvenda"));
                produtoModel.setProObs(resultSet.getString("pro_obs"));
                listaProduto.add(produtoModel);
            }
            return listaProduto;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //connection.close();
            //ps.close();
            //resultSet.close();
        }
        return null;
    }
    //---------------------------------------------------------------------------
    public String totalRegistros(String pesquisa, String campoapesquisar) throws SQLException {
        PreparedStatement psConta = null;
        ResultSet resultSetConta = null;
        String sqlConta = "";
        try {
            if (campoapesquisar.equals("procodigo")) {
                if (pesquisa.equals("")) {
                    sqlConta = "select count(*) AS contaRegistros from produto where " + campoapesquisar + " > 0";// order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
                } else {
                    sqlConta = "select count(*) AS contaRegistros from produto where " + campoapesquisar + " = " + pesquisa;//+" order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
                }
            } else {
                sqlConta = "select count(*) AS contaRegistros from produto where " + campoapesquisar + " like '%" + pesquisa + "%'";// order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;        
            }
            psConta = connection.prepareStatement(sqlConta);
            resultSetConta = psConta.executeQuery();
            resultSetConta.next();
            String qtdTotalRegistros = resultSetConta.getString("contaRegistros");
            return qtdTotalRegistros;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            psConta.close();
            resultSetConta.close();
        }
        return "Nerizon com erro";
    }
    //---------------------------------------------------------------------------
    public boolean excluiProduto(ProdutoModel produtoModel) throws SQLException {
        String sql = "delete from produto where procodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, produtoModel.getProCodigo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }

        return false;
    }
    //---------------------------------------------------------------------------
    public void alteraProduto(ProdutoModel produtoModel) throws SQLException {
        String sql = "UPDATE produto SET procodigo=?,catcodigo=?,for_codigo=?,prodescricao=?,proqtdestoque=?,proprecocusto=?,properclucro=?,proprecovenda=?,prodatacadastro=?,prodataultvenda=?,pro_obs=? WHERE procodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, produtoModel.getProCodigo());
            ps.setInt(2, produtoModel.getCatCodigo());
            ps.setInt(3, produtoModel.getForCodigo());
            ps.setString(4, produtoModel.getProDescricao());
            ps.setDouble(5, produtoModel.getProQtdEstoque());
            ps.setDouble(6, produtoModel.getProPrecoCusto());
            ps.setInt(7, produtoModel.getProPercLucro());
            ps.setDouble(8, produtoModel.getProPrecoVenda());
            ps.setDate(9, new java.sql.Date(produtoModel.getProDataCadastro().getTime()));
            
            if( produtoModel.getProDataUltVenda() != null ){
                ps.setDate(10, new java.sql.Date(produtoModel.getProDataUltVenda().getTime()));
            }else{
                ps.setDate(10, null);
            }
            
            //ps.setDate(10, new java.sql.Date(produtoModel.getProDataUltVenda().getTime()));
            
            
            ps.setString(11, produtoModel.getProObs());
            ps.setInt(12, produtoModel.getProCodigo());

            System.out.println("sql alterar= " + ps.toString());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }
    //---------------------------------------------------------------------------
    public void novoProduto(ProdutoModel produtoModel) throws SQLException {
        String sql = "INSERT INTO produto (procodigo,catcodigo,for_codigo,prodescricao,proqtdestoque,proprecocusto,properclucro,proprecovenda,prodatacadastro,prodataultvenda,pro_obs) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, produtoModel.getProCodigo());
            ps.setInt(2, produtoModel.getCatCodigo());
            ps.setInt(3, produtoModel.getForCodigo());
            ps.setString(4, produtoModel.getProDescricao());
            ps.setDouble(5, produtoModel.getProQtdEstoque());
            ps.setDouble(6, produtoModel.getProPrecoCusto());
            ps.setInt(7, produtoModel.getProPercLucro());
            ps.setDouble(8, produtoModel.getProPrecoVenda());
            
            //ps.setDate(9, new java.sql.Date(produtoModel.getProDataCadastro().getTime()));
            if( produtoModel.getProDataCadastro() != null ){
                ps.setDate(9, new java.sql.Date(produtoModel.getProDataCadastro().getTime()));
            }else{
                ps.setDate(9, null);
            }
            
            if( produtoModel.getProDataUltVenda() != null ){
                ps.setDate(10, new java.sql.Date(produtoModel.getProDataUltVenda().getTime()));
            }else{
                ps.setDate(10, null);
            }
            
            ps.setString(11, produtoModel.getProObs());

            System.out.println("sql = " + ps.toString());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }
    //---------------------------------------------------------------------------
    
    
}//classe
