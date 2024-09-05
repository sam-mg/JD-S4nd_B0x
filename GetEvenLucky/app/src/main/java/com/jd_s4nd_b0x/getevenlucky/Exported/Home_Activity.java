package com.jd_s4nd_b0x.getevenlucky.Exported;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jd_s4nd_b0x.getevenlucky.R;

public class Home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getIntent() != null && getIntent().getAction() != null) {
            handleIncomingIntent(getIntent());
        } else {
            Log.d("Home_Activity", "No valid intent received.");
        }
    }

    private void handleIncomingIntent(Intent intent) {
        if ("com.jd_s4nd_b0x.getevenlucky.VULNERABLE_ACTION".equals(intent.getAction())) {
            String secretCode = intent.getStringExtra("SECRET_CODE");
            if ("12345".equals(secretCode)) {
                Intent protectedIntent = new Intent(this, Protected_Activity.class);
                startActivity(protectedIntent);
            } else {
                Log.d("Home_Activity", "Incorrect secret code.");
            }
        }
    }
}