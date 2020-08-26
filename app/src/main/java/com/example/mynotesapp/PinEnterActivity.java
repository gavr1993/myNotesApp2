package com.example.mynotesapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PinEnterActivity extends AppCompatActivity {
    private static final int PIN_LENGTH = 4;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private ImageButton btnDel;
    private TextView pinErrTxt;
    private String enteredPin = "";
    private Keystore keystore;
    private TextView dot1;
    private TextView dot2;
    private TextView dot3;
    private TextView dot4;

    public void setKeystore(Keystore keystore) {
        this.keystore = keystore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_enter);
        init();
        listen();
    }

    private void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnDel = findViewById(R.id.btnDel);
        pinErrTxt = findViewById(R.id.pinErrTxt);
        dot1 = findViewById(R.id.dot1);
        dot2 = findViewById(R.id.dot2);
        dot3 = findViewById(R.id.dot3);
        dot4 = findViewById(R.id.dot4);
    }

    private void listen() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "1";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "2";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "3";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "4";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "5";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "6";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "7";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "8";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "9";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredPin += "0";
                paintPlaceholder();
                entPinCheck(enteredPin);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delNum(enteredPin);
                paintPlaceholder();
            }
        });
    }

    private void paintPlaceholder() {
        switch (enteredPin.length()) {
            case 0:
                dot1.setTextColor(Color.GRAY);
                dot2.setTextColor(Color.GRAY);
                dot3.setTextColor(Color.GRAY);
                dot4.setTextColor(Color.GRAY);
                break;
            case 1:
                dot1.setTextColor(Color.RED);
                dot2.setTextColor(Color.GRAY);
                dot3.setTextColor(Color.GRAY);
                break;
            case 2:
                dot2.setTextColor(Color.RED);
                dot3.setTextColor(Color.GRAY);
                break;
            case 3:
                dot3.setTextColor(Color.RED);
                dot4.setTextColor(Color.GRAY);
                break;
            case 4:
                dot4.setTextColor(Color.RED);
                break;
        }
    }

    public static String delNum(String enteredPin) {
        return (enteredPin == null || enteredPin.length() == 0)
                ? null
                : (enteredPin.substring(0, enteredPin.length() - 1));
    }

    public void entPinCheck(@NonNull final String enteredPin) {
        if (enteredPin.length() == PIN_LENGTH) {
            if (keystore.checkPin(enteredPin)) {
                Intent intent = new Intent(this, NotesActivity.class);
                startActivity(intent);
                finish();
            } else {
                pinErrTxt.setText(R.string.pinEntErr);
                this.enteredPin = "";
                paintPlaceholder();
            }
        }
    }
}

