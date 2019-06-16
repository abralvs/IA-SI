package com.example.sergipetour.buscagulosa;

public class Distancia {
    int idCidade;
    int idReferencia;
    double distancia;

    public Distancia(int idCidade, double distancia) {
        this.idCidade = idCidade;
        this.distancia = distancia;
    }

    public int getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

}
