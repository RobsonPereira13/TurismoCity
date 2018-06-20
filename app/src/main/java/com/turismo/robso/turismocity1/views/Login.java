package com.turismo.robso.turismocity1.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.turismo.robso.turismocity1.R;
import com.turismo.robso.turismocity1.basicas.Usuario;
import com.turismo.robso.turismocity1.conexao.UsuarioDados;

public class Login extends AppCompatActivity {

    private ImageView imagemLog;
    private Button btLogar,btCadastre;
    private EditText senhaLog,emailLog;
    private SQLiteDatabase database;
    private UsuarioDados dados;
    private Usuario u;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Turismo City - Login");

        senhaLog = (EditText)findViewById(R.id.editTextSenhaLogid);
        emailLog = (EditText)findViewById(R.id.editTextEmailLogid);
        imagemLog =(ImageView)findViewById(R.id.imageViewLogid);
        btLogar = (Button)findViewById(R.id.buttonLogid);
        btCadastre = (Button)findViewById(R.id.buttonCadastrarid);


        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarEntradas();

            }
        });

        btCadastre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Cadastro.class);
                startActivity(i);

            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    //validando campos da tela de login
    private boolean validarEntradas() {
        u = new Usuario();
        u.email = String.valueOf(emailLog.getText().toString());
        u.senha = String.valueOf(senhaLog.getText().toString());
        dados = new UsuarioDados(Login.this);
        database = dados.getReadableDatabase();

        if (emailLog.getText().toString().trim().equals("")) {
            emailLog.setError("Informe o e-mail");
            emailLog.setHint("Informe o e-mail");
            emailLog.requestFocus();
            return false;
        } else if (senhaLog.getText().toString().trim().equals("")) {
            senhaLog.setError("Informe o senha");
            senhaLog.setHint("Informe o senha");
            senhaLog.requestFocus();
            return false;
            //Validando acesso
     //  }else if(dados.authLogin(emailLog.getText().toString(),senhaLog.getText().toString()) == 0){
       //     emailLog.setText("");
       //     senhaLog.setText("");
       //     Toast toast = Toast.makeText(Login.this,"Senha ou email inválido",Toast.LENGTH_LONG);
       //     View toastView = toast.getView();
       //     toastView.setBackgroundResource(R.drawable.msg_red);
       //     toast.show();
       //     return false;
        }

        selectDB();
        Toast toast = Toast.makeText(Login.this,"Bem-vindo "+u.getNome(),Toast.LENGTH_LONG);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.msg_blue);
        toast.show();
        Intent i = new Intent(Login.this, Home.class);
        i.putExtra("idUser", u.getId());
        i.putExtra("pontos",u.getPontuacao());
        i.putExtra("message",u.getMensagem());
        startActivity(i);
        return true;
    }


    private void selectDB() {
        Cursor c = database.rawQuery("SELECT * FROM "+UsuarioDados.USUARIO_TABELA_NOME+" WHERE "+UsuarioDados.USUARIO_COLUNA_EMAIL+"='"+emailLog.getText().toString()+"' AND "+UsuarioDados.USUARIO_COLUNA_SENHA+"='"+senhaLog.getText().toString()+"';", null);
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                u.setId(c.getInt(0));
                u.setNome(c.getString(1));
                u.setPontuacao(c.getFloat(5));
                u.setMensagem(c.getString(6));
                c.moveToNext();
            }
        }

    }

}


