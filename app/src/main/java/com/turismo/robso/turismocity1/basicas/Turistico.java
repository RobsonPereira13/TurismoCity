package com.turismo.robso.turismocity1.basicas;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GabrielDev on 09/06/2018.
 */

public class Turistico implements Parcelable {

    private int idTur;
    private String nomeTur;
    private Cidade cidade;

    public Turistico(){

    }

    public Turistico(Cidade cidade) {
        this.cidade = new Cidade();
    }

    protected Turistico(Parcel in) {
        idTur = in.readInt();
        nomeTur = in.readString();
        cidade = in.readParcelable(Cidade.class.getClassLoader());
    }

    public static final Creator<Turistico> CREATOR = new Creator<Turistico>() {
        @Override
        public Turistico createFromParcel(Parcel in) {
            return new Turistico(in);
        }

        @Override
        public Turistico[] newArray(int size) {
            return new Turistico[size];
        }
    };

    public int getIdTur() {
        return idTur;
    }

    public void setIdTur(int idTur) {
        this.idTur = idTur;
    }

    public String getNomeTur() {
        return nomeTur;
    }

    public void setNomeTur(String nomeTur) {
        this.nomeTur = nomeTur;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idTur);
        dest.writeString(nomeTur);
        dest.writeParcelable(cidade, flags);
    }
}
