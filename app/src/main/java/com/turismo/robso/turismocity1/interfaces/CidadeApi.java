package com.turismo.robso.turismocity1.interfaces;

import com.turismo.robso.turismocity1.basicas.Cidade;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by robso on 08/06/2018.
 */

public interface CidadeApi {

    @GET("/cidade/{cidade}")
    Call<Cidade> getCidade(@Path("cidade")String cidade);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://educacao.dadosabertosbr.com/api/cidades/pe")
            .addConverterFactory(GsonConverterFactory.create())
            .build();








}
