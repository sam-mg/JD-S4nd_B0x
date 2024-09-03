package com.jd_s4nd_b0x.getevenlucky.Login_Activity;

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

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String enteredUsername = usernameInput.getText().toString();
            String enteredPassword = passwordInput.getText().toString();

            if (!enteredUsername.equals("5h3nron") || !enteredPassword.equals("4ndr0id_m4n")) {
                Toast.makeText(Login_Activity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Login_Activity.this, Happy.class);
                startActivity(intent);
            }
        });
    }
}