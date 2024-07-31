package lab04.Testes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab04.Profissionais;
import lab04.repository.ProfissionaisRepository;

import java.math.BigDecimal;
import java.util.List;

public class TesteProfissionais {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab04");
        EntityManager manager = factory.createEntityManager();

        ProfissionaisRepository repo = new ProfissionaisRepository(manager);

        Profissionais prof1 = new Profissionais();
        prof1.setNome("Maria Silva");
        prof1.setProfissao("Engenheira Civil");
        prof1.setTelefone1("123456789");
        prof1.setTelefone2("987654321");
        prof1.setValorHora(new BigDecimal("100.00"));

        Profissionais prof2 = new Profissionais();
        prof2.setNome("João Souza");
        prof2.setProfissao("Arquiteto");
        prof2.setTelefone1("987654321");
        prof2.setTelefone2("123456789");
        prof2.setValorHora(new BigDecimal("150.00"));

        repo.inserir(prof1);
        repo.inserir(prof2);


        // Atualizando o profissional
        /*
        repo.buscaPorId(1).setProfissao("Engenheira de Software");
        repo.atualizar(repo.buscaPorId(1));
        */


        // Deletando o profissional
        /*
        repo.deletar(repo.buscaPorId(2));
          */

        // Listando todos os profissionais
        List<Profissionais> profissionais = repo.mostrarTodos();
        System.out.println("Lista de todos os profissionais cadastrados:");
        for (Profissionais prof : profissionais) {
            System.out.println(prof);
        }

        manager.close();
        factory.close();
    }
}
