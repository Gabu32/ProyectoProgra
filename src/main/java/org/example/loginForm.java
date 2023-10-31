package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginForm extends JDialog{
    private JPanel loginPanel;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JCheckBox ingresarComoEntrenadorCheckBox;

    private User users;
    private Trainer trainers;

    public loginForm(JFrame parent, User users, Trainer trainers){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450,450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.users = users;
        this.trainers = trainers;

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textField.getText();
                String password = new String(passwordField.getPassword());
                boolean loginAsTrainer = ingresarComoEntrenadorCheckBox.isSelected();

                if (loginAsTrainer) {
                    boolean valid = getAuthenticatedTrainer(email, password);
                    if (valid) {
                        JOptionPane.showMessageDialog(loginPanel, "Inicio de sesión como entrenador exitoso.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(loginPanel, "Inicio de sesión fallido como entrenador.");
                    }
                } else {
                    boolean valid = getAuthenticatedUser(email, password);
                    if (valid) {
                        JOptionPane.showMessageDialog(loginPanel, "Inicio de sesión exitoso.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(loginPanel, "Inicio de sesión fallido.");
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private boolean getAuthenticatedUser(String email, String password){
        for(User user : users.getUsersList()){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private boolean getAuthenticatedTrainer(String email, String password){
        for(Trainer trainer : trainers.getTrainersList()){
            if(trainer.getEmail().equals(email) && trainer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
