package org.example;

import java.util.Scanner;
public class Menu {
    private User users;
    private Excersice excersices;
    private Training trainings;
    Scanner input = new Scanner(System.in);

    public int getUserChoice(){
        System.out.print("Ingrese su opción: ");
        int choice = input.nextInt();
        System.out.println("\n");
        return choice;
    }

    public void mainMenu(){
        System.out.println("1. Menu usuarios");
        System.out.println("2. Menu ejercicios");
        System.out.println("3. Menu entrenamientos");
        System.out.println("0. Cerrar programa\n");
    }

    public void mainMenu(int choice){
        switch(choice){
            case 1:
                userMenu();
                userMenu(getUserChoice());
                break;

            case 2:
                excerciseMenu();
                excerciseMenu(getUserChoice());
                break;

            case 3:
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
        System.out.println("3. Leer datos usuarios");
        System.out.println("0. Volver a menu principal\n");
    }

    public void userMenu(int choice){
        switch(choice){
            case 1:
                if(users == null){
                    System.out.println("Primero necesita inicializar la lista\n");
                    break;
                }

                users.addUser();
                System.out.println("\n\n");
                break;

            case 2:
                if(users == null){
                    System.out.println("Primero necesita inicializar la lista\n");
                    break;
                }

                users.printUsers();
                System.out.println("\n\n");
                break;

            case 3:
                users = new User();
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
        System.out.println("3. Leer datos ejercicios");
        System.out.println("0. Volver al menu principal\n");
    }

    public void excerciseMenu(int choice){
        switch(choice){
            case 1:
                if(excersices == null){
                    System.out.println("Primero necesita inicializar la lista\n");
                    break;
                }

                excersices.addExcercise();
                System.out.println("\n\n");
                break;

            case 2:
                if(excersices == null){
                    System.out.println("Primero necesita inicializar la lista\n");
                    break;
                }

                excersices.printExcercises();
                System.out.println("\n\n");
                break;

            case 3:
                excersices = new Excersice();
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
        System.out.println("3. Leer datos entrenamiento");
        System.out.println("0. Volver al menu principal\n");
    }

    public void trainingMenu(int choice){
        switch(choice){
            case 1:
                if(trainings == null){
                    System.out.println("Primero necesita inicializar la lista\n");
                    break;
                }

                trainings.addTraining();
                System.out.println("\n\n");
                break;

            case 2:
                if(trainings == null){
                    System.out.println("Primero necesita inicializar la lista\n");
                    break;
                }

                trainings.printTrainings();
                System.out.println("\n\n");
                break;

            case 3:
                trainings = new Training();
                break;

            case 0:
                return;

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }
}
