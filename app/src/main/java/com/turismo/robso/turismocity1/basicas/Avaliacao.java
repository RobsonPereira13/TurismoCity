package com.turismo.robso.turismocity1.basicas;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GabrielDev on 09/06/2018.
 */

public class Avaliacao implements Parcelable {

    public int idAvali;
    public int pontos;
    public String msgAvali;
    public Usuario usuario;

    public Avaliacao(){

    }

    public Avaliacao(Usuario usuario) {
        this.usuario = new Usuario();
    }

    public Avaliacao(Parcel in){
        idAvali = in.readInt();
        pontos = in.readInt();
        msgAvali = in.readString();
        usuario = (Usuario) in.readParcelable(Usuario.class.getClassLoader());
    }


    public static final Creator<Avaliacao> CREATOR = new Creator<Avaliacao>() {
        @Override
        public Avaliacao createFromParcel(Parcel in) {
            return new Avaliacao(in);
        }

        @Override
        public Avaliacao[] newArray(int size) {
            return new Avaliacao[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idAvali);
        dest.writeInt(pontos);
        dest.writeString(msgAvali);
        dest.writeParcelable(usuario, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getIdAvali() {
        return idAvali;
    }

    public void setIdAvali(int idAvali) {
        this.idAvali = idAvali;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getMsgAvali() {
        return msgAvali;
    }

    public void setMsgAvali(String msgAvali) {
        this.msgAvali = msgAvali;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

