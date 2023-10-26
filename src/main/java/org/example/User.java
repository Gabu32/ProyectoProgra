package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.text.*;

public class User {
    private String name;
    private String password;
    private String gender;
    private int age;
    private double weight; //kilogramos
    private double height; //metros
    private double bmi;
    //private ArrayList<Training> trainings;
    private Scanner input = new Scanner(System.in);
    private ArrayList<User> usersList = new ArrayList<>();
    public String file = "datos/datosUsuarios.txt";

    // Constructores
    public User(){
        this.readData();
    }
    public User(String name,String password,String gender,int age,double weight,double height){
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        if(height != 0) { this.bmi = weight / (height*height); }
        //this.trainings = new ArrayList<>();
    }

    // Setters y Getters
    public void  setImc(int weight, double height){
        if((height != 0) || weight != 0){
            this.bmi = weight / (height*height);
        }
    }
    public double getBmi(){
        return bmi;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPassword(String passWord){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public double getWeight(){
        return weight;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public double getHeight(){
        return height;
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
            System.out.printf("Nombre: %s\nConstraseña: %s\nGenero: %s\nEdad: %s\nPeso: %.1f (kg)  Altura: %.1f (m)  IMC: %.2f\n",
                    user.getName(), user.getPassword(), user.getGender(), user.getAge(), user.getWeight(), user.getHeight(), user.getBmi());
            //System.out.println("Entrenamientos: "+a.trainings);
            System.out.println();
        }
    }

    public void readData(){
        try{
            BufferedReader data = new BufferedReader(new FileReader(file));

            String line;
            String[] fields = new String[6];

            while((line = data.readLine())!= null){
                fields = line.split(",");
                usersList.add(new User(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4]), Double.parseDouble(fields[5])));
            }

        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }catch(IOException e){}
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
