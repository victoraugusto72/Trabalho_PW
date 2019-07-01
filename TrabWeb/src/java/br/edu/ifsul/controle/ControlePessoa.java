package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable {
    @EJB
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;

    public ControlePessoa(){

    }

    public String listar(){
            return "/privado/pessoa/listar?faces-redirect=true";
    }

    public void novo(){
            objeto = new Pessoa();        
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

    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public Pessoa getObjeto() {
        return objeto;
    }

}
