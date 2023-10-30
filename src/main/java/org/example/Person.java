package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.text.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Person {
    private String name;
    private String password;
    private String gender;
    private int age;
    private double weight; //kilogramos
    private double height; //metros
    private double bmi;

    public Person(){

    }
    public Person(String name, String password, String gender, int age, double weight, double height){
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        if(height != 0) { this.bmi = weight / (height*height); }
    }

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
}
