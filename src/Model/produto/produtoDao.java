package Model.produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.connection.FBConexao;

public class produtoDao {
    FBConexao conexao = new FBConexao();
    ResultSet rs;
    PreparedStatement pst;
    // inseri um novo produto
    public void inserirProduto(produtoBeans pr){
        String insert = "EXECUTE PROCEDURE PRODUTO_INSERIR (?, ?, ?, ?);";
        try {
            pst = conexao.conectar().prepareStatement(insert);
            pst.setString(1, pr.getNomeProduto());
            pst.setString(2, pr.getDescricaoProduto());
            pst.setLong(3, pr.getQtdeProduto());
            pst.setDouble(4, pr.getValorProduto());
            pst.executeUpdate();
            System.out.println("Inseriu com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não conseguiu inserir");
        } finally {
            conexao.desconectar();
        }
    }
    // lista todos os produtos
    public ArrayList<produtoBeans> listarProduto() {
        ArrayList<produtoBeans> produtos = new ArrayList<>();
        String read = "SELECT * FROM PRODUTO_PESQ_NOME ('%')";
        try {
            pst = conexao.conectar().prepareStatement(read);
            rs = pst.executeQuery();
            while (rs.next()) {
                long a = rs.getLong(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                long d = rs.getLong(4);
                double e = rs.getDouble(5);
                produtos.add(new produtoBeans(a, b, c, d, e));
            }
            return produtos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }
    // atualiza o produto inserindo novas informações
    public void updateProduto(produtoBeans pr){
        String update = "EXECUTE PROCEDURE PRODUTO_UPDATE (?, ?, ?, ?, ?);";
        try {
            pst = conexao.conectar().prepareStatement(update);
            pst.setLong(1, pr.getIdProduto());
            pst.setString(2, pr.getNomeProduto());
            pst.setString(3, pr.getDescricaoProduto());
            pst.setLong(4, pr.getQtdeProduto());
            pst.setDouble(5, pr.getValorProduto());
            pst.execute();
            System.out.println("Atualizou com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não conseguiu atualizar");
        } finally {
            conexao.desconectar();
        }
    }
    // deleta um produto
    public void deletarProduto(produtoBeans pr) {
        String delete = "EXECUTE PROCEDURE PRODUTO_DELETE (?)";
        try {
            pst = conexao.conectar().prepareStatement(delete);
            pst.setLong(1, pr.getIdProduto());
            pst.execute();
            System.out.println("Deletou com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não conseguiu deletar");
        }finally {
            conexao.desconectar();
        }
    }
}
