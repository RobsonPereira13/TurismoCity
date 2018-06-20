package com.turismo.robso.turismocity1.basicas;

import android.arch.lifecycle.ViewModel;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by GabrielDev on 29/05/2018.
 */

public class Cidade  implements Parcelable {

    public int idCid;
    public String nomeCid;
    public Usuario usuario;

    public Cidade() {

    }

    public Cidade(Parcel in) {
        idCid = in.readInt();
        nomeCid = in.readString();
        usuario = (Usuario) in.readParcelable(Usuario.class.getClassLoader());
    }

    public static final Creator<Cidade> CREATOR = new Creator<Cidade>() {
        @Override
        public Cidade createFromParcel(Parcel in) {
            return new Cidade(in);
        }

        @Override
        public Cidade[] newArray(int size) {
            return new Cidade[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idCid);
        parcel.writeString(nomeCid);
        parcel.writeParcelable(usuario, i);
    }


    public int getIdCid() {
        return idCid;
    }

    public void setIdCid(int idCid) {
        this.idCid = idCid;
    }

    public String getNomeCid() {
        return nomeCid;
    }

    public void setNomeCid(String nomeCid) {
        this.nomeCid = nomeCid;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + idCid +
                ", nome='" + nomeCid + '\'' +
                '}';
    }

}
