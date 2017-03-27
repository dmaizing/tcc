package br.com.futurebrindes.dao;

import br.com.futurebrindes.factory.ConnectionFactory;
import br.com.futurebrindes.javabean.model.FornecedorModel;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FornecedorDao {

    private Connection connection;

    public FornecedorDao() {
        this.connection = new ConnectionFactory().getConnetion();
    }

    public List getListaFornecedorParaSelectOption() throws SQLException {

        //String   sql = "select * from cliente order by clinome";        
        String sql = "SELECT * FROM fornecedor ORDER BY for_nome";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<FornecedorModel> listaFornecedor = new ArrayList<FornecedorModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                FornecedorModel fornecedorModel = new FornecedorModel();
                fornecedorModel.setForCodigo(resultSet.getInt("for_codigo"));
                fornecedorModel.setForNome(resultSet.getString("for_nome"));
                listaFornecedor.add(fornecedorModel);
            }
            return listaFornecedor;

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
            resultSet.close();
        }
        return null;
    }

    public List getListaFornecedorPaginada(int pagina, String ordenacao, String pesquisa, String campoapesquisar) throws SQLException {
        int limite = 10;
        int offset = (pagina * limite) - limite;
        String sql = "";

        if (campoapesquisar.equals("for_codigo")) {
            if (pesquisa.equals("")) {
                sql = "SELECT * FROM fornecedor WHERE " + campoapesquisar + " > 0 ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;
            } else {
                sql = "SELECT * FROM fornecedor WHERE " + campoapesquisar + " = " + pesquisa + " ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;
            }
        } else {
            sql = "SELECT * FROM fornecedor WHERE " + campoapesquisar + " LIKE '%" + pesquisa + "%' ORDER BY " + ordenacao + " LIMIT 10 OFFSET " + offset;
        }

        out.println("sql = " + sql);

        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<FornecedorModel> listaFornecedor = new ArrayList<FornecedorModel>();
        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                FornecedorModel fornecedorModel = new FornecedorModel();
                fornecedorModel.setForCodigo(resultSet.getInt("for_codigo"));
                fornecedorModel.setBaiCodigo(resultSet.getInt("bai_codigo"));
                fornecedorModel.setLogCodigo(resultSet.getInt("log_codigo"));
                fornecedorModel.setCidCodigo(resultSet.getInt("cid_codigo"));
                fornecedorModel.setForNome(resultSet.getString("for_nome"));
                fornecedorModel.setForFantasia(resultSet.getString("for_fantasia"));
                fornecedorModel.setForNumero(resultSet.getString("for_numero"));
                fornecedorModel.setForComplemento(resultSet.getString("for_complemento"));
                fornecedorModel.setForCep(resultSet.getString("for_cep"));
                fornecedorModel.setForCNPJ(resultSet.getString("for_cnpj"));
                fornecedorModel.setForInscEst(resultSet.getString("for_inscest"));
                fornecedorModel.setForDataCadastro(resultSet.getDate("for_datacadastro"));
                fornecedorModel.setForFoneCel(resultSet.getString("for_fonecel"));
                fornecedorModel.setForFone2(resultSet.getString("for_fone2"));
                fornecedorModel.setForEmail(resultSet.getString("for_email"));
                fornecedorModel.setForObs(resultSet.getString("for_obs"));
                listaFornecedor.add(fornecedorModel);
            }
            return listaFornecedor;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
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
            if (campoapesquisar.equals("for_codigo")) {
                if (pesquisa.equals("")) {
                    sqlConta = "select count(*) AS contaRegistros from fornecedor where " + campoapesquisar + " > 0";// order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
                } else {
                    sqlConta = "select count(*) AS contaRegistros from fornecedor where " + campoapesquisar + " = " + pesquisa;//+" order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;
                }
            } else {
                sqlConta = "select count(*) AS contaRegistros from fornecedor where " + campoapesquisar + " like '%" + pesquisa + "%'";// order by "+ordenacao+ " LIMIT 10 OFFSET " + offset;        
            }
            psConta = connection.prepareStatement(sqlConta);
            resultSetConta = psConta.executeQuery();
            resultSetConta.next();
            String qtdTotalRegistros = resultSetConta.getString("contaRegistros");
            return qtdTotalRegistros;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            psConta.close();
            resultSetConta.close();
        }
        return "(Total Registros) Result com erro";
    }

    public boolean excluiFornecedor(FornecedorModel fornecedorModel) throws SQLException {
        String sql = "delete from fornecedor where for_codigo=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            //System.out.println("baicodigo = "+bairro.getBaiCodigo());
            ps.setInt(1, fornecedorModel.getForCodigo());
            //System.out.println("sql = "+ps.toString());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }

        return false;
    }

    public void alteraFornecedor(FornecedorModel fornecedorModel) throws SQLException {
        //String sql = "UPDATE cliente SET baicodigo=?,logcodigo=?,cidcodigo=?,clinome=?,clinumero=?,clicomplemento=?,clicep=?,clirg=?,clicpf=?,clidatanasc=?,clidatacadastro=?,clifonecel=?,clifone2=?,cliemail=?,clifoto=?,clisexo=?,cliobs=?  where clicodigo=?";
        String sql = "UPDATE fornecedor SET "
                + "bai_codigo=?,log_codigo=?,cid_codigo=?,for_nome=?,for_fantasia=?,for_numero=?,for_complemento=?,"
                + "for_cep=?,for_cnpj=?,for_inscest=?,for_datacadastro=?,for_fonecel=?,for_fone2=?,for_email=?,for_obs=?"
                + "where for_codigo=?";

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, fornecedorModel.getBaiCodigo());
            ps.setInt(2, fornecedorModel.getLogCodigo());
            ps.setInt(3, fornecedorModel.getCidCodigo());
            ps.setString(4, fornecedorModel.getForNome());
            ps.setString(5, fornecedorModel.getForFantasia());
            ps.setString(6, fornecedorModel.getForNumero());
            ps.setString(7, fornecedorModel.getForComplemento());
            ps.setString(8, fornecedorModel.getForCep());
            ps.setString(9, fornecedorModel.getForCNPJ());
            ps.setString(10, fornecedorModel.getForInscEst());
            ps.setDate(11, new java.sql.Date(fornecedorModel.getForDataCadastro().getTime()));
            ps.setString(12, fornecedorModel.getForFoneCel());
            ps.setString(13, fornecedorModel.getForFone2());
            ps.setString(14, fornecedorModel.getForEmail());
            ps.setString(15, fornecedorModel.getForObs());
            ps.setInt(16, fornecedorModel.getForCodigo());
            System.out.println("sql alterar= " + ps.toString());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }

    public void novoFornecedor(FornecedorModel fornecedorModel) throws SQLException {
        //String sql = "insert into cliente (baicodigo,logcodigo,cidcodigo,clinome,clinumero,clicomplemento,clicep,clirg,clicpf,clidatanasc,clidatacadastro,clifonecel,clifone2,cliemail,clifoto,clisexo,cliobs) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String sql = "INSERT INTO fornecedor ("
                + "bai_codigo,log_codigo,cid_codigo,for_nome,for_fantasia,for_numero,for_complemento,"
                + "for_cep,for_cnpj,for_inscest,for_datacadastro,for_fonecel,for_fone2,for_email,for_obs) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //System.out.println(sql);

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, fornecedorModel.getBaiCodigo());
            ps.setInt(2, fornecedorModel.getLogCodigo());
            ps.setInt(3, fornecedorModel.getCidCodigo());
            ps.setString(4, fornecedorModel.getForNome());
            ps.setString(5, fornecedorModel.getForFantasia());
            ps.setString(6, fornecedorModel.getForNumero());
            ps.setString(7, fornecedorModel.getForComplemento());
            ps.setString(8, fornecedorModel.getForCep());
            ps.setString(9, fornecedorModel.getForCNPJ());
            ps.setString(10, fornecedorModel.getForInscEst());
            ps.setDate(11, new java.sql.Date(fornecedorModel.getForDataCadastro().getTime()));
            ps.setString(12, fornecedorModel.getForFoneCel());
            ps.setString(13, fornecedorModel.getForFone2());
            ps.setString(14, fornecedorModel.getForEmail());
            ps.setString(15, fornecedorModel.getForObs());
            System.out.println("sql = " + ps.toString());

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
            ps.close();
        }
    }

}
