import java.util.ArrayList;
import java.util.Arrays;

public class Lab2
{
    /**
     * generatePalindromeSequences:     generates Santa's palindromes of length N
     * 
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

    public static void main(String[] args)
    {
        Lab2 l2 = new Lab2();

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
    }
}