/*
 * Copyright (c) 2021.
 * Julio Cezar Riffel<julioriffel@gmail.com>
 */

package com.br.natanfc.filmesflix.api

import br.com.julioriffel.mvvm.model.Gerencia
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

    @GET("v1/rest/gerencias/lista.json")
    fun getAllMovies(): Call<List<Gerencia>>

}