package com.example.testpokeapi;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Fragment class
 */
public class DetailFragment extends Fragment {
    PokemonViewModel viewModel;
    private EditText userInputName;
    private Button submitButton;
    private TextView resultName, resultHeight, resultWeight, resultEntry;
    private LinearLayout layoutName, layoutWeight, layoutHeight, layoutEntry;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        //find ids
        userInputName = v.findViewById(R.id.editPokemonName);
        submitButton = v.findViewById(R.id.submitButton);

        //api results shown in these textviews
        resultName = v.findViewById(R.id.resultName);
        resultHeight = v.findViewById(R.id.resultHeight);
        resultWeight = v.findViewById(R.id.resultWeight);
        resultEntry = v.findViewById(R.id.resultEntry);

        //define layouts in the fragment
        layoutName = v.findViewById(R.id.layout_name);
        layoutHeight = v.findViewById(R.id.layout_height);
        layoutWeight = v.findViewById(R.id.layout_weight);
        layoutEntry = v.findViewById(R.id.layout_entry);

        //set the layouts invisible on start
        layoutName.setVisibility(LinearLayout.INVISIBLE);
        layoutHeight.setVisibility(LinearLayout.INVISIBLE);
        layoutWeight.setVisibility(LinearLayout.INVISIBLE);
        layoutEntry.setVisibility(LinearLayout.INVISIBLE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPokemonData(userInputName.getText().toString());
                //set the invisible layouts back to visibe, showing API information
                layoutName.setVisibility(LinearLayout.VISIBLE);
                layoutHeight.setVisibility(LinearLayout.VISIBLE);
                layoutWeight.setVisibility(LinearLayout.VISIBLE);
                layoutEntry.setVisibility(LinearLayout.VISIBLE);

            }
        });

        initViewModel();

        return v;
    }

    //init the view model
    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }
        });

        //get information of the api ,and set it on the textviews
        viewModel.getPokeName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                resultName.setText(s);
            }
        });

        viewModel.getPokedexEntry().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                resultEntry.setText(Integer.toString(s));
            }
        });

        viewModel.getPokeHeight().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                resultHeight.setText(s);
            }
        });

        viewModel.getPokeWeight().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                resultWeight.setText(s);
            }
        });
    }

}
