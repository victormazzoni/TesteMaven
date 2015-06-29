package com.affero.questionarioreacao.entidade;

import com.affero.questionarioreacao.entidade.Curso;
import com.affero.questionarioreacao.entidade.Questao;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-28T17:04:54")
@StaticMetamodel(Resposta.class)
public class Resposta_ { 

    public static volatile SingularAttribute<Resposta, Integer> Resposta;
    public static volatile SingularAttribute<Resposta, Curso> curso;
    public static volatile SingularAttribute<Resposta, Integer> id;
    public static volatile SingularAttribute<Resposta, Questao> questao;

}