package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class Training {
    private String trainingName;
    private ArrayList<Excercise> excercises;
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
    public Training(String trainingName){
        this.trainingName = trainingName;
    }
    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }
    public String getTrainingName() {
        return trainingName;
    }

    public void addTraining(){
        System.out.print("Introduzca nombre entrenamiento: ");
        String excersiceName = input.nextLine();

        Training newTraining = new Training(excersiceName);
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
                trainingsList.add(new Training(fields[0]));
            }

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException e){ e.printStackTrace(); }
    }
}
