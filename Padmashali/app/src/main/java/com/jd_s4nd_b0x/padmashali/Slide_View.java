package com.jd_s4nd_b0x.padmashali;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Slide_View extends AppCompatActivity {
    private TextView yearTitle, yearData;
    private ImageButton btnPrevious, btnNext;
    private ImageView yearImage;

    private int[] years = {2010, 2015, 2020};
    private int currentYearIndex = 0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slide_view);

        yearTitle = findViewById(R.id.yearTitle);
        yearData = findViewById(R.id.yearData);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        yearImage = findViewById(R.id.yearImage);

        // Set initial year info
        updateYearInfo(currentYearIndex);

        // Previous button listener
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentYearIndex > 0) {
                    currentYearIndex--;
                    updateYearInfo(currentYearIndex);
                } else {
                    Toast.makeText(Slide_View.this, "Already at the beginning", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Next button listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentYearIndex < years.length - 1) {
                    currentYearIndex++;
                    updateYearInfo(currentYearIndex);
                } else {
                    Toast.makeText(Slide_View.this, "Already at the end", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateYearInfo(int index) {
        // Update year title
        yearTitle.setText(String.valueOf(years[index]));

        // Update year image
        int imageResource = getYearImage(years[index]);
        if (imageResource != 0) {
            yearImage.setImageResource(imageResource);
        } else {
            yearImage.setBackgroundResource(R.drawable.black_image);
        }

        // Update year data
        yearData.setText(getString(getResources().getIdentifier("info_" + years[index], "string", getPackageName())));
    }


    // Method to get image resource for a particular year (sample implementation)
    private int getYearImage(int year) {
        // Replace with your logic to get image resource for each year
        switch (year) {
            case 2010:
                return R.drawable.ic_image_2010;
            case 2015:
                return R.drawable.ic_image_2015;
            case 2020:
                return R.drawable.ic_image_2020;
            default:
                return android.R.color.black; // Default placeholder image
        }
    }
}