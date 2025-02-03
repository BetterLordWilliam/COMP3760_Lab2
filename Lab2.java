import java.util.ArrayList;
import java.util.Arrays;

public class Lab2
{
    /**
     * Generates Santa's palindromes of length N
     * Palindromes consist of the characters 'A','B', and 'C'
     * 
     * @param N         int, the length of the palindrome
     * @return          ArrayList<String>, the palindromes which are of length N
     */
    public ArrayList<String> generatePalindromeSequences(int N)
    {   
        // Base case, return an ArrayList containing 'A', 'B', 'C'
        if (N == 1)
        {
            // String[] ret = {"A", "B", "C"};
            ArrayList<String> arr = new ArrayList<>(Arrays.asList("A", "B", "C"));
            return arr;
        }
        else if (N % 2 == 0)
        {
            // Length of each string is even
            // Duplicate the character in the center of each string
            ArrayList<String> arr = generatePalindromeSequences(N-1);
            ArrayList<String> arr_n = new ArrayList<>();
            for (String string : arr)
            {
                string = string.substring(0, string.length()/2)
                    + string.charAt(string.length()/2)
                    + string.substring(string.length()/2);
                arr_n.add(string);
            }
            arr = null;
            return arr_n;
        }

        // Bookend the existing substrings of the previous odd array list with characters
        // Do this for 'A', 'B' and 'C'
        ArrayList<String> arr = generatePalindromeSequences(N-2);
        ArrayList<String> arr_n = new ArrayList<>();
        for (String string : arr)
        {
            arr_n.add('A' + string + 'A');
            arr_n.add('B' + string + 'B');
            arr_n.add('C' + string + 'C');
        }
        arr = null;
        return arr_n;
    }
    
    /**
     * Generate the list of characters which for the variable length version of this problem.
     * 
     * @param numColors
     * @return
     */
    private ArrayList<String> returnBasePalindromeListForVariableColors(int numColors)
    {
        // Technically this won't work for lists with more than 52 (twice the alphabet)
        // Unlikely to be tested.............. right.....
        ArrayList<String> arr = new ArrayList<>();
        int added = 0;
        for (char i = 65; i <= 122 && added < numColors; i++)
        {
            arr.add(i + "");
            if (i == 90)        // jump to lowercase
                i = 96;
            added++;
        }
        return arr;
    }

    /**
     * Generate's Santa's palindromes of length N
     * Palindromes consist of ASCII characters in range numColors (a-z, A-B)
     * 
     * @param N
     * @param numColors
     * @return
     */
    public ArrayList<String> generatePalindromeSequencesVariableColors(int N, int numColors)
    {
        // Base case, return a list with strings for each of the number of colors
        if (N == 1)
        {
            return returnBasePalindromeListForVariableColors(numColors);
        }
        else if (N % 2 == 0)
        {
            // Length of each string is even
            // Duplicate the character in the center of each string
            // Same code
            ArrayList<String> arr = generatePalindromeSequencesVariableColors(N-1, numColors);
            ArrayList<String> arr_n = new ArrayList<>();
            for (String string : arr)
            {
                string = string.substring(0, string.length()/2)
                    + string.charAt(string.length()/2)
                    + string.substring(string.length()/2);
                arr_n.add(string);
            }
            arr = null;
            return arr_n;
        }

        // Bookend the existing substrings of the previous odd array list with characters
        // Do this for all the characters in the list of length 1 (base case)
        // Just call the generate helper method with same numColors as input
        ArrayList<String> characters = returnBasePalindromeListForVariableColors(numColors);
        ArrayList<String> arr = generatePalindromeSequencesVariableColors(N-2, numColors);
        ArrayList<String> arr_n = new ArrayList<>();
        for (String string : arr)
        {
            // Just loop over the list of characters
            // Logic is the same
            for (String character : characters)
            {
                arr_n.add(character + string + character);
            }
        }
        arr = null;
        return arr_n;
    }

    public static void main(String[] args)
    {
        Lab2 l2 = new Lab2();

        // Non-donut stuff
        // Generate and Store sequences
        ArrayList<ArrayList<String>> sequences = new ArrayList<>();
        for (int i = 1; i <= 5; i++)
            sequences.add(l2.generatePalindromeSequences(i));

        // Length output
        for (ArrayList<String> sequence : sequences)
            System.out.println(String.format("Length: %d", sequence.size()));

        // Sequence output
        for (ArrayList<String> sequence : sequences)
            System.out.println(String.format("Sequence: %s", sequence));
    
        // Donut testing
        // Generate and store sequences
        int characters = 5;
        ArrayList<ArrayList<String>> sequences2 = new ArrayList<>();
        for (int i = 1; i <= 6; i++)
            sequences2.add(l2.generatePalindromeSequencesVariableColors(i, characters));

        // Length output
        for (ArrayList<String> sequence : sequences2)
            System.out.println(String.format("Length: %d", sequence.size()));

        // Sequence output
        for (ArrayList<String> sequence : sequences2)
            System.out.println(String.format("Sequence: %s", sequence));
    }
}