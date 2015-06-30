/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affero.questionarioreacao.entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author VMWG
 */
@Entity
@Table(name = "RESPOSTA")
public class Resposta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String idCurso;
    private Integer questao;
    private Double resposta;
    
    public Resposta(String idCurso, Integer idQuestao, Double resposta){
        this.idCurso = idCurso;
        this.questao = idQuestao;
        this.resposta = resposta;
    }
    
    public String getCurso() {
        return idCurso;
    }

    public void setCurso(String curso) {
        this.idCurso = curso;
    }

    public Integer getQuestao() {
        return questao;
    }

    public void setQuestao(Integer questao) {
        this.questao = questao;
    }

    public Double getResposta() {
        return resposta;
    }

    public void setResposta(Double Resposta) {
        this.resposta = Resposta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resposta)) {
            return false;
        }
        Resposta other = (Resposta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ id=" + id + ", idCurso=" + idCurso + ", questao=" + questao + ", resposta=" + resposta + " ]";
    }
    
}
