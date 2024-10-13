package com.jd_s4nd_b0x.mulyam;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Loading_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loading_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String stockName = getIntent().getStringExtra("stockName");
        fetchStockData(stockName);
    }

    private void fetchStockData(String stockName) {
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();
                String apiKeyYahoo = DecryptionUtil.decrypt(new KeyProvider().getEncryptedYahooFinanceApiKey(), Loading_View.this);

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\"stock\":\"" + stockName + "\"}");

                Request request = new Request.Builder()
                        .url("https://yahoo-finance160.p.rapidapi.com/info")
                        .post(body)
                        .addHeader("x-rapidapi-key", apiKeyYahoo)
                        .addHeader("x-rapidapi-host", "yahoo-finance160.p.rapidapi.com")
                        .addHeader("Content-Type", "application/json")
                        .build();

                Response response = client.newCall(request).execute();

                assert response.body() != null;
                String stockData = response.body().string();

                Intent intent = new Intent(Loading_View.this, Final_View.class);
                intent.putExtra("stockData", stockData);
                startActivity(intent);
                finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}