package org.example;

// import necessary libraries


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String URL = "https://jservice.kenzie.academy/api/clues";
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!

     */

    public static void main(String[] args) {
        //Write main execution code here
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
//Loop while the value of exitLoop is false
        while (!exitLoop) {

            try {
                Quiz quiz = new Quiz();
                quiz.prompt();
                System.out.println("Do you want to take another quiz? Y/N");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("N")) {
                    exitLoop = true;
                }
            } catch (IOException e) {
                e.getStackTrace();
            }

        }
    }
}

