package com.example.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PinEnterActivity extends AppCompatActivity {
    private ImageView circle2 = findViewById(R.id.circle2);
    private ImageView circle4 = findViewById(R.id.circle4);
    private ImageView circle6 = findViewById(R.id.circle6);
    private ImageView circle8 = findViewById(R.id.circle8);
    private Button btn1 = findViewById(R.id.btn1);
    private Button btn2 = findViewById(R.id.btn2);
    private Button btn3 = findViewById(R.id.btn3);
    private Button btn4 = findViewById(R.id.btn4);
    private Button btn5 = findViewById(R.id.btn5);
    private Button btn6 = findViewById(R.id.btn6);
    private Button btn7 = findViewById(R.id.btn7);
    private Button btn8 = findViewById(R.id.btn8);
    private Button btn9 = findViewById(R.id.btn9);
    private Button btn0 = findViewById(R.id.btn0);
    private ImageButton btnDel = findViewById(R.id.btnDel);
    private TextView pinErrTxt = findViewById(R.id.pinErrTxt);
    private String enteredPin = "";
    private Keystore keystore;
    private final int PIN_LENGTH = 4;

    public void setKeystore(Keystore keystore) {
        this.keystore = keystore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_enter);
        listen();
    }

    private void listen() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "1";
                entPinCheck(enteredPin);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "2";
                entPinCheck(enteredPin);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "3";
                entPinCheck(enteredPin);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "4";
                entPinCheck(enteredPin);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "5";
                entPinCheck(enteredPin);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "6";
                entPinCheck(enteredPin);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "7";
                entPinCheck(enteredPin);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "8";
                entPinCheck(enteredPin);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "9";
                entPinCheck(enteredPin);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "0";
                entPinCheck(enteredPin);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delNum(enteredPin);
            }
        });
    }

    public static String delNum(String enteredPin) {
        return (enteredPin == null || enteredPin.length() == 0)
                ? null
                : (enteredPin.substring(0, enteredPin.length() - 1));
    }

    public void entPinCheck(String enteredPin) {
        if (enteredPin.length() == PIN_LENGTH) {
            keystore.checkPin(enteredPin);
            if (keystore.checkPin(enteredPin)) {
                Intent intent = new Intent(PinEnterActivity.this, NotesActivity.class);
                startActivity(intent);
            } else {
                pinErrTxt.setText(R.string.pinEntErr);
                enteredPin = "";
            }
        }
    }
}

