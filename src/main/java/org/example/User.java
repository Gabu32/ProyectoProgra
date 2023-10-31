//TODO: Crear funcion para validar datos, retornara siempre String y se cambiara el tipo de dato segun se requiera
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
    public User(String name, String password, String email, String gender, int age, double weight, double height, int ID){
        super(name, password, email, gender, age, ID);
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
    public ArrayList<User> getUsersList() {
        return usersList;
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
        System.out.print("Introduzca su email: ");
        String email = input.nextLine();
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

        User newUser = new User(name, pass, email, gender, age, weight, height, ID);

        addUser(newUser);
        usersList.add(newUser);
    }

    public void addUser(User newUser){
        File file = new File(ruta);
        try{
            FileWriter output = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(output);

            String[] userData = {newUser.getName(), newUser.getPassword(), newUser.getEmail(), newUser.getGender(), String.valueOf(newUser.getAge()),
                    String.valueOf(newUser.getWeight()), String.valueOf(newUser.getHeight()), String.valueOf(newUser.getID())};
            writer.writeNext(userData);

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User searchUser(int ID){
        for(User user : usersList){
            if(user.getID() == ID){
                return user;
            }
        }
        return null;
    }
    public void deleteUser(int ID){
        User userToDelete = searchUser(ID);

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
                String[] userData = {user.getName(), user.getPassword(), user.getEmail(), user.getGender(), String.valueOf(user.getAge()),
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
                usersList.add(new User(fields[0], fields[1], fields[2], fields[3], Integer.parseInt(fields[4]), Double.parseDouble(fields[5]),
                            Double.parseDouble(fields[6]), Integer.parseInt(fields[7])));
            }
            reader.close();
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException e){ e.printStackTrace(); }
    }

    public void modifyUser(int ID){
        User userToModify = searchUser(ID);

        if(userToModify == null){
            System.out.println("Usuario no encontrado\n");
            return;
        }

        System.out.println("¿Qué dato desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Contraseña");
        System.out.println("3. Email");
        System.out.println("4. Género");
        System.out.println("5. Edad");
        System.out.println("6. Peso");
        System.out.println("7. Altura");

        int choice = input.nextInt();
        input.nextLine();

        switch(choice){
            case 1:
                System.out.print("Nuevo nombre: ");
                userToModify.setName(input.nextLine());
                break;
            case 2:
                System.out.print("Nueva contraseña: ");
                userToModify.setPassword(input.nextLine());
                break;
            case 3:
                System.out.println("Nuevo email: ");
                userToModify.setEmail(input.nextLine());
                break;
            case 4:
                System.out.print("Nuevo género: ");
                userToModify.setGender(input.nextLine());
                break;
            case 5:
                System.out.print("Nueva edad: ");
                userToModify.setAge(input.nextInt());
                input.nextLine();
                break;
            case 6:
                System.out.print("Nuevo peso (en kilogramos): ");
                userToModify.setWeight(input.nextDouble());
                input.nextLine();
                break;
            case 7:
                System.out.print("Nueva altura (en metros): ");
                userToModify.setHeight(input.nextDouble());
                input.nextLine();
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        rewriteCSV();
    }
}
