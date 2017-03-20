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
        Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        connection.close();
        ps.close();
        resultSet.close();
    }
    return null;
}
}
