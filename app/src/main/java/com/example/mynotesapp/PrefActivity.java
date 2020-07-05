package com.example.mynotesapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
public class PrefActivity extends AppCompatActivity {
    private Keystore keystore;
    private EditText pinEdtx;
    private TextInputLayout pinInput;
    private Button savePinBtn;
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
        savePinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinInput.setError(null);
                final String enteredPin = String.valueOf(pinEdtx.getText());
                if (enteredPin.length() < PIN_LENGTH) {
                    pinInput.setError(getString(R.string.newPinError));
                } else {
                    keystore.saveNew(enteredPin);
                    Intent intent = new Intent(PrefActivity.this, NotesActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void init() {
        pinInput = findViewById(R.id.pinInput);
        pinEdtx = findViewById(R.id.pinEdtx);
        savePinBtn = findViewById(R.id.savePinBtn);
    }
}