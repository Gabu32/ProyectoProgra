package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import static java.lang.Integer.parseInt;

public class Training {
    private int trainingID;
    private String trainingName;
    private ArrayList<Excersice> excersices;
    private ArrayList<Training> trainingsList = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    private String ruta = "datos/datosEntrenamientos.csv";

    public Training(){
        try {
            this.readData();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public Training(String trainingName,int trainingID){
        this.trainingName = trainingName;
        this.trainingID=trainingID;
    }

    public int getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(int trainingID) {
        this.trainingID = trainingID;
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
        System.out.print("Introduzca id del entrenamiento: ");
        int trainingID= input.nextInt();

        Training newTraining = new Training(trainingName,trainingID);
        addTraining(newTraining);
        trainingsList.add(newTraining);
    }

    public void addTraining(Training newTraining){
        File file = new File(ruta);
        try{
            FileWriter output = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(output);

            String[] userData = {newTraining.getTrainingName()};
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

            for (Training training : trainingsList) {
                String[] trainingData = {training.getTrainingName(), String.valueOf(training.getTrainingID())};
                writer.writeNext(trainingData);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Training searchTraining(int trID){
        for(Training training : trainingsList){
            if(training.getTrainingID() == trID){
                return training;
            }
        }
        return null;
    }
    public void deleteTraining(int trID){
        Training trainingToDelete = searchTraining(trID);

        if(trainingToDelete != null){
            trainingsList.remove(trainingToDelete);
            rewriteCSV();
            System.out.println("Usuario eliminado correctamente\n");
        }
        else{
            System.out.println("Usuario no encontrado\n");
        }
    }

    public void printTrainings(){
        for(Training training : trainingsList){
            System.out.print(training.getTrainingName() + " ");
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
                trainingsList.add(new Training(fields[0],parseInt(fields[1])));
            }

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException e){ e.printStackTrace(); }
    }
}
