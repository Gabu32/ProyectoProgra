package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import static java.lang.Integer.parseInt;

public class Training {
    private int ID;
    private String trainingName;
<<<<<<< Updated upstream
    private ArrayList<Excersice> excersices;
=======
    //private ArrayList<Excercise> excercises;
>>>>>>> Stashed changes
    private ArrayList<Training> trainingsList = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    private String ruta = "datos/datosEntrenamientos.csv";
    private int lastID;

    public Training(){
        try {
            this.readData();
            lastID = trainingsList.get(trainingsList.size()-1).getID();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public Training(String trainingName, int ID){
        this.ID = ID;
        this.trainingName = trainingName;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }
    public String getTrainingName() {
        return trainingName;
    }

    public void addTraining(){
        System.out.print("Introduzca nombre entrenamiento: ");
        String trainingName = input.nextLine();

        int ID = lastID + 1;
        lastID = ID;

        Training newTraining = new Training(trainingName, ID);

        addTraining(newTraining);
        trainingsList.add(newTraining);
    }
    public void addTraining(Training newTraining){
        File file = new File(ruta);
        try{
            FileWriter output = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(output);

            String[] trainingData = {newTraining.getTrainingName(), String.valueOf(newTraining.getID())};
            writer.writeNext(trainingData);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void rewriteCSV(){
        File file = new File(ruta);

        try {
            FileWriter output = new FileWriter(file);
            CSVWriter writer = new CSVWriter(output);

            for (Training training : trainingsList) {
                String[] trainingData = {training.getTrainingName(), String.valueOf(training.getID())};
                writer.writeNext(trainingData);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Training searchTraining(int ID){
        for(Training training : trainingsList){
            if(training.getID() == ID){
                return training;
            }
        }
        return null;
    }
    public void deleteTraining(int ID){
        Training trainingToDelete = searchTraining(ID);

        if(trainingToDelete != null){
            trainingsList.remove(trainingToDelete);
            rewriteCSV();
            System.out.println("Entrenamiento eliminado correctamente\n");
        }
        else{
            System.out.println("Entrenamiento no encontrado\n");
        }
    }
    public void printTrainings(){
        for(Training training : trainingsList){
            System.out.printf("ID: %d Nombre: %s\n", training.getID(), training.getTrainingName());
        }
        System.out.println();
    }

    public void readData() throws CsvValidationException{
        File file = new File(this.ruta);
        try{
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] fields;

            while((fields = reader.readNext()) != null){
                trainingsList.add(new Training(fields[0], parseInt(fields[1])));
            }

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException e){ e.printStackTrace(); }
    }
}
