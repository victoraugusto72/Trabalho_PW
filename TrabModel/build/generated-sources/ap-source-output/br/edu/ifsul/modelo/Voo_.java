package br.edu.ifsul.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Voo.class)
public abstract class Voo_ {

	public static volatile SingularAttribute<Voo, Double> tempoEstimado;
	public static volatile ListAttribute<Voo, Aeroporto> escalas;
	public static volatile SingularAttribute<Voo, Boolean> ativo;
	public static volatile SingularAttribute<Voo, String> periodicidade;
	public static volatile ListAttribute<Voo, VooAgendado> voosAgendados;
	public static volatile SingularAttribute<Voo, Integer> id;
	public static volatile SingularAttribute<Voo, String> descricao;

}

