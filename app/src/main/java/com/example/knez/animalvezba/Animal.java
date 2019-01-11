package com.example.knez.animalvezba;

public class Animal{
    private String name;
    private int soundR;
    private int[] photoArray = new int[5];

    public Animal(String name, int img0, int img1, int img2, int img3, int img4, int sound) {
        soundR = sound;
        photoArray[0] = img0;
        photoArray[1] = img1;
        photoArray[2] = img2;
        photoArray[3] = img3;
        photoArray[4] = img4;
        this.name = name;
    }

    public Animal() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSound() {
        return soundR;
    }

    public void setSound(int sound) {
        this.soundR = sound;
    }

    public void setNumber(int x, int y){
        photoArray[x] = y;
    }

    public int getNumber(int x){
       return photoArray[x];
    }


}
