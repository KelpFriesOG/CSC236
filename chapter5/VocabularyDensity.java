package chapter5;

import java.io.FileReader;
import java.util.Scanner;

public class VocabularyDensity {

    public static void main(String[] args) {

        // Try this by passing either
        // DeclarationOfIndependene.txt
        // or
        // MagnaCarta.txt
        // as arguments to the compiler!

        final int CAPACITY = 1000;
        // Capacity of the collection we will create.
        String filename = args[0];
        // Name of the input file that contains the text.
        String word;
        // Used to refer to the word we are on.
        int numWords = 0;
        // A counter that counts up for each word encountered
        int numUnique;
        // The number of unique words which will be set to
        // the size of the collection later on.
        double density;
        // Our final answer which will be set to
        // numWords / numUnique

        CollectionInterface<String> words = new ArrayCollection<String>(CAPACITY);

        // Setting up the file reading
        try {
            FileReader reader = new FileReader("chapter5/" + filename);
            Scanner wordsIn = new Scanner(reader);
            wordsIn.useDelimiter("[^a-zA-Z']+");
            // Delimiters are non-letters (i.e. things we wanna skip)
            // The ^ indicates EXCEPT, or EVERYTHING BUT
            // So delimiters are everything BUT letters from a-z and A-Z and
            // the apostrophe (').

            while (wordsIn.hasNext()) {
                word = wordsIn.next();
                word = word.toLowerCase();

                if (!words.contains(word)) {
                    words.add(word);
                }

                numWords++;
            }

            numUnique = words.size();
            density = (double) numWords / numUnique;
            System.out.println("Number of Words: " + numWords);
            System.out.println("Number of Unique Words: " + numUnique);
            System.out.println("Density: " + density);

            wordsIn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
