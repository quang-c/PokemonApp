package com.example.testpokeapi;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.testpokeapi.Model.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonViewModel extends AndroidViewModel {
    private Repository pokeRepository = new Repository();
    private MutableLiveData<String> pokeName = new MutableLiveData<>();
    private MutableLiveData<String> pokeHeight = new MutableLiveData<>();
    private MutableLiveData<String> pokeWeight = new MutableLiveData<>();
    private MutableLiveData<Integer> pokedexEntry = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();


    public PokemonViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getError() {
        return error;
    }
    public MutableLiveData<String> getPokeName() {
        return pokeName;
    }
    public MutableLiveData<String> getPokeHeight() { return pokeHeight; }
    public MutableLiveData<String> getPokeWeight() { return pokeWeight; }
    public MutableLiveData<Integer> getPokedexEntry() { return pokedexEntry; }


    public void getPokemonData(String name) {
        pokeRepository
                .getPokemon(name)
                .enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(@NonNull Call<Pokemon> call, @NonNull Response<Pokemon> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            pokeName.setValue(response.body().getName());
                            pokeHeight.setValue(response.body().getHeight());
                            pokeWeight.setValue(response.body().getWeight());
                            pokedexEntry.setValue(response.body().getId());
                        } else {
                            error.setValue("Api Error: " + response.message());
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Pokemon> call, @NonNull Throwable t) {
                        error.setValue("Api Error: " + t.getMessage());
                    }
                });
    }


}
