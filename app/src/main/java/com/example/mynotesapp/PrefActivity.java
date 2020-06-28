package com.example.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrefActivity extends AppCompatActivity {
    private Keystore keystore;
    private EditText pinEdtx;
    private Switch symbolShowSwitch;
    private Button savePinBtn;
    private TextView errorEdtx;
    private final int PIN_LENGTH = 4;

    public void setKeystore(Keystore keystore) {
        this.keystore = keystore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);
        init();
        listen();
    }

    private void listen() {
        final String enteredPin = String.valueOf(pinEdtx.getText());

        symbolShowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (symbolShowSwitch.isChecked()) {
                    pinEdtx.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    pinEdtx.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                }
            }
        });

        savePinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (enteredPin.length() < PIN_LENGTH) errorEdtx.setText(R.string.newPinError);
                else {
                    keystore.saveNew(enteredPin);
                    intent = new Intent(PrefActivity.this, NotesActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void init() {
        pinEdtx = findViewById(R.id.pinEdtx);
        symbolShowSwitch = findViewById(R.id.symbolShowSwitch);
        savePinBtn = findViewById(R.id.savePinBtn);
        errorEdtx = findViewById(R.id.errorEdtx);
    }
}
