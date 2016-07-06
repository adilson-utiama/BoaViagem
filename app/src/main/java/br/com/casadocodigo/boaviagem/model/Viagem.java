package br.com.casadocodigo.boaviagem.model;

import java.util.Date;

/**
 * Created by adilson on 04/07/2016.
 */
public class Viagem {

    private Long id;
    private String destino;
    private Integer tipoViagem;
    private Date dataChegada;
    private Date dataSaida;
    private Double orcamento;
    private Integer quantidadePessoa;

    public Viagem(){}

    public Viagem(Long id, String destino, Integer tipoViagem,
                  Date dataChegada, Date dataSaida, Double orcamento, Integer quantidadePessoa ){
        this.id = id;
        this.tipoViagem = tipoViagem;
        this.dataChegada = dataChegada;
        this.dataSaida = dataSaida;
        this.orcamento = orcamento;
        this.quantidadePessoa = quantidadePessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getTipoViagem() {
        return tipoViagem;
    }

    public void setTipoViagem(Integer tipoViagem) {
        this.tipoViagem = tipoViagem;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    public Integer getQuantidadePessoa() {
        return quantidadePessoa;
    }

    public void setQuantidadePessoa(Integer quantidadePessoa) {
        this.quantidadePessoa = quantidadePessoa;
    }
}
