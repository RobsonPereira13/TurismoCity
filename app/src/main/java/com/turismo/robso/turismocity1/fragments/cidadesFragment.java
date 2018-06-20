package com.turismo.robso.turismocity1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.turismo.robso.turismocity1.R;

import java.util.ArrayList;


public class cidadesFragment extends Fragment {

private ListView listaCidades,listaHoteis;
 private  String [] cidades ={"Recife","Jaboatão"};
    private  String [] hoteis ={"Recife","Jaboatão"};
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_cidades,container, false);
        listaCidades = (ListView) v.findViewById(R.id.list_cidade);
        listaHoteis = (ListView) v.findViewById(R.id.hoteislist);

        ArrayAdapter<String> adptador = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                cidades
        );
        listaCidades.setAdapter(adptador);
        listaCidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
int cod = position;
if(cod == 0) {
    Toast.makeText(getActivity(),"Muito ladrao", Toast.LENGTH_LONG).show();
}else{
    Toast.makeText(getActivity(),"Muito assalto", Toast.LENGTH_LONG).show();
}
    }
});
        return v;
    }

}
