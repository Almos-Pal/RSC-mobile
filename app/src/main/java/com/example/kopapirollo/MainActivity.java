package com.example.kopapirollo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ImageView playerChoiceImage;
    public ImageView computerChoiceImage;
    public Button rockButton;
    public Button paperButton;
    public Button scissorsButton;
    public TextView resultTextView;
    private int wins;
    private  int loses;
    private Random random;
    private AlertDialog alertDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(0);
                playerChoiceImage.setImageResource(R.drawable.rock);


            }
        });   paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check(1);
                playerChoiceImage.setImageResource(R.drawable.paper);
            }
        });   scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(2);
                playerChoiceImage.setImageResource(R.drawable.scissors);

            }
        });
    }


    public void check(int playerChoose){

        int computerChoose = random.nextInt(3);
        switch (computerChoose){
            case 0:
                computerChoiceImage.setImageResource(R.drawable.rock);

                break;
            case 1:
                computerChoiceImage.setImageResource(R.drawable.paper);
                break;
            case 2:
                computerChoiceImage.setImageResource(R.drawable.scissors);
                break;
        }
        if(playerChoose == computerChoose){
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
        }else if(playerChoose == 0 && computerChoose == 2 || playerChoose == 1 && computerChoose == 0 || playerChoose == 2 && computerChoose == 1){
            Toast.makeText(this, "Nyertél", Toast.LENGTH_SHORT).show();

            wins++;
        }else{
            Toast.makeText(this, "Vesztettél", Toast.LENGTH_SHORT).show();

            loses++;
        }

        resultTextView.setText("Eredmény: Ember: " + wins + " Gép: " + loses);
        if( wins == 3 || loses == 3){
            alertDialog.setTitle(winCondition());
            alertDialog.show();
        }

    }
    public String winCondition(){
        if (wins > loses){
            return "Győzelem!";
        }
        return "Vereség!";
    }

    public void init(){
        playerChoiceImage = findViewById(R.id.yourResultImageView);
        computerChoiceImage = findViewById(R.id.enemyResultImageView);
        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);
        resultTextView = findViewById(R.id.resultTextView);
        wins = 0;
        loses = 0;
        random = new Random();
        alertDialog = new AlertDialog.Builder(MainActivity.this).setMessage("Szeretne új játékot játszani?").setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                wins = 0;
                loses = 0;
                resultTextView.setText("Eredmény: Ember: " + wins + " Gép: " + loses);

            }
        }).setCancelable(false).create()
        ;


    }

}