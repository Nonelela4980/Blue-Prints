package com.example.blueprints;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
public class connect_activity extends AppCompatActivity {
    TextView name_textField, ip_addressTextField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //hide the actionbar for this activity
        setContentView(R.layout.activity_connect_activity);

        name_textField = findViewById(R.id.name_textField);
        ip_addressTextField = findViewById(R.id.ipAddress_textField);
    }
    public void startBtnClick(View view) {
        if (name_textField.getText().toString().equals("") || ip_addressTextField.getText().toString().equals("")) {
            Toast.makeText(this, "Fill the missing field(s)", Toast.LENGTH_LONG).show();
        } else{
        }
    }
    public void backBtn_Login(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}