package com.example.sergipetour.buscagulosa;
import java.util.ArrayList;

public class MelhorEscolha {
    private ArrayList<Cidade> caminho;
    private boolean achou;
    private VetorOrdenado fronteira;

    public MelhorEscolha() {

        this.caminho = new ArrayList<>();
        this.achou = false;
    }

    public void Buscar(Cidade origem, int idObjetivo){

        System.out.println("Atual: "+origem.getNome()+"-"+origem.getId());
        origem.setVisitado(true);
        this.caminho.add(origem);
        if(origem.getId()==idObjetivo){
            this.achou=true;
        }else{
            this.fronteira = new VetorOrdenado();
            this.fronteira.inserir(origem, idObjetivo);
            }
            if(this.fronteira.getPrimeiro() != null && !this.achou){
                System.out.println("get " +this.fronteira.getPrimeiro().getNome());
                this.Buscar(this.fronteira.getPrimeiro(), idObjetivo);
            }
    }

    public ArrayList<Cidade> getCaminho() {
        return caminho;
    }
}