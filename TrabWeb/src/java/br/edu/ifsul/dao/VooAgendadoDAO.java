package br.edu.ifsul.dao;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.VooAgendado;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class VooAgendadoDAO extends DAOGenerico<VooAgendado> implements Serializable {

        public VooAgendadoDAO(){

                super(VooAgendado.class);

                // inicializar as ordenações possiveis        
                listaOrdem.add(new Ordem("id", "ID", "="));
                listaOrdem.add(new Ordem("aeronave", "Aeronave", "like"));
                // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
                ordemAtual = listaOrdem.get(1);
                // inicializar o conversor com a lista de ordens
                converterOrdem = new ConverterOrdem(listaOrdem);//pelo fato desse converter estar sendo inicializado manualmente, não é necessário a anota @Named
        }

        @Override
        public VooAgendado getObjectById(Object id) throws Exception {
                VooAgendado obj = em.find(VooAgendado.class, id);
                // Deve-se inicializar as coleções para não gerar erro de LazyInicializationException
                obj.getPassagens().size();
                return obj;
        }
}