/*
 * Prof Neri Neitzke - Mais de 100 palestras em 7 países
 * Videoaulas Neri wwww.informaticon.com.br
 * email: videoaulaneri@gmail.com
 *//*
 * Prof Neri Neitzke - Mais de 100 palestras em 7 países
 * Videoaulas Neri wwww.informaticon.com.br
 * email: videoaulaneri@gmail.com
 */

package br.com.futurebrindes.dao;

import br.com.futurebrindes.factory.ConnectionFactory;
import br.com.futurebrindes.javabean.model.BairroModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
    Videoaulas Neri www.informaticon.com.br   email:videoaulaneri@gmail.com
 */
public class BairroDao {
    
        private Connection connection;

    public BairroDao() {
        this.connection = new ConnectionFactory().getConnetion();
    }
    
    public List getListaBairroParaSelectOption() throws SQLException {
       
        String   sql = "select * from bairro  order by baidescricao";        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<BairroModel> listaBairro = new ArrayList<BairroModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                BairroModel bairro = new BairroModel();
                bairro.setBaiCodigo(resultSet.getInt("baiCodigo"));
                bairro.setBaiDescricao(resultSet.getString("baiDescricao"));
                listaBairro.add(bairro);
            }
            return listaBairro;
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
        }
        return null;
    }
    
       public List getListaBairroPaginada(int pagina, String ordenacao, String pesquisa, String campoapesquisar) throws SQLException {
        int limite = 10;
        int offset = (pagina * limite) - limite;
        String sql = "";  
          
//        if (campoapesquisar.equals("baicodigo")) 
//            if (pesquisa.equals(""))
//                sql = "select * from bairro where "+campoapesquisar+" > 0 order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
//            else
//                sql = "select * from bairro where "+campoapesquisar+" = "+pesquisa+" order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
//        else
//             sql = "select * from bairro where "+campoapesquisar+" like '%"+pesquisa+"%' order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;        
//        

/*

String sql = "";
        if (campoapesquisar.equals("nivel"))
            if (pesquisa.equals(""))
               //sql = "select * from usuarios where "+campoapesquisar+" > 0  order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
                sql = "SELECT * FROM usuarios WHERE "+campoapesquisar+" > 0 ORDER BY " + ordenacao + " LIMIT " + offset + "," + limite;
            else
                //sql = "select * from usuarios where "+campoapesquisar+" = "+pesquisa+" order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
                sql = "SELECT * FROM usuarios WHERE "+campoapesquisar+" = "+ pesquisa +" ORDER BY " + ordenacao + " LIMIT " + offset + "," + limite;    
        else
             //sql = "select * from usuarios where "+campoapesquisar+" like '%"+pesquisa+"%' order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
            sql = "SELECT * FROM usuarios WHERE "+campoapesquisar+" LIKE '%"+ pesquisa +"%' ORDER BY " + ordenacao + " LIMIT " + offset + "," + limite;

*/

        if (campoapesquisar.equals("baicodigo")) 
            if (pesquisa.equals(""))
                sql = "SELECT * FROM bairro WHERE " + campoapesquisar + " > 0 ORDER BY " + ordenacao + " LIMIT " + offset + "," + limite;
            else
                sql = "SELECT * FROM bairro WHERE " + campoapesquisar + " = " + pesquisa + " ORDER BY " + ordenacao+ " LIMIT " + offset + "," + limite;
        else
             sql = "SELECT * FROM bairro WHERE " + campoapesquisar + " LIKE '%" + pesquisa + "%' ORDER BY " + ordenacao + " LIMIT " + offset + "," + limite;        

        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<BairroModel> listaBairro = new ArrayList<BairroModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                BairroModel bairro = new BairroModel();
                bairro.setBaiCodigo(resultSet.getInt("baiCodigo"));
                bairro.setBaiDescricao(resultSet.getString("baiDescricao"));
                listaBairro.add(bairro);
            }
            return listaBairro;
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
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
            if (campoapesquisar.equals("baicodigo"))
                if (pesquisa.equals(""))
                    sqlConta = "select count(*) AS contaRegistros from bairro where "+campoapesquisar+"> 0";
                else
                   sqlConta = "select count(*) AS contaRegistros from bairro where "+campoapesquisar+" = "+pesquisa;
            
            else     
                sqlConta = "select count(*) AS contaRegistros from bairro where "+campoapesquisar+" like '%"+pesquisa+"%'";
            psConta = connection.prepareStatement(sqlConta);
            resultSetConta = psConta.executeQuery();
            resultSetConta.next();
            String qtdTotalRegistros = resultSetConta.getString("contaRegistros");
            return qtdTotalRegistros;
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            psConta.close();
            resultSetConta.close();
        }
        return "Nerizon com erro";
    }
    
     //excluir bairro usando Model BairroModel
    public boolean excluiBairro(BairroModel bairro) throws SQLException {
        String sql = "delete from bairro where baicodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            //System.out.println("baicodigo = "+bairro.getBaiCodigo());
            ps.setInt(1, bairro.getBaiCodigo());
            //System.out.println("sql = "+ps.toString());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }

        return false;
    }

    public void alteraBairro(BairroModel bairro) throws SQLException {
        String sql = "UPDATE bairro SET baiDescricao=?  where baiCodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, bairro.getBaiDescricao());
            ps.setInt(2, bairro.getBaiCodigo());
           
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }



    public void novoBairro(BairroModel bairro) throws SQLException {
        String sql = "insert into bairro (baidescricao) values (?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, bairro.getBaiDescricao());
            //System.out.println("sql novo registro = "+ps.toString());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }

}
