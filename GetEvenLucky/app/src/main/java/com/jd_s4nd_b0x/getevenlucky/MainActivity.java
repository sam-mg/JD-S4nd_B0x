package com.jd_s4nd_b0x.getevenlucky;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jd_s4nd_b0x.getevenlucky.Collision.Collision_Avoid;
import com.jd_s4nd_b0x.getevenlucky.Exported.Home_Activity;
import com.jd_s4nd_b0x.getevenlucky.Fire_Base.Fire_Base;
import com.jd_s4nd_b0x.getevenlucky.Logee.Log_Doubt;
import com.jd_s4nd_b0x.getevenlucky.Login_Activity.Login_Activity;
import com.jd_s4nd_b0x.getevenlucky.SharedPrefs.Shared_Preferences;
import com.jd_s4nd_b0x.getevenlucky.Stringee.Strings;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Logee Button
        Button logeeButton = findViewById(R.id.button_logee);
        logeeButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Log_Doubt.class)));

//        Stringee Button
        Button stringeeButton = findViewById(R.id.button_stringee);
        stringeeButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Strings.class)));

//        Shared Preferences Button
        Button shared_preferencesButton = findViewById(R.id.button_Shared_Preferences);
        shared_preferencesButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Shared_Preferences.class)));

//        Login Activity Button
        Button login_activityButton = findViewById(R.id.button_Login_Activity);
        login_activityButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Login_Activity.class)));

//        Fire Base Button
        Button fire_baseButton = findViewById(R.id.button_Fire_Base);
        fire_baseButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Fire_Base.class)));

//        Protected Activity Button
        Button protected_activityButton = findViewById(R.id.button_Protected_Activity);
        protected_activityButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Home_Activity.class)));

//        Collision Avoid Button
        Button collision_avoidButton = findViewById(R.id.button_Collision_Avoid);
        collision_avoidButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Collision_Avoid.class)));
    }
}