package org.example;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Trainer extends Person{
    private String specialty;
    private Scanner input = new Scanner(System.in);
    private ArrayList<Trainer> trainersList = new ArrayList<>();
    private String ruta = "datos/datosEntrenadores.csv";
    private int lastID;

    public Trainer(){
        try {
            this.readData();
            lastID = trainersList.get(trainersList.size()-1).getID();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public Trainer(String name, String password, String gender, int age, int ID, String specialty) {
        super(name, password, gender, age, ID);
        this.specialty=specialty;
    }

    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void addTrainer(){
        System.out.print("Introduzca su nombre: ");
        String name = input.nextLine();
        System.out.print("Introduzca su contraseña: ");
        String pass = input.nextLine();
        System.out.print("Introduzca su genero: ");
        String gender = input.nextLine();
        System.out.print("Introduzca su edad: ");
        int age = input.nextInt();
        input.nextLine();
        System.out.print("Introduzca su especialidad: ");
        String specialty = input.nextLine();

        int ID = lastID + 1;
        lastID = ID;

        Trainer newTrainer = new Trainer(name, pass, gender, age, ID, specialty);
        addTrainer(newTrainer);
        trainersList.add(newTrainer);
    }

    public void addTrainer(Trainer newTrainer){
        File file = new File(ruta);
        try{
            FileWriter output = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(output);

            String[] userData = {newTrainer.getName(), newTrainer.getPassword(), newTrainer.getGender(), String.valueOf(newTrainer.getAge()),
                    String.valueOf(newTrainer.getID()), newTrainer.getSpecialty()};
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

            for (Trainer trainer : trainersList) {
                String[] userData = {trainer.getName(), trainer.getPassword(), trainer.getGender(), String.valueOf(trainer.getAge()),
                        String.valueOf(trainer.getSpecialty())};
                writer.writeNext(userData);
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Trainer searchTrainer(int userID){
        for(Trainer trainer : trainersList){
            if(trainer.getID() == userID){
                return trainer;
            }
        }
        return null;
    }
    public void deleteTrainer(int userID){
        Trainer trainerToDelete = searchTrainer(userID);

        if(trainerToDelete != null){
            trainersList.remove(trainerToDelete);
            rewriteCSV();
            System.out.println("Usuario eliminado correctamente\n");
        }
        else{
            System.out.println("Usuario no encontrado\n");
        }
    }
    public void printTrainers(){
        System.out.println();
        for(Trainer trainer : trainersList){
            trainer.printData();
            System.out.printf("Especialidad: %s\n", trainer.getSpecialty());
            //System.out.println("Entrenamientos: "+a.trainings);
            System.out.println();
        }
    }

    public void readData() throws CsvValidationException {
        File file = new File(this.ruta);
        try{
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] fields;

            while((fields = reader.readNext()) != null){
                trainersList.add(new Trainer(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]),
                        Integer.parseInt(fields[4]), fields[5]));
            }
            reader.close();
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException e){ e.printStackTrace(); }
    }

}
