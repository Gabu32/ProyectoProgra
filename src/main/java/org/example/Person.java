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
    private String email;
    private String gender;
    private int age;

    public Person(){

    }
    public Person(String name, String password, String email, String gender, int age, int ID){
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public void printData(){
        System.out.println();
        System.out.printf("ID %d\nNombre: %s\nConstraseña: %s\nEmail: %s\nGenero: %s\nEdad: %s\n",
                    getID(), getName(), getPassword(), getEmail(), getGender(), getAge());
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

}
