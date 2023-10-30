package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.text.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Person {
    private int ID;
    private String name;
    private String password;
    private String gender;
    private int age;
    private double weight; //kilogramos
    private double height; //metros
    private double bmi;

    public Person(){

    }
    public Person(String name, String password, String gender, int age, double weight, double height, int ID){
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        if(height != 0) { this.bmi = weight / (height*height); }
    }

    public void printData(){
        System.out.println();
        System.out.printf("Nombre: %s\nConstraseña: %s\nGenero: %s\nEdad: %s\nPeso: %.1f (kg)  Altura: %.1f (m)  IMC: %.2f\n",
                    getName(), getPassword(), getGender(), getAge(), getWeight(), getHeight(), getBmi());
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void  setBmi(int weight, double height){
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
}
