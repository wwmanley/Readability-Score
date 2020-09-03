import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;

public class Main {

   static File file = new File("./words.txt");
   public static int polySyllables = 0;

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        DecimalFormat df2 = new DecimalFormat("#.##");

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
                System.out.println("Automated Readability Index: " + df2.format(score()) + " " + ageGroup(score()));
                break;
            case "FK":
                System.out.println("Flesch–Kincaid readability tests: " + df2.format(fleshKincaidTest()) + " " + ageGroup(fleshKincaidTest()));
                break;
            case "SMOG":
                System.out.println("Simple Measure of Gobbledygook: " + df2.format(smogTest()) + " " + ageGroup(smogTest()));
                break;
            case "CL":
                System.out.println("Coleman–Liau index: " + df2.format(colemanIndex()) + " " + ageGroup(colemanIndex()));
                break;
            case "all":
                System.out.println("Automated Readability Index: " + df2.format(score()) + " " + ageGroup(score()));
                System.out.println("Simple Measure of Gobbledygook: " + df2.format(smogTest()) + " " + ageGroup(smogTest()));
                System.out.println("Flesch–Kincaid readability tests: " + df2.format(fleshKincaidTest()) + " " + ageGroup(fleshKincaidTest()));
                System.out.println("Coleman–Liau index: " + df2.format(colemanIndex()) + " " + ageGroup(colemanIndex()));
        }

    }

    public static Scanner createScanner() {

        Scanner reader = null;

        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        return reader;
    }

    public static int numberOfWords() {

        Scanner reader = createScanner();
        int numberOfWords = 0;

        while (reader.hasNext()) {
            reader.next();
            numberOfWords++;
        }

        reader.close();
        return numberOfWords;
    }

    public static int numberOfSentences() {

        Scanner reader = createScanner();
        StringBuilder sentences = new StringBuilder();

        while (reader.hasNextLine()) {
            sentences.append(reader.nextLine());
        }

        String[] sentenceSplit = sentences.toString().split("[.!?]");
        reader.close();
        return sentenceSplit.length;
    }

    public static int numberOfCharacters() {

        Scanner reader = createScanner();
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

    public static int numberOfSyllables() {

        Scanner reader = createScanner();
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

    public static double fleshKincaidTest() {
        return 0.39 * numberOfWords() / numberOfSentences() + 11.8 * numberOfSyllables() / numberOfWords() - 15.59; // Provided formula for Flesh Kincaid Test
    }

    public static double smogTest() {
        return 1.043 * Math.sqrt((double) polySyllables * 30 / (double) numberOfSentences()) + 3.1291; // Provided formula for SMOG Test
    }

    public static double colemanIndex() {
        double L = ((double) numberOfCharacters() / (double) numberOfWords()) * 100;
        double S = ((double) numberOfSentences() / (double) numberOfWords()) * 100;
        return 0.0588 * L - 0.296 * S - 15.8; // Provided formula for Coleman Index
    }

    public static double score() {

        return (4.71 * numberOfCharacters() / numberOfWords() + 0.5 * numberOfWords() / numberOfSentences() - 21.43);
    }

    public static String ageGroup(double number)  {

        double score = Math.round(number);

        switch ((int) score) {
            case 1:
                return "(About 6 years old)";
            case 2:
                return "(About 7 years old)";
            case 3:
                return "(About 9 years old)";
            case 4:
                return "(About 10 years old)";
            case 5:
                return "(About 11 years old)";
            case 6:
                return "(About 12 years old)";
            case 7:
                return "(About 13 years old)";
            case 8:
                return "(About 14 years old)";
            case 9:
                return "(About 15 years old)";
            case 10:
                return "(About 16 years old)";
            case 11:
                return "(About 17 years old)";
            case 12:
                return "(About 18 years old)";
            case 13:
                return "(About 24 years old)";
            default:
                return "(About 24+ years old)";
        }
    }
}

