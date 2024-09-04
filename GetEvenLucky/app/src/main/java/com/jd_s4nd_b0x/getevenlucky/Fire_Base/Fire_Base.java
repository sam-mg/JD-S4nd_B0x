package com.jd_s4nd_b0x.getevenlucky.Fire_Base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jd_s4nd_b0x.getevenlucky.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fire_Base extends AppCompatActivity {

    private EditText editTextKey;
    private Button buttonCheck;
    private TextView textViewResult;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fire_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://flag-44313-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = database.getReference("flag");

        editTextKey = findViewById(R.id.editTextKey);
        buttonCheck = findViewById(R.id.buttonCheck);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkKey();
            }
        });
    }

    private void checkKey() {
        String key = editTextKey.getText().toString().trim();
        if (key.isEmpty()) {
            textViewResult.setText("Please enter a key.");
            return;
        }

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String correctFlag = dataSnapshot.getValue(String.class);
                if (key.equals(correctFlag)) {
                    textViewResult.setText("Correct key!");
                } else {
                    textViewResult.setText("Incorrect key.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                textViewResult.setText("Error fetching data.");
            }
        });
    }
}