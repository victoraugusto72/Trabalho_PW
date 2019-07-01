package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@FacesConverter(value = "converterPessoa") 
public class ConverterPessoa implements Serializable, Converter {
    
    @PersistenceContext(unitName = "PW-2019-1Web")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-2019-1");
    private EntityManager em = emf.createEntityManager();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.length() == 0 || string.equals("Selecione")) {
            return null;
        }
        return em.find(Pessoa.class, Integer.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        
        try {
            Pessoa obj = (Pessoa) o;
            return obj.getId().toString();
        } catch(Exception e) {
            return o.toString();
        }
        
    }

}
