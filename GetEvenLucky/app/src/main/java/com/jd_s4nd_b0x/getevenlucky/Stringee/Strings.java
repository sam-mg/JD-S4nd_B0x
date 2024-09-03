package com.jd_s4nd_b0x.getevenlucky.Stringee;

import android.content.Intent;
import android.os.Bundle;
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

public class Strings extends AppCompatActivity {
    private String storedUsername;
    private String storedPassword;

    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_strings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        storedUsername = getString(R.string.username);
        storedPassword = getString(R.string.password);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String enteredUsername = usernameInput.getText().toString();
            String enteredPassword = passwordInput.getText().toString();
            if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
                Intent intent = new Intent(Strings.this, Happy.class);
                startActivity(intent);
            } else {
                Toast.makeText(Strings.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}