package com.jd_s4nd_b0x.sc4nvu3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Camera_Check extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camera_check);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textView = findViewById(R.id.camera_availability_info);
        if (!checkCameraHardware(this)) {
            textView.setText("This device does not have a camera.\nOnly photos can be scanned.");
        } else {
            textView.setText("Camera is available.\nYou can continue.");
        }

        Button continueButton = findViewById(R.id.continue_btn);
        continueButton.setOnClickListener(v -> {
            // Proceed to qr_page activity
            Intent intent = new Intent(Camera_Check.this, qr_page.class);
            intent.putExtra("hasCamera", checkCameraHardware(this));
            startActivity(intent);
            finish();
        });
    }

    private boolean checkCameraHardware(Context context) {
        // If yes return True or else False
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
}

