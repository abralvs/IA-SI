package com.example.sergipetour.buscagulosa;

import android.annotation.SuppressLint;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Cidade {
    private int id;
    private String nome;
    private LatLng coordenadas;               // objeto que contem a latitude e longitude
    private ArrayList<Integer> idAdj;         // id das cidades adjacentes
    private ArrayList<Adjacencia> adj;        //Array com cidades adjacentes
    private ArrayList<Distancia> distancias;  //distancia de todas as cidades pra ela em linha reta, q representa a heuristica
    private boolean visitado;                 // para identificar se ela já foi visitada pelo algoritmo

    public Cidade(int id, String nome,LatLng coordenadas) {
        this.id = id;
        this.nome = nome;
        this.coordenadas = coordenadas;
        this.idAdj = new ArrayList<>();
        this.adj = new ArrayList<>();
        this.distancias = new ArrayList<>();
        this.visitado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LatLng getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(LatLng coordenadas) {
        this.coordenadas = coordenadas;
    }

    public ArrayList<Adjacencia> getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Adjacencia> adj) {
        this.adj = adj;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public ArrayList<Distancia> getDistancias() {
        return distancias;
    }

    public void setDistancias(ArrayList<Distancia> distancias) {
        this.distancias = distancias;
    }

    public ArrayList<Integer> getIdAdj() {
        return idAdj;
    }

    public void setIdAdj(ArrayList<Integer> idAdj) {
        this.idAdj = idAdj;
    }


    public Distancia getDistanciaObjetivo(int idObj){
        for(Distancia d : getDistancias()){
            d.setIdReferencia(getId());
            if(d.idCidade == idObj)
                return d;
        }

        return null;
    }
    @SuppressLint("DefaultLocale")
    public String getDistanciaAdj(int id){
        for(Distancia d : getDistancias()){
            if(d.idCidade == id)
                return String.format("h: %.2f",d.distancia);
        }
        return null;
    }

}
