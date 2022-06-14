package lt.viko.eif.vvilimaite.project;

import lt.viko.eif.vvilimaite.project.constructor.Film;
import lt.viko.eif.vvilimaite.project.constructor.FilmDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static protected ArrayList<Film> films;

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        /**
         * Get random number from array, will be good random answer. To avoid repeating answer with the same choice
         */
        Integer goodAnswer = 0 + (int) (Math.random() * 4);
        boolean exit = true;
        int score = 0, round = 0;
        /**
         * Fetch all found movie data
         */
        while (exit) {
            System.out.println("Wait..........");
            getFilms();
            round++;
            System.out.println("?????????????????? END GAME PRESS 0 ?????????????????????");
            System.out.println("??????????????????Guess what movie is ?????????????????????");
            System.out.println(films.get(goodAnswer).getImgUrl());
            for (int i = 0; i < 4; i++) {
                System.out.println(i + 1 + "- " + films.get(i).getFilmName());
            }
            System.out.println("Enter your answer");
            int choice = sc.nextInt();
            if (choice == 0) {
                System.out.println("Score: " + (round - 1) + "/" + score);
                exit = false;
            } else if (films.get(goodAnswer).getFilmName().equals(films.get(choice - 1).getFilmName())) {
                System.out.println("Good answer ");
                score++;
            } else {
                System.out.println("Bad choice, the answer is: " + films.get(goodAnswer).getFilmName());

            }
        }
    }

    public static void getFilms() throws IOException, InterruptedException {
        films = new ArrayList<>();
        /**
         * Responsible to generate random number to find film by specific id (example: tt0944947) from server
         */
        Random rand = new Random();
        Integer randomNumber;
        String code;
        while (films.size() != 4) {
            randomNumber = rand.nextInt(1000000);
            code = "tt0" + randomNumber;
            Film film = FilmDao.getFilmData(code);
            if (film != null) {
                films.add(FilmDao.getFilmData(code));
            }

        }
    }
}
