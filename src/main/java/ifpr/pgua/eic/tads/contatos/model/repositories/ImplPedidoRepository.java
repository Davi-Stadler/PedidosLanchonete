package ifpr.pgua.eic.tads.contatos.model.repositories;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tads.contatos.model.daos.BebidaDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.LancheDAO;
import ifpr.pgua.eic.tads.contatos.model.daos.PedidoDAO;
import ifpr.pgua.eic.tads.contatos.model.entities.Bebida;
import ifpr.pgua.eic.tads.contatos.model.entities.Lanche;
import ifpr.pgua.eic.tads.contatos.model.entities.Pedido;

public class ImplPedidoRepository implements PedidoRepository {

    private PedidoDAO dao;
    private LancheDAO lancheDao;
    private BebidaDAO bebidaDao;

    public ImplPedidoRepository(PedidoDAO dao, LancheDAO lancheDao, BebidaDAO bebidaDao) {
        this.dao = dao;
        this.lancheDao = lancheDao;
        this.bebidaDao = bebidaDao;
    }

    @Override
    public Resultado<Pedido> cadastrar(String observacao, Bebida bebida, Lanche lanche) {
        if (observacao.isBlank() || observacao.isEmpty()) {
            return Resultado.erro("Observação inválida!");
        }

        Pedido pedido = new Pedido(observacao, bebida, lanche);
        return dao.criar(pedido);

    }

    @Override
    public Resultado<List<Pedido>> listar() {
        Resultado<List<Pedido>> resultado = dao.listar();

        if (resultado.foiSucesso()) {
            List<Pedido> lista = resultado.comoSucesso().getObj();
            for (Pedido p : lista) {
                Resultado<Lanche> res2 = lancheDao.buscarLanche(p);
                Resultado<Bebida> res3 = bebidaDao.buscarBebida(p);
                if (res2.foiErro()) {
                    return res2.comoErro();
                }
                if (res3.foiErro()) {
                    return res3.comoErro();
                }
                p.setLanche(res2.comoSucesso().getObj());
                p.setBebida(res3.comoSucesso().getObj());
            }
        }
        return resultado;
    }
}
