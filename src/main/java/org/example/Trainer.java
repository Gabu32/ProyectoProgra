package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Trainer extends Person{
    private String specialty;
    private Scanner input = new Scanner(System.in);
    private ArrayList<Trainer> trainersList = new ArrayList<>();
    public String ruta = "datos/datosEntrenadores.csv";

    public Trainer(){
        try {
            this.readData2();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public Trainer(String name, String password, String gender, int age, double weight, double height, String specialty) {
        super(name, password, gender, age, weight, height);
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
        System.out.print("Introduzca su peso (en kilogramos): ");
        double weight = input.nextDouble();
        input.nextLine();
        System.out.print("Introduzca su estatura (en metros): ");
        double height = input.nextDouble();
        input.nextLine();
        System.out.print("Introduzca su especialidad: ");
        String specialty = input.nextLine();

        Trainer newTrainer = new Trainer(name, pass, gender, age, weight, height, specialty);

        trainersList.add(newTrainer);
    }
    public void printTrainers(){
        System.out.println();
        for(Trainer trainer : trainersList){
            System.out.printf("Nombre: %s\nConstraseña: %s\nGenero: %s\nEdad: %s\nPeso: %.1f (kg)  Altura: %.1f (m)  IMC: %.2f\nEspecialidad: %s\n",
                    trainer.getName(), trainer.getPassword(), trainer.getGender(), trainer.getAge(), trainer.getWeight(), trainer.getHeight(),
                    trainer.getBmi(), trainer.getSpecialty());
            //System.out.println("Entrenamientos: "+a.trainings);
            System.out.println();
        }
    }

    public void readData2() throws CsvValidationException {
        File file = new File(this.ruta);
        try{
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] fields;

            while((fields = reader.readNext()) != null){
                trainersList.add(new Trainer(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]),
                        Double.parseDouble(fields[4]), Double.parseDouble(fields[5]), fields[6]));
            }

        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException e){ e.printStackTrace(); }
    }

}
