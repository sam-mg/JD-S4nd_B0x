package com.jd_s4nd_b0x.padmashali;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Slide_View extends AppCompatActivity {
    private TextView yearTitle, yearData;
    private ImageButton btnPrevious, btnNext;
    private ImageView yearImage;

    private int[] years = {1804, 1810, 1818, 1820, 1830, 1842, 1844, 1852, 1855, 1857, 1895, 1900, 1952, 1953, 1955, 1958, 1960, 1970, 1980, 1990, 2000, 2010, 2020};
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
                    Intent intent = new Intent(Slide_View.this, rerotate_phone.class);
                    startActivity(intent);
                    finish();
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
        String resourceName = "info_" + years[index];
        int resId = getResources().getIdentifier(resourceName, "string", getPackageName());
        if (resId != 0) {
            yearData.setText(getString(resId));
        } else {
            yearData.setText("Information not available");
        }

        // Reset scroll position to top
        ScrollView dataScrollView = findViewById(R.id.dataScrollView);
        dataScrollView.scrollTo(0, 0);
    }


    // Method to get image resource for a particular year (sample implementation)
    private int getYearImage(int year) {
        // Replace with your logic to get image resource for each year
        switch (year) {
            case 1804:
                return R.drawable.ic_image_1804;
            case 1810:
                return R.drawable.ic_image_1810;
            case 1818:
                return R.drawable.ic_image_1818;
            case 1820:
                return R.drawable.ic_image_1820;
            case 1830:
                return R.drawable.ic_image_1830;
            case 1842:
                return R.drawable.ic_image_1842;
            case 1844:
                return R.drawable.ic_image_1844;
            case 1852:
                return R.drawable.ic_image_1852;
            case 1855:
                return R.drawable.ic_image_1855;
            case 1857:
                return R.drawable.ic_image_1857;
            case 1895:
                return R.drawable.ic_image_1895;
            case 1900:
                return R.drawable.ic_image_1900;
            case 1952:
                return R.drawable.ic_image_1952;
            case 1953:
                return R.drawable.ic_image_1953;
            case 1955:
                return R.drawable.ic_image_1955;
            case 1958:
                return R.drawable.ic_image_1958;
            case 1960:
                return R.drawable.ic_image_1960;
            case 1970:
                return R.drawable.ic_image_1970;
            case 1980:
                return R.drawable.ic_image_1980;
            case 1990:
                return R.drawable.ic_image_1990;
            case 2000:
                return R.drawable.ic_image_2000;
            case 2010:
                return R.drawable.ic_image_2010;
            case 2020:
                return R.drawable.ic_image_2020;
            default:
                return android.R.color.black; // Default placeholder image
        }
    }
}
