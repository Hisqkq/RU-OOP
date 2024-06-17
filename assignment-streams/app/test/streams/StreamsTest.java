package streams;

import java.util.*;
import java.util.stream.*;
import org.junit.*;
import static org.junit.Assert.*;


public class StreamsTest {

    // The first 50 digits of PI
    private static final Integer[] PI = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6,2,6,4,3,3,8,3,2,7,9,5,0,2,8,8,4,1,9,7,1,6,9,3,9,9,3,7,5,1,0};

    @Test
    public void countEvenNumbers() {
        // Use streams and lambdas to count the number of even digits in PI
        long result = Arrays.stream(PI)
            .filter(val -> val % 2 == 0)
            .count();
        assertEquals(20, result);
    }

    
    
    @Test
    public void sumOddNumbers() {
        // Use streams and lambdas to sum the odd digits of PI
        int result = Arrays.stream(PI)
            .filter(val -> val % 2 != 0)
            .reduce(0, Integer::sum);

        int result2 = Arrays.stream(PI)
            .filter(val -> val % 2 == 1)
            .mapToInt(Integer::intValue)
            .sum();
        assertEquals(157, result);
    }
    
    
    
    @Test
    public void multiplyNumbers() {
        // Use streams and lambdas to multiply the 5 biggest digits of PI
        int result = Arrays.stream(PI)
            .sorted(Comparator.reverseOrder())
            .limit(5)
            .reduce(1, (a, b) -> a * b);
        assertEquals(59049, result);
    }
   
   
 
    @Test
    public void calculateTax() {
        // The following is a list of prices excluding tax.
        // Use streams and lambdas to include tax, then sum the results.
        Double tax = 0.12; // 12% tax
        Double[] prices = {11.5, 10.0, 1234.5678, 17.3, 19.99};
        Double result = Arrays.stream(prices)
            .map((a) -> a + a * tax)
            .reduce(0.0, Double::sum);
        assertEquals(1448.560736, (double)result, 0.0000001);
    }


    
    @Test
    public void sumStringIntegers() {
        // Use streams to sum the numbers
        // Hint: use Integer::parseInt
        String[] input = {"1", "5", "13", "7", "2"};
        int result = Arrays.stream(input)
            .mapToInt(Integer::parseInt)
            .sum();
        assertEquals(28, result);
    }
    
    
    
    @Test
    public void streamOfStreams() {
        // Use streams to sum all numbers of a two-dimensional array
        int[][] input =
        { {1, 2, 3, 1}
        , {4, 5, 6}
        , {7, 7, 7, 7, 7}
        };
        
        // Hint: Stream.of(input) is a stream of int arrays
        int result = Arrays.stream(input)
            .flatMapToInt(Arrays::stream)
            .sum();
        
        assertEquals(57, result);
    }



    @Test
    public void everySecondElement() {
        // Use streams to remove every second element from the list
        Integer[] input_    = {3, 7, 5, 8, 1, 1, 1, 3, 9, -12, 100, 42, 17, 13, 0, 7};
        Integer[] expected_ = {3,    5,    1,    1,    9,      100,     17,     0   };
        List<Integer> input = Arrays.asList(input_);
        List<Integer> expected = Arrays.asList(expected_);
        
        // Hint 1: Create an IntStream with the indexes of the elements you want to keep
        //   - Use IntStream.range()
        //   - Create a stream with numbers 0 to input.size()
        //   - Filter all even numbers
        // Hint 2: Replace those indexes with the elements of the input array
        //   - To turn an IntStream into a Stream<Integer> you can use Stream.mapToObj
        // Hint 3: Use Stream.collect and Collectors.toList() to turn a stream into a List
        List<Integer> result = IntStream.range(0, input.size())
            .filter(i -> i % 2 == 0)
            .mapToObj(input::get)
            .collect(Collectors.toList());

        assertEquals(expected, result);
    }



    // Helper function.
    // Performs rot13 encoding on an integer representing a character code point.
    // All letters [a-zA-Z] are rotated by 13 places.
    // All other characters are left unchanged.
    public static int rot13(int x) {
        if( x >= 'a' && x <= 'z') {
            x = x - 'a';
            return ((x + 13) % 26) + 'a';
        }
        else if( x >= 'A' && x <= 'Z' ) {
            x = x - 'A';
            return ((x + 13) % 26) + 'A';
        }
        else {
            return x;
        }
    }

    public static String rot13(String s) {
        // Split the string into a stream, decode each letter, and reassemble
        // the stream into a string.
        // Hint 1: String has a function to turn a string into a stream of integers
        // Hint 2: To construct a string from a list of characters, use
        //         Stream::collect with StringBuilder::new and StringBuilder::append
        // Hint 3: Use a type cast to turn an integer x into a character: (char)x
        return s.codePoints()
            .map(StreamsTest::rot13)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    @Test
    public void testRot13() {
        // Implement the function rot13 to decode the following message.
        String text = "Sne bhg va gur hapunegrq onpxjngref bs gur hasnfuvbanoyr "
                + "raq bs gur jrfgrea fcveny nez bs gur Tnynkl yvrf n fznyy "
                + "haertneqrq lryybj fha. Beovgvat guvf ng n qvfgnapr bs "
                + "ebhtuyl avargl-gjb zvyyvba zvyrf vf na hggreyl vafvtavsvpnag "
                + "yvggyr oyhr terra cynarg jubfr ncr-qrfpraqrq yvsr sbezf ner "
                + "fb nznmvatyl cevzvgvir gung gurl fgvyy guvax qvtvgny jngpurf "
                + "ner n cerggl arng vqrn.";
        String rotOnce = rot13(text);
        System.out.println("rotOnce = " + rotOnce);
        String rotTwice = rot13(rotOnce);
        assertEquals("rot13(rot13(text)) equals text", text, rotTwice);
        assertFalse(text.equals(rotOnce));
    }

    

    @Test
    public void philosophers() {
        // Use streams and lambdas to remove all non-greek philosophers from the map.
        Map<String,String> philosophers = new HashMap();
        philosophers.put("Aristotele", "Greek");
        philosophers.put("Russell", "British");
        philosophers.put("Quine", "American");
        philosophers.put("Frege", "German");
        philosophers.put("Plato", "Greek");
        philosophers.put("Wittgenstein", "Austrian");
        philosophers.put("Socrates", "Greek");
        philosophers.put("Brouwer", "Dutch");
        philosophers.put("Aurelius", "Roman");

        Map<String,String> expected = new HashMap();
        expected.put("Aristotele", "Greek");
        expected.put("Plato", "Greek");
        expected.put("Socrates", "Greek");

        // Hint 1: To create a stream of a map, use its entrySet.
        //         You should turn it into a stream of Map.Entry.
        // Hint 2: To reassemble a map from a stream of Entries, use Stream.collect
        //         with Collectors.toMap, Map.Entry::getKey, Map.Entry::getValue
        Map<String,String> result = philosophers.entrySet().stream()
            .filter(val -> val.getValue().equals("Greek"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertEquals(expected, result);
    }
}
