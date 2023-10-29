package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Excersice {
    private String excerciseName;
    private int burntCalories;
    private int reps;
    private String difficulty; //begginer, intermediate, expert
    private Scanner input = new Scanner(System.in);
    private ArrayList<Excersice> excercisesList = new ArrayList<>();
    private String ruta = "datos/datosEjercicios.csv";

    public Excersice(){
        try {
            this.readData();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
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

    public void readData() throws CsvValidationException{
        File file = new File(this.ruta);
        try{
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] fields;

            while((fields = reader.readNext()) != null){
                excercisesList.add(new Excersice(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), fields[3]));
            }

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException e){ e.printStackTrace(); }
    }
}
