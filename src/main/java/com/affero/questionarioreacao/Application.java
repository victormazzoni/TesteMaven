package com.affero.questionarioreacao;


import com.affero.questionarioreacao.servico.QuestaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VMWG
 */
@SpringBootApplication
public class Application {
    
    
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}
