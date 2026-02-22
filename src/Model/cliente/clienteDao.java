package Model.cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.connection.FBConexao;

public class clienteDao {
    FBConexao conexao = new FBConexao();
    PreparedStatement pst;
    ResultSet rs;
    //inseri um novo cliente
    public void inserirCliente(clienteBeans cl){
        String insert = "EXECUTE PROCEDURE CLIENTE_INSERIR (?, ?, ?, ?, ?);";
        try {
            pst = conexao.conectar().prepareStatement(insert);
            pst.setString(1, cl.getNomeCliente());
            pst.setInt(2, cl.getIdadeCliente());
            pst.setString(3, cl.getCpfCnpjCliente());
            pst.setString(4, String.valueOf(cl.getFisicaJuridicaCliente()));
            pst.setString(5, cl.getTelefoneCliente());
            pst.execute();
            System.out.println("Inseriu com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível inserir");
        } finally {
            conexao.desconectar();
        }
    }
    // mostra todos os clientes
    public ArrayList<clienteBeans> listarCliente() {
        ArrayList<clienteBeans> clientes = new ArrayList<>();
        String read = "SELECT p.ID, p.NOME, p.IDADE, p.CPF_CNPJ, p.F_J, p.TELEFONE FROM CLIENTE_PESQ_NOME ('%') p;";
        try {
            pst = conexao.conectar().prepareStatement(read);
            rs = pst.executeQuery();
            while (rs.next()) {
                long a = rs.getLong(1);
                String b = rs.getString(2);
                int c = rs.getInt(3);
                String d = rs.getString(4);
                char e = rs.getString(5).charAt(0);
                String f = rs.getString(6);
                clientes.add(new clienteBeans(a, b, c, d, e, f));
            }
            return clientes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }
    // atualiza o cliente inserindo novas informações
    public void updateCliente(clienteBeans cl){
        String update = "EXECUTE PROCEDURE CLIENTE_UPDATE (?, ?, ?, ?, ?, ?);";
        try {
            pst = conexao.conectar().prepareStatement(update);
            pst.setLong(1, cl.getIdCliente());
            pst.setString(2, cl.getNomeCliente());
            pst.setInt(3, cl.getIdadeCliente());
            pst.setString(4, cl.getCpfCnpjCliente());
            pst.setString(5, String.valueOf(cl.getFisicaJuridicaCliente()));
            pst.setString(6, cl.getTelefoneCliente());
            pst.execute();
            System.out.println("Atualizou com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível atualizar");
        } finally {
            conexao.desconectar();
        }
    }
    // deleta o cliente
    public void deleteCliente(clienteBeans cl){
        String delete = "EXECUTE PROCEDURE CLIENTE_DELETE (?);";
        try {
            pst = conexao.conectar().prepareStatement(delete);
            pst.setLong(1, cl.getIdCliente());
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
