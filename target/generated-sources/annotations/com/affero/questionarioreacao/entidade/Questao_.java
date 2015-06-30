package com.affero.questionarioreacao.entidade;

import com.affero.questionarioreacao.entidade.Resposta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-30T08:20:31")
@StaticMetamodel(Questao.class)
public class Questao_ { 

    public static volatile SingularAttribute<Questao, Boolean> obrigatorio;
    public static volatile SingularAttribute<Questao, String> nome;
    public static volatile SingularAttribute<Questao, Integer> id;
    public static volatile ListAttribute<Questao, Resposta> respostas;

}