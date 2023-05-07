/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author lucia
 */
public class SingleConnection {

    //Apenas 1 conexão com o banco de dados (Singleton) o que tem várias são sessões/transações, o que é aberto e fechado.
    //Deploy Local:
    private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";   
    private static String user = "postgres";
    private static String senha = "admin";   
    //Deploy Hospedagem: ....

    private static Connection connection = null;

    static {
        //Caso chamar a classe diretamente também vai rodar o conectar
        conectar();
        System.out.println("Passei pelo metodo static amigo!!");
    }

    public SingleConnection() {
        //Caso instancie o objeto já vai rodar o conectar
        conectar();
    }

    private static void conectar() {
        //Conexão feita apenas uma vez, se ela estiver NULA!
        try {
            if (connection == null) {
                //Carrega o driver de conexão com o banco
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(banco, user, senha);
                //Para não efetuar commit no banco sem nosso comando:
                connection.setAutoCommit(false);
                System.out.println("Conectou com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
