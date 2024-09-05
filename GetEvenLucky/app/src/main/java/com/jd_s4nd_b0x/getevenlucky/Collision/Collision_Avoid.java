package com.jd_s4nd_b0x.getevenlucky.Collision;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jd_s4nd_b0x.getevenlucky.R;

public class Collision_Avoid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_collision_avoid);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText inputEmail = findViewById(R.id.input_email);
        Button checkButton = findViewById(R.id.check_button);

        checkButton.setOnClickListener(v -> {
            String enteredEmail = inputEmail.getText().toString();
            String collide = "john@github.com";
            if ((!enteredEmail.equals(collide)) && enteredEmail.toUpperCase().equals(collide.toUpperCase()) && !enteredEmail.equals(collide.toUpperCase())) {
                Toast.makeText(Collision_Avoid.this.getApplicationContext(), "great you have made a collision :)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Collision_Avoid.this.getApplicationContext(), "try again :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}