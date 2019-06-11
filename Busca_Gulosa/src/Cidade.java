import java.util.ArrayList;

public class Cidade {
    private int id;
    private String nome;
    private double lat;
    private double lon;
    private ArrayList<Integer> idAdj;
    private ArrayList<Adjacencia> adj;
    private ArrayList<Distancia> distancias;  //uma cidade tem a distancia de todas as cidades pra ela em linha reta, q rep a heuristica
    private boolean visitado;

    public Cidade(int id, String nome, double lat, double lon) {
        this.id = id;
        this.nome = nome;
        this.lat = lat;
        this.lon = lon;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
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
}
