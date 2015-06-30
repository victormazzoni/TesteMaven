package com.affero.questionarioreacao.web;

import com.affero.questionarioreacao.servico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.affero.questionarioreacao.entidade.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="/questionario_reacao")
public class QuestionarioController {
    private final QuestaoServico servico;
    private final RespostaServico servicoRes;
    private final AvaliacaoServico servicoAva;
    public String curso;
   
    @Autowired
    public QuestionarioController(QuestaoServico servico, RespostaServico servicoRes, AvaliacaoServico servicoAva) {
        this.servico = servico;
        this.servicoRes = servicoRes;
        this.servicoAva = servicoAva;
    }
    
    @RequestMapping(value="/{curso}", method = RequestMethod.GET)
    <Iterable>String getQuestoes(@PathVariable("curso") String idCurso) {
        curso = idCurso;
        String form = new String();
        for(Questao q:servico.getQuestoes()){
            Integer id = q.getId();
            String compl = new String();
            
            if (id<=3){
                compl = "<select name='Nota"+ id +"' required>\n" +
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
                        "</select> <br />";
            }else if (id==4){
                compl = "<input type='radio' name='Nota"+ id +"' value='1' required>Sim<br />\n" +
                        "<input type='radio' name='Nota"+ id +"' value='0'>Nao<br />\n";
            }else if (id>4){
                compl = "<input type='radio' name='Nota"+ id +"' value='1'>Sim<br />\n" +
                        "<input type='radio' name='Nota"+ id +"' value='0'>Nao<br />\n";
            }
           
            form = form + q.getNome() + "\n <br />" + compl + "\n <br />";
        }  
        
        return "<html><head><title>Questionario de Reação</title></head><body>" +
                "<h2>Questionario de Reação</h2>\n" +
                "\n" +
                "<p>* campos obrigatórios</p>\n" +
                "\n" +
                "<form method=\"POST\" action='reacao'>\n" + form + 
                "Comentário\n <br />" +
                "<textarea maxlength=\"1000\" name='Comentario'></textarea>\n" +
                "\n <br /> <br />" +
                "<input type='submit' value='Enviar' />" +
                "</form></body></html>";
    }
    
    @RequestMapping(value="/reacao", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

        Double Total = 10.0;
        Double Avaliacao = 45.0;
       
        PrintWriter writer = response.getWriter();
          
        String htmlParcial = "<html><meta charset=\"UTF-8\"><head><title>Questionario de Reação</title>";
        htmlParcial += "<script>function mostraParcial(){document.getElementById(\"parcial\").style.visibility = \"visible\";}</script>";
        htmlParcial += "</head><body>";
        htmlParcial += "<h1>Respostas enviadas com sucesso!</h1><br /><br />";
        htmlParcial += "<input type='button' onclick=\"mostraParcial()\" value=\"Visualizar Parcial\" />";
        htmlParcial += "<div id=\"parcial\" style=\"visibility:hidden\">";
        for(Questao q:servico.getQuestoes()){
            Integer id = q.getId();
            String pergunta = q.getNome();
            
            String nota = request.getParameter("Nota"+Integer.toString(id));
            Double n0 = 0.0;
            if (nota!=null){
                n0 = Double.parseDouble(nota);
            }else{
                nota = "Não avaliado.";
            }
            servicoRes.save(new Resposta(curso,id,n0));
                     
            htmlParcial += "<p><b>" + pergunta + ": </b>";
            //htmlParcial += nota + "</p><br/>";
            htmlParcial += "<h2>Avaliacao " + Avaliacao + "<br/>"; 
            //Avaliacao = Nota + Avaliacao;
            htmlParcial += "<h2>Avaliacao " + Avaliacao + "<br/>"; 
            Double mediaPer = servico.CalculaMedia(Avaliacao, Total);
            htmlParcial += "Media " + mediaPer + "</h2>";
        }
        
        htmlParcial += "</div>";
        htmlParcial += "</body></html>";
        
        Integer idQuestaoPrev = 0;
        Double SomaTotal = 0.0;
        Double TotalRespostas = 0.0;
        Double Media = 0.0;
            
        for(Resposta r:servicoRes.getRespostas()){
            Integer idQuestao = r.getQuestao();
            
            if (idQuestao!=idQuestaoPrev){
                Media = 0.0;
                TotalRespostas = 0.0;
                SomaTotal = r.getResposta();
                TotalRespostas++;
                Media = SomaTotal/TotalRespostas;
                System.out.println("Questao:" + idQuestao);
                System.out.println("Soma:" + SomaTotal);
                System.out.println("Total:" + TotalRespostas);
                System.out.println("Media:" + Media + "\n");
                idQuestaoPrev = idQuestao;
                
            }else{
                SomaTotal = SomaTotal + r.getResposta();
                TotalRespostas++;
                Media = SomaTotal/TotalRespostas;
                System.out.println("Questao:" + idQuestao);
                System.out.println("Soma:" + SomaTotal);
                System.out.println("Total:" + TotalRespostas);
                System.out.println("Media:" + Media + "\n");
            }  
System.out.println(r);
        }
        

        writer.println(htmlParcial);
         
    }

}