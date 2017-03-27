package br.com.futurebrindes.dao;

import br.com.futurebrindes.factory.ConnectionFactory;
import br.com.futurebrindes.javabean.model.ItensVendaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ItensVendaDao {
    
    private Connection connection;
    
    public ItensVendaDao() {
        this.connection = new ConnectionFactory().getConnetion();
    }
    //---------------------------------------------------------------------------
    public void novoItemVenda(ItensVendaModel itensVendaModel) throws SQLException {
        String sql = "insert into itensvenda (procodigo, vencodigo, venquantidade) values (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, itensVendaModel.getProcodigo());
            ps.setInt(2, itensVendaModel.getVencodigo());
            ps.setInt(3, itensVendaModel.getVenquantidade());
            //ps.setDouble(4, itensVendaModel.getVentotal());
            System.out.println("sql novo registro = " + ps.toString());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ItensVendaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //connection.close();
            //ps.close();
        }
    }
    //---------------------------------------------------------------------------
    public List getListaItensVenda(int codigoVenda) throws SQLException {
        String sql = "select * from itensvenda, produto where vencodigo = " + codigoVenda + " and itensvenda.procodigo = produto.procodigo ";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<ItensVendaModel> listaItensVenda = new ArrayList<ItensVendaModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ItensVendaModel itensVendaMovel = new ItensVendaModel();
                itensVendaMovel.setVentotal(resultSet.getDouble("ventotal"));
                itensVendaMovel.setProcodigo(resultSet.getInt("procodigo"));
                itensVendaMovel.setVencodigo(resultSet.getInt("vencodigo"));
                itensVendaMovel.setVenquantidade(resultSet.getInt("venquantidade"));
                itensVendaMovel.setProdescricao(resultSet.getString("prodescricao"));
                itensVendaMovel.setPrecounitario(resultSet.getDouble("proprecovenda"));
                listaItensVenda.add(itensVendaMovel);
            }
            return listaItensVenda;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
        }
        return null;
    }

    
}
