package com.example.testpokeapi;

import com.example.testpokeapi.Api.ApiService;
import com.example.testpokeapi.Api.PokeApi;
import com.example.testpokeapi.Model.Pokemon;

import retrofit2.Call;

public class Repository {
    private ApiService pokeApiService = PokeApi.create();
    public Call<Pokemon> getPokemon(String name) {
        return pokeApiService.getPokemon(name);
    }
}
