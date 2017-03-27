package br.com.futurebrindes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.futurebrindes.factory.ConnectionFactory;
import br.com.futurebrindes.javabean.model.CidadeModel;

public class CidadeDao {

    private Connection connection;

    public CidadeDao() {
        this.connection = new ConnectionFactory().getConnetion();
    }

    public List getListaCidadeCombo() throws SQLException {

        String sql = "select * from cidade order by ciddescricao";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<CidadeModel> listaCidade = new ArrayList<CidadeModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                CidadeModel cidadeModel = new CidadeModel();
                cidadeModel.setCidCodigo(resultSet.getInt("cidcodigo"));
                cidadeModel.setCidDescricao(resultSet.getString("ciddescricao"));
                cidadeModel.setCidUf(resultSet.getString("ciduf"));
                listaCidade.add(cidadeModel);
            }
            return listaCidade;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
        }
        return null;
    }
    
    
    public List getListaCidadePaginada(int pagina, String ordenacao, String pesquisa, String campoapesquisar) throws SQLException {
        int limite = 10;
        int offset = (pagina * limite) - limite;
        String sql = "";  
          
        if (campoapesquisar.equals("cidcodigo")) 
            if (pesquisa.equals(""))
                sql = "select * from cidade where "+campoapesquisar+" > 0 order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
            else
                sql = "select * from cidade where "+campoapesquisar+" = "+pesquisa+" order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
        else
             sql = "select * from cidade where "+campoapesquisar+" like '%"+pesquisa+"%' order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<CidadeModel> listaCidade = new ArrayList<CidadeModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                CidadeModel cidade = new CidadeModel();
                cidade.setCidCodigo(resultSet.getInt("cidcodigo"));
                cidade.setCidDescricao(resultSet.getString("ciddescricao"));
                listaCidade.add(cidade);
            }
            return listaCidade;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //connection.close();
            //ps.close();
            //resultSet.close();
        }
        return null;
    }
    
    public String totalRegistros(String pesquisa, String campoapesquisar) throws SQLException {
        PreparedStatement psConta = null;
        ResultSet resultSetConta = null;     
        String sqlConta = "";
        try {
            if (campoapesquisar.equals("cidcodigo"))
                if (pesquisa.equals(""))
                    sqlConta = "select count(*) AS contaRegistros from cidade where "+campoapesquisar+"> 0";
                else
                   sqlConta = "select count(*) AS contaRegistros from cidade where "+campoapesquisar+" = "+pesquisa;
            
            else     
                sqlConta = "select count(*) AS contaRegistros from cidade where "+campoapesquisar+" like '%"+pesquisa+"%'";
            
            psConta = connection.prepareStatement(sqlConta);
            resultSetConta = psConta.executeQuery();
            resultSetConta.next();
            String qtdTotalRegistros = resultSetConta.getString("contaRegistros");
            return qtdTotalRegistros;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            psConta.close();
            resultSetConta.close();
        }
        return "Nerizon com erro";
    }
    
     //excluir bairro usando Model BairroModel
    public boolean excluiCidade(CidadeModel cidade) throws SQLException {
        String sql = "delete from cidade where cidcodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            //System.out.println("baicodigo = "+bairro.getBaiCodigo());
            ps.setInt(1, cidade.getCidCodigo());
            //System.out.println("sql = "+ps.toString());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }

        return false;
    }
    
    public void alteraCidade(CidadeModel cidade) throws SQLException {
        String sql = "UPDATE cidade SET ciddescricao=? where cidcodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cidade.getCidDescricao());
            ps.setInt(2, cidade.getCidCodigo());
           
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }
    
    public void novaCidade(CidadeModel cidade) throws SQLException {
        String sql = "insert into cidade (ciddescricao) values (?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cidade.getCidDescricao());
            //System.out.println("sql novo registro = "+ps.toString());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }
    
}
