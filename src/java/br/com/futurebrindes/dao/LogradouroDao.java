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
import br.com.futurebrindes.javabean.model.LogradouroModel;

public class LogradouroDao {
    private Connection connection;

public LogradouroDao() {
    this.connection = new ConnectionFactory().getConnetion();
}
public List getListaLogradouroCombo() throws SQLException {
    
    String sql = "select * from logradouro order by logdescricao";        
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    List<LogradouroModel> listaLogradouro = new ArrayList<LogradouroModel>();
    try {
        ps = connection.prepareStatement(sql);
        resultSet = ps.executeQuery();
        while (resultSet.next()) {
        	LogradouroModel logradouroModel = new LogradouroModel();
        	logradouroModel.setLogcodigo(resultSet.getInt("logcodigo"));
        	logradouroModel.setLogdescricao(resultSet.getString("logdescricao"));
        	listaLogradouro.add(logradouroModel);
        }
        return listaLogradouro;
    } catch (SQLException ex) {
        Logger.getLogger(LogradouroDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        connection.close();
        ps.close();
        resultSet.close();
    }
    return null;
}

 public List getListaLogradouroPaginada(int pagina, String ordenacao, String pesquisa, String campoapesquisar) throws SQLException {
        int limite = 10;
        int offset = (pagina * limite) - limite;
        String sql = "";  
          
        if (campoapesquisar.equals("logcodigo")) 
            if (pesquisa.equals(""))
                sql = "select * from logradouro where "+campoapesquisar+" > 0 order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
            else
                sql = "select * from logradouro where "+campoapesquisar+" = "+pesquisa+" order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
        else
            sql = "select * from logradouro where "+campoapesquisar+" like '%"+pesquisa+"%' order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;        
            
            
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<LogradouroModel> listaLogradouro = new ArrayList<LogradouroModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                LogradouroModel logradouro = new LogradouroModel();
                logradouro.setLogcodigo(resultSet.getInt("logcodigo"));
                logradouro.setLogdescricao(resultSet.getString("logdescricao"));
                listaLogradouro.add(logradouro);
            }
            return listaLogradouro;
        } catch (SQLException ex) {
            Logger.getLogger(LogradouroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //connection.close();
            //ps.close();
            //resultSet.close();
        }
        return null;
    }

    //metodo para retornar o numero total de registros para paginacao
    public String totalRegistros(String pesquisa, String campoapesquisar) throws SQLException {
        PreparedStatement psConta = null;
        ResultSet resultSetConta = null;     
        String sqlConta = "";
        try {
            if (campoapesquisar.equals("logcodigo"))
                if (pesquisa.equals(""))
                    sqlConta = "select count(*) AS contaRegistros from logradouro where "+campoapesquisar+"> 0";
                else
                   sqlConta = "select count(*) AS contaRegistros from logradouro where "+campoapesquisar+" = "+pesquisa;
            
            else     
                sqlConta = "select count(*) AS contaRegistros from logradouro where "+campoapesquisar+" like '%"+pesquisa+"%'";
            psConta = connection.prepareStatement(sqlConta);
            resultSetConta = psConta.executeQuery();
            resultSetConta.next();
            String qtdTotalRegistros = resultSetConta.getString("contaRegistros");
            return qtdTotalRegistros;
        } catch (SQLException ex) {
            Logger.getLogger(LogradouroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            psConta.close();
            resultSetConta.close();
        }
        return "Nerizon com erro";
    }
    
     //excluir bairro usando Model LogradouroModel
    public boolean excluiLogradouro(LogradouroModel logradouro) throws SQLException {
        String sql = "delete from logradouro where logcodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            //System.out.println("baicodigo = "+bairro.getBaiCodigo());
            ps.setInt(1, logradouro.getLogcodigo());
            //System.out.println("sql = "+ps.toString());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LogradouroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }

        return false;
    }

    public void alteraLogradouro(LogradouroModel logradouro) throws SQLException {
        String sql = "UPDATE logradouro SET logdescricao=?  where logcodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, logradouro.getLogdescricao());
            ps.setInt(2, logradouro.getLogcodigo());
           
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LogradouroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }


    public void novoLogradouro(LogradouroModel logradouro) throws SQLException {
        String sql = "insert into logradouro (logdescricao) values (?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, logradouro.getLogdescricao());
            //System.out.println("sql novo registro = "+ps.toString());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LogradouroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }
}
