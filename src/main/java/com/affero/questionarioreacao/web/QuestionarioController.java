package com.affero.questionarioreacao.web;

import com.affero.questionarioreacao.servico.QuestaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.affero.questionarioreacao.entidade.Questao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="/questionario_reacao")
public class QuestionarioController {
    private final QuestaoServico servico;
   
    @Autowired
    public QuestionarioController(QuestaoServico servico) {
        this.servico = servico;
    }
    
    @RequestMapping(value="/{curso}", method = RequestMethod.GET)
    <Iterable>String getQuestoes(@PathVariable("curso") String idCurso) {
        String form = new String();
        for(Questao q:servico.getQuestoes()){
            Integer id = q.getId();
            String compl = new String();
            System.out.println(q);
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
                        "</select> <br /> <br />";
            }else if (id==4){
                compl = "<input type='radio' name='Nota"+ id +"' value='1' required>Sim<br />\n" +
                        "<input type='radio' name='Nota"+ id +"' value='0'>Nao<br />\n";
            }else if (id>4){
                compl = "<input type='radio' name='Nota"+ id +"' value='1'>Sim<br />\n" +
                        "<input type='radio' name='Nota"+ id +"' value='0'>Nao<br />\n";
            }
           
            form = form + q.getNome() + "\n <br />" + compl + "\n <br />";
        }  
        
        return "<html><head><title>Questionario de ReaÃ§Ã£o</title></head><body>" +
                "<h2>Questionario de ReaÃ§Ã£o</h2>\n" +
                "\n" +
                "<p>* campos obrigatÃ³rios</p>\n" +
                "\n" +
                "<form method=\"POST\" action='reacao'>\n" + form + 
                "ComentÃ¡rio\n <br />" +
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
        
        Double Nota1 = Double.parseDouble(request.getParameter("Nota1"));
        Double Nota2 = Double.parseDouble(request.getParameter("Nota2"));
        Double Nota3 = Double.parseDouble(request.getParameter("Nota3"));
        Double Nota4 = Double.parseDouble(request.getParameter("Nota4"));
        
        String n5 = request.getParameter("Nota5");
        Double Nota5 = 0.0;
        if (n5!=null){Nota5 = Double.parseDouble(n5);}
        String n6 = request.getParameter("Nota6");
        Double Nota6 = 0.0;
        if (n6!=null){Nota6 = Double.parseDouble(n6);}
        String n7 = request.getParameter("Nota7");
        Double Nota7 = 0.0;
        if (n7!=null){Nota7 = Double.parseDouble(n7);}
        String n8 = request.getParameter("Nota8");
        Double Nota8 = 0.0;
        if (n8!=null){Nota8 = Double.parseDouble(n8);}
        String n9 = request.getParameter("Nota9");
        Double Nota9 = 0.0;
        if (n9!=null){Nota9 = Double.parseDouble(n9);}
        String n10 = request.getParameter("Nota10");
        Double Nota10 = 0.0;
        if (n10!=null){Nota10 = Double.parseDouble(n10);}
       

        PrintWriter writer = response.getWriter();
         
        String htmlRespone = "<html><head>";
        htmlRespone += "<script>function mostraParcial(){document.getElementById(\"parcial\").style.visibility = \"visible\";}</script>";
        htmlRespone += "</head><body>";
        htmlRespone += "Respostas enviadas com sucesso! <br />";
        htmlRespone += "<input type='button' onclick=\"mostraParcial()\" value=\"Visualizar Parcial\" />";
        htmlRespone += "<div id=\"parcial\" style=\"visibility:hidden\">";
        for(Questao q:servico.getQuestoes()){
            Integer id = q.getId();
            String pergunta = q.getNome();
            htmlRespone += "<p><b>" + pergunta + ": </b>";
            htmlRespone += Nota1 + "</p><br/>";
            htmlRespone += "<h2>Nota5 " + Nota5 + "<br/>";
            htmlRespone += "<h2>Avaliacao " + Avaliacao + "<br/>"; 
            Avaliacao = Nota1 + Avaliacao;
            htmlRespone += "<h2>Avaliacao " + Avaliacao + "<br/>"; 
            Double mediaPer = servico.CalculaMedia(Avaliacao, Total);
            htmlRespone += "Media " + mediaPer + "</h2>";
        }
        htmlRespone += "</div>";
        htmlRespone += "</body></html>";
         
        writer.println(htmlRespone);
         
    }

}