package com.jd_s4nd_b0x.padmashali;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Base64;

public class Final_Result extends AppCompatActivity {
    Button copyButton;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_final_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        TextView rollNumberTextView = findViewById(R.id.rollNumberTextView);
        copyButton = findViewById(R.id.copyButton); // Initialize copyButton

        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);

        // Retrieve roll number from SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String rollNumberValue = sharedPreferences.getString("roll_number", "");

        if (score == 0) {
            // Hide score, roll number, and copy button
            scoreTextView.setVisibility(View.GONE);
            rollNumberTextView.setVisibility(View.GONE);
            copyButton.setVisibility(View.GONE);

            // Show message for low score and retry button
            TextView messageTextView = findViewById(R.id.messageTextView);
            messageTextView.setText("Your score is very low. Retry?");
            Button retryButton = findViewById(R.id.retryButton);
            retryButton.setVisibility(View.VISIBLE);
        } else {
            // Show score and roll number
            scoreTextView.setText("Your score: " + score + "/" + totalQuestions);
            rollNumberTextView.setText("Roll Number: " + rollNumberValue);

            // Generate encrypted flag based on score and roll number
            String encryptedFlag = generateEncryptedFlag(score, rollNumberValue);

            // Encode the flag using Base64
            String encodedFlag = Base64.encodeToString(encryptedFlag.getBytes(), Base64.DEFAULT);

            // Set encoded flag to copy button tag
            copyButton.setTag(encodedFlag);
        }
    }

    public void copyScoreToClipboard(View view) {
        String encryptedFlag = (String) copyButton.getTag(); // Get encoded flag from copy button tag

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Flag", encryptedFlag);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(this, "Flag copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    private String generateEncryptedFlag(int score, String rollNumber) {
        String flag = "";
        switch (score) {
            case 1:
                flag = "_1_r3c0m3nd_u_t0_r3try";
                break;
            case 2:
                flag = "_p4y_m0re_4tt3nt10n_n3xt_t1m3";
                break;
            case 3:
                flag = "_u_pr3f3rr3d_t0_st4y_4v3r4g3";
                break;
            case 4:
                flag = "_u_4ll_g0t_3v3ryth1ng_c0rr3ct";
                break;
            case 5:
                flag = "_W0w_u_g0t_it_4ll_c0rr3ct!!!";
                break;
            default:
                flag = "";
                break;
        }
        return rollNumber + flag;
    }

    public void retry(View view) {
        // Clear SharedPreferences
        sharedPreferences.edit().clear().apply();

        // Restart the main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}