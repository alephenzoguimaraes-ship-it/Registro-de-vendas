package Model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FBConexao{
    private String driver = "org.firebirdsql.jdbc.FBDriver";
    private String url = "jdbc:firebirdsql://localhost:5252/C:\\Users\\Aleph\\Documents\\RegistroVendas\\RegistroBD\\RegistroVendas.fdb";
    private String user = "sysdba";
    private String senha = "masterkey";
    
    // conecta no banco
    private Connection con = null;
    public Connection conectar() throws ClassNotFoundException{
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            System.out.println("conectou ao banco");
            return con;
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            System.err.println("Não foi possível conectar");
            return null;
        }
    }
    // desconecta do banco
    public void desconectar(){
        try {
            if(con != null && !con.isClosed()) {
                con.close();
                System.out.println("desconectou do banco");
            } else if(con.isClosed()) {
                System.out.println("A conexão já está fechada");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("não foi possível desconectar");
        }
    }
}
