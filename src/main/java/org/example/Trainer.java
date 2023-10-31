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
    public Trainer(String name, String password, String email, String gender, int age, int ID, String specialty) {
        super(name, password, email, gender, age, ID);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    public ArrayList<Trainer> getTrainersList() {
        return trainersList;
    }

    public void addTrainer(){
        System.out.print("Introduzca su nombre: ");
        String name = input.nextLine();
        System.out.print("Introduzca su contraseña: ");
        String pass = input.nextLine();
        System.out.print("Introduzca su email: ");
        String email = input.nextLine();
        System.out.print("Introduzca su genero: ");
        String gender = input.nextLine();
        System.out.print("Introduzca su edad: ");
        int age = input.nextInt();
        input.nextLine();
        System.out.print("Introduzca su especialidad: ");
        String specialty = input.nextLine();

        int ID = lastID + 1;
        lastID = ID;

        Trainer newTrainer = new Trainer(name, pass, email, gender, age, ID, specialty);
        addTrainer(newTrainer);
        trainersList.add(newTrainer);
    }

    public void addTrainer(Trainer newTrainer){
        File file = new File(ruta);
        try{
            FileWriter output = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(output);

            String[] trainerData = {newTrainer.getName(), newTrainer.getPassword(), newTrainer.getEmail(), newTrainer.getGender(),
                    String.valueOf(newTrainer.getAge()), String.valueOf(newTrainer.getID()), newTrainer.getSpecialty()};
            writer.writeNext(trainerData);

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
                String[] trainerData = {trainer.getName(), trainer.getPassword(), trainer.getEmail(), trainer.getGender(),
                        String.valueOf(trainer.getAge()), String.valueOf(trainer.getID()), trainer.getSpecialty()};
                writer.writeNext(trainerData);
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Trainer searchTrainer(int ID){
        for(Trainer trainer : trainersList){
            if(trainer.getID() == ID){
                return trainer;
            }
        }
        return null;
    }
    public void deleteTrainer(int ID){
        Trainer trainerToDelete = searchTrainer(ID);

        if(trainerToDelete != null){
            trainersList.remove(trainerToDelete);
            rewriteCSV();
            System.out.println("Entrenador eliminado correctamente\n");
        }
        else{
            System.out.println("Entrenador no encontrado\n");
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
                trainersList.add(new Trainer(fields[0], fields[1], fields[2], fields[3], Integer.parseInt(fields[4]),
                        Integer.parseInt(fields[5]), fields[6]));
            }
            reader.close();
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException e){ e.printStackTrace(); }
    }

    public void modifyTrainer(int trainerID){
        Trainer trainerToModify = searchTrainer(trainerID);

        if(trainerToModify == null){
            System.out.println("Usuario no encontrado\n");
            return;
        }

        System.out.println("¿Qué dato desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Contraseña");
        System.out.println("3. Email");
        System.out.println("4. Género");
        System.out.println("5. Edad");

        int choice = input.nextInt();
        input.nextLine();

        switch(choice){
            case 1:
                System.out.print("Nuevo nombre: ");
                trainerToModify.setName(input.nextLine());
                break;
            case 2:
                System.out.print("Nueva contraseña: ");
                trainerToModify.setPassword(input.nextLine());
                break;
            case 3:
                System.out.println("Nuevo email: ");
                trainerToModify.setEmail(input.nextLine());
                break;
            case 4:
                System.out.print("Nuevo género: ");
                trainerToModify.setGender(input.nextLine());
                break;
            case 5:
                System.out.print("Nueva edad: ");
                trainerToModify.setAge(input.nextInt());
                input.nextLine();
                break;
            case 6:
                System.out.print("Nueva especialidad: ");
                trainerToModify.setSpecialty(input.nextLine());
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        rewriteCSV();
    }

}
