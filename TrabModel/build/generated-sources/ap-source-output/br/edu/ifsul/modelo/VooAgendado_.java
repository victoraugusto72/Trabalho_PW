package br.edu.ifsul.modelo;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VooAgendado.class)
public abstract class VooAgendado_ {

	public static volatile ListAttribute<VooAgendado, Passagem> passagens;
	public static volatile SingularAttribute<VooAgendado, Integer> totalPassageiros;
	public static volatile SingularAttribute<VooAgendado, Calendar> data;
	public static volatile SingularAttribute<VooAgendado, Voo> voo;
	public static volatile SingularAttribute<VooAgendado, Integer> id;
	public static volatile SingularAttribute<VooAgendado, String> aeronave;

}

