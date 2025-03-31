package com.example.mysimplecalculator;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public Pair<Float, Float> getValues() {
        EditText input_1 = findViewById(R.id.value_1);
        EditText input_2 = findViewById(R.id.value_2);

        Float value_1 = Float.parseFloat(input_1.getText().toString());
        Float value_2 = Float.parseFloat(input_2.getText().toString());
        return new Pair<>(value_1, value_2);
    }

    public void plus(View view) {
        String result;
        try {
            Pair<Float, Float> values = getValues();
            result = Float.toString(values.first + values.second);
        } catch (Exception e) {
            result = "error";
        }

        TextView result_view = findViewById(R.id.result);
        result_view.setText(result);
    }

    public void minus(View view) {
        String result;
        try {
            Pair<Float, Float> values = getValues();
            result = Float.toString(values.first - values.second);
        } catch (Exception e) {
            result = "error";
        }

        TextView result_view = findViewById(R.id.result);
        result_view.setText(result);
    }

    public void div(View view) {
        String result;
        try {
            Pair<Float, Float> values = getValues();
            if (values.second == 0) {
                result = "error";
            } else {
                result = Float.toString(values.first / values.second);
            }
        } catch (Exception e) {
            result = "error";
        }

        TextView result_view = findViewById(R.id.result);
        result_view.setText(result);
    }

    public void times(View view) {
        String result;
        try {
            Pair<Float, Float> values = getValues();
            result = Float.toString(values.first * values.second);

        } catch (Exception e) {
            result = "error";
        }

        TextView result_view = findViewById(R.id.result);
        result_view.setText(result);
    }
}