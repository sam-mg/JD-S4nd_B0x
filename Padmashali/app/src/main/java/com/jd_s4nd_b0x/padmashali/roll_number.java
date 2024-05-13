package com.jd_s4nd_b0x.padmashali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class roll_number extends AppCompatActivity {
    TextInputEditText editText_RollNumber;
    Button ctn_button;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_roll_number);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        editText_RollNumber = findViewById(R.id.roll_num);
        ctn_button = findViewById(R.id.question_continue_btn);
        ctn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll_numbr;
                roll_numbr = String.valueOf(editText_RollNumber.getText());
                if (TextUtils.isEmpty(roll_numbr)){
                    Toast.makeText(roll_number.this, "Roll Number Fields is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("roll_number", roll_numbr);
                editor.apply();

                Intent intent = new Intent(roll_number.this, questions_mcq.class);
                intent.putExtra("ROLL_NUMBER", roll_numbr);
                startActivity(intent);
                finish();
            }
        });
    }
}