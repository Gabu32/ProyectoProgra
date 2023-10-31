package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import static java.lang.Integer.parseInt;

public class Excersice {
    private int id;
    private String excerciseName;
    private int burntCalories;
    private int reps;
    private String difficulty; //beginner, intermediate, expert
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
    public Excersice(String excerciseName, int cal, int reps, String difficulty,int id){
        this.excerciseName = excerciseName;
        this.burntCalories = cal;
        this.reps = reps;
        this.difficulty = difficulty;
        this.id= id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        System.out.print("Introduzca id del ejercicio: ");
        int id= input.nextInt();
        input.nextLine();
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

        Excersice newExcersice = new Excersice(excerciseName, burntCalories, reps, difficulty,id);

        excercisesList.add(newExcersice);
    }

<<<<<<< Updated upstream:src/main/java/org/example/Excersice.java

=======
    public void addExcercise(Excercise newExcersice){
        File file = new File(ruta);

        try{
            FileWriter output = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(output);

            String[] excerciseData = {newExcersice.getExcerciseName(), String.valueOf(newExcersice.getBurntCalories()),
                    String.valueOf(newExcersice.getReps()), newExcersice.getDifficulty(), String.valueOf(newExcersice.getID())};
            writer.writeNext(excerciseData);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> Stashed changes:src/main/java/org/example/Excercise.java
    public void rewriteCSV() {
        File file = new File(ruta);
        try {
            FileWriter output = new FileWriter(file);
            CSVWriter writer = new CSVWriter(output);

<<<<<<< Updated upstream:src/main/java/org/example/Excersice.java
            for (Excersice excersice : excercisesList) {
                String[] userData = {excersice.getExcerciseName(), String.valueOf(excersice.getBurntCalories()), String.valueOf(excersice.getReps()),excersice.getDifficulty(),String.valueOf(excersice.getId())};
                writer.writeNext(userData);
=======
            for (Excercise excersice : excercisesList) {
                String[] excerciseData = {excersice.getExcerciseName(), String.valueOf(excersice.getBurntCalories()),
                        String.valueOf(excersice.getReps()), excersice.getDifficulty(), String.valueOf(excersice.getID())};
                writer.writeNext(excerciseData);
>>>>>>> Stashed changes:src/main/java/org/example/Excercise.java
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
<<<<<<< Updated upstream:src/main/java/org/example/Excersice.java
    public Excersice searchExcersice(int exID){
        for(Excersice excersice : excercisesList){
            if(excersice.getId() == exID){
=======
    public Excercise searchExcercise(int ID){
        for(Excercise excersice : excercisesList){
            if(excersice.getID() == ID){
>>>>>>> Stashed changes:src/main/java/org/example/Excercise.java
                return excersice;
            }
        }
        return null;
    }
<<<<<<< Updated upstream:src/main/java/org/example/Excersice.java
    public void deleteExcersice(int exID){
        Excersice excersiceToDelete = searchExcersice(exID);
=======
    public void deleteExcercise(int ID){
        Excercise excersiceToDelete = searchExcercise(ID);
>>>>>>> Stashed changes:src/main/java/org/example/Excercise.java

        if(excersiceToDelete != null){
            excercisesList.remove(excersiceToDelete);
            rewriteCSV();
            System.out.println("Ejercicio eliminado correctamente\n");
        }
        else{
            System.out.println("Ejercicio no encontrado\n");
        }
    }
    public void printExcercises(){
<<<<<<< Updated upstream:src/main/java/org/example/Excersice.java
        for(Excersice excersice : excercisesList){
            System.out.printf("Nombre: %s\nRepeticiones: %d\nCalorias quemadas: %d\nDificultad: %s\n\n",
                    excersice.getExcerciseName(), excersice.getReps(), excersice.getBurntCalories(), excersice.getDifficulty());
=======
        for(Excercise excersice : excercisesList){
            System.out.printf("ID: %d\nNombre: %s\nRepeticiones: %d\nCalorias quemadas: %d\nDificultad: %s\n\n",
                    excersice.getID(), excersice.getExcerciseName(), excersice.getReps(), excersice.getBurntCalories(), excersice.getDifficulty());
>>>>>>> Stashed changes:src/main/java/org/example/Excercise.java
        }
    }

    public void readData() throws CsvValidationException{
        File file = new File(this.ruta);
        try{
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] fields;

            while((fields = reader.readNext()) != null){
                excercisesList.add(new Excersice(fields[0], parseInt(fields[1]), parseInt(fields[2]), fields[3],parseInt(fields[4])));
            }

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException e){ e.printStackTrace(); }
    }
}
