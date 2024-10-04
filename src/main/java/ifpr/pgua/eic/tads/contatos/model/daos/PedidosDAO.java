package ifpr.pgua.eic.tads.contatos.model.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidosDAO {

    private static final String URL = "jdbc:mysql://wagnerweinert.com.br:3306/tads23_davimendes";
    private static final String USER = "tads23_davimendes";
    private static final String PASSWORD = "tads23_davimendes";

    public void inserirPedido(String observacao, String lanche, String bebida) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO pedidos (observacao, id_lanche, id_bebida) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, observacao);
                statement.setString(2, lanche);
                statement.setString(3, bebida);
                statement.executeUpdate();
                System.out.println("Pedido inserido com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerPedido(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM pedidos WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                System.out.println("Pedido removido com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarPedidos() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM pedidos";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String observacao = resultSet.getString("observacao");
                    int lanche = resultSet.getInt("id_lanche");
                    int bebida = resultSet.getInt("id_bebida");
                    System.out.println(
                            "ID: " + id + ", Campo2: " + observacao + ", Campo3: " + lanche + ", Campo4:" + bebida);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}