package com.example.easyconvertor;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
    }

    public void convert(View view) {
        double valueToConvert  = Double.parseDouble(editValueToConvert.getText().toString());
        double dollarQuote = Double.parseDouble(editDollarQuote.getText().toString());
        double result = valueToConvert * dollarQuote;

        textResult.setText("Valor convertido: " + result);
    }

}