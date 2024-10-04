
package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.entities.Lanche;

public interface LancheRepository {
    Resultado<Lanche> cadastrar(String nome, double valor);

    Resultado<List<Lanche>> listar();

    Resultado<Lanche> getById(int id);

}
