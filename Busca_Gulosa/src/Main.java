public class Main {
    public static void main(String[] args){
        Cidades cidades = new Cidades();
        int id =  cidades.getCidades().get(0).getId();
        System.out.println("ID: " + cidades.getCidades().get(0).getId() + ", NOME: " + cidades.getCidades().get(0).getNome());
        for(Adjacencia adj : cidades.getCidades().get(0).getAdj()){
           System.out.println("Cidade adj: " + adj.getCidade().getNome()
                   + " distancia: " + adj.getCidade().getDistancias().get(id-1).getDistancia());

        }
    }
}
