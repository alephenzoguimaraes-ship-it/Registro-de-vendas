package Model.vendas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.connection.FBConexao;

public class pedidosDao {
    FBConexao conexao = new FBConexao();
    PreparedStatement pst;
    ResultSet rs;
    // inseri uma nova venda
    public void inserirPedido(pedidosBeans p){
        String insert = "EXECUTE PROCEDURE PEDIDOS_INSERIR (?, ?, ?, ?, ?, ?);";
        try {
            pst = conexao.conectar().prepareStatement(insert);
            pst.setLong(1, p.getIdCliente());
            pst.setLong(2, p.getIdProduto());
            pst.setLong(3, p.getIdFuncionario());
            pst.setLong(4, p.getQtdeComprada());
            pst.setBoolean(5, p.isStatusPagamentos());
            pst.setDate(6, java.sql.Date.valueOf(p.getDataCompra()));
            pst.execute();
            System.out.println("Inserir com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível inserir");
        } finally {
            conexao.desconectar();
        }
    }
    // atualizar essa venda com novas informações
    public void updatePedidos(pedidosBeans p){
        String update = "EXECUTE PROCEDURE PEDIDOS_UPDATE (?, ?, ?, ?, ?, ?, ?);";
        try {
            pst = conexao.conectar().prepareStatement(update);
            pst.setLong(1, p.getIdPedido());
            pst.setLong(2, p.getIdCliente());
            pst.setLong(3, p.getIdProduto());
            pst.setLong(4, p.getIdFuncionario());
            pst.setLong(5, p.getQtdeComprada());
            pst.setBoolean(6, p.isStatusPagamentos());
            pst.setDate(7, java.sql.Date.valueOf(p.getDataCompra()));
            pst.execute();
            System.out.println("Atualizado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível atualizar");
        } finally {
            conexao.desconectar();
        }
    }
    // deleta a venda
    public void deletePedidos(pedidosBeans p){
        String delete = "EXECUTE PROCEDURE PEDIDOS_DELETE (?);";
        try {
            pst = conexao.conectar().prepareStatement(delete);
            pst.setLong(1, p.getIdPedido());
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
