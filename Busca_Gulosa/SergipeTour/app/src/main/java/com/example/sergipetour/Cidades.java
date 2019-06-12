package com.example.sergipetour;

import android.content.res.AssetManager;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Cidades {
    ArrayList<Cidade> cidades;

    public Cidades(AssetManager asset) {
        this.cidades = new ArrayList<>();
        adicionarCidades(asset); //adiciona cidades do arquivo;
        adicionarHeuristica();
        adicionarAdj();
    }

    public ArrayList<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(ArrayList<Cidade> cidades) {
        this.cidades = cidades;
    }

    public void adicionarCidades(AssetManager assetManager){


        try {
            InputStream inputStream = assetManager.open("cidades.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bw = new BufferedReader(inputStreamReader);

            String linha = bw.readLine();
            int id = 1;
            ArrayList<Cidade> cidadesTemp = new ArrayList<>();

            while(linha != null){
                ArrayList<Integer> idAdj = new ArrayList<>();
                String[] dados = linha.split(",");
                Cidade cidade = new Cidade(id, dados[0], new LatLng(Double.parseDouble(dados[1]), Double.parseDouble(dados[2]))); //id, nome, lat, lon, adj
                if(dados.length >= 4)                          // se tiver cidade adj entra no laco, já que a informacao está a partir do indice 3
                    for(int i = 3; i < dados.length; i++)
                        idAdj.add(Integer.parseInt(dados[i]));    //adicionando os id's das cidades adj.

                cidade.setIdAdj(idAdj);  //setando o arraylist de id's de cidades adj do obj cidade.
                cidadesTemp.add(cidade);
                id++;
                linha = bw.readLine();
            }
            this.cidades.addAll(cidadesTemp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarHeuristica(){
        for(Cidade city : getCidades()){
            ArrayList<Distancia> distancias = new ArrayList<>();
            for(Cidade cityTemp : getCidades()){
                if(city.getId() != cityTemp.getId()){
                    double x2, x1, y2, y1;
                    x1 = city.getCoordenadas().latitude;
                    x2 = cityTemp.getCoordenadas().latitude;   //pegando x e y para fazer o calculo em linha reta
                    y1 = city.getCoordenadas().longitude;
                    y2 = cityTemp.getCoordenadas().longitude;
                    double distancia = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2)); //raiz de (delta x ao qdr + delta y ao qdr)
                    distancias.add(new Distancia(cityTemp.getId(), distancia));
                }
            }
            city.setDistancias(distancias);
        }

    }

    public void adicionarAdj(){
        ArrayList<Adjacencia> adjs;
        for(Cidade city : getCidades()){
            adjs = new ArrayList<>();
            for(int idAdj : city.getIdAdj()) {     //percorrre o arraylist de id's de cidades adj e adicionas as cidades
                Cidade cityAdj = cidades.get(idAdj-1);   //pega a cidade a partir do id;
                Adjacencia adj = new Adjacencia(cityAdj);
                adjs.add(adj);
            }
            city.setAdj(adjs);   //setando array de adjacencias da cidade com o obj adj, com a cidade e a distancia
        }
    }
}
