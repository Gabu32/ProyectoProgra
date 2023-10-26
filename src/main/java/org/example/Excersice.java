package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Excersice {
    private String excerciseName;
    private int burntCalories;
    private int reps;
    private String difficulty; //begginer, intermediate, expert
    private Scanner input = new Scanner(System.in);
    private ArrayList<Excersice> excercisesList = new ArrayList<>();
    private String file = "datos/datosEjercicios.txt";

    public Excersice(){
        this.readData();
    }
    public Excersice(String excerciseName, int cal, int reps, String difficulty){
        this.excerciseName = excerciseName;
        this.burntCalories = cal;
        this.reps = reps;
        this.difficulty = difficulty;
    }

    public void setExcerciseName(String excerciseName){
        this.excerciseName = excerciseName;
    }
    public String getExcerciseName(){
        return excerciseName;
    }
    public void setBurntCalories(int burntCalories) {
        this.burntCalories = burntCalories;
    }
    public int getBurntCalories() {
        return burntCalories;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }
    public int getReps() {
        return reps;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public String getDifficulty() {
        return difficulty;
    }

    public void addExcercise(){
        System.out.print("Introduzca nombre ejercicio: ");
        String excerciseName = input.nextLine();

        System.out.print("Introduzca cantidad de calorias quemadas: ");
        int burntCalories = input.nextInt();
        input.nextLine();

        System.out.print("Introduzca cantidad de repeticiones: ");
        int reps = input.nextInt();
        input.nextLine();

        System.out.print("Introduzca dificultad del ejercicio: ");
        String difficulty = input.nextLine();

        Excersice newExcersice = new Excersice(excerciseName, burntCalories, reps, difficulty);

        excercisesList.add(newExcersice);
    }
    public void printExcercises(){
        for(Excersice excersice : excercisesList){
            System.out.printf("Nombre: %s\nRepeticiones: %d\nCalorias quemadas: %d\nDificultad: %s\n\n",
                    excersice.getExcerciseName(), excersice.getReps(), excersice.getBurntCalories(), excersice.getDifficulty());
        }
    }

    public void readData(){
        try{
            BufferedReader data = new BufferedReader(new FileReader(file));

            String line;
            String[] fields = new String[4];

            while((line = data.readLine()) != null){
                fields = line.split(",");
                excercisesList.add(new Excersice(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), fields[3]));
            }

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException e){}
    }
}
