package com.example.sergipetour;

class VetorOrdenado {
    private int numElementos;
    private Cidade[] cidades;

    public VetorOrdenado(int tamanho) {
        this.numElementos = 0;
        this.cidades = new Cidade[tamanho];
    }

    public void inserir(Cidade cidade, int idObjetivo){
        if (this.numElementos == 0){
            this.cidades[0] = cidade;
            this.numElementos = 1;
        }else{
            int pos = 0;
            for(int i=0;i<this.numElementos;i++){
                if(cidade.getDistancias().get(idObjetivo+1).distancia > this.cidades[pos].getDistancias().get(idObjetivo+1).distancia){
                    pos++;
                }
            }

            for(int i=this.numElementos;i>pos;i--){
                this.cidades[i]=this.cidades[i-1];
            }

            this.cidades[pos] = cidade;
            this.numElementos++;
        }

    }

    public Cidade getPrimeiro(){
        return this.cidades[0];
    }

    public void mostrar(int idObjetivo){
        for(int i=0;i<numElementos;i++){
            System.out.println("\t"+this.cidades[i].getNome()+" "+ this.cidades[i].getId() +" - "+ this.cidades[i].getDistancias().get(idObjetivo).distancia);
        }
    }
}
