package com.example.sergipetour;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.sergipetour.MapsActivity.cities;

public class VetorOrdenado {
    private Cidade city;

    VetorOrdenado() {
        city = null;
    }

    public void inserir(Cidade cidade, int idObjetivo){
        int id = -1;

        System.out.println("sleep");
        for(Distancia d : cidade.getDistancias()) {
            if (d.idCidade == idObjetivo) {
                id = d.idCidade;
                for (Adjacencia ad : cidade.getAdj()) {
                    if (ad.getCidade().getId() == id) {
                        System.out.println("SIm cidade " + cidade.getId());  //verifica se a cidade é adj ao objetivo
                        city = ad.getCidade();
                        break;
                    }
                }

            }
        }

        if (city == null) {
            Distancia[] tempDistancias = new Distancia[cidade.getAdj().size()];
            for (int i = 0; i < cidade.getAdj().size(); i++)                       //cria um array e preenche com a distancias entre as cidades adj e o objetivo.
                tempDistancias[i] = cidade.getAdj().get(i).getCidade().getDistanciaObjetivo(idObjetivo);

            Distancia tmp = null;
            for (int i = 0; i < tempDistancias.length; i++)
                for (int j = 1; j < tempDistancias.length; j++)
                    if (tempDistancias[i].getDistancia() > tempDistancias[j].getDistancia()) {  //ordena essas distancias CRESC, usando um bubble.
                        tmp = tempDistancias[i];
                        tempDistancias[i] = tempDistancias[j];
                        tempDistancias[j] = tmp;
                    }
            for (Cidade cid : cities.getCidades())
                if (cid.getId() == tempDistancias[0].getIdReferencia()) {    //como foi ordenado, ele pega a primeira distancia, que é a menor.
                    System.out.println("opa cidade " + tempDistancias[0].getIdReferencia());  //pega a cidade que tem essa menor distancia.
                    //                    city = cid;
                    break;
                }
        }
    }

    public Cidade getPrimeiro(){
        return this.city;
    }

  /*  public void mostrar(int idObjetivo){
        int id=0;
        for(int i=0;i<numElementos;i++){
            ArrayList<Distancia> d = this.cidades[i].getDistancias();
            for(int j=0;j<d.size();j++){
                if(d.get(j).idCidade == idObjetivo){
                    id = j;
                    break;
                }
            }
            System.out.println("\t"+this.cidades[i].getNome()+" "+ this.cidades[i].getId() +" - "+ this.cidades[i].getDistancias().get(id).distancia);
        }
    } */

}
