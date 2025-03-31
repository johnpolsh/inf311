package com.example.myadvancedcalculator;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> numeric = new ArrayList<>();
    ArrayList<String> ops = new ArrayList<>();

    Boolean errorState = false;

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

    public void appendDisplay(String text) {
        EditText display = findViewById(R.id.display);
        display.getText().append(text);
    }

    public void clearDisplay() {
        EditText display = findViewById(R.id.display);
        display.getText().clear();
    }

    public void setDisplayText(String text) {
        EditText display = findViewById(R.id.display);
        display.setText(text);
    }

    public Editable getDisplayText() {
        EditText display = findViewById(R.id.display);
        return display.getText();
    }

    public double getRemainder() {
        EditText display = findViewById(R.id.display);

        double remainder = 0.;
        try {
            remainder = Double.parseDouble(display.getText().toString());
        } catch (NumberFormatException e) {
            return 0.;
        }

        return remainder;
    }

    public double parseInput(Integer idx) {
        return Double.parseDouble(numeric.get(idx));
    }

    public double calculate(double a, double b) {
        String op = ops.get(0);

        double result = 0.;
        if (Objects.equals(op, "+")) {
            result = a + b;
        } else if (Objects.equals(op, "-")) {
            result = a - b;
        } else if (Objects.equals(op, "*")) {
            result = a * b;
        } else if (Objects.equals(op, "/")) {
            if (b == 0) {
                throw new ArithmeticException("division by zero");
            }
            result = a / b;
        }

        return result;
    }

    public void number(View view) {
        String digit = view.getTag().toString();

        boolean hasNumber = false;
        String val = "";
        if (numeric.isEmpty()) {
            hasNumber = !digit.equals(".");
            val += digit;
            setDisplayText("");
        } else {
            String last = numeric.remove(numeric.size() - 1);
            val = last + digit;
            if (digit.equals(".")) {
                hasNumber = !last.isEmpty();
            } else {
                hasNumber = true;
            }
        }

        numeric.add(val);
        errorState = false;
        if (hasNumber) {
            appendDisplay(digit);
        }
    }

    public void arith(View view) {
        String digit = view.getTag().toString();

        if (numeric.isEmpty()) {
            String remainder = Double.toString(getRemainder());
            numeric.add(remainder);
            setDisplayText(remainder);
            errorState = false;
        }

        if (ops.isEmpty()) {
            ops.add(digit);
            numeric.add("");
            appendDisplay(digit);
        }
    }

    public void back(View view) {
        if (!errorState) {
            Editable text = getDisplayText();
            if (text.toString().isEmpty()) {
                return;
            }

            String newText = text.toString().replaceAll(".$", "");
            setDisplayText(newText);

            if (newText.isEmpty()) {
                numeric.clear();
                ops.clear();
                setDisplayText("");
                return;
            }

            if (ops.isEmpty()) {
                if (numeric.size() == 1) {
                    String s = numeric.remove(0).replaceAll("$.", "");
                    if (!s.isEmpty()) {
                        numeric.add(s);
                    }
                } else {
                    numeric.clear();
                    ops.clear();
                    setDisplayText("");
                }
            } else {
                if (numeric.size() == 1) {
                    ops.clear();
                } else if (numeric.size() == 2) {
                    String s = numeric.remove(1).replaceAll("$.", "");
                    if (!s.isEmpty()) {
                        numeric.add(s);
                    }
                }
            }
        }
    }

    public void clear(View view) {
        numeric.clear();
        ops.clear();
        clearDisplay();
        errorState = false;
    }

    public void equals(View view) {
        try {
            double a = parseInput(0);
            double b = parseInput(1);

            double result = calculate(a, b);
            setDisplayText(Double.toString(result));
        } catch (Exception e) {
            errorState = true;
            setDisplayText("erro");
        }

        numeric.clear();
        ops.clear();
    }
}