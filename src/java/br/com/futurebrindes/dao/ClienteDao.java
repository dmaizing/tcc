package br.com.futurebrindes.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.futurebrindes.factory.ConnectionFactory;
import br.com.futurebrindes.javabean.model.ClienteModel;
import static java.lang.System.out;

public class ClienteDao {

    private Connection connection;

    public ClienteDao() {
        this.connection = new ConnectionFactory().getConnetion();
    }

    public List getListaClienteParaSelectOption() throws SQLException {

        //String   sql = "select * from cliente order by clinome";        
        String sql = "SELECT * FROM cliente ORDER BY clinome";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<ClienteModel> listaCliente = new ArrayList<ClienteModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ClienteModel clienteModel = new ClienteModel();
                clienteModel.setCliCodigo(resultSet.getInt("clicodigo"));
                clienteModel.setCliNome(resultSet.getString("clinome"));
                listaCliente.add(clienteModel);
            }
            return listaCliente;
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
        }
        return null;
    }

    public List getListaClientePaginada(int pagina, String ordenacao, String pesquisa, String campoapesquisar) throws SQLException {
        int limite = 10;
        int offset = (pagina * limite) - limite;
        String sql = "";

        if (campoapesquisar.equals("clicodigo")) {
            if (pesquisa.equals("")) {
                sql = "SELECT * FROM cliente WHERE " + campoapesquisar + " > 0 ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;
            } else {
                sql = "SELECT * FROM cliente WHERE " + campoapesquisar + " = " + pesquisa + " ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;
            }
        } else {
            sql = "SELECT * FROM cliente WHERE " + campoapesquisar + " LIKE '%" + pesquisa + "%' ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;
        }

        //sql = "SELECT * FROM cliente ORDER BY " + campoapesquisar + " LIMIT 10 OFFSET 0";  
        //sql = "SELECT * FROM cliente WHERE "+campoapesquisar+" LIKE %'" + pesquisa + "'% ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;        
        //sql = "SELECT * FROM cliente WHERE "+campoapesquisar+" LIKE '%"+pesquisa+"%' ORDER BY "+ordenacao+ " LIMIT 10 OFFSET " + offset;        
        //out.println("sql = " + sql);
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<ClienteModel> listaCliente = new ArrayList<ClienteModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ClienteModel clienteModel = new ClienteModel();
                clienteModel.setCliCodigo(resultSet.getInt("clicodigo"));
                clienteModel.setCliNome(resultSet.getString("clinome"));
                clienteModel.setLogCodigo(resultSet.getInt("logcodigo"));
                clienteModel.setBaiCodigo(resultSet.getInt("baicodigo"));
                clienteModel.setCidCodigo(resultSet.getInt("cidcodigo"));
                clienteModel.setCliNumero(resultSet.getString("clinumero"));
                clienteModel.setCliComplemento(resultSet.getString("clicomplemento"));
                clienteModel.setCliCep(resultSet.getString("clicep"));
                clienteModel.setCliRg(resultSet.getString("clirg"));
                clienteModel.setCliCpf(resultSet.getString("clicpf"));
                clienteModel.setCliDataNasc(resultSet.getDate("clidatanasc"));
                clienteModel.setCliDataCadastro(resultSet.getDate("clidatacadastro"));
                clienteModel.setCliFoneCel(resultSet.getString("clifonecel"));
                clienteModel.setCliFone2(resultSet.getString("clifone2"));
                clienteModel.setCliEmail(resultSet.getString("cliemail"));
                clienteModel.setCliFoto(resultSet.getString("clifoto"));
                clienteModel.setCliSexo(resultSet.getString("clisexo"));
                clienteModel.setCliObs(resultSet.getString("cliobs"));
                listaCliente.add(clienteModel);
            }
            return listaCliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
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
            if (campoapesquisar.equals("clicodigo")) {
                if (pesquisa.equals("")) {
                    sqlConta = "select count(*) AS contaRegistros from cliente where " + campoapesquisar + " > 0";// order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
                } else {
                    sqlConta = "select count(*) AS contaRegistros from cliente where " + campoapesquisar + " = " + pesquisa;//+" order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
                }
            } else {
                sqlConta = "select count(*) AS contaRegistros from cliente where " + campoapesquisar + " like '%" + pesquisa + "%'";// order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;        
            }
            psConta = connection.prepareStatement(sqlConta);
            resultSetConta = psConta.executeQuery();
            resultSetConta.next();
            String qtdTotalRegistros = resultSetConta.getString("contaRegistros");
            return qtdTotalRegistros;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            psConta.close();
            resultSetConta.close();
        }
        return "Nerizon com erro";
    }

    public boolean excluiCliente(ClienteModel clienteModel) throws SQLException {
        String sql = "delete from cliente where clicodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            //System.out.println("baicodigo = "+bairro.getBaiCodigo());
            ps.setInt(1, clienteModel.getCliCodigo());
            //System.out.println("sql = "+ps.toString());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }

        return false;
    }

    public void alteraCliente(ClienteModel clienteModel) throws SQLException {
        String sql = "UPDATE cliente SET baicodigo=?,logcodigo=?,cidcodigo=?,clinome=?,clinumero=?,clicomplemento=?,clicep=?,clirg=?,clicpf=?,clidatanasc=?,clidatacadastro=?,clifonecel=?,clifone2=?,cliemail=?,clifoto=?,clisexo=?,cliobs=?  where clicodigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, clienteModel.getBaiCodigo());
            ps.setInt(2, clienteModel.getLogCodigo());
            ps.setInt(3, clienteModel.getCidCodigo());
            ps.setString(4, clienteModel.getCliNome());
            ps.setString(5, clienteModel.getCliNumero());
            ps.setString(6, clienteModel.getCliComplemento());
            ps.setString(7, clienteModel.getCliCep());
            ps.setString(8, clienteModel.getCliRg());
            ps.setString(9, clienteModel.getCliCpf());
            ps.setDate(10, new java.sql.Date(clienteModel.getCliDataNasc().getTime()));
            ps.setDate(11, new java.sql.Date(clienteModel.getCliDataCadastro().getTime()));
            ps.setString(12, clienteModel.getCliFoneCel());
            ps.setString(13, clienteModel.getCliFone2());
            ps.setString(14, clienteModel.getCliEmail());
            ps.setString(15, clienteModel.getCliFoto());
            ps.setString(16, clienteModel.getCliSexo());
            ps.setString(17, clienteModel.getCliObs());
            ps.setInt(18, clienteModel.getCliCodigo());

            System.out.println("sql alterar= " + ps.toString());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }

    public void novoCliente(ClienteModel clienteModel) throws SQLException {
        String sql = "insert into cliente (baicodigo,logcodigo,cidcodigo,clinome,clinumero,clicomplemento,clicep,clirg,clicpf,clidatanasc,clidatacadastro,clifonecel,clifone2,cliemail,clifoto,clisexo,cliobs) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //String sql = "insert into cliente (baicodigo,logcodigo,cidcodigo,clinome,clinumero,clicomplemento,clicep,clirg,clicpf,clifonecel,clifone2,cliemail,clifoto,clisexo,cliobs) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        System.out.println(sql);

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, clienteModel.getBaiCodigo());
            ps.setInt(2, clienteModel.getLogCodigo());
            ps.setInt(3, clienteModel.getCidCodigo());
            ps.setString(4, clienteModel.getCliNome());
            ps.setString(5, clienteModel.getCliNumero());
            ps.setString(6, clienteModel.getCliComplemento());
            ps.setString(7, clienteModel.getCliCep());
            ps.setString(8, clienteModel.getCliRg());
            ps.setString(9, clienteModel.getCliCpf());

            //ps.setDate(10, new java.sql.Date( clienteModel.getCliDataNasc().getTime() ) );  
            //ps.setDate(11, new java.sql.Date( clienteModel.getCliDataCadastro().getTime() ) );  
            ps.setDate(10, new java.sql.Date(clienteModel.getCliDataNasc().getTime()));
            ps.setDate(11, new java.sql.Date(clienteModel.getCliDataCadastro().getTime()));

            ps.setString(12, clienteModel.getCliFoneCel());
            ps.setString(13, clienteModel.getCliFone2());
            ps.setString(14, clienteModel.getCliEmail());
            ps.setString(15, clienteModel.getCliFoto());
            ps.setString(16, clienteModel.getCliSexo());
            ps.setString(17, clienteModel.getCliObs());

            System.out.println("sql = " + ps.toString());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }

}
