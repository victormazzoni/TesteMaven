/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affero.questionarioreacao.repositorio;

import com.affero.questionarioreacao.entidade.Questao;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author VMWG
 */
public interface QuestaoRepositorio extends CrudRepository<Questao, Integer> {
    
}
