package ifpr.pgua.eic.tads.contatos.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.Bebida;
import ifpr.pgua.eic.tads.contatos.model.entities.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.entities.Pedido;

public class JDBCBebidaDAO implements BebidaDAO {

    private FabricaConexoes conexaoFabrica;

    public JDBCBebidaDAO(FabricaConexoes conexaoFabrica) {
        this.conexaoFabrica = conexaoFabrica;
    }

    @Override
    public Resultado<Bebida> criar(Bebida bebida) {
        try (Connection conexao = conexaoFabrica.getConnection();) {
            PreparedStatement declaracaoSQL = conexao
                    .prepareStatement("INSERT INTO oo_bebidas(nome,valor) VALUES (?,?)");

            declaracaoSQL.setString(1, bebida.getNome());
            declaracaoSQL.setDouble(2, bebida.getValor());

            declaracaoSQL.executeUpdate();

            return Resultado.sucesso("Bebida cadastrada", bebida);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<List<Bebida>> listar() {
        ArrayList<Bebida> listaBebidas = new ArrayList<>();
        try (Connection conexao = conexaoFabrica.getConnection();) {
            PreparedStatement declaracaoSQL = conexao.prepareStatement("SELECT * FROM oo_bebidas");

            ResultSet resultadoConsulta = declaracaoSQL.executeQuery();

            while (resultadoConsulta.next()) {
                int id = resultadoConsulta.getInt("id");
                String nome = resultadoConsulta.getString("nome");
                Double valor = resultadoConsulta.getDouble("valor");

                Bebida bebida = new Bebida(id, nome, valor);

                listaBebidas.add(bebida);
            }
            conexao.close();
            return Resultado.sucesso("Lista de bebidas:", listaBebidas);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<Bebida> buscarBebida(Pedido pedido) {
        try (Connection conexao = conexaoFabrica.getConnection();) {
            PreparedStatement declaracaoSQL = conexao.prepareStatement(
                    "SELECT * FROM oo_bebidas INNER JOIN pedidos ON idbebida = pedidos.idbebida WHERE pedidos.id = ?");

            declaracaoSQL.setInt(1, pedido.getId());

            ResultSet resultadoConsulta = declaracaoSQL.executeQuery();

            if (resultadoConsulta.next()) {
                int id = resultadoConsulta.getInt("idbebida");
                String nome = resultadoConsulta.getString("nome");
                Double valor = resultadoConsulta.getDouble("valor");

                Bebida bebida = new Bebida(id, nome, valor);
                return Resultado.sucesso("Bebida carregada", bebida);
            }
            return Resultado.erro("Bebida não encontrada");
        } catch (SQLException e) {
            return Resultado.erro("Erro na seleção: " + e.getMessage());
        }
    }
}
