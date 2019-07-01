package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Voo;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class VooDAO extends DAOGenerico<Voo> implements Serializable {

        public VooDAO(){

                super(Voo.class);

                // inicializar as ordenações possiveis        
                listaOrdem.add(new Ordem("id", "ID", "="));
                listaOrdem.add(new Ordem("descricao", "Descricao", "like"));
                // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
                ordemAtual = listaOrdem.get(1);
                // inicializar o conversor com a lista de ordens
                converterOrdem = new ConverterOrdem(listaOrdem);//pelo fato desse converter estar sendo inicializado manualmente, não é necessário a anota @Named
        }

        @Override
        public Voo getObjectById(Object id) throws Exception {
                Voo obj = em.find(Voo.class, id);
                // Deve-se inicializar as coleções para não gerar erro de LazyInicializationException
                obj.getEscalas().size();
                obj.getVoosAgendados().size();
                return obj;
        }
}