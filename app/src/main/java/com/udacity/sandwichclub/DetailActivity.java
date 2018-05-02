package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }


        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
//Just using the int 2 for testing with populating the data.
    private void populateUI(Sandwich sandwich) {
        //String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        //String json = sandwiches[2];
        //Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        TextView descriptionTextView = findViewById(R.id.description_tv);
        descriptionTextView.setText(sandwich.getDescription());



        TextView OriginTextview = findViewById(R.id.origin_tv);
        OriginTextview.setText(sandwich.getPlaceOfOrigin());

        List <String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        TextView akaTextView = findViewById(R.id.also_known_tv);
        //akaTextView.setText((CharSequence) sandwich.getAlsoKnownAs());
        akaTextView.setText(getFormattedString(alsoKnownAsList));


        List <String> ingredients = sandwich.getIngredients();
        TextView ingredientsTextView = findViewById(R.id.ingredients_tv);
        //ingredientsTextView.setText((CharSequence) sandwich.getIngredients());
        ingredientsTextView.setText(getFormattedString(ingredients));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



    }
    private String getFormattedString(List<String> list) {

        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {

                stringBuilder.append(list.get(i));

                if (i != size - 1) {
                    stringBuilder.append(" \n ");
                }

            }
        }
        return stringBuilder.toString();
    }
}
