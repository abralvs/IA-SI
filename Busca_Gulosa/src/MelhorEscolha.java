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
            this.fronteira = new VetorOrdenado(origem.getAdj().size());
            for(Adjacencia city: origem.getAdj()){
                if(!city.getCidade().isVisitado()){
                    city.getCidade().setVisitado(true);
                    this.fronteira.inserir(city.getCidade(), idObjetivo);
                }
            }
            this.fronteira.mostrar(idObjetivo);
            if(this.fronteira.getPrimeiro() != null && this.achou == false){
                this.Buscar(this.fronteira.getPrimeiro(), idObjetivo);
            }
        }
    }

    public ArrayList<Cidade> getCaminho() {
        return caminho;
    }
}
