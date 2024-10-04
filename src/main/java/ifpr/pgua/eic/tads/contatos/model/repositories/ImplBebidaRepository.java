package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.daos.BebidaDAO;
import ifpr.pgua.eic.tads.contatos.model.entities.Bebida;

public class ImplBebidaRepository implements BebidaRepository {

    private BebidaDAO dao;
    private List<Bebida> listaDeBebidas;

    public ImplBebidaRepository(BebidaDAO dao) {
        this.dao = dao;
        this.listaDeBebidas = new ArrayList<>();
    }

    @Override
    public Resultado<Bebida> cadastrar(String nome, double valor) {
        if (nome.isEmpty() || nome.isBlank()) {
            return Resultado.erro("Nome inválido!");
        }
        if (valor == 0.0) {
            return Resultado.erro("Valor inválido!");
        }

        Bebida bebida = new Bebida(nome, valor);
        return dao.criar(bebida);
    }

    @Override
    public Resultado<List<Bebida>> listar() {
        var resultado = dao.listar();

        if (resultado.foiSucesso()) {
            listaDeBebidas.clear();
            listaDeBebidas.addAll(resultado.comoSucesso().getObj());
        }

        return resultado;
    }

    @Override
    public Resultado<Bebida> getById(int id) {
        if (!listaDeBebidas.isEmpty()) {
            Bebida bebidaEncontrada = listaDeBebidas.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
            if (bebidaEncontrada != null) {
                return Resultado.sucesso("Bebida encontrada", bebidaEncontrada);
            }
        }
        return Resultado.erro("Problema ao buscar bebida!");
    }
}
