package com.jd_s4nd_b0x.sc4nvu3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.zxing.android.BitmapLuminanceSource;
import com.journeyapps.zxing.integration.android.IntentIntegrator;
import com.journeyapps.zxing.integration.android.IntentResult;

import java.io.InputStream;

public class CameraActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Preview preview;
    private ImageView imageView;
    private ImageButton imageFromPhone;
    private Button buttonCapture;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_page);

        imageView = findViewById(R.id.image_view);
        imageFromPhone = findViewById(R.id.image_from_phone);
        buttonCapture = findViewById(R.id.button_capture);
        textViewResult = findViewById(R.id.text_view_result);

        imageFromPhone.setOnClickListener(v -> openImageFromPhone());

        buttonCapture.setOnClickListener(v -> {
            if (preview == null) {
                Toast.makeText(this, "No camera preview available", Toast.LENGTH_SHORT).show();
                return;
            }
            initiateQRCodeScan();
        });

        if (checkCameraHardware()) {
            startCamera();
        } else {
            Toast.makeText(this, "No camera available, showing default image", Toast.LENGTH_SHORT).show();
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                preview = new Preview.Builder().build();
                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build();

                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                cameraProvider.bindToLifecycle(this, cameraSelector, preview);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private boolean checkCameraHardware() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    private void openImageFromPhone() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
            imageView.setVisibility(View.VISIBLE);
            scanQRCodeFromImage(selectedImage);
        } else {
            IntentIntegrator IntentIntegrator;
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (intentResult != null) {
                String contents = intentResult.getContents();
                if (contents != null) {
                    textViewResult.setText(contents);
                    textViewResult.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(this, "Scan failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initiateQRCodeScan() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setPrompt("Scan a QR Code");
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        intentIntegrator.initiateScan();
    }

    private void scanQRCodeFromImage(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            MultiFormatReader reader = new MultiFormatReader();
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(bitmap)));
            Result result = reader.decode(binaryBitmap);
            textViewResult.setText(result.getText());
            textViewResult.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            Toast.makeText(this, "QR Code scanning failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
            }
        }
    }
}