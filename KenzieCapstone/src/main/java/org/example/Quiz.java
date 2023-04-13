package org.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Quiz {
    private int playerScore;
    public static final String URL = "https://jservice.kenzie.academy/api/clues";

    public void prompt () throws JsonProcessingException {
        // INTRODUCTION
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the quiz");
        System.out.println("What is your name?");
        String userName = scan.nextLine();

        System.out.println("You will be asked 10 questions, " + userName);

        // LOOP AND PRESENT QUESTIONS
        try {
            ObjectMapper mapper = new ObjectMapper();
            String www =CustomHttpClient.sendGET(URL);
            MyDTO dto = mapper.readValue(www, MyDTO.class);

            List<Clues> question = dto.getClues();
            Clues clues = new Clues();
            CustomHttpClient.getCluesList(question);

            for (Clues qwerty : question) {
                for (int i = 0; i < 10; i++) {
                    int randomIndex = (int) (Math.random() * question.size());
                    String randomElement = String.valueOf(question.get(randomIndex));
                    String answer = question.get(randomIndex).getAnswer();
                    System.out.println("Question: " + question.get(randomIndex).getQuestion() + "\nCategory: " +
                            question.get(randomIndex).getCategory().getTitle());
                    // REMOVE THE RANDOM INDEX TO PREVENT REPEAT QUESTIONS
                    question.remove(randomIndex);
                    // RECEIVE ANSWER , FORMAT IT , CHECK IF CORRECT , AND UPDATE SCORE
                    System.out.println("What is your answer?");
                    String userAnswer = scan.nextLine().trim().toLowerCase().trim();

                    if (Objects.equals(userAnswer, answer.toLowerCase())) {
                        playerScore++;
                        System.out.println("You got that one right! Your current score is: " + playerScore);
                        if (playerScore >= 8) {
                            System.out.println("You passed the quiz! Your score is: " + playerScore);
                            break;
                        }
                    } else {
                        System.out.println("The correct answer is: " +
                                question.get(randomIndex).getAnswer());
                    }
                }
                System.out.println("You did not pass the quiz. Your score is: " + playerScore);
                break;
            }
        } catch (JsonParseException e){
            e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
