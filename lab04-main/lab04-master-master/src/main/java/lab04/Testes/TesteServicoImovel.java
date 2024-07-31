package lab04.Testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab04.Imoveis;
import lab04.ServicosImovel;
import lab04.repository.ImovelRepository;
import lab04.repository.LocacaoRepository;
import lab04.repository.ProfissionaisRepository;
import lab04.repository.ServCliRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TesteServicoImovel {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab04");
        EntityManager manager = factory.createEntityManager();

        ServCliRepository servCliRepository = new ServCliRepository(manager);
        ImovelRepository imovelRepository = new ImovelRepository(manager);
        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);
        ProfissionaisRepository profissionaisRepository = new ProfissionaisRepository(manager);


        ServicosImovel servico1 = new ServicosImovel();
        /*
        servico1.setDataServico(LocalDate.now());
        servico1.setValorTotal(new BigDecimal("500.00"));
        servico1.setImovel(imovelRepository.buscaPorId(1));
        servico1.setProfissional(profissionaisRepository.buscaPorId(1));
        servico1.setLocacao(locacaoRepository.buscaPorId(1));
        */

        ServicosImovel servico2 = new ServicosImovel();
        /*
        servico2.setDataServico(LocalDate.now());
        servico2.setValorTotal(new BigDecimal("1000.00"));
        servico2.setImovel(imovelRepository.buscaPorId(1));
        servico2.setLocacao(locacaoRepository.buscaPorId(1));
        servico2.setProfissional(profissionaisRepository.buscaPorId(1));


        // Inserindo o serviço
        servCliRepository.inserir(servico1);
        servCliRepository.inserir(servico2);
        */

        // Atualizando o serviço
        /*
        servCliRepository.buscaPorId(1).setValorTotal(new BigDecimal("600.00"));
        servCliRepository.atualizar(servCliRepository.buscaPorId(1));
        System.out.println("Serviço atualizado: " + servCliRepository);
        */


        // Deletando o serviço
        /*
        servCliRepository.deletar(servCliRepository.buscaPorId(2));
        */

        // Listando todos os serviços

        manager.persist(imovelRepository.buscaPorId(2));
        List<ServicosImovel> servicos = servCliRepository.mostrarTodos();
        System.out.println("Lista de todos os serviços cadastrados:");
        for (ServicosImovel servico : servicos) {
            System.out.println(servico);
        }

        List<ServicosImovel> servicosPorLocacao = servCliRepository.listarPorLocacao(1);
        System.out.println("Lista de serviços por locação:");
        for (ServicosImovel servico : servicosPorLocacao) {
            System.out.println(servico);
        }

        manager.close();
        factory.close();
    }
}
