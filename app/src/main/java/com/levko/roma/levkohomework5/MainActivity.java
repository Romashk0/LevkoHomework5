package com.levko.roma.levkohomework5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTargetEmail, etThemeEmail, etTextEmail;
    private Button btnSendEmail, btnCallSupport;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListeners();

    }

    private void findViews() {
        etTargetEmail = (EditText) findViewById(R.id.et_target_AM);
        etThemeEmail = (EditText) findViewById(R.id.et_theme_AM);
        etTextEmail = (EditText) findViewById(R.id.et_text_AM);
        btnSendEmail = (Button) findViewById(R.id.btn_send_AM);
        btnCallSupport = (Button) findViewById(R.id.btn_call_AM);
    }

    private void setListeners() {
        btnSendEmail.setOnClickListener(this);
        btnCallSupport.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_AM:
                sendEmail();
                break;
            case R.id.btn_call_AM:
                callToSupport();
                break;

        }
    }

    private void sendEmail() {
        String sToWhom = etTargetEmail.getText().toString();
        String sTheme = etThemeEmail.getText().toString();
        String sTextToSend = etTextEmail.getText().toString();

        if (!TextUtils.isEmpty(sToWhom) && !TextUtils.isEmpty(sTheme) && !TextUtils.isEmpty(sTextToSend)) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{sToWhom});
            email.putExtra(Intent.EXTRA_SUBJECT, sTheme);
            email.putExtra(Intent.EXTRA_TEXT, sTextToSend);
            email.setType("Choose an Email client:");

            startActivity(Intent.createChooser(email, "Choose an Email client:"));
        }else Toast.makeText(this, Constants.WRITE_IN_EMPTY_FIELDS, Toast.LENGTH_LONG).show();
    }

    private void callToSupport() {
        Uri phoneNumber = Uri.parse("tel:" + Constants.NUMBER_TO_CALL);
        Intent toCall = new Intent(Intent.ACTION_CALL, phoneNumber);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, Constants.NOT_CALL_THIS_DEVICE, Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(toCall);
    }
}
