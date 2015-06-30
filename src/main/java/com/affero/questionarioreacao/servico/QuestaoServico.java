/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affero.questionarioreacao.servico;

import org.springframework.stereotype.Service;
import com.affero.questionarioreacao.repositorio.QuestaoRepositorio;
import com.affero.questionarioreacao.entidade.Questao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author VMWG
 */
@Service
public class QuestaoServico {
    
    private QuestaoRepositorio repositorio;
    
    @Autowired
    public QuestaoServico(QuestaoRepositorio repositorio) {
        this.repositorio = repositorio;
    }
    
    public Iterable<Questao> getQuestoes() {
        return repositorio.findAll();
    }
    
    public void save(Questao questao) {
        repositorio.save(questao);
    }
       
    public Double CalculaMedia(Double nota, Double total){
        Double media = nota / total;
        return media;
    }
}
