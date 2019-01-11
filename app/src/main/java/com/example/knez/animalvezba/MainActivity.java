package com.example.knez.animalvezba;


import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int animalSound;
    private Animal choosenAnimal;
    private Animal[] animalArray = new Animal[18];
    private Animal[] selectedAnimalArray = new Animal[9];
    private MediaPlayer mp;
    private int points;
    private int highScore;
    private TextView tv1;
    private TextView tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animal bear = new Animal("bear", R.drawable.bear0, R.drawable.bear1, R.drawable.bear2, R.drawable.bear3, R.drawable.bear4, R.raw.bear);
        Animal dog = new Animal("dog", R.drawable.dog0, R.drawable.dog1, R.drawable.dog2, R.drawable.dog3, R.drawable.dog4, R.raw.dog);
        Animal sheep = new Animal("sheep", R.drawable.sheep0, R.drawable.sheep1, R.drawable.sheep2, R.drawable.sheep3, R.drawable.sheep4, R.raw.sheep);
        Animal cat = new Animal("cat", R.drawable.cat0, R.drawable.cat1, R.drawable.cat2, R.drawable.cat3, R.drawable.cat4, R.raw.cat);
        Animal horse = new Animal("horse", R.drawable.horse0, R.drawable.horse1, R.drawable.horse2, R.drawable.horse3, R.drawable.horse4, R.raw.horse);
        Animal wolf = new Animal("wolf", R.drawable.wolf0, R.drawable.wolf1, R.drawable.wolf2, R.drawable.wolf3, R.drawable.wolf4, R.raw.wolf);
        Animal pig = new Animal("pig", R.drawable.pig0, R.drawable.pig1, R.drawable.pig2, R.drawable.pig3, R.drawable.pig4, R.raw.pig);
        Animal cow = new Animal("cow", R.drawable.cow0, R.drawable.cow1, R.drawable.cow2, R.drawable.cow3, R.drawable.cow4, R.raw.cow);
        Animal bird = new Animal("bird", R.drawable.bird0, R.drawable.bird1, R.drawable.bird2, R.drawable.bird3, R.drawable.bird4, R.raw.bird);
        Animal tiger = new Animal("tiger", R.drawable.tiger0, R.drawable.tiger1, R.drawable.tiger2, R.drawable.tiger3, R.drawable.tiger4, R.raw.tiger);
        Animal mouse = new Animal("mouse", R.drawable.mouse0, R.drawable.mouse1, R.drawable.mouse2, R.drawable.mouse3, R.drawable.mouse4, R.raw.mouse);
        Animal elephant = new Animal("elephant", R.drawable.elephant0, R.drawable.elephant1, R.drawable.elephant2, R.drawable.elephant3, R.drawable.elephant4, R.raw.elephant);
        Animal monkey = new Animal("monkey", R.drawable.monkey0, R.drawable.monkey1, R.drawable.monkey2, R.drawable.monkey3, R.drawable.monkey4, R.raw.monkey);
        Animal seal = new Animal("seal", R.drawable.seal0, R.drawable.seal1, R.drawable.seal2, R.drawable.seal3, R.drawable.seal4, R.raw.seal);
        Animal squirrel = new Animal("squirrel", R.drawable.squirrel0, R.drawable.squirrel1, R.drawable.squirrel2, R.drawable.squirrel3, R.drawable.squirrel4, R.raw.squirrel);
        Animal owl = new Animal("owl", R.drawable.owl0, R.drawable.owl1, R.drawable.owl2, R.drawable.owl3, R.drawable.owl4, R.raw.owl);
        Animal raccoon = new Animal("raccoon", R.drawable.raccoon0, R.drawable.raccoon1, R.drawable.raccoon2, R.drawable.raccoon3, R.drawable.raccoon4, R.raw.raccoon);
        Animal snake = new Animal("snake", R.drawable.snake0, R.drawable.snake1, R.drawable.snake2, R.drawable.snake3, R.drawable.snake4, R.raw.snake);

        animalArray[0] = bear;
        animalArray[1] = dog;
        animalArray[2] = sheep;
        animalArray[3] = cat;
        animalArray[4] = horse;
        animalArray[5] = wolf;
        animalArray[6] = pig;
        animalArray[7] = cow;
        animalArray[8] = bird;
        animalArray[9] = tiger;
        animalArray[10] = mouse;
        animalArray[11] = elephant;
        animalArray[12] = monkey;
        animalArray[13] = seal;
        animalArray[14] = squirrel;
        animalArray[15] = owl;
        animalArray[16] = raccoon;
        animalArray[17] = snake;

        int[] selectedNumers = selectedAnimal(18);
        for(int i = 0; i < selectedAnimalArray.length; i++){
            selectedAnimalArray[i] = animalArray[selectedNumers[i]];
        }

        choosenAnimal = chooseAnimal(selectedAnimalArray);
        animalSound = choosenAnimal.getSound();


        Button restart = (Button)findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               recreate();
            }
        });

        Button btn = (Button)findViewById(R.id.animal_sound);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(MainActivity.this, animalSound);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopPlayer(mediaPlayer);
                    }
                });
                mp.start();
            }
        });

        GridLayout gl = (GridLayout)findViewById(R.id.grid);
        tv1 = (TextView)findViewById(R.id.points);
        tv2 = (TextView)findViewById(R.id.high_score);

        SharedPreferences prefsHS = getSharedPreferences("myPrefsHS", MODE_PRIVATE);
        highScore = prefsHS.getInt("highScore", 0);

        tv1.setText("p: " + points);
        tv2.setText("hs: " + highScore);

        for(int i = 0; i < selectedAnimalArray.length; i++){
            addAnimalPic(selectedAnimalArray[i], gl);
        }

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("points", points);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        points = savedInstanceState.getInt("points", 0);
    }

    public Animal chooseAnimal(Animal[] array) {
            Random rand = new Random();
            int n = rand.nextInt(9);
        return selectedAnimalArray[n];
    }

    public int choosePic(){
        Random rand = new Random();
        int n = rand.nextInt(5);
        return n;
    }


    public void addAnimalPic( Animal animal, GridLayout gridLayout){
        View animalPic = getLayoutInflater().inflate(R.layout.animal_pic, null);
        final String name = animal.getName();

        ImageView img = (ImageView)animalPic.findViewById(R.id.animal_pic);
        img.setImageResource(animal.getNumber(choosePic()));
        gridLayout.addView(animalPic);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (name.equals(choosenAnimal.getName())) {
                        mp = MediaPlayer.create(MainActivity.this, R.raw.happykids);
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                stopPlayer(mediaPlayer);
                            }
                        });
                        points++;
                        if(points > highScore){
                            highScore = points;
                            SharedPreferences prefs = getSharedPreferences("myPrefsHS", MODE_PRIVATE);
                            SharedPreferences.Editor prefsHSEditor = prefs.edit();
                            prefsHSEditor.putInt("highScore", highScore);
                            prefsHSEditor.commit();
                        }
                        tv1.setText("p: " + points);
                        tv2.setText("hs: " + highScore);
                        mp.start();
                        recreate();
                    }else{
                        mp = MediaPlayer.create(MainActivity.this, R.raw.crowdboo);
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                stopPlayer(mediaPlayer);
                            }
                        });
                        points = 0;
                        tv1.setText("p: " + points);
                        mp.start();
                        recreate();
                    }
            }
        });

    }

    private void stopPlayer(MediaPlayer mediaPlayer) {
        if(null != mediaPlayer){
            if(mediaPlayer.isPlaying())
                mediaPlayer.stop();

            mediaPlayer.release();
            mp = null;
        }
    }

    public int[] selectedAnimal(int niz){
        int[] selected = new int[9];
        for(int i = 0; i < selected.length; i++){
            while(true){
                Random rand = new Random();
                int x = rand.nextInt(niz);
                if(isThereANumber(selected, x) == false){
                    selected[i] = x;
                    break;
                }else{
                    continue;
                }
            }
        }
        return selected;
    }

    public static boolean isThereANumber(int[] niz, int br){
        for(int i = 0; i < niz.length; i++){
            if(niz[i] == br){
                return true;
            }
        }
        return false;
    }

}
