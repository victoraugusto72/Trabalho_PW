package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Classe;
import br.edu.ifsul.modelo.Passagem;
import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteInsert {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TesteInsert() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("PW-2019-1");
        em = emf.createEntityManager(); 
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void Teste() {
        // Inserir Pessoa
        // pessoa 1
        Pessoa joao = new Pessoa();
        joao.setCpf("692.029.860-02");
        joao.setEmail("joao@jmail.com.br");
        joao.setNome("Joãozinho das baixadas");
        joao.setTelefone("40028922");
        
        em.getTransaction().begin();
        em.persist(joao);
        em.getTransaction().commit();
        
        // pessoa 2
        Pessoa johnny = new Pessoa();
        johnny.setCpf("423.692.230-49");
        johnny.setEmail("johnnyjoestar@jjmail.com.br.jp");
        johnny.setNome("Johnny Joestar");
        johnny.setTelefone("84779922");
        
        em.getTransaction().begin();
        em.persist(johnny);
        em.getTransaction().commit();
       
        
        // Inserir Classe
        // classe 1
        Classe primeira = new Classe();
        primeira.setNome("primeira");
        primeira.setValor(1271.17);
    
        em.getTransaction().begin();
        em.persist(primeira);
        em.getTransaction().commit();
        
        // classe 2
        Classe segunda = new Classe();
        segunda.setNome("segunda");
        segunda.setValor(131.71);
       
        em.getTransaction().begin();
        em.persist(segunda);
        em.getTransaction().commit();
        
        // Inserir Cidade
        // cidade 1
        Cidade pf = new Cidade();
        pf.setNome("Passo Fundo");
        pf.setPais("Brasil");
        
        em.getTransaction().begin();
        em.persist(pf);
        em.getTransaction().commit();
        
        // cidade 2
        Cidade poa = new Cidade();
        poa.setNome("Porto Alegre");
        poa.setPais("Brasil");
        
        em.getTransaction().begin();
        em.persist(poa);
        em.getTransaction().commit();
        
        
        // Inserir Aeroporto
        
        // aeroporto 1
        Aeroporto aero = new Aeroporto();
        aero.setCidade(em.find(Cidade.class, 1));
        aero.setNome("Aero Pf");
        aero.setOperacaoNoturna(Boolean.TRUE);
        
        em.getTransaction().begin();
        em.persist(aero);
        em.getTransaction().commit();
    
        // aeroporto 2
        Aeroporto aero2 = new Aeroporto();
        aero2.setCidade(em.find(Cidade.class, 2));
        aero2.setNome("Porto Aero");
        aero2.setOperacaoNoturna(Boolean.TRUE);
        
        em.getTransaction().begin();
        em.persist(aero2);
        em.getTransaction().commit();
        

        // Inserir Voo
        
        // voo 1
        Voo voo_um = new Voo();
        voo_um.setAtivo(Boolean.TRUE);
        voo_um.setDescricao("Voo Muito Locao para POA");
        voo_um.setPeriodicidade("Mensal");
        voo_um.setTempoEstimado(234.34);
        List<Aeroporto> escalas = new ArrayList<>();
        escalas.add(em.find(Aeroporto.class, 2));
        voo_um.setEscalas(escalas);
        voo_um.setVoosAgendados(null);
        
        em.getTransaction().begin();
        em.persist(voo_um);
        em.getTransaction().commit();
        
        // voo 2
        Voo voo_dois = new Voo();
        voo_dois.setAtivo(Boolean.TRUE);
        voo_dois.setDescricao("Voo Muito Locao para POA");
        voo_dois.setPeriodicidade("Mensal");
        voo_dois.setTempoEstimado(234.34);
        List<Aeroporto> escalas_dois = new ArrayList<>();
        escalas_dois.add(em.find(Aeroporto.class, 1));
        voo_dois.setEscalas(escalas_dois);
        voo_dois.setVoosAgendados(null);
        
        em.getTransaction().begin();
        em.persist(voo_dois);
        em.getTransaction().commit();
        
        
        // Inserir VooAgendado
        
        // voo agendado 1
        VooAgendado agendado_1 = new VooAgendado();
        agendado_1.setAeronave("2131D");
        agendado_1.setData(Calendar.getInstance());
        agendado_1.setPassagens(null);
        agendado_1.setTotalPassageiros(666);
        agendado_1.setVoo(em.find(Voo.class, 1));
        
        em.getTransaction().begin();
        em.persist(agendado_1);
        em.getTransaction().commit();
        
        // voo agendado 2
        VooAgendado agendado_2 = new VooAgendado();
        agendado_2.setAeronave("7532D");
        agendado_2.setData(Calendar.getInstance());
        agendado_2.setPassagens(null);
        agendado_2.setTotalPassageiros(777);
        agendado_2.setVoo(em.find(Voo.class, 2));
        
        em.getTransaction().begin();
        em.persist(agendado_2);
        em.getTransaction().commit();
        
        
        // Inserir Passagem
        // passagem 1
        Passagem p1 = new Passagem();
        p1.setBagagem(76);
        p1.setClasse(em.find(Classe.class, 1));
        p1.setDataCompra(Calendar.getInstance());
        p1.setPessoa(em.find(Pessoa.class, 1));
        p1.setVooAgendado(em.find(VooAgendado.class, 1));
        
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        // passagem 2
        Passagem p2 = new Passagem();
        p2.setBagagem(34);
        p2.setClasse(em.find(Classe.class, 2));
        p2.setDataCompra(Calendar.getInstance());
        p2.setPessoa(em.find(Pessoa.class, 2));
        p2.setVooAgendado(em.find(VooAgendado.class, 2));
        
        em.getTransaction().begin();
        em.persist(p2);
        em.getTransaction().commit();
        
        // Inserir permissoes
        // permissao 1
        Permissao per1 = new Permissao();
        per1.setNome("USUARIO");
        per1.setDescricao("eh usuario");
        em.getTransaction().begin();
        em.persist(per1);
        em.getTransaction().commit();
        
        // permissao 2
        Permissao per2 = new Permissao();
        per2.setNome("ADMINISTRADOR");
        per2.setDescricao("eh administrador");
        em.getTransaction().begin();
        em.persist(per2);
        em.getTransaction().commit();
        
        // permissao 3
        Permissao per3 = new Permissao();
        per3.setNome("CLIENTE");
        per3.setDescricao("eh cliente");
        em.getTransaction().begin();
        em.persist(per3);
        em.getTransaction().commit();
        
        // Inserir Usuarios
        // usuario 1
        Set<Permissao> permissoes = new HashSet<>();
        permissoes.add(per2);
        Usuario user1 = new Usuario();
        user1.setDataCadastro(Calendar.getInstance());
        user1.setNomeUsuario("userName");
        user1.setSenha("senha");
        user1.setPermissoes(permissoes);
        user1.setPessoa(johnny);
        em.getTransaction().begin();
        em.persist(user1);
        em.getTransaction().commit();
        
        // usuario 2
        Set<Permissao> permissoes2 = new HashSet<>();
        permissoes.add(per1);
        Usuario user2 = new Usuario();
        user2.setDataCadastro(Calendar.getInstance());
        user2.setNomeUsuario("usuario");
        user2.setSenha("pass");
        user2.setPermissoes(permissoes2);
        user2.setPessoa(joao);
        em.getTransaction().begin();
        em.persist(user2);
        em.getTransaction().commit();
        
        // usuario 3
        Set<Permissao> permissoes3 = new HashSet<>();
        permissoes.add(per3);
        Usuario user3 = new Usuario();
        user3.setDataCadastro(Calendar.getInstance());
        user3.setNomeUsuario("nomeUsuario");
        user3.setSenha("123");
        user3.setPermissoes(permissoes3);
        user3.setPessoa(joao);
        em.getTransaction().begin();
        em.persist(user3);
        em.getTransaction().commit();
    }
    
}
