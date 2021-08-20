package com.am.scannerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipException;


public class InfoGreenPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_green_pass);

        Intent intent = getIntent();
        String code = intent.getStringExtra("it.am.greenpass.code");
        Decoder decoder = new Decoder();
        JSONObject json = null;
        try {
            json = decoder.decode(code);



        } catch (IncorrectQRCodeException e) {
            e.printStackTrace();
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!(json == null)){
            TextView tv = findViewById(R.id.textView);
            TextView tv1 = findViewById(R.id.textView1);
            TextView tv2 = findViewById(R.id.textView2);
            TextView tv3 = findViewById(R.id.textView3);
            TextView certificate = findViewById(R.id.certificate);

            try {
               String date = json.getJSONObject("-260").getJSONObject("1").getJSONArray("v").getJSONObject(0).getString("dt").replace("-","/");
                String certificato = json.getJSONObject("-260").getJSONObject("1").getJSONArray("v").getJSONObject(0).getString("ci");
                certificate.setText(certificato);
                String year = date.substring(0,4);
                String month = date.substring(5,7);
                String day = date.substring(8,10);
                tv.setText("Ultima dose: "+day+"/"+month+"/"+year);

                int dosi = json.getJSONObject("-260").getJSONObject("1").getJSONArray("v").getJSONObject(0).getInt("dn");
                tv1.setText("Dosi effettuate : " + dosi + " / 2");

                String surname = json.getJSONObject("-260").getJSONObject("1").getJSONObject("nam").getString("fn");
                String name = json.getJSONObject("-260").getJSONObject("1").getJSONObject("nam").getString("gn");
                String data_n = json.getJSONObject("-260").getJSONObject("1").getString("dob");

                String year_n = data_n.substring(0,4);
                String month_n = data_n.substring(5,7);
                String day_n = data_n.substring(8,10);

                tv2.setText("Nome: "+ name+"   Cognome: " + surname);
                tv3.setText("Nato il: "+day_n+"/"+month_n+"/"+year_n);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplicationContext(),"JSON NULLO" , Toast.LENGTH_SHORT).show();
        }

    }
}