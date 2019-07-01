package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.dao.PassagemDAO;
import br.edu.ifsul.dao.VooAgendadoDAO;
import br.edu.ifsul.dao.VooDAO;
import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Passagem;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controleVoo")
@SessionScoped
public class ControleVoo implements Serializable {

    // voo em si
    private Voo objeto;

    @EJB
    private VooDAO dao;

    // escalas
    @EJB
    private AeroportoDAO daoEscala;
    
    private Aeroporto escala;
    
    // voo agendado
    @EJB
    private VooAgendadoDAO daoVooAgendado;
    private ControleVooAgendado controleVooAgendado;
    
    private VooAgendado vooAgendado;
    private Boolean novoVooAgendado;
    
    // passagem do voo agendado
    @EJB
    private PassagemDAO daoPassagem;
    
    private Passagem passagem;
    private Boolean novaPassagem;
    
    public ControleVoo() {

    }
    
    // Escalas
    public void salvarEscala() {
        if(escala != null) {
            objeto.getEscalas().add(escala);
            Util.mensagemInformacao("Escala adicionada com sucesso");
        } else {
            Util.mensagemErro("Selecione uma escala");
        }
        
    }
    
    public void removerEscala(int index) {
        this.getObjeto().getEscalas().remove(index);
        Util.mensagemInformacao("Escala removida com sucesso");
    }
    
    // Voos Agendados
    public void novoVooAgendado(){
        setVooAgendado(new VooAgendado());
        getVooAgendado().setPassagens(new ArrayList<>());
        getVooAgendado().setVoo(this.getObjeto());
        novoVooAgendado = true;
    }
    
    public void alterarVooAgendado(int index){
        setVooAgendado(objeto.getVoosAgendados().get(index));
        novoVooAgendado = false;
    }            
    public void salvarVooAgendado(){
        if(novoVooAgendado){
            vooAgendado.setVoo(objeto);
            objeto.getVoosAgendados().add(vooAgendado);
        }
        Util.mensagemInformacao("Voo Agendado adicionado com sucesso");
    }                
    public void removerVooAgendado(int index){
        this.getObjeto().getVoosAgendados().remove(index);
        Util.mensagemInformacao("Voo Agendado removido com sucesso");
    }
    
    public String listar() {
            return "/privado/voo/crudvoo?faces-redirect=true";
    }

    // Passagens do voo agendado
    public void novaPassagem(){
        setPassagem(new Passagem());
        getPassagem().setVooAgendado(getVooAgendado());
        novaPassagem = true;
    }
    
    public void alterarPassagem(int index){
        setPassagem(vooAgendado.getPassagens().get(index));
        novaPassagem = false;
    }            
    public void salvarPassagem(){
        if(novaPassagem){
            passagem.setVooAgendado(vooAgendado);
            vooAgendado.getPassagens().add(passagem);
        }
        Util.mensagemInformacao("Passagem adicionada com sucesso");
    }
    public void removerPassagem(int index){
        this.getVooAgendado().getPassagens().remove(index);
        Util.mensagemInformacao("Passagem removida com sucesso");
    }
    
    // Voo
    public void novo() {
        objeto = new Voo();
        objeto.setEscalas(new ArrayList<>());
        objeto.setVoosAgendados(new ArrayList<>());
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                            Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                            Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + 
                            Util.getMensagemErro(e));
        }
    }
    
    // relatorio
    public void imprimirVoos() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("Voos_A4", parametros, dao.getListaTodos());
    }

    // getters e setters
    public Voo getObjeto() {
        return objeto;
    }

    public void setObjeto(Voo objeto) {
        this.objeto = objeto;
    }

    public VooDAO getDao() {
        return dao;
    }

    public void setDao(VooDAO dao) {
        this.dao = dao;
    }

    public AeroportoDAO getDaoEscala() {
        return daoEscala;
    }

    public void setDaoEscala(AeroportoDAO daoEscala) {
        this.daoEscala = daoEscala;
    }

    public VooAgendadoDAO getDaoVooAgendado() {
        return daoVooAgendado;
    }

    public void setDaoVooAgendado(VooAgendadoDAO daoVooAgendado) {
        this.daoVooAgendado = daoVooAgendado;
    }

    public Aeroporto getEscala() {
        return escala;
    }

    public void setEscala(Aeroporto escala) {
        this.escala = escala;
    }

    public VooAgendado getVooAgendado() {
        return vooAgendado;
    }

    public void setVooAgendado(VooAgendado vooAgendado) {
        this.vooAgendado = vooAgendado;
    }

    public ControleVooAgendado getControleVooAgendado() {
        return controleVooAgendado;
    }

    public void setControleVooAgendado(ControleVooAgendado controleVooAgendado) {
        this.controleVooAgendado = controleVooAgendado;
    }

    public Boolean getNovoVooAgendado() {
        return novoVooAgendado;
    }

    public void setNovoVooAgendado(Boolean novoVooAgendado) {
        this.novoVooAgendado = novoVooAgendado;
    }

    public PassagemDAO getDaoPassagem() {
        return daoPassagem;
    }

    public void setDaoPassagem(PassagemDAO daoPassagem) {
        this.daoPassagem = daoPassagem;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public Boolean getNovaPassagem() {
        return novaPassagem;
    }

    public void setNovaPassagem(Boolean novaPassagem) {
        this.novaPassagem = novaPassagem;
    }
    
}
