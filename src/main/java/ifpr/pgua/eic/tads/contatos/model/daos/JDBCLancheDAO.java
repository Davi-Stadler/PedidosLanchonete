package ifpr.pgua.eic.tads.contatos.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.FabricaConexoes;
import ifpr.pgua.eic.tads.contatos.model.entities.Lanche;
import ifpr.pgua.eic.tads.contatos.model.entities.Pedido;

public class JDBCLancheDAO implements LancheDAO {

    private FabricaConexoes conexaoFabrica;

    public JDBCLancheDAO(FabricaConexoes conexaoFabrica) {
        this.conexaoFabrica = conexaoFabrica;
    }

    @Override
    public Resultado<Lanche> criar(Lanche lanche) {
        try (Connection conexao = conexaoFabrica.getConnection();) {
            PreparedStatement declaracaoSQL = conexao
                    .prepareStatement("INSERT INTO oo_lanches(nome,valor) VALUES (?,?)");

            declaracaoSQL.setString(1, lanche.getNome());
            declaracaoSQL.setDouble(2, lanche.getValor());

            declaracaoSQL.executeUpdate();

            return Resultado.sucesso("Lanche cadastrado", lanche);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<List<Lanche>> listar() {
        ArrayList<Lanche> listaLanches = new ArrayList<>();
        try (Connection conexao = conexaoFabrica.getConnection();) {
            PreparedStatement declaracaoSQL = conexao.prepareStatement("SELECT * FROM oo_lanches");

            ResultSet resultadoConsulta = declaracaoSQL.executeQuery();

            while (resultadoConsulta.next()) {
                int id = resultadoConsulta.getInt("id");
                String nome = resultadoConsulta.getString("nome");
                Double valor = resultadoConsulta.getDouble("valor");

                Lanche lanche = new Lanche(id, nome, valor);

                listaLanches.add(lanche);
            }
            conexao.close();
            return Resultado.sucesso("Lista de lanches:", listaLanches);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado<Lanche> buscarLanche(Pedido pedido) {
        try (Connection conexao = conexaoFabrica.getConnection();) {
            PreparedStatement declaracaoSQL = conexao.prepareStatement(
                    "SELECT * FROM oo_lanches INNER JOIN pedidos ON idlanche = pedidos.idlanche WHERE pedidos.id = ?");

            declaracaoSQL.setInt(1, pedido.getId());

            ResultSet resultadoConsulta = declaracaoSQL.executeQuery();

            if (resultadoConsulta.next()) {
                int id = resultadoConsulta.getInt("idlanche");
                String nome = resultadoConsulta.getString("nome");
                Double valor = resultadoConsulta.getDouble("valor");

                Lanche lanche = new Lanche(id, nome, valor);
                return Resultado.sucesso("Lanche carregado", lanche);
            }
            return Resultado.erro("Lanche não encontrado");
        } catch (SQLException e) {
            return Resultado.erro("Erro na seleção: " + e.getMessage());
        }
    }
}
