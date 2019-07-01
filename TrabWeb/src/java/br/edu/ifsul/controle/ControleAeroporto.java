package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controleAeroporto")
@SessionScoped
public class ControleAeroporto implements Serializable {
    @EJB
    private AeroportoDAO<Aeroporto> dao;
    private Aeroporto objeto;

    public ControleAeroporto(){

    }

    public String listar(){
            return "/privado/aeroporto/listar?faces-redirect=true";
    }

    public void novo(){
            objeto = new Aeroporto();        
    }

    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                                Util.getMensagemErro(e));
        } 
    }

    public void excluir(Object id){
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                            Util.getMensagemErro(e));
        }
    }

    public void salvar(){
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

    public AeroportoDAO<Aeroporto> getDao() {
        return dao;
    }

    public Aeroporto getObjeto() {
        return objeto;
    }

}
