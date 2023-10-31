package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import static java.lang.Integer.parseInt;

public class Excercise{
    private int ID;
    private String excerciseName;
    private int burntCalories;
    private int reps;
    private String difficulty; //begginer, intermediate, expert
    private Scanner input = new Scanner(System.in);
    private ArrayList<Excercise> excercisesList = new ArrayList<>();
    private String ruta = "datos/datosEjercicios.csv";
    private int lastID;
    public Excercise(){
        try {
            this.readData();
            lastID = excercisesList.get(excercisesList.size()-1).getID();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public Excercise(String excerciseName, int cal, int reps, String difficulty, int ID){
        this.excerciseName = excerciseName;
        this.burntCalories = cal;
        this.reps = reps;
        this.difficulty = difficulty;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    public void setID(int id) {
        this.ID = id;
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

        int ID = lastID + 1;
        lastID = ID;

        Excercise newExcersice = new Excercise(excerciseName, burntCalories, reps, difficulty, ID);

        addExcercise(newExcersice);
        excercisesList.add(newExcersice);
    }

    public void addExcercise(Excercise newExcersice){
        File file = new File(ruta);

        try{
            FileWriter output = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(output);

            String[] userData = {newExcersice.getExcerciseName(), String.valueOf(newExcersice.getBurntCalories()),
                    String.valueOf(newExcersice.getReps()), newExcersice.getDifficulty(), String.valueOf(newExcersice.getID())};
            writer.writeNext(userData);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void rewriteCSV() {
        File file = new File(ruta);
        try {
            FileWriter output = new FileWriter(file);
            CSVWriter writer = new CSVWriter(output);

            for (Excercise excersice : excercisesList) {
                String[] userData = {excersice.getExcerciseName(), String.valueOf(excersice.getBurntCalories()),
                        String.valueOf(excersice.getReps()), excersice.getDifficulty(), String.valueOf(excersice.getID())};
                writer.writeNext(userData);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Excercise searchExcercise(int exID){
        for(Excercise excersice : excercisesList){
            if(excersice.getID() == exID){
                return excersice;
            }
        }
        return null;
    }
    public void deleteExcercise(int exID){
        Excercise excersiceToDelete = searchExcercise(exID);

        if(excersiceToDelete != null){
            excercisesList.remove(excersiceToDelete);
            rewriteCSV();
            System.out.println("Usuario eliminado correctamente\n");
        }
        else{
            System.out.println("Usuario no encontrado\n");
        }
    }
    public void printExcercises(){
        for(Excercise excersice : excercisesList){
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
                excercisesList.add(new Excercise(fields[0], parseInt(fields[1]), parseInt(fields[2]), fields[3], parseInt(fields[4])));
            }

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException e){ e.printStackTrace(); }
    }
}
