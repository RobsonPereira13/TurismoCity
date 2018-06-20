package com.turismo.robso.turismocity1.views;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.turismo.robso.turismocity1.R;
import com.turismo.robso.turismocity1.basicas.Cidade;
import com.turismo.robso.turismocity1.basicas.Usuario;
import com.turismo.robso.turismocity1.conexao.UsuarioDados;

public class Inicial extends AppCompatActivity {
    private ImageView imagemInicial;

    private Cidade city;
    private Bitmap bitmap;
    private ImageView imageCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        imagemInicial = (ImageView)findViewById(R.id.imageViewInicialid);

        imagemInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Inicial.this,Login.class);
                startActivity(i);
            }
        });
    }

}
