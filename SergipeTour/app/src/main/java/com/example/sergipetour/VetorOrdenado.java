package com.example.sergipetour;

import static com.example.sergipetour.MapsActivity.cities;

public class VetorOrdenado {
    private Cidade city;

    VetorOrdenado() {
        city = null;
    }

    public void inserir(Cidade cidade, int idObjetivo){
        int id = -1;

        for(Distancia d : cidade.getDistancias()) {
            if (d.idCidade == idObjetivo) {
                id = d.idCidade;
                for (Adjacencia ad : cidade.getAdj()) {
                    if (ad.getCidade().getId() == id) {       //verifica se a cidade é adj ao objetivo
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
                if (cid.getId() == tempDistancias[0].getIdReferencia()) {
                    city = cid;                                                //como foi ordenado, ele pega a primeira distancia, que é a menor.
                    break;                                                   //pega a cidade que tem essa menor distancia.
                }
        }
    }

    public Cidade getPrimeiro(){
        return this.city;
    }

}
