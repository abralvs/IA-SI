package com.example.sergipetour;

import android.Manifest;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Cidades citys;
    private ImageButton btnTodasAdjacencias;
    private boolean mostrandoTodasAdj = false;
    private List<Polyline> arestas = new ArrayList<>();
    private List<Polyline> rota = new ArrayList<>();
    private List<Polyline> arvoreDebug = new ArrayList<>();
    private List<Polyline> caminhoDebug = new ArrayList<>();
    private ImageButton btnGerarRota;
    private ArrayList<LatLng> pontos;
    private Spinner inicio;
    private Spinner destino;
    private ImageButton btnDebug;
    Cidade initAux = null, destAux = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnDebug = findViewById(R.id.btnDebug);
        inicio = findViewById(R.id.spInicio);
        destino = findViewById(R.id.spDestino);
        btnTodasAdjacencias = findViewById(R.id.btnTodasAdjacencias);
        btnGerarRota = findViewById(R.id.btnGerarRota);


        // Setando cidades no spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inicio.setAdapter(adapter);
        destino.setAdapter(adapter);

        AssetManager assetManager = getResources().getAssets();
        citys = new Cidades(assetManager);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.style));

        // Adicionando todos os marcadores
        Marker m;
        for (int i = 0; i < citys.getCidades().size();i++) {
            mMap.addMarker(new MarkerOptions().position(citys.getCidades()
                    .get(i)
                    .getCoordenadas())
                    .title(citys.getCidades().get(i).getNome()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-10.6826,-37.4273),10));

        btnGerarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rota.size() > 0) {
                    for(Polyline rot : rota)
                        rot.remove();
                    rota.clear();
                }
                System.out.println(rota.size());

                pegaCidadesEcolhidas();
                Toast.makeText(getBaseContext(),"init: "+initAux.getNome()+"  destino: "+destAux.getNome()
                        +" - id: "+destAux.getId(),Toast.LENGTH_LONG).show();
                // chamando método guloso
                MelhorEscolha gulosa = new MelhorEscolha();
                gulosa.Buscar(initAux, destAux.getId());
                System.out.println(rota.size());

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(initAux.
                        getCoordenadas().latitude, initAux.getCoordenadas().longitude), 10)); //camera no inicio

                pontos = new ArrayList<>();
                for (Cidade c: gulosa.getCaminho())
                    pontos.add(c.getCoordenadas());

                rota.add(mMap.addPolyline(new PolylineOptions()
                        .addAll(pontos)
                        .width(5)
                        .color(Color.BLUE)));

                pontos.clear();
                System.out.println(pontos.size());
            }
        });


        btnTodasAdjacencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mostrandoTodasAdj) {
                    mostrandoTodasAdj = true;
                    pontos = new ArrayList<>();
                    for (int i = 0; i < citys.getCidades().size(); i++) {
                        pontos.add(citys.getCidades().get(i).getCoordenadas());
                        for (int j = 0; j < citys.getCidades().get(i).getAdj().size(); j++) {
                            pontos.add(citys.getCidades().get(i).getAdj().get(j).getCidade().getCoordenadas());

                            arestas.add(mMap.addPolyline(new PolylineOptions()
                                    .addAll(pontos)
                                    .width(5)
                                    .color(Color.RED)));
                            pontos.remove(1);
                        }
                        pontos.removeAll(pontos);
                    }
                }
                else{
                    for(Polyline aresta : arestas){
                        aresta.remove();
                    }
                    mostrandoTodasAdj = false;
                }
            }
        });


        btnDebug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arvoreDebug.size() > 0) {
                    limpaAresta();
                }

                pegaCidadesEcolhidas();
                MelhorEscolha gulosa = new MelhorEscolha();
                gulosa.Buscar(initAux, destAux.getId());

                // adicionando todas as adijacencias (as que levam pro destino e as que não para formar a arvore)
                pontos = new ArrayList<>();
                for (Cidade c: gulosa.getCaminho()){
                    if (c.getId() != destAux.getId()) {
                        pontos.add(c.getCoordenadas());
                        for (int i = 0; i < c.getAdj().size(); i++) {
                            pontos.add(c.getAdj().get(i).getCidade().getCoordenadas());
                            arvoreDebug.add(mMap.addPolyline(new PolylineOptions()
                                    .addAll(pontos)
                                    .width(5)
                                    .color(Color.RED)));

                            pontos.remove(1);
                        }
                        pontos.removeAll(pontos);
                    }
                    // adicionando apenas a linha que leva ao destino com uma cor diferente

                    // -----------------------------------------------------------

                    pontos.removeAll(pontos);
                    for (Cidade ci: gulosa.getCaminho())
                        pontos.add(ci.getCoordenadas());

                    caminhoDebug.add(mMap.addPolyline(new PolylineOptions()
                            .addAll(pontos)
                            .width(5)
                            .color(Color.BLUE)));
                    pontos.removeAll(pontos);
                }
            }
        });

    }

    public void pegaCidadesEcolhidas(){
        // pegando indices das ciades
        for (int i = 0; i < citys.getCidades().size(); i++) {
            if (citys.getCidades().get(i).getNome().equals(inicio.getSelectedItem().toString()))
                initAux = citys.getCidades().get(i);

            if (citys.getCidades().get(i).getNome().equals(destino.getSelectedItem().toString()))
                destAux = citys.getCidades().get(i);
        }
    }

    public void limpaAresta(){

        for (Polyline no : arvoreDebug)   //caso tenha arvore tracada remove
            no.remove();

        for(Polyline caminho : caminhoDebug)  //remove o caminho
            caminho.remove();
    }
}
