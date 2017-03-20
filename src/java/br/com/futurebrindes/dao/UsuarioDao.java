package br.com.futurebrindes.dao;

import br.com.futurebrindes.factory.ConnectionFactory;
import br.com.futurebrindes.javabean.model.UsuarioModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao {
    private Connection connection;

    public UsuarioDao() {
        this.connection = new ConnectionFactory().getConnetion();
    }
    
    /* Método que verifica se o usuário existe no banco de dados
    *  e existindo, se a senha digitada é a mesma cadastrada
    *  retorna verdadeiro caso afirmativo
    */
    public boolean verificaUsuario(UsuarioModel usuarios) throws SQLException {
        String sql = "select * from usuarios where usuario=? and senha=?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuarios.getUsuario());
            ps.setString(2, usuarios.getSenha());
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
        }
        return false;
    }

    /* Método que retorna determinado usuário e a senha
    *  da tabela usuario
    *  Retorna um objeto UsuarioModel, representando um usuario
    */        
    public UsuarioModel getUsuario(String usuario, String senha) throws SQLException {
        String sql = "select * from usuarios where usuario=? and senha=?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                UsuarioModel usuarios = new UsuarioModel();
                usuarios.setUsuario(usuario);
                usuarios.setSenha(senha);
                usuarios.setNivel(resultSet.getInt("nivel"));
                usuarios.setNomeCompleto(resultSet.getString("nomecompleto"));
//                System.out.println("O connection esta fechado ? = "+connection.isClosed());
//                System.out.println("O PreparedStatement esta fechado ? = "+ps.isClosed());
//                System.out.println("O ResultSet esta fechado ? = "+resultSet.isClosed());

                return usuarios;
            }
        } catch (SQLException ex) {
//            System.out.println("catch O connection esta fechado ? = "+connection.isClosed());
//                System.out.println("catchO PreparedStatement esta fechado ? = "+ps.isClosed());
//                System.out.println("catchO ResultSet esta fechado ? = "+resultSet.isClosed());
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
//                System.out.println("O connection esta fechado ? = "+connection.isClosed());
//                System.out.println("O PreparedStatement esta fechado ? = "+ps.isClosed());
//                System.out.println("O ResultSet esta fechado ? = "+resultSet.isClosed());
        }
        return null;
    }

    public List getListaUsuarios() throws SQLException {
        String sql = "select * from usuarios";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<UsuarioModel> listaUsuarios = new ArrayList<UsuarioModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                UsuarioModel usuarios = new UsuarioModel();
                usuarios.setUsuario(resultSet.getString("usuario"));
                usuarios.setSenha(resultSet.getString("senha"));
                usuarios.setNivel(resultSet.getInt("nivel"));
                usuarios.setNomeCompleto(resultSet.getString("nomecompleto"));
                listaUsuarios.add(usuarios);
            }
            return listaUsuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
        }
        return null;
    }

    public List getListaUsuariosPaginada(int pagina, String ordenacao, String pesquisa, String campoapesquisar) throws SQLException {
        int limite = 10;
        int offset = (pagina * limite) - limite;
        
        //String sql = "SELECT * FROM usuarios LIMIT "+offSet+","+limite;
        //String sql = "select * from usuarios LIMIT 10 OFFSET " + offset;
        
        //String sql = "SELECT * FROM usuarios LIMIT "+offset+","+limite;
        //String sql = "SELECT * FROM usuarios ORDER BY " + ordenacao + " LIMIT " + offset + "," + limite;
        
        //String sql = "SELECT * FROM usuarios WHERE nomecompleto LIKE '%"+ pesquisa +"%' ORDER BY " + ordenacao + " LIMIT " + offset + "," + limite;
        
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
        
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<UsuarioModel> listaUsuarios = new ArrayList<UsuarioModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                UsuarioModel usuarios = new UsuarioModel();
                usuarios.setUsuario(resultSet.getString("usuario"));
                usuarios.setSenha(resultSet.getString("senha"));
                usuarios.setNivel(resultSet.getInt("nivel"));
                usuarios.setNomeCompleto(resultSet.getString("nomecompleto"));
                listaUsuarios.add(usuarios);
            }
            return listaUsuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
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
        
        //String sqlConta = "select count(*) AS contaRegistros from usuarios WHERE nomecompleto LIKE '%"+ pesquisa +"%'";
        //String sqlConta = "select count(*) AS contaRegistros from usuarios WHERE "+ campoapesquisar +" LIKE '%"+ pesquisa +"%'";
            
        String sqlConta = "";
        try {
            if (campoapesquisar.equals("nivel"))
                if (pesquisa.equals(""))            
                    sqlConta = "select count(*) AS contaRegistros from usuarios where "+campoapesquisar+" > 0";
                else
                    sqlConta = "select count(*) AS contaRegistros from usuarios where "+campoapesquisar+" = "+pesquisa;
            else     
                sqlConta = "select count(*) AS contaRegistros from usuarios where "+campoapesquisar+" like '%"+pesquisa+"%'";
            
            psConta = connection.prepareStatement(sqlConta);
            resultSetConta = psConta.executeQuery();
            resultSetConta.next();
            String qtdTotalRegistros = resultSetConta.getString("contaRegistros");
            return qtdTotalRegistros;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            psConta.close();
            resultSetConta.close();
        }
        return "Nerizon com erro";
    }

    //excluir usuarios usando Model Usuarios
    public boolean excluiUsuario(UsuarioModel usuarios) throws SQLException {
        String sql = "delete from usuarios where usuario=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuarios.getUsuario());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }

        return false;
    }

    public void alteraUsuario(UsuarioModel usuarios) throws SQLException {
        String sql = "UPDATE usuarios SET senha=?, nivel=?,nomecompleto=? where usuario=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuarios.getSenha());
            ps.setInt(2, usuarios.getNivel());
            ps.setString(3, usuarios.getNomeCompleto());
            ps.setString(4, usuarios.getUsuario());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }

    //excluir usuarios usando variavel String
    public boolean excluiUsuario1(String usuarios) throws SQLException {
        String sql = "delete from usuarios where usuario=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuarios);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }

        return false;
    }
    
    
    public void novoUsuario(UsuarioModel usuarios) throws SQLException {
        String sql = "insert into usuarios(usuario, senha, nivel, nomecompleto) values (?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuarios.getUsuario());
            ps.setString(2, usuarios.getSenha());
            ps.setInt(3, usuarios.getNivel());
            ps.setString(4, usuarios.getNomeCompleto());
            System.out.println("Query: "+ps.toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }
    
    
   
        
    
}
