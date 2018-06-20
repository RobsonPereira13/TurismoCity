package com.turismo.robso.turismocity1.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.turismo.robso.turismocity1.R;
import com.turismo.robso.turismocity1.basicas.Usuario;
import com.turismo.robso.turismocity1.basicas.Utility;
import com.turismo.robso.turismocity1.conexao.UsuarioDados;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Atualizar extends AppCompatActivity {
    private SQLiteDatabase database;
    private Button atualizar;
    private Usuario user;
    private UsuarioDados usuarioDados;
    private EditText edTextNome, edTextEmail, edTextSenha;
    private int id;

    private ImageButton btImagem;
    private ImageView imageCamera;
    private  Bitmap bitmap;
    private  byte[] imgByte;

    private String userChoosenTask;
    private Button btAtualizar;
    int opcao = 0;

    private static final int CAMERA_REQUEST = 1000;
    private static final int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        usuarioDados = new UsuarioDados(this);
        edTextNome = (EditText) findViewById(R.id.editTextNomeCadid);
        edTextEmail = (EditText) findViewById(R.id.editTextEmailCadid);
        edTextSenha = (EditText) findViewById(R.id.editTextSenhaCadid);

        imageCamera = (ImageView) findViewById(R.id.imagemCameid);
        btAtualizar = (Button) findViewById(R.id.btnAtualizar);
        btImagem = (ImageButton) findViewById(R.id.imageButtonCameid);
        database = usuarioDados.getReadableDatabase();


        Bundle b = getIntent().getExtras();
        if (b!=null){
            id= (int) b.get("id");
            selectDb(id);
            edTextNome.setText(b.getString("nome"));
            edTextEmail.setText(b.getString("email"));
            edTextSenha.setText(b.getString("senha"));
        }


        btImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });


        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarEntradas();
            }
        });
    }

    private void selectDb(int id){

        Cursor c = database.rawQuery("SELECT * FROM "+UsuarioDados.USUARIO_TABELA_NOME+" WHERE "+UsuarioDados.USUARIO_COLUNA_ID+"=' "+ this.id +"';", null);
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                imgByte= c.getBlob(4);
                Bitmap bitmap=BitmapFactory.decodeByteArray(imgByte,0,imgByte.length);
                imageCamera.setImageBitmap(bitmap);
                c.moveToNext();
            }
        }
    }



    private boolean validarEntradas() {
        if (edTextNome.getText().toString().trim().equals("")) {
            edTextNome.setError("Informe o nome");
            edTextNome.requestFocus();
            return false;
        } else if (edTextEmail.getText().toString().trim().equals("")) {
            edTextEmail.setError("Informe o e-mail");
            edTextEmail.requestFocus();
            return false;
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(edTextEmail.getText()).matches()) {
            edTextEmail.setError("E-mail inválido");
            edTextEmail.requestFocus();
            return false;
        }else if (edTextSenha.getText().toString().trim().equals("") || edTextSenha.getText().toString().equals(null)) {
            edTextSenha.setError("Informe o senha");
            edTextSenha.requestFocus();
            return false;
        }else if(edTextSenha.getText().toString().length()<6){
            edTextSenha.setError("A senha em até 6 caracteres!");
            edTextSenha.requestFocus();
            return false;
        }else if(!isValidPassword(edTextSenha.getText().toString())){
            edTextSenha.setError("Senha inválida(deve ter Número,Maiúsculo,Minúsculo e caracteres especiais)!");
            edTextSenha.requestFocus();
            return false;
        }


        bitmap = ((BitmapDrawable) imageCamera.getDrawable()).getBitmap();
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, saida);
        byte[] img = saida.toByteArray();

        user = new Usuario();

        if (usuarioDados.getUsuario(this.id) != null) {
            user.setId(this.id);
            user.nome = String.valueOf(edTextNome.getText());
            user.email = String.valueOf(edTextEmail.getText());
            user.senha = String.valueOf(edTextSenha.getText());
            user.setFoto(img);
            usuarioDados.update(user);
            Toast toast =  Toast.makeText(getApplicationContext(), "Usuario Atualizado!", Toast.LENGTH_LONG);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.msg_orange);
            toast.show();
            Intent i = new Intent(Atualizar.this, Listagem.class);
            i.putExtra("idUser", user.getId());
            startActivity(i);
            finish();
        }
        return true;
    }

    private static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d).{6,20})").matcher(password);
        return matcher.matches();
    }

    private void escolherImagemDaCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    private void escolherImagemDaGaleria() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);//
            startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_PHOTO);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void selectImage() {
        final String escolhaTirarFoto = "Tirar Foto";
        final String escolhaFotoGaleria = "Escolher da Galeria";
        final CharSequence[] items = {escolhaTirarFoto, escolhaFotoGaleria,
                "Cancelar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Atualizar.this);
        builder.setTitle("Obter Foto");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(Atualizar.this);
                if (items[item].equals(escolhaTirarFoto)) {
                    userChoosenTask = "Take Photo";
                    escolherImagemDaCamera();
                } else if (items[item].equals(escolhaFotoGaleria)) {
                    userChoosenTask = "Choose from Library";
                    escolherImagemDaGaleria();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            if (opcao == 0) {
                imageCamera.setImageBitmap(mphoto);
            } else {
                imageCamera.setImageBitmap(mphoto);
            }

        } else if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
            if (opcao == 0) {
                imageCamera.setImageURI(selectedImage);
            } else {
                imageCamera.setImageURI(selectedImage);
            }
        }

    }


}
