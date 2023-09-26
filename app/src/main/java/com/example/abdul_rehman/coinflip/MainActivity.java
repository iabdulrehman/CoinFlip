package com.example.abdul_rehman.coinflip;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button flip;
    Random r;
    ImageView imageView;
    TextView textView;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        flip = (Button) findViewById(R.id.btnFlip);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        String stored = sp.getString(getString(R.string.key),"0");
        int x = getResources().getIdentifier("h"+stored, "drawable", getPackageName());
        //Toast.makeText(this,"Country" + stored,Toast.LENGTH_SHORT).show();
        imageView.setImageResource(x);

        r = new Random();

        flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stored = sp.getString(getString(R.string.key),"0");

                flipCoin(stored);

                /*
                RotateAnimation rotateAnimation = new RotateAnimation(0,360,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(2000);
                imageView.startAnimation(rotateAnimation);*/
            }
        });
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to Exit ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent s = new Intent(getApplicationContext(),Setting.class);
            s.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            s.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(s);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flipCoin(final String c) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1500);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //String a = "h"+c;
                int x = getResources().getIdentifier("h"+c, "drawable", getPackageName());
                int y = getResources().getIdentifier("t"+c, "drawable", getPackageName());

                //imageView.setImageResource(r.nextFloat() > 0.5f ? R.drawable.ukt : R.drawable.ukh);

                float val = r.nextFloat();

                //imageView.setImageResource(r.nextFloat() > 0.5f ? x : y);
                imageView.setImageResource(val > 0.5f ? x : y);

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);

                if(val > 0.5f)
                {
                    textView.setText("Head Wins !!!");
                }
                else
                {
                    textView.setText("Tail Wins !!!");
                }

                imageView.startAnimation(fadeIn);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imageView.startAnimation(fadeOut);
        textView.setText("");
    }
}
