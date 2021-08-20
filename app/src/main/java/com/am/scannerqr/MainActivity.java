package com.am.scannerqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;
    private TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 0);




        int permissionCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        scannerView = (ZXingScannerView) findViewById(R.id.zxscan);
        txtResult = (TextView) findViewById(R.id.txtResult);
        if(permissionCamera == PackageManager.PERMISSION_GRANTED){
            scannerView.startCamera();
            scannerView.setFlash(false);
            scannerView.setResultHandler(MainActivity.this);

        }else{
           Toast.makeText(getApplicationContext(),"Devi abilitare il permesso alla fotocamera" , Toast.LENGTH_SHORT).show();
        }








    }



    @Override
    public void handleResult(Result rawResult){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        txtResult.setText(rawResult.getText());
            if(rawResult.getText().length() == 8){
                newScan();
            }
        if(rawResult.getText().startsWith("HC1:")){
            Intent intent = new Intent(this, InfoGreenPass.class);
            String code = rawResult.getText().substring(4);
            intent.putExtra("it.am.greenpass.code", code);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Analizzato , attendere 2 secondi prima del prossimo scan!" , Toast.LENGTH_SHORT).show();
            (new Handler()).postDelayed(this::newScan, 2000);

        }


    }

    private void newScan(){
        scannerView.startCamera();
        scannerView.setResultHandler(this);




    }



    @Override
    public void onDestroy(){

        scannerView.stopCamera();
        super.onDestroy();

    }



}