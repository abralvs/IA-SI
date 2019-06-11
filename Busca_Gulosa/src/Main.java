public class Main {
    public static void main(String[] args){
//        Cidades cidades = new Cidades();
//        int id =  cidades.getCidades().get(0).getId();
//        System.out.println("ID: " + cidades.getCidades().get(0).getId() + ", NOME: " + cidades.getCidades().get(0).getNome());
//        for(Adjacencia adj : cidades.getCidades().get(0).getAdj()){
//            System.out.println("Cidade adj: " + adj.getCidade().getNome()
//                    + " distancia: " + adj.getCidade().getDistancias().get(id-1).getDistancia());
//
//        }
//
        Cidades citys = new Cidades();

        MelhorEscolha gulosa = new MelhorEscolha();

        gulosa.Buscar(citys.getCidades().get(2), citys.getCidades().get(26).getId());

        for(Cidade a: gulosa.getCaminho()){
            System.out.print(a.getNome() + " -> ");
        }
    }
}
