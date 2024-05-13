package com.jd_s4nd_b0x.padmashali;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class rerotate_phone extends AppCompatActivity {
    Button rerotate_done;
    boolean isFirstClick = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rerotate_phone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rerotate_done = findViewById(R.id.rerotate_done);
        rerotate_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstClick) {
                    Toast.makeText(rerotate_phone.this, "Please make sure your phone is in vertical orientation.", Toast.LENGTH_SHORT).show();
                    isFirstClick = false;
                } else {
                    Intent intent = new Intent(rerotate_phone.this, roll_number.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}