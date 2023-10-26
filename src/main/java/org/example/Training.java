package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Training {
    private String trainingName;
    private ArrayList<Excersice> excersices;
    private ArrayList<Training> trainingsList = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    private String file = "datos/datosEntrenamientos.txt";

    public Training(){
        this.readData();
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

        trainingsList.add(newTraining);
    }

    public void printTrainings(){
        for(Training training : trainingsList){
            System.out.print(training.getTrainingName() + " ");
        }
        System.out.println();
    }

    public void readData(){
        try{
            BufferedReader data = new BufferedReader(new FileReader(file));

            String line;
            String[] fields = new String[1];

            while((line = data.readLine()) != null){
                fields = line.split(",");
                trainingsList.add(new Training(fields[0]));
            }

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException e){}
    }
}
