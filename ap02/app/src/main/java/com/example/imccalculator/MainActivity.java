package com.example.imccalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    public void generateReport(View view) {
        Intent it = new Intent(this, Report.class);
        it.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        String name = ((EditText) findViewById(R.id.name_input)).getText().toString();
        int age = Integer.parseInt(((EditText) findViewById(R.id.age_input)).getText().toString());
        float weight = Float.parseFloat(((EditText) findViewById(R.id.weight_input)).getText().toString());
        float height = Float.parseFloat(((EditText) findViewById(R.id.height_input)).getText().toString());

        it.putExtra("name", name);
        it.putExtra("age", age);
        it.putExtra("weight", weight);
        it.putExtra("height", height);

        startActivity(it);
    }
}