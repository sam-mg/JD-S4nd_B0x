package com.jd_s4nd_b0x.mulyam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Final_View extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_final_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String stockData = getIntent().getStringExtra("stockData");
        assert stockData != null;

        try {
            JSONObject jsonObject = new JSONObject(stockData);
            String longName = jsonObject.getString("longName");
            String symbol = jsonObject.getString("symbol");
            String country = jsonObject.getString("country");
            double currentPrice = jsonObject.getDouble("currentPrice");
            String currency = jsonObject.getString("currency");
            String currentTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());

            convertCurrency(currentPrice, currency, (inrPrice) -> {
                runOnUiThread(() -> {
                    TextInputEditText longNameEditText = findViewById(R.id.longNameTextView);
                    TextInputEditText symbolEditText = findViewById(R.id.symbolTextView);
                    TextInputEditText countryEditText = findViewById(R.id.countryTextView);
                    TextInputEditText currentPriceEditText = findViewById(R.id.currentPriceTextView);
                    TextInputEditText fetchedTimeEditText = findViewById(R.id.fetchedTimeTextView);

                    longNameEditText.setText(longName);
                    symbolEditText.setText(symbol);
                    countryEditText.setText(country);
                    currentPriceEditText.setText(String.format("₹ %.2f", inrPrice));
                    fetchedTimeEditText.setText(currentTime);
                });

            });
        } catch (Exception e) {
            Log.e("JSONError", "Error parsing JSON", e);
        }
    }

    private void convertCurrency(double amount, String fromCurrency, CurrencyConversionCallback callback) {
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();

                String apiKey = DecryptionUtil.decrypt(new KeyProvider().getEncryptedCurrencyApiKey(), Final_View.this);

                String url = String.format("https://api.currencyapi.com/v3/latest?apikey=%s&currencies=INR&base_currency=%s", apiKey, fromCurrency);
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String responseData = response.body().string();
                    JSONObject jsonResponse = new JSONObject(responseData);
                    double exchangeRate = jsonResponse.getJSONObject("data").getJSONObject("INR").getDouble("value");
                    double inrPrice = amount * exchangeRate;

                    callback.onConversionCompleted(inrPrice);
                }
            } catch (Exception e) {
                Log.e("CurrencyError", "Error fetching currency data", e);
            }
        }).start();
    }

    interface CurrencyConversionCallback {
        void onConversionCompleted(double inrPrice);
    }
}