package com.jd_s4nd_b0x.getevenlucky.Logee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jd_s4nd_b0x.getevenlucky.Happy;
import com.jd_s4nd_b0x.getevenlucky.R;

public class Log_Doubt extends AppCompatActivity {
    private String encryptedFlag;
    private EditText editTextFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_doubt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        encryptedFlag = a.getEncryptedFlag();
        Log.d("MainActivity", "Flag: " + encryptedFlag);
        editTextFlag = findViewById(R.id.editTextFlag);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(v -> {
            String enteredFlag = editTextFlag.getText().toString().trim();
            if (encryptedFlag.equals(enteredFlag)) {
                Intent intent = new Intent(Log_Doubt.this, Happy.class);
                startActivity(intent);
            }
        });
    }
}