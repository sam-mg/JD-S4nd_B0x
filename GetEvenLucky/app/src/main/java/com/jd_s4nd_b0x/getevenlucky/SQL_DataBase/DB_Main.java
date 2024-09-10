package com.jd_s4nd_b0x.getevenlucky.SQL_DataBase;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jd_s4nd_b0x.getevenlucky.R;

public class DB_Main extends AppCompatActivity {

    private TextView dbInfoTextView;
    private SQLiteDatabase database;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_db_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbInfoTextView = findViewById(R.id.db_info_text_view);
        dbInfoTextView.setText("SQLite databases are often used to store sensitive data. Explore the database for hidden information.");

        SQLiteOpenHelper dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        displayHint();
    }

    private void displayHint() {
        Cursor cursor = database.rawQuery("SELECT flag FROM Flags WHERE id=1", null);
        if (cursor.moveToFirst()) {
            String encodedFlag = cursor.getString(0);
        }
        cursor.close();
    }
}