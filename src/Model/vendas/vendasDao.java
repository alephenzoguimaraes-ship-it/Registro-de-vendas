package Model.vendas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.connection.FBConexao;

public class vendasDao {
    FBConexao conexao = new FBConexao();
    PreparedStatement pst;
    ResultSet rs;
    // lista todas as vendas
    public ArrayList<vendasBeans> listarVendas(){
        ArrayList<vendasBeans> vendas = new ArrayList<>();
        String read = "SELECT r.ID_PEDIDO, r.ID_CLIENTE, r.NOME_CLIENTE, r.ID_PRODUTO, r.NOME_PRODUTO, CAST(r.VALOR_PRODUTO * r.QTDE_COMPRADA AS NUMERIC(15,2)) AS VALOR_TOTAL, r.QTDE_COMPRADA, r.ID_FUNCIONARIO, r.NOME_FUNCIONARIO, r.STATUS_PAGAMENTO, r.DATA_COMPRA FROM VENDAS r;";
        try {
            pst = conexao.conectar().prepareStatement(read);
            rs = pst.executeQuery();
            while (rs.next()) {
                Long a = rs.getLong(1);
                Long b = rs.getLong(2);
                String c = rs.getString(3);
                Long d = rs.getLong(4);
                String e = rs.getString(5);
                double f = rs.getDouble(6);
                Long g = rs.getLong(7);
                Long h = rs.getLong(8);
                String i = rs.getString(9);
                boolean j = rs.getBoolean(10);
                LocalDate k = rs.getDate(11).toLocalDate();
                vendas.add(new vendasBeans(a, b, c, d, e, f, g, h, i, j, k));
            }
            return vendas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }
}

