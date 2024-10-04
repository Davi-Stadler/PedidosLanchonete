package ifpr.pgua.eic.tads.contatos.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.entities.Pedido;

public class JDBCPedidoDAO implements PedidoDAO {

    private FabricaConexoes fabricaConexao;

    public JDBCPedidoDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    public Resultado<Pedido> criar(Pedido pedido) {
        try (Connection con = fabricaConexao.getConnection();) {

            PreparedStatement pstm = con
                    .prepareStatement("INSERT INTO pedidos(observacao,idbebida,idlanche) VALUES (?,?,?)");

            pstm.setString(1, pedido.getObservacao());
            pstm.setInt(2, pedido.getBebida().getId());
            pstm.setInt(3, pedido.getLanche().getId());

            pstm.executeUpdate();

            return Resultado.sucesso("Pedido cadastrado", pedido);
        } catch (SQLException e) {
            return Resultado.erro("Problema ao conectar " + e.getMessage());
        }
    }

    public Resultado<List<Pedido>> listar() {
        ArrayList<Pedido> oo_pedido = new ArrayList<>();

        try (Connection con = fabricaConexao.getConnection();) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM pedidos");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String observacao = rs.getString("observacao");

                Pedido pedido = new Pedido(id, observacao);

                oo_pedido.add(pedido);
            }
            con.close();
            return Resultado.sucesso("Lista de pedidos:", oo_pedido);
        } catch (SQLException e) {
            return Resultado.erro("Problema ao fazer seleção!! " + e.getMessage());
        }

    }

}
