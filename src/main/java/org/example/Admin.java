package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.opencsv.exceptions.CsvValidationException;
public class Admin {
    private String name;
    private String password;

    public Admin(String name, String password) {
        name = this.name;
        password = this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public String getPassWord() {
        return password;
    }

    public void showReports() {

    }
}
