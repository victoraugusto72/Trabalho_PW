package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import java.io.Serializable;
import javax.ejb.Stateful;
import br.edu.ifsul.modelo.Aeroporto;

@Stateful
public class AeroportoDAO<TIPO> extends DAOGenerico<Aeroporto> implements Serializable {
    
    public AeroportoDAO() {
        super(Aeroporto.class);
    
        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);//pelo fato desse converter estar sendo inicializado manualmente, não é necessário a anota @Named
    }
    
}
