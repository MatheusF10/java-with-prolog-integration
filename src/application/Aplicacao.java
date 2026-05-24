package application;

import java.util.Scanner;

/**
 * Main application entry-point class.
 */
public class Aplicacao {
    public static void main(String[] args) {
        // Opens the resource scanner at the application entry point
        Scanner scanner = new Scanner(System.in);
        
        // Initializes the engine bound to the core rules file (which handles the untouched DB)
        IntegradorProlog engine = new IntegradorProlog("motor_inferencia.pl");
        
        // Triggers the UI Control Loop
        Menu ui = new Menu(engine, scanner);
        // Start menu UI
        ui.exibir();
    }
}