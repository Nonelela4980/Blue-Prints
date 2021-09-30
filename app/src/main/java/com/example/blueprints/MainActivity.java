package com.example.blueprints;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Dialog gameRulesPopUp;
    TextView game_rules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        //hide the actionbar for this activity
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hides activity bar
        setContentView(R.layout.activity_main);

        gameRulesPopUp=new Dialog(this);
        gameRulesPopUp.setContentView(R.layout.game_rules_layout);
        game_rules=gameRulesPopUp.findViewById(R.id.game_rules_text);

        ScrollingMovementMethod scroll_gameRules=new ScrollingMovementMethod();
        game_rules.setMovementMethod(scroll_gameRules);
    }

    //Connects takes the user to another activity
    public void playBtnClick(View view) {
        Intent intent=new Intent(getApplicationContext(),connect_activity.class);
        startActivity(intent);
    }

    /**Displays game rules as a popup to the user when it clicks the game rules btn
     * */
    public void gameRulesBtnClick(View view)
    {
       ImageView check=gameRulesPopUp.findViewById(R.id.aggreeGameRules);
       check.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               gameRulesPopUp.dismiss();
           }
       });
        gameRulesPopUp.show();
    }

    public void closeApplication(View view) {
        finish();
    }
}