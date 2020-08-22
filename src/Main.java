package readability;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Main {

   static File file = new File("src/words.txt");
   static int polySyllables = 0;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner reader = new Scanner(System.in);

        System.out.println("Words: " + numberOfWords());
        System.out.println("Sentences: " + numberOfSentences());
        System.out.println("Characters: " + numberOfCharacters());
        System.out.println("Syllables: " + numberOfSyllables());
        System.out.println("Polysyllables: " + polySyllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        String userInput = reader.nextLine();
        System.out.println("");

        switch (userInput) {
            case "ARI":
                System.out.println("Automated Readability Index: " + score() + " " + ageGroup(score()));
                break;
            case "FK":
                System.out.println("Flesch–Kincaid readability tests: " + fleshKincaidTest() + " " + ageGroup(fleshKincaidTest()));
                break;
            case "SMOG":
                System.out.println("Simple Measure of Gobbledygook: " + smogTest() + " " + ageGroup(smogTest()));
                break;
            case "CL":
                System.out.println("Coleman–Liau index: " + colemanIndex() + " " + ageGroup(colemanIndex()));
                break;
            case "all":
                System.out.println("Automated Readability Index: " + score() + " " + ageGroup(score()));
                System.out.println("Simple Measure of Gobbledygook: " + smogTest() + " " + ageGroup(smogTest()));
                System.out.println("Flesch–Kincaid readability tests: " + fleshKincaidTest() + " " + ageGroup(fleshKincaidTest()));
                System.out.println("Coleman–Liau index: " + colemanIndex() + " " + ageGroup(colemanIndex()));
        }

    }

    public static int numberOfWords() throws FileNotFoundException {

        Scanner reader = new Scanner(file);
        int numberOfWords = 0;

        while (reader.hasNext()) {
            reader.next();
            numberOfWords++;
        }

        reader.close();
        return numberOfWords;
    }

    public static int numberOfSentences() throws FileNotFoundException {

        Scanner reader = new Scanner(file);
        StringBuilder sentences = new StringBuilder();

        while (reader.hasNextLine()) {
            sentences.append(reader.nextLine());
        }

        String[] sentenceSplit = sentences.toString().split("[.!?]");
        reader.close();
        return sentenceSplit.length;
    }

    public static int numberOfCharacters() throws FileNotFoundException {

        Scanner reader = new Scanner(file);
        StringBuilder sentences = new StringBuilder();

        while (reader.hasNextLine()) {
            sentences.append(reader.nextLine());
        }

        String[] visibleSymbols = sentences.toString().split("[\\s]");
        int numberOfCharacters = 0;

        for (String element : visibleSymbols) {
            numberOfCharacters += element.length();
        }

        reader.close();
        return numberOfCharacters;
    }

    public static int numberOfSyllables() throws FileNotFoundException {

        Scanner reader = new Scanner(file);
        StringBuilder combinedWords = new StringBuilder();
        int numberOfSyllables = 0;

        while (reader.hasNextLine()) {
            combinedWords.append(reader.nextLine());
        }

        String[] wordList = combinedWords.toString().toLowerCase().split("[.!?\\s]+");

        boolean previousIsVowel = false;

        for (String element : wordList) {
            int vowelCount = 0;
            for (int i = 0; i < element.length(); i++) {

                if (i == 0) {
                    previousIsVowel = false;
                }

                if (i == element.length() - 1 && element.charAt(i) == 'e') {
                    break;
                }

                if (isVowel(element.charAt(i))) {
                    if (!previousIsVowel) {
                        vowelCount++;
                    }
                    previousIsVowel = true;
                } else {
                    previousIsVowel = false;
                }
            }
            if (vowelCount == 0) {
                vowelCount = 1;
            }

            if (vowelCount > 2) {
                polySyllables++;
            }

            numberOfSyllables += vowelCount;
        }

        reader.close();
        return numberOfSyllables;
    }

    public static boolean isVowel(char letter) {

        return letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'y';
    }

    public static double fleshKincaidTest() throws FileNotFoundException {
        return 0.39 * numberOfWords() / numberOfSentences() + 11.8 * numberOfSyllables() / numberOfWords() - 15.59;
    }

    public static double smogTest() throws FileNotFoundException {
        return 1.043 * Math.sqrt((double) polySyllables * 30 / (double) numberOfSentences()) + 3.1291;
    }

    public static double colemanIndex() throws FileNotFoundException {
        double L = ((double) numberOfCharacters() / (double) numberOfWords()) * 100;
        double S = ((double) numberOfSentences() / (double) numberOfWords()) * 100;
        return 0.0588 * L - 0.296 * S - 15.8;
    }

    public static double score() throws FileNotFoundException {

        return (4.71 * numberOfCharacters() / numberOfWords() + 0.5 * numberOfWords() / numberOfSentences() - 21.43);
    }

    public static String ageGroup(double number)  {

        double score = Math.round(number);

        if (score == 1.0) {
            return "(About 6 years old)";
        } else if (score == 2.0) {
            return "(About 7 years old)";
        } else if (score == 3.0) {
            return "(About 9 years old)";
        } else if (score == 4.0) {
            return "(About 10 years old)";
        } else if (score == 5.0) {
            return "(About 11 years old)";
        } else if (score == 6.0) {
            return "(About 12 years old)";
        } else if (score == 7.0) {
            return "(About 13 years old)";
        } else if (score == 8.0) {
            return "(About 14 years old)";
        } else if (score == 9.0) {
            return "(About 15 years old)";
        } else if (score == 10.0) {
            return "(About 16 years old)";
        } else if (score == 11.0) {
            return "(About 17 years old)";
        } else if (score == 12.0) {
            return "(About 18 years old)";
        } else if (score == 13.0) {
            return "(About 24 years old)";
        } else {
            return "(About 24+ years old)";
        }
    }
}

