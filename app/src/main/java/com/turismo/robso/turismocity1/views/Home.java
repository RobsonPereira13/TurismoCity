package com.turismo.robso.turismocity1.views;

import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.turismo.robso.turismocity1.Mapa.MapsActivity;
import com.turismo.robso.turismocity1.R;
import com.turismo.robso.turismocity1.basicas.Avaliacao;
import com.turismo.robso.turismocity1.fragments.cidadesFragment;
import com.turismo.robso.turismocity1.fragments.hoteisFragment;
import com.turismo.robso.turismocity1.fragments.tabPageAdapter;
import com.turismo.robso.turismocity1.fragments.turisticosFragment;

import org.json.JSONObject;

import java.net.URL;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    tabPageAdapter  tabPageAdapter;

    private int idUser;
    private String message;
    private float pontuacao;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
        String URL = "http://educacao.dadosabertosbr.com/api/cidades/pe";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String TAG = "";
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("onResponse: ",response.toString() );

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e( "onErrorResponse: ",error.toString() );
                    }
                }

        );

    */

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
         tabPageAdapter = new tabPageAdapter(getSupportFragmentManager());

          tabPageAdapter.AddFragment(new cidadesFragment(),"Cidades");
         tabPageAdapter.AddFragment(new hoteisFragment(),"Hotéis");
         tabPageAdapter.AddFragment(new turisticosFragment(),"Turísticos");


        viewPager.setAdapter(tabPageAdapter);
          tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                Bundle b = getIntent().getExtras();

                if (b!=null) {
                    Intent i = new Intent(this,AvaliacaoActivity.class);
                    idUser = (int) b.get("idUser");
                    pontuacao = (float) b.get("pontos");
                    message = b.getString("message");
                    i.putExtra("idUser",idUser);
                    i.putExtra("pontuacao",pontuacao);
                    i.putExtra("msg",message);
                    startActivity(i);
                }

                break;
            default:
                return false;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_localização) {
            Intent i = new Intent(Home.this,MapsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_perfil) {

            Bundle b = getIntent().getExtras();
            if (b!=null) {
                Intent i = new Intent(this,Listagem.class);
                idUser = (int) b.get("idUser");
                i.putExtra("idUser",idUser);
                startActivity(i);
            }

        }else if(id == R.id.nav_sair){

            final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
            builder.setTitle("Turismo City - Sair");
            builder.setMessage("Deseja sair no sistema?");

            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(Home.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            });

            builder.setNegativeButton("Não",null);
            alerta = builder.create();
            builder.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
