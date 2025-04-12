package com.example.imccalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        String name = it.getStringExtra("name");
        Integer age = it.getIntExtra("age", 1);
        Float weight = it.getFloatExtra("weight", 1.f);
        Float height = it.getFloatExtra("height", 1.f);

        Float imc = weight / (height * height);

        String classi;
        if (imc < 18.5) {
            classi = "Abaixo do Peso";
        } else if (imc < 25.0) {
            classi = "Saudável";
        } else if (imc < 30.0) {
            classi = "Sobrepeso";
        } else if (imc < 35.0) {
            classi = "Obesidade Grau I";
        } else if (imc < 40.0) {
            classi = "Obesidade Grau II (severa)";
        } else {
            classi = "Obesidade Grau III (mórbida)";
        }

        TextView text_name = findViewById(R.id.text_name);
        TextView text_age = findViewById(R.id.text_age);
        TextView text_weight = findViewById(R.id.text_weight);
        TextView text_height = findViewById(R.id.text_height);
        TextView text_imc = findViewById(R.id.text_imc);
        TextView text_classi = findViewById(R.id.text_classi);

        text_name.setText(name);
        text_age.setText(age.toString());
        text_weight.setText(weight.toString());
        text_height.setText(height.toString());
        text_imc.setText(imc.toString());
        text_classi.setText(classi);
    }

    public void returnToForm(View view) {
        Intent it = new Intent(this, MainActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(it);
    }
}