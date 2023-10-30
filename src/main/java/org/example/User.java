package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.text.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class User extends Person{
    //private ArrayList<Training> trainings;
    private Scanner input = new Scanner(System.in);
    private ArrayList<User> usersList = new ArrayList<>();
    public String ruta = "datos/datosUsuarios.csv";

    // Constructores
    public User(){
        try {
            this.readData();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public User(String name,String password,String gender,int age,double weight,double height){
        super(name, password, gender, age, weight, height);
    }

    public void addUser(){
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

        User newUser = new User(name, pass, gender, age, weight, height);

        usersList.add(newUser);
    }
    public void printUsers(){
        System.out.println();
        for(User user : usersList){
            user.print();
            //System.out.println("Entrenamientos: "+a.trainings);
            System.out.println();
        }
    }

    public void readData() throws CsvValidationException{
        File file = new File(this.ruta);
        try{
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] fields;

            while((fields = reader.readNext()) != null){
                usersList.add(new User(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4]), Double.parseDouble(fields[5])));
            }

        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException e){ e.printStackTrace(); }
    }

    //Metodos

    /*
    public void chooseTraining(){

    }
    public void changeTraining(){

    }
    public void showTraining(){

    }
    */
}
