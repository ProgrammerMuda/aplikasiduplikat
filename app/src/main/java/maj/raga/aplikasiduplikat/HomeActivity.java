package maj.raga.aplikasiduplikat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import maj.raga.aplikasiduplikat.Function.functions;
import maj.raga.aplikasiduplikat.activitiy.LoginActivity;


public class HomeActivity extends functions {

    Button button1,button2,button3,button4,button5,button6;
    ImageView img, signout1 ;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user == null){
                    myIntent(LoginActivity.class);
                    finish();
                }
            }
        };

        button1 = (Button)findViewById(R.id.bt1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, colorActivity.class));
                finish();


            }
        });

        button2 = (Button)findViewById(R.id.bt2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, ShapeActivity.class));
                finish();


            }
        });

        button3 = (Button)findViewById(R.id.bt3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, AnimalActivity.class));
                finish();


            }
        });

        button4 = (Button)findViewById(R.id.bt4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, transportActivity.class));
                finish();


            }
        });

        button5 = (Button)findViewById(R.id.bt5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, FoodActivity.class));
                finish();


            }
        });

        button6 = (Button)findViewById(R.id.bt6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, BajuActivity.class));
                finish();


            }
        });

        img=(ImageView)findViewById(R.id.exit);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder
                        .setMessage("Shutdown app???")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
        });

       signout1 = (ImageView)findViewById(R.id.signout);
       signout1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               auth.signOut();
               myIntent(LoginActivity.class);
               finish();

           }
       });



    }
}
