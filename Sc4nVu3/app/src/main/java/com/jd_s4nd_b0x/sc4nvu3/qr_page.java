package com.jd_s4nd_b0x.sc4nvu3;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.FileNotFoundException;
import java.io.IOException;

public class qr_page extends AppCompatActivity {

    private SurfaceView surfaceView;
    private TextView textView;
    private CameraSource cameraSource;
    private BarcodeDetector barcodeDetector;

    private ImageView no_camera_image;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton image_from_phone;
    private Button capture_btn;
    private FrameLayout camera_preview;

    private Button copy_text_btn;
    private Button open_link_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_page);

        surfaceView = findViewById(R.id.camera);
        textView = findViewById(R.id.text);
        no_camera_image = findViewById(R.id.no_camera);
        image_from_phone = findViewById(R.id.image_from_phone);
        capture_btn = findViewById(R.id.button_capture);
        camera_preview = findViewById(R.id.camera_preview);

        copy_text_btn = findViewById(R.id.copy_text);
        open_link_btn = findViewById(R.id.open_link);

        // Set buttons initially to invisible
        copy_text_btn.setVisibility(View.INVISIBLE);
        open_link_btn.setVisibility(View.INVISIBLE);

        // Check if the device has a camera
        if (!checkCameraHardware()) {
            no_camera_image.setVisibility(View.VISIBLE);
            camera_preview.setVisibility(View.GONE);
        } else {
            no_camera_image.setVisibility(View.GONE);
            camera_preview.setVisibility(View.VISIBLE);
        }

        // Initialize BarcodeDetector and CameraSource
        barcodeDetector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .setAutoFocusEnabled(true)
                .build();

        // Add a callback to handle SurfaceView creation
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(qr_page.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(qr_page.this, new String[]{Manifest.permission.CAMERA}, 1);
                        return;
                    }
                    cameraSource.start(surfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                // Leave this method empty, as we don't need to handle surface changes
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        // Set up barcode detector processor
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                // Release resources
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if (qrcodes.size() != 0) {
                    textView.post(() -> {
                        String qrContent = qrcodes.valueAt(0).displayValue;
                        textView.setText(qrContent);
                        copy_text_btn.setVisibility(View.VISIBLE);
                        if (qrContent.startsWith("http://") || qrContent.startsWith("https://")) {
                            open_link_btn.setVisibility(View.VISIBLE);
                        } else {
                            open_link_btn.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

        // Initialize onClick listener for image_from_phone button
        image_from_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageFromPhone();
            }
        });

        // Initialize onClick listener for capture_btn button
        capture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle capture button click (if needed)
                Toast.makeText(qr_page.this, "Capture button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize onClick listener for copy_text button
        copy_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyTextToClipboard(textView.getText().toString());
            }
        });

        // Initialize onClick listener for open_link button
        open_link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(textView.getText().toString());
            }
        });
    }

    // Method to check if the device has a camera
    private boolean checkCameraHardware() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    // Method to handle result of image selection from gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                camera_preview.setBackground(Drawable.createFromStream(
                        getContentResolver().openInputStream(imageUri), imageUri.toString())); // Set the background of camera_preview to the selected image
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to open image selection from phone's gallery
    private void openImageFromPhone() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Method to copy text to clipboard
    private void copyTextToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("QR Code", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    // Method to open a link in a browser
    private void openLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    // Release camera resources when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
    }
}