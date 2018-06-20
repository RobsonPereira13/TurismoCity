package com.turismo.robso.turismocity1.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.turismo.robso.turismocity1.R;
import com.turismo.robso.turismocity1.basicas.Avaliacao;
import com.turismo.robso.turismocity1.basicas.Usuario;
import com.turismo.robso.turismocity1.conexao.AvaliacaoDados;
import com.turismo.robso.turismocity1.conexao.UsuarioDados;

public class AvaliacaoActivity extends AppCompatActivity {

    private EditText mensagem;
    private RatingBar ratingBar;
    private int id;
    private  float pontos;
    private String message;
    private Button comentar,sair;
    private SQLiteDatabase database;
    private Usuario usuario;
    private UsuarioDados usuarioDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        usuarioDados = new UsuarioDados(this);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        mensagem = (EditText) findViewById(R.id.etMessage);
        comentar = (Button) findViewById(R.id.btComentar);

        Bundle b = getIntent().getExtras();
        if (b!=null) {
            id = (int) b.get("idUser");
            pontos = (float) b.get("pontuacao");
            ratingBar.setRating(pontos);
            message = b.getString("msg");
        }

        mensagem.setText(this.message);
        addListenerOnRatingBar();
        usuario = new Usuario();
        usuario.setId(this.id);
        comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AvaliacaoActivity.this,Home.class);
                if(usuarioDados.getUsuario(usuario.getId()) != null){
                    usuario.setPontuacao(pontos);
                    usuario.setMensagem(mensagem.getText().toString());
                    usuarioDados.updateAvaliacao(usuario);
                    i.putExtra("idUser",id);
                    i.putExtra("pontos",pontos);
                    i.putExtra("message",mensagem.getText().toString());
                    startActivity(i);
                    finish();
                }
            }
        });

    }


    public void addListenerOnRatingBar() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                    pontos = (float) rating;
            }
        });
    }



}
