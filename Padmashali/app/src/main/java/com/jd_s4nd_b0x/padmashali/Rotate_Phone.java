package com.jd_s4nd_b0x.padmashali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class Rotate_Phone extends AppCompatActivity{
    ImageView rotate_phone;
    Button continue_btn;
    boolean isFirstClick = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rotate_phone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rotate_phone = findViewById(R.id.rotate_phone);
        continue_btn = findViewById(R.id.continue_btn);

//        Picasso.get().load(R.drawable.ic_rotate_phone).into(rotate_phone);
        continue_btn.setOnClickListener(v -> {
            if (isFirstClick) {
                Toast.makeText(Rotate_Phone.this, "Please make sure your phone is in landscape orientation.", Toast.LENGTH_SHORT).show();
                isFirstClick = false;
            } else {
                 Intent intent = new Intent(Rotate_Phone.this, Slide_View.class);
                 startActivity(intent);
                 finish();
            }
        });
    }
}