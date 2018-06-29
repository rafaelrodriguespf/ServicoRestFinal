/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.servico;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rafael
 */
@XmlRootElement
public class Correio implements Serializable{
    
    private String codigoServico;
    private String cepDestino;
    private String peso;
    private Double valorFrete;
    private Integer prazoEntrega;

    public Correio() {
    }

    public Correio(String codigoServico, String cepDestino, String peso, Double valorFrete, Integer prazoEntrega) {
        this.codigoServico = codigoServico;
        this.cepDestino = cepDestino;
        this.peso = peso;
        this.valorFrete = valorFrete;
        this.prazoEntrega = prazoEntrega;
    }
    
    

    /**
     * @return the codigoServico
     */
    public String getCodigoServico() {
        return codigoServico;
    }

    /**
     * @param codigoServico the codigoServico to set
     */
    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    /**
     * @return the cepDestino
     */
    public String getCepDestino() {
        return cepDestino;
    }

    /**
     * @param cepDestino the cepDestino to set
     */
    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    /**
     * @return the peso
     */
    public String getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(String peso) {
        this.peso = peso;
    }

    /**
     * @return the valorFrete
     */
    public Double getValorFrete() {
        return valorFrete;
    }

    /**
     * @param valorFrete the valorFrete to set
     */
    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    /**
     * @return the prazoEntrega
     */
    public Integer getPrazoEntrega() {
        return prazoEntrega;
    }

    /**
     * @param prazoEntrega the prazoEntrega to set
     */
    public void setPrazoEntrega(Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

   
    
    
    
 
    
}
