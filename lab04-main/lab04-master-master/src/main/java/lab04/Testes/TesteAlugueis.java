package lab04.Testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab04.Alugueis;
import lab04.repository.AlugueisRepository;
import lab04.repository.ClienteRepository;
import lab04.repository.LocacaoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TesteAlugueis {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab04");
        EntityManager manager = factory.createEntityManager();

        AlugueisRepository alugueisRepository = new AlugueisRepository(manager);
        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);
        

        Alugueis aluguel = new Alugueis();
        aluguel.setDataPagamento(LocalDate.now());
        aluguel.setDataVencimento(LocalDate.now().plusDays(10));
        aluguel.setValorPago(new BigDecimal("800.00"));
        aluguel.setLocacao(locacaoRepository.buscaPorId(2));
        manager.persist(aluguel);

        Alugueis aluguelAtrasado = new Alugueis();
        aluguelAtrasado.setDataPagamento(LocalDate.now().plusDays(1));
        aluguelAtrasado.setDataVencimento(LocalDate.now().minusDays(5));
        aluguelAtrasado.setValorPago(new BigDecimal("900.00"));
        aluguelAtrasado.setLocacao(locacaoRepository.buscaPorId(2));
        manager.persist(aluguelAtrasado);

        // Deletando um Aluguel
        /*
        locacaoRepository.deletar(locacaoRepository.buscaPorId(2));
          */

        // Listar todos os aluguéis de um determinado inquilino
        System.out.println("Listando todos os aluguéis do inquilino:");
        List<Alugueis> alugueisInquilino = alugueisRepository.listarPorInquilino("João");
        for (Alugueis a : alugueisInquilino) {
            System.out.println(a);
        }

        // Listar todos os aluguéis de uma locação específica
        System.out.println("Listando todos os aluguéis da locação:");
        List<Alugueis> alugueisLocacao = alugueisRepository.listarPorLocacao(2);
        for (Alugueis a : alugueisLocacao) {
            System.out.println(a);
        }

        // Listar todos os aluguéis com limite de preço
        System.out.println("Listando todos os aluguéis com limite de preço:");
        List<Alugueis> alugueisPorPreco = alugueisRepository.listarPorLimitePreco(new BigDecimal("850.00"));
        for (Alugueis a : alugueisPorPreco) {
            System.out.println(a);
        }

        // Listar todos os aluguéis pagos com atraso
        System.out.println("Listando todos os aluguéis pagos com atraso:");
        List<Alugueis> alugueisAtrasados = alugueisRepository.listarAtrasados();
        for (Alugueis a : alugueisAtrasados) {
            System.out.println(a);
        }
        manager.close();
        factory.close();
    }
}
