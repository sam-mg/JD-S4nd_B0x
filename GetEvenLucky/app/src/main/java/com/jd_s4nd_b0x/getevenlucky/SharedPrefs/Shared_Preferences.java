package com.jd_s4nd_b0x.getevenlucky.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jd_s4nd_b0x.getevenlucky.MainActivity;
import com.jd_s4nd_b0x.getevenlucky.R;

public class Shared_Preferences extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_preferences);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText userInput = findViewById(R.id.userInput);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = userInput.getText().toString();

                if (!input.isEmpty()) {
                    // Create a flag from the user's input and store it in SharedPreferences
                    String generatedFlag = "p3nt35t{" + input + "}";

                    SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(generatedFlag, ""); // Store the generated flag directly as a key
                    editor.apply();

                    // Show a confirmation message
                    Toast.makeText(Shared_Preferences.this, "Flag stored successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Empty input
                    Toast.makeText(Shared_Preferences.this, "Please enter something", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}