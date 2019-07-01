package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PassagemDAO;
import br.edu.ifsul.dao.VooAgendadoDAO;
import br.edu.ifsul.modelo.Passagem;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controleVooAgendado")
@SessionScoped
public class ControleVooAgendado implements Serializable {

    private VooAgendado objeto;

    @EJB
    private VooAgendadoDAO dao;
    
    @EJB
    private PassagemDAO daoVooAgendado;
    
    private Passagem passagem;
    private Boolean novaPassagem;
    
    public ControleVooAgendado() {

    }
    
    // Passagens
    public void novaPassagem(){
        setPassagem(new Passagem());
        novaPassagem = true;
    }
    
    public void alterarPassagem(int index){
        setPassagem(objeto.getPassagens().get(index));
        novaPassagem = false;
    }            
    public void salvarPassagem(){
        if(novaPassagem){
            passagem.setVooAgendado(objeto);
            if(objeto == null)
                System.out.println("ALERTAALERTA");
            if(objeto.getPassagens() == null)
                objeto.setPassagens(new ArrayList<>());
            objeto.getPassagens().add(passagem);
        }
        Util.mensagemInformacao("Passagem adicionada com sucesso");
    }                
    public void removerPassagem(int index){
        this.getObjeto().getPassagens().remove(index);
        Util.mensagemInformacao("Passagem removida com sucesso");
    }
    
    public String listar() {
            return "/privado/voo/crudvooagendado?faces-redirect=true";
    }

    public void novo() {
        objeto = new VooAgendado();
        objeto.setPassagens(new ArrayList<Passagem>());
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

    public VooAgendado getObjeto() {
        return objeto;
    }

    public void setObjeto(VooAgendado objeto) {
        this.objeto = objeto;
    }

    public VooAgendadoDAO getDao() {
        return dao;
    }

    public void setDao(VooAgendadoDAO dao) {
        this.dao = dao;
    }

    public PassagemDAO getDaoVooAgendado() {
        return daoVooAgendado;
    }

    public void setDaoVooAgendado(PassagemDAO daoVooAgendado) {
        this.daoVooAgendado = daoVooAgendado;
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
