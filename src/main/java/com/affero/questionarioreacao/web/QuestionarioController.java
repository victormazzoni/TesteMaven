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
    
    @RequestMapping(value="/{curso}", method = RequestMethod.GET)
    <Iterable>String getQuestoes(@PathVariable("curso") String idCurso) {
        String form = new String();
        for(Questao q:servico.getQuestoes()){
            Integer a = q.getId();
            String compl = new String();
            System.out.println(q);
            if (a<=3){
                compl = "<select name='Nota"+ a +"' required>\n" +
                            "<option value=''>--Selecione--</option>\n" +
                            "<option value='1'>1</option>\n" +
                            "<option value='2'>2</option>\n" +
                            "<option value='3'>3</option>\n" +
                            "<option value='4'>4</option>\n" +
                            "<option value='5'>5</option>\n" +
                            "<option value='6'>6</option>\n" +
                            "<option value='7'>7</option>\n" +
                            "<option value='8'>8</option>\n" +
                            "<option value='9'>9</option>\n" +
                            "<option value='10'>10</option>\n" +
                        "</select> <br /> <br />";
            }else if (a==3){
                compl = "<input type='radio' name='Nota"+ a +"' value='1' required>Sim<br />\n" +
                        "<input type='radio' name='Nota"+ a +"' value='0'>Nao<br />\n";
            }else if (a>3){
                compl = "<input type='radio' name='Nota"+ a +"' value='1'>Sim<br />\n" +
                        "<input type='radio' name='Nota"+ a +"' value='0'>Nao<br />\n";
            }
           
            form = form + q.getNome() + "\n <br />" + compl + "\n <br />";

        }
        
        
        return "<h2>Questionario de Avaliação</h2>\n" +
                "\n" +
                "<p>* campos obrigatórios</p>\n" +
                "\n" +
                "<form method=\"POST\" action='avaliacao.html'>\n" + form + "\n <br />" +
                "Comentário\n <br />" +
                "<textarea maxlength=\"1000\" name='Comentario'></textarea>\n" +
                "\n <br /> <br />" +
                "<input type='submit' value='Enviar' /></form>";
    }
    

}