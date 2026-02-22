package Model.funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.connection.FBConexao;

public class funcionarioDao {
    FBConexao conexao = new FBConexao();
    PreparedStatement pst;
    ResultSet rs;
    // inseri um novo funcionario
    public void inserirFuncionario(funcionarioBeans fu){
        String insert = "EXECUTE PROCEDURE FUNCIONARIO_INSERIR (?, ?, ?, ?, ?, ?, ?);";
        try {
            pst = conexao.conectar().prepareStatement(insert);
            pst.setString(1, fu.getNomeFuncionario());
            pst.setInt(2, fu.getIdadeFuncionario());
            pst.setString(3, fu.getCpfFuncionario());
            pst.setString(4, fu.getTelefoneFuncionario());
            pst.setDouble(5, fu.getComissaoFuncionario());
            pst.setString(6, fu.getProfissaoFuncionario());
            pst.setDouble(7, fu.getSalarioFuncionario());
            pst.execute();
            System.out.println("Inseriu com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível inserir");
        } finally {
            conexao.desconectar();
        }
    }
    // lista todos os funcionarios
    public ArrayList<funcionarioBeans> listaFuncionario(){
        ArrayList<funcionarioBeans> funcionarios = new ArrayList<>();
        String read = "SELECT p.ID, p.NOME, p.IDADE, p.CPF, p.TELEFONE, p.COMISSAO, p.PROFISSAO, p.SALARIO FROM FUNCIONARIO_PESQ_NOME ('%') p;";
        try {
            pst = conexao.conectar().prepareStatement(read);
            rs = pst.executeQuery();
            while (rs.next()) {
                long a = rs.getLong(1);
                String b = rs.getString(2);
                int c = rs.getInt(3);
                String d = rs.getString(4);
                String e = rs.getString(5);
                double f = rs.getDouble(6);
                String g = rs.getString(7);
                double h = rs.getDouble(8);
                funcionarios.add(new funcionarioBeans(a, b, c, d, e, f, g, h));
            }
            return funcionarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }
    // atualiza os funcionarios inserindo novas informações
    public void updateFuncionario(funcionarioBeans f){
        String update = "EXECUTE PROCEDURE FUNCIONARIO_UPDATE (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            pst = conexao.conectar().prepareStatement(update);
            pst.setLong(1, f.getIdFuncionario());
            pst.setString(2, f.getNomeFuncionario());
            pst.setInt(3, f.getIdadeFuncionario());
            pst.setString(4, f.getCpfFuncionario());
            pst.setString(5, f.getTelefoneFuncionario());
            pst.setDouble(6, f.getComissaoFuncionario());
            pst.setString(7, f.getProfissaoFuncionario());
            pst.setDouble(8, f.getSalarioFuncionario());
            pst.execute();
            System.out.println("Atualizou com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível atualizar");
        } finally {
            conexao.desconectar();
        }
    }
    // deleta um funcionario
    public void deleteFuncionario(funcionarioBeans f){
        String delete = "EXECUTE PROCEDURE FUNCIONARIO_DELETE (?);";
        try {
            pst = conexao.conectar().prepareStatement(delete);
            pst.setLong(1, f.getIdFuncionario());
            pst.execute();
            System.out.println("Deletou com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível deletar");
        } finally {
            conexao.desconectar();
        }
    }
}
