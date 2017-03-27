package br.com.futurebrindes.dao;

import br.com.futurebrindes.factory.ConnectionFactory;
import br.com.futurebrindes.javabean.model.VendaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VendaDao {
    
    private Connection connection;

    public VendaDao() {
        this.connection = new ConnectionFactory().getConnetion();
    }

    public void novaVenda(VendaModel vendaModel) throws SQLException {
        String sql = "insert into venda (clicodigo, vendata, venvalortotal) values (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, vendaModel.getCliCodigo());
            ps.setDate(2, new java.sql.Date(vendaModel.getVenData().getTime()));
            ps.setDouble(3, vendaModel.getVenValorTotal());
            System.out.println("sql novo registro = "+ps.toString());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //connection.close();
            //ps.close();
        }
    }
    //---------------------------------------------------------------------------
    public int totalRegistros() throws SQLException {
        PreparedStatement psConta = null;
        ResultSet resultSetConta = null;
        String sqlConta = "select count(*) AS contaRegistros from venda";// order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;        
        try {
            psConta = connection.prepareStatement(sqlConta);
            resultSetConta = psConta.executeQuery();
            resultSetConta.next();
            int qtdTotalRegistros = Integer.parseInt(resultSetConta.getString("contaRegistros"));
            return qtdTotalRegistros;
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            psConta.close();
            resultSetConta.close();
        }
        return 0;
    }
    
}
