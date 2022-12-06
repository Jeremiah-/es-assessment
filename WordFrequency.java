import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// test strings below
/**
 I thought not. It's not a story the "Jedi" would tell you. It's a Sith legend. Darth Plagueis... was a Dark Lord of the Sith so powerful and so wise, he could use the Force to influence the midi-chlorians... to create... life. He had such a knowledge of the dark side, he could even keep the ones he cared about... from dying.
 “the cat is in the bag",
 */


/**
 * Counts and prints the frequency of words in a given string
 */
public class WordFrequency {
    public static void main(String[] args) {
        System.out.println("Please enter a string you would like to count the word frequency of:");

        // Set up a scanner to retrieve user input
        Scanner scanner = new Scanner(System.in);
        String user_input = scanner.nextLine();
        scanner.close();

        String[] words = split_and_sanitize_user_input(user_input);

        HashMap<String, Integer> word_frequencies = get_word_frequency(words);

        sort_and_print_words(word_frequencies);
    }

    /**
     * Splits the given string and converts the words to lowercase
     */
    private static String[] split_and_sanitize_user_input(String user_input) {

        // TODO JIRA-1, need to fix: this currently includes an empty string when strings begin with a quote
        String[] words = user_input.split("[ ,.;:\"/?!]+");

        for (int i = 0; i < words.length; ++i){
            words[i] = words[i].toLowerCase();
        }

        return words;
    }

    /**
     * Stores the given array of strings into a map with k:v of word:word_count
     */
    private static HashMap<String, Integer> get_word_frequency(String[] words) {
        HashMap<String, Integer> word_frequencies = new HashMap<>();
        for (String word: words) {
            // TODO JIRA-1: remove this conditional when the split regex has been fixed
            if (!word.isEmpty()) {
                int count = word_frequencies.getOrDefault(word, 0);
                word_frequencies.put(word, ++count);
            }
        }

        return word_frequencies;
    }

    /**
     * Sorts the given map and prints the words from highest count to lowest count
     */
    private static void sort_and_print_words(Map<String, Integer> word_frequencies) {
        word_frequencies.entrySet()
                .stream()
                .sorted((kv1, kv2) -> kv2.getValue() - kv1.getValue())
                .forEachOrdered(kv -> System.out.printf("%d %s %n", kv.getValue(), kv.getKey()));
    }
}
