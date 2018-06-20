package com.turismo.robso.turismocity1.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.turismo.robso.turismocity1.R;
import com.turismo.robso.turismocity1.basicas.Usuario;
import com.turismo.robso.turismocity1.conexao.UsuarioDados;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Listagem extends AppCompatActivity {
    private SQLiteDatabase database;
    private UsuarioDados usuarioDados;
    private Usuario user;
    private Button btAtualizar;
    private TextView nome,mail;
    private int idUser;
    private ImageView img;
    private  Bitmap bitmap;
    private  byte[] imgByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        usuarioDados = new UsuarioDados(this);
        database = usuarioDados.getReadableDatabase();
        btAtualizar =  (Button) findViewById(R.id.btnAtualizar);
        img = (ImageView) findViewById(R.id.imageView2);
        nome = (TextView) findViewById(R.id.txtNome);
        mail = (TextView) findViewById(R.id.txtMail);

        user =  new Usuario();
        Bundle b = getIntent().getExtras();
        if(b!=null){
            idUser = (int) b.get("idUser");
        }

        user.setId(idUser);
        selectDb();

        nome.setText(user.getNome());
        mail.setText(user.getEmail());

        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id= user.getId();
                String pass = user.getSenha();
                Intent in = new Intent(Listagem.this,Atualizar.class);
                in.putExtra("id",id);
                in.putExtra("nome",nome.getText().toString());
                in.putExtra("email",mail.getText().toString());
                in.putExtra("senha",pass.toString());
                startActivity(in);

            }
        });

    }

    private void selectDb(){

        Cursor c = database.rawQuery("SELECT * FROM "+UsuarioDados.USUARIO_TABELA_NOME+" WHERE "+UsuarioDados.USUARIO_COLUNA_ID+"=' "+user.getId()+"';", null);
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                user.setId(c.getInt(0));
                user.setNome(c.getString(1));
                user.setEmail(c.getString(2));
                user.setSenha(c.getString(3));
                imgByte= c.getBlob(4);
                Bitmap bitmap=BitmapFactory.decodeByteArray(imgByte,0,imgByte.length);
                img.setImageBitmap(bitmap);
                //Toast.makeText(Listagem.this, "Table Name=> "+c.getString(1), Toast.LENGTH_LONG).show();
                c.moveToNext();
            }
        }

    }



}
