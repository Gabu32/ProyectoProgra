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
    private String difficulty; //beginner, intermediate, expert
    private Scanner input = new Scanner(System.in);
    private ArrayList<Excercise> excercisesList = new ArrayList<>();
    private String ruta = "datos/datosEjercicios.csv";
    private int lastID;

    public Excercise(){
        try {
            this.readData();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public Excercise(String excerciseName, int cal, int reps, String difficulty,int ID){
        this.excerciseName = excerciseName;
        this.burntCalories = cal;
        this.reps = reps;
        this.difficulty = difficulty;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
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

        Excercise newExcercise = new Excercise(excerciseName, burntCalories, reps, difficulty, ID);

        addExcercise(newExcercise);
        excercisesList.add(newExcercise);
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

    public Excercise searchExcercise(int ID){
        for(Excercise excersice : excercisesList){
            if(excersice.getID() == ID){
                return excersice;
            }
        }
        return null;
    }

    public void deleteExcercise(int ID){
        Excercise excerciseToDelete = searchExcercise(ID);

        if(excerciseToDelete != null){
            excercisesList.remove(excerciseToDelete);
            rewriteCSV();
            System.out.println("Ejercicio eliminado correctamente\n");
        }
        else{
            System.out.println("Ejercicio no encontrado\n");
        }
    }
    public void printExcercises(){
        for(Excercise excercise : excercisesList){
            System.out.printf("ID: %d\nNombre: %s\nRepeticiones: %d\nCalorias quemadas: %d\nDificultad: %s\n\n",
                    excercise.getID(), excercise.getExcerciseName(), excercise.getReps(), excercise.getBurntCalories(), excercise.getDifficulty());
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

    public void modifyExcercise(int ID){
        Excercise excerciseToModify = searchExcercise(ID);

        if(excerciseToModify == null){
            System.out.println("Ejercicio no encontrado");
            return;
        }

        System.out.println("¿Qué dato desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Repeticiones");
        System.out.println("3. Calorias quemadas");
        System.out.println("4. Dificultad");

        int choice = input.nextInt();
        input.nextLine();

        switch(choice) {
            case 1:
                System.out.print("Nuevo nombre: ");
                excerciseToModify.setExcerciseName(input.nextLine());
                break;
            case 2:
                System.out.print("Nueva contraseña: ");
                excerciseToModify.setReps(input.nextInt());
                input.nextLine();
                break;
            case 3:
                System.out.println("Nuevo email: ");
                excerciseToModify.setBurntCalories(input.nextInt());
                input.nextLine();
                break;
            case 4:
                System.out.print("Nuevo género: ");
                excerciseToModify.setDifficulty(input.nextLine());
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        rewriteCSV();
    }
}
