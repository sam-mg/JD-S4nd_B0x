package com.jd_s4nd_b0x.padmashali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Final_Result extends AppCompatActivity {
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
        TextView flagTextView = findViewById(R.id.flagTextView);
        Button retryButton = findViewById(R.id.retryButton);

        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);

        // Retrieve roll number from SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String rollNumberValue = sharedPreferences.getString("roll_number", "");

        if (score == 0) {
            // Hide score and roll number
            scoreTextView.setVisibility(View.GONE);
            rollNumberTextView.setVisibility(View.GONE);

            // Show message for low score and retry button
            TextView messageTextView = findViewById(R.id.messageTextView);
            messageTextView.setText("Your score is very low. Retry?");
            retryButton.setVisibility(View.VISIBLE);
        } else {
            // Show score and roll number
            scoreTextView.setText("Your score: " + score + "/" + totalQuestions);
            rollNumberTextView.setText("Roll Number: " + rollNumberValue);

            // Generate and display the flag based on score
            String flag = generateFlag(score);
            flagTextView.setText(flag);
            flagTextView.setVisibility(View.VISIBLE);
        }
    }

    private String generateFlag(int score) {
        switch (score) {
            case 1:
                return "{1_r3c0m3nd_u_t0_r3try}";
            case 2:
                return "{p4y_m0re_4tt3nt10n_n3xt_t1m3}";
            case 3:
                return "{u_pr3f3rr3d_t0_st4y_4v3r4g3}";
            case 4:
                return "{u_4ll_g0t_3v3ryth1ng_c0rr3ct}";
            case 5:
                return "{W0w_u_g0t_it_4ll_c0rr3ct!!!}";
            default:
                return "";
        }
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