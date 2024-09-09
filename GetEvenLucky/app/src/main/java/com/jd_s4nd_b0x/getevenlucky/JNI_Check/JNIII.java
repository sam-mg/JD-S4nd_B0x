package com.jd_s4nd_b0x.getevenlucky.JNI_Check;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jd_s4nd_b0x.getevenlucky.Happy;
import com.jd_s4nd_b0x.getevenlucky.R;

public class JNIII extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    public native int compareString(String input);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jniii);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText inputEditText = findViewById(R.id.passwordInput);
        Button compareButton = findViewById(R.id.log_in);

        compareButton.setOnClickListener(v -> {
            String userInput = inputEditText.getText().toString();
            int result = compareString(userInput);
            if (result == 1) {
                Intent intent = new Intent(JNIII.this, Happy.class);
                startActivity(intent);
            } else {
                Toast.makeText(JNIII.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}