package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.text.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class User extends Person{
    private Scanner input = new Scanner(System.in);
    private ArrayList<User> usersList = new ArrayList<>();
    private String ruta = "datos/datosUsuarios.csv";
    private int lastID;
    private double weight; //kilogramos
    private double height; //metros
    private double bmi;
    private Training currentTraining;

    // Constructores
    public User(){
        try {
            this.readData();
            lastID = usersList.get(usersList.size()-1).getID();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
    public User(String name,String password,String gender,int age,double weight,double height, int ID){
        super(name, password, gender, age, ID);
        this.weight=weight;
        this.height=height;
        if(height != 0) { this.bmi = weight / (height*height); }
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getBmi() {
        return bmi;
    }
    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
    public Training getCurrentTraining() {
        return currentTraining;
    }
    public void setCurrentTraining(Training currentTraining) {
        this.currentTraining = currentTraining;
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

        int ID = lastID + 1;
        lastID = ID;

        User newUser = new User(name, pass, gender, age, weight, height, ID);

        addUser(newUser);
        usersList.add(newUser);
    }

    public void addUser(User newUser){
        File file = new File(ruta);
        try{
            FileWriter output = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(output);

            String[] userData = {newUser.getName(), newUser.getPassword(), newUser.getGender(), String.valueOf(newUser.getAge()),
                    String.valueOf(newUser.getWeight()), String.valueOf(newUser.getHeight()), String.valueOf(newUser.getID())};
            writer.writeNext(userData);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User searchUser(int userID){
        for(User user : usersList){
            if(user.getID() == userID){
                return user;
            }
        }
        return null;
    }
    public void deleteUser(int userID){
        User userToDelete = searchUser(userID);

        if(userToDelete != null){
            usersList.remove(userToDelete);
            rewriteCSV();

            System.out.println("Usuario eliminado correctamente\n");
        }
        else{
            System.out.println("Usuario no encontrado\n");
        }
    }

    public void rewriteCSV() {
        File file = new File(ruta);
        try {
            FileWriter output = new FileWriter(file);
            CSVWriter writer = new CSVWriter(output);

            for (User user : usersList) {
                String[] userData = {user.getName(), user.getPassword(), user.getGender(), String.valueOf(user.getAge()),
                        String.valueOf(user.getWeight()), String.valueOf(user.getHeight()), String.valueOf(user.getID()) };
                writer.writeNext(userData);
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printUsers(){
        System.out.println();
        for(User user : usersList){
            user.printData();
            System.out.printf("Peso: %.1f (kg)  Altura: %.1f (m)  IMC: %.2f\n", user.getWeight(), user.getHeight(), user.getBmi());
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

            while((fields = reader.readNext()) != null) {
                usersList.add(new User(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4]),
                            Double.parseDouble(fields[5]), Integer.parseInt(fields[6])));
            }
            reader.close();
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
