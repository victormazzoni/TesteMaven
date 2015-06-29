package com.affero.questionarioreacao.web;

import com.affero.questionarioreacao.servico.QuestaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.affero.questionarioreacao.entidade.Questao;

@RestController
@RequestMapping(value="/questionario_reacao")
public class QuestionarioController {
    private QuestaoServico servico;
   
    @Autowired
    public QuestionarioController(QuestaoServico servico) {
        this.servico = servico;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    <Iterable>String getQuestoes() {
        for(Questao q:servico.getQuestoes()){
            return q.getNome();
        }
        return null;
    }
    
    
    
    @RequestMapping(value="/{meu}", method = RequestMethod.GET)
    String home(@PathVariable("meu") String idCurso) {
        return "<h2>Questionario de Avaliacao</h2>\n" +
"        \n" +
"        <p>* campos obrigatorios</p>\n" +
"        \n" +
"        <form method=\"POST\" action='avaliacao.html'>\n" +
"            Como voce avaliaria sua expectativa em relacao ao curso? *\n <br />" +            
"            <select name='NotaExpectativa' required>\n" +
"                <option value=''>--Selecione--</option>\n" +
"                <option value='1'>1</option>\n" +
"                <option value='2'>2</option>\n" +
"                <option value='3'>3</option>\n" +
"                <option value='4'>4</option>\n" +
"                <option value='5'>5</option>\n" +
"                <option value='6'>6</option>\n" +
"                <option value='7'>7</option>\n" +
"                <option value='8'>8</option>\n" +
"                <option value='9'>9</option>\n" +
"                <option value='10'>10</option>\n" +
"            </select>\n <br /> <br />" +
"            \n" +
"            Como voce avaliaria a realizacao do curso? *\n <br />" +
"            <select name='NotaRealizacao' required>\n" +
"                <option value=''>--Selecione--</option>\n" +
"                <option value='1'>1</option>\n" +
"                <option value='2'>2</option>\n" +
"                <option value='3'>3</option>\n" +
"                <option value='4'>4</option>\n" +
"                <option value='5'>5</option>\n" +
"                <option value='6'>6</option>\n" +
"                <option value='7'>7</option>\n" +
"                <option value='8'>8</option>\n" +
"                <option value='9'>9</option>\n" +
"                <option value='10'>10</option>\n" +
"            </select>\n <br /> <br />" +
"            \n" +
"            Que nota voce daria para o instrutor desse treinamento? *\n <br />" +
"            <select name='NotaInstrutor' required>\n" +
"                <option value=''>--Selecione--</option>\n" +
"                <option value='1'>1</option>\n" +
"                <option value='2'>2</option>\n" +
"                <option value='3'>3</option>\n" +
"                <option value='4'>4</option>\n" +
"                <option value='5'>5</option>\n" +
"                <option value='6'>6</option>\n" +
"                <option value='7'>7</option>\n" +
"                <option value='8'>8</option>\n" +
"                <option value='9'>9</option>\n" +
"                <option value='10'>10</option>\n" +
"            </select>\n" +
"            \n <br /> <br />" +
"            O conteudo foi adequado e e' aplicavel no seu dia-a-dia? *\n <br />" +
"            <input type='radio' name='AdequadoAplicavel' value='1' required>Sim<br />\n" +
"            <input type='radio' name='AdequadoAplicavel' value='0'>Nao<br />\n" +
"            \n <br />" +
"            Marque as opcoes verdadeiras\n" +
"            \n <br /> <br />" +
"            Posso aplicar o que aprendi imediatamente no meu dia-a-dia\n <br />" +
"            <input type='radio' name='Aplicavel' value='1'>V<br />\n" +
"            <input type='radio' name='Aplicavel' value='0'>F<br />\n" +
"            \n <br /> " +
"            O instrutor dominava o conteudo apresentado\n <br />" +
"            <input type='radio' name='InstrutorDominava' value='1'>V<br />\n" +
"            <input type='radio' name='InstrutorDominava' value='0'>F<br />\n" +
"            \n <br /> " +
"            Todos ficaram atentos ao curso durante toda sua duracao\n <br />" +
"            <input type='radio' name='Atencao' value='1'>V<br />\n" +
"            <input type='radio' name='Atencao' value='0'>F<br />\n" +
"            \n <br />" +
"            Poucas pessoas faltaram ou se ausentaram durante o treinamento\n <br />" +
"            <input type='radio' name='PoucosFaltosos' value='1'>V<br />\n" +
"            <input type='radio' name='PoucosFaltosos' value='0'>F<br />\n" +
"            \n <br />" +
"            Gostaria que houvesse outros treinamentos como esse\n <br />" +
"            <input type='radio' name='MaisTreinamentos' value='1'>V<br />\n" +
"            <input type='radio' name='MaisTreinamentos' value='0'>F<br />\n" +
"            \n <br />" +
"            Precisamos de mais aprofundamento no tema apresentado\n <br />" +
"            <input type='radio' name='Aprofundamento' value='1'>V<br />\n" +
"            <input type='radio' name='Aprofundamento' value='0'>F<br />\n" +
"            \n <br />" +
"            Comentario\n <br />" +
"            <textarea maxlength=\"1000\" name='Comentario'></textarea>\n" +
"            \n <br /> <br />" +
"            <input type='submit' value='Enviar' />";
    }

}