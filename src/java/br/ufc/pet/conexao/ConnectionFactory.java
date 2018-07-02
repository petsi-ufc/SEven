package br.ufc.pet.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 * @author Escritorio projetos
 */
public class ConnectionFactory {

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            
          return DriverManager.getConnection("jdbc:postgresql://localhost:5432/seven", "postgres", "postgres");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }

    public static Connection getConexao(String JNDINome) throws Exception {
        // Obtém a raiz da hierarquia de nomes
        InitialContext contexto = new InitialContext();

        // Obtém a origem dos dados
        DataSource ds = (DataSource) contexto.lookup("java:comp/env/" + JNDINome);

        // Obtém uma conexão
        Connection con = ds.getConnection();
        return con;
    }
    //TESTE BANCO

    public static void main(String args[]) {
        String JNDINome = "jdbc/bd_seven";
        Connection conn;
        try {
            conn = ConnectionFactory.getConexao(JNDINome);
        } catch (Exception ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception, Exception, Exception {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        close(conn, stmt, rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) throws Exception {
        close(conn, stmt, null);
    }

    public static void closeConnection(Connection conn) throws Exception {
        close(conn, null, null);
    }
}
