package com.turismo.robso.turismocity1.basicas;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by robso on 08/05/2018.
 */

public class Usuario implements Parcelable {
    public int id;
    public String nome;
    public String email;
    public String senha;
    public byte[] foto;
    public float pontuacao;
    public String mensagem;

    public Usuario(){

    }

    protected Usuario(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        email = in.readString();
        senha = in.readString();
        foto = in.createByteArray();
        pontuacao = in.readFloat();
        mensagem = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", foto=" + Arrays.toString(foto) +
                '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nome);
        parcel.writeString(email);
        parcel.writeString(senha);
        parcel.writeByteArray(foto);
        parcel.writeFloat(pontuacao);
        parcel.writeString(mensagem);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public float getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(float pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
