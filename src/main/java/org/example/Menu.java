package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {
    private User users;
    private Trainer trainers;
    private Excercise excersices;
    private Training trainings;
    Scanner input = new Scanner(System.in);

    public Menu(){
        users = new User();
        trainers = new Trainer();
        excersices = new Excercise();
        trainings = new Training();

        loginForm loginModule = new loginForm(null, users, trainers);
    }

    public int getUserChoice(String mensaje){
        boolean inputValid = false;
        int choice = -1;

        while (!inputValid) {
            try {
                System.out.print(mensaje);
                choice = input.nextInt();
                System.out.println("\n");
                inputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número válido.");
                input.nextLine();
            }
        }
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
                userMenu(getUserChoice("Ingrese su opción: "));
                break;

            case 2:
                trainerMenu();
                trainerMenu(getUserChoice("Ingrese su opción: "));
                break;

            case 3:
                excerciseMenu();
                excerciseMenu(getUserChoice("Ingrese su opción: "));
                break;

            case 4:
                trainingMenu();
                trainingMenu(getUserChoice("Ingrese su opción: "));
                break;

            case 0:
                System.out.println("Cerrando programa");
                System.exit(0);

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }

    public void userMenu(){
        System.out.println("1. Agregar un usuario");
        System.out.println("2. Mostrar lista usuarios");
        System.out.println("3. Eliminar un usuario");
        System.out.println("4. Modificar datos de un usuario");
        System.out.println("0. Volver a menu principal\n");
    }

    public void userMenu(int choice){
        switch(choice){
            case 1:
                users.addUser();
                System.out.println("\n");
                break;

            case 2:
                users.printUsers();
                System.out.println("\n");
                break;

            case 3:
                users.deleteUser(getUserChoice("Ingrese ID usuario a eliminar: "));
                System.out.println("\n");
                break;

            case 4:
                users.modifyUser(getUserChoice("Ingrese ID usuario a modificar: "));
                System.out.println("\n");
                break;

            case 0:
                return;

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }

    public void trainerMenu(){
        System.out.println("1. Agregar un entrenador");
        System.out.println("2. Mostrar lista entrenadores");
        System.out.println("3. Eliminar un entrenador");
        System.out.println("4. Modificar datos de un entrenador");
        System.out.println("0. Volver a menu principal\n");
    }

    public void trainerMenu(int choice){
        switch(choice){
            case 1:
                trainers.addTrainer();
                System.out.println("\n");
                break;

            case 2:
                trainers.printTrainers();
                System.out.println("\n");
                break;

            case 3:
                trainers.deleteTrainer(getUserChoice("Ingrese ID entrenador a eliminar: "));
                System.out.println("\n");
                break;

            case 4:
                trainers.modifyTrainer(getUserChoice("Ingrese ID entrenador a modificar: "));
                System.out.println("\n");
                break;

            case 0:
                return;

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }

    public void excerciseMenu(){
        System.out.println("1. Agregar un ejercicio");
        System.out.println("2. Mostrar lista ejercicios");
        System.out.println("3. Eliminar un ejercicio");
        System.out.println("4. Modificar datos de un ejercicio");
        System.out.println("0. Volver al menu principal\n");
    }

    public void excerciseMenu(int choice){
        switch(choice){
            case 1:
                excersices.addExcercise();
                System.out.println("\n");
                break;

            case 2:
                excersices.printExcercises();
                System.out.println("\n");
                break;

            case 3:
                excersices.deleteExcercise(getUserChoice("Ingrese ID ejercicio a eliminar: "));
                System.out.println("\n");
                break;

            case 4:
                excersices.modifyExcercise(getUserChoice("Ingrese ID ejercicio a modificar: "));
                System.out.println("\n");
                break;

            case 0:
                return;

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }

    public void trainingMenu(){
        System.out.println("1. Agregar un entrenamiento");
        System.out.println("2. Mostrar lista entrenamientos");
        System.out.println("3. Eliminar un entrenamiento");
        System.out.println("4. Modificar datos de un entrenamiento");
        System.out.println("0. Volver al menu principal\n");
    }

    public void trainingMenu(int choice){
        switch(choice){
            case 1:
                trainings.addTraining();
                System.out.println("\n");
                break;

            case 2:
                trainings.printTrainings();
                System.out.println("\n");
                break;

            case 3:
                trainings.deleteTraining(getUserChoice("Ingrese ID entrenamiento a eliminar: "));
                System.out.println("\n");
                break;

            case 4:
                trainings.modifyTraining(getUserChoice("Ingrese ID entrenamiento a modificar: "));
                System.out.println("\n");
                break;

            case 0:
                return;

            default:
                System.out.println("Opción no válida. Por favor, elija una opción válida.\n");
        }
    }
}
