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
}
