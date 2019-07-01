package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Passagem;
import br.edu.ifsul.modelo.Voo;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class PassagemDAO extends DAOGenerico<Passagem> implements Serializable {

        public PassagemDAO(){

                super(Passagem.class);

                // inicializar as ordenações possiveis        
                listaOrdem.add(new Ordem("id", "ID", "="));
                listaOrdem.add(new Ordem("bagagem", "Bagagem", "="));
                // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
                ordemAtual = listaOrdem.get(1);
                // inicializar o conversor com a lista de ordens
                converterOrdem = new ConverterOrdem(listaOrdem);//pelo fato desse converter estar sendo inicializado manualmente, não é necessário a anota @Named
        }
        
}