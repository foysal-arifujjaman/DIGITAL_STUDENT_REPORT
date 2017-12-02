package com.jesatadila.expandableview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exlistbase);

        MyExpandable myExpandable = (MyExpandable) findViewById(R.id.expandable1);
       // myExpandable.setHeaderBackgroundColor(Color.DKGRAY);

        Button button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmail();
            }
        });

    }


    private void sendmail() {


        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{
            "nuheljft@gmail.com"
        });
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hello There");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Add Message here");

        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent,
                    "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getBaseContext(),
                    "No email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }

    }


}

