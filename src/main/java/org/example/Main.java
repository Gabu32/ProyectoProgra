package org.example;

// TODO: 10/9/2023 Agregar try & catch en los inputs en el caso que se ingrese tipo de dato equivocado

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();

        while(true){
            menu.mainMenu();
            int choice = menu.getUserChoice("Ingrese su opción: ");
            menu.mainMenu(choice);
        }
    }
}