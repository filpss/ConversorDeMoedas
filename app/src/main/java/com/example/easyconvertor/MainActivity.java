package com.example.easyconvertor;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final String API_URL = "https://economia.awesomeapi.com.br/json/last/USD";

    private EditText editValueToConvert;
    private EditText editDollarQuote;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editValueToConvert = findViewById(R.id.valueToConvert);
        editDollarQuote = findViewById(R.id.dollarQuote);
        textResult = findViewById(R.id.result);

        fetchDollarQuote();
    }

    private void fetchDollarQuote() {
        new Thread(() -> {
            String jsonString = HttpHandler.getJsonFromUrl(API_URL);
            try {
                JSONObject json = new JSONObject(jsonString);
                JSONObject usdBr = json.getJSONObject("USDBRL");
                final String dollarQuote = usdBr.getString("ask");

                runOnUiThread(() -> {
                    DecimalFormat df = new DecimalFormat("#.##");
                    editDollarQuote.setText(df.format(Double.parseDouble(dollarQuote)));
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void convert(View view) {
        double valueToConvert = Double.parseDouble(editValueToConvert.getText().toString());
        double dollarQuote = Double.parseDouble(editDollarQuote.getText().toString());
        double result = valueToConvert * dollarQuote;

        textResult.setText("Valor convertido: " + result);
    }

}