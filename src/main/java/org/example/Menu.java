package org.example;

import java.util.Scanner;
public class Menu {
    private User users;
    private Trainer trainers;
    private Excersice excersices;
    private Training trainings;
    Scanner input = new Scanner(System.in);

    public Menu(){
        users = new User();
        trainers = new Trainer();
        excersices = new Excersice();
        trainings = new Training();
    }

    public int getUserChoice(){
        System.out.print("Ingrese su opción: ");
        int choice = input.nextInt();
        System.out.println("\n");
        return choice;
    }

    public void mainMenu(){
        System.out.println("1. Menu usuarios");
        System.out.println("2. Menu entrenadores");
        System.out.println("3. Menu ejercicios");
        System.out.println("4. Menu entrenamientos");
        System.out.println("0. Cerrar programa\n");
    }

    public void mainMenu(int choice){
        switch(choice){
            case 1:
                userMenu();
                userMenu(getUserChoice());
                break;

            case 2:
                trainerMenu();
                trainerMenu(getUserChoice());
                break;

            case 3:
                excerciseMenu();
                excerciseMenu(getUserChoice());
                break;

            case 4:
                trainingMenu();
                trainingMenu(getUserChoice());
                break;

            case 0:
                System.out.println("Cerrando programa");
                System.exit(0);

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }

    public void userMenu(){
        System.out.println("1. Agregar usuario");
        System.out.println("2. Mostrar lista usuarios");
        System.out.println("0. Volver a menu principal\n");
    }

    public void userMenu(int choice){
        switch(choice){
            case 1:
                users.addUser();
                System.out.println("\n\n");
                break;

            case 2:
                users.printUsers();
                System.out.println("\n\n");
                break;

            case 0:
                return;

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }

    public void trainerMenu(){
        System.out.println("1. Agregar entrenador");
        System.out.println("2. Mostrar lista entrenadores");
        System.out.println("0. Volver a menu principal\n");
    }

    public void trainerMenu(int choice){
        switch(choice){
            case 1:
                trainers.addTrainer();
                System.out.println("\n\n");
                break;

            case 2:
                trainers.printTrainers();
                System.out.println("\n\n");
                break;

            case 0:
                return;

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }

    public void excerciseMenu(){
        System.out.println("1. Agregar ejercicio");
        System.out.println("2. Mostrar lista ejercicios");
        System.out.println("0. Volver al menu principal\n");
    }

    public void excerciseMenu(int choice){
        switch(choice){
            case 1:
                excersices.addExcercise();
                System.out.println("\n\n");
                break;

            case 2:
                excersices.printExcercises();
                System.out.println("\n\n");
                break;

            case 0:
                return;

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }

    public void trainingMenu(){
        System.out.println("1. Agregar entrenamiento");
        System.out.println("2. Mostrar lista entrenamiento");
        System.out.println("0. Volver al menu principal\n");
    }

    public void trainingMenu(int choice){
        switch(choice){
            case 1:
                trainings.addTraining();
                System.out.println("\n\n");
                break;

            case 2:
                trainings.printTrainings();
                System.out.println("\n\n");
                break;

            case 0:
                return;

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }
}
