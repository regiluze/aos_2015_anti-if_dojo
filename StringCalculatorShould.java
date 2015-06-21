import org.junit.Test;
import static org.junit.Assert.*;

public class StringCalculatorShould {


    @Test
    public void return_zero_when_input_is_an_empty_string(){

        assertEquals(0, StringCalculator.add(""));

    }

    @Test
    public void return_the_same_number_when_input_is_one_number_string(){

        assertEquals(2, StringCalculator.add("2"));

    }

    @Test
    public void return_the_sum_of_numbers_when_input_is_an_unknown_amount_of_numbers(){

        assertEquals(8, StringCalculator.add("2,6"));
        assertEquals(20, StringCalculator.add("2,6,2,10"));
    }

    @Test
    public void handle_new_line_delimiter(){

        assertEquals(9, StringCalculator.add("1\n2,6"));

    }

    @Test
    public void handle_custom_delimiter(){

        assertEquals(9, StringCalculator.add("//;1\n2;6"));

    }


 static class StringCalculator{


     public static final String CUSTOM_DELIMITER_PREFIX = "//";
     public static final String DEFAULT_DELIMITER = ",";
     public static final String NEW_LINE_DELIMITER = "\n";

     public static int add(String numbers) {
        String delimiter = extractDelimiter(numbers);
        String sanitizedString = sanitizeInput(numbers, delimiter);
        return add(extractNumbers(delimiter, sanitizedString));

    }



     private static String extractDelimiter(String input){
         return extractFirstDelimiter(ensureDelimiterPresence(input));
     }

     private static String extractFirstDelimiter(String input) {
         return String.valueOf(input.split(CUSTOM_DELIMITER_PREFIX)[1].charAt(0));
     }

     private static String[] extractNumbers(String delimiter, String sanitizeString) {
         return sanitizeString.split(delimiter);
     }

     private static String ensureDelimiterPresence(String input){
         return input + CUSTOM_DELIMITER_PREFIX + DEFAULT_DELIMITER;
     }

     private static String sanitizeInput(String input,String delimiter){
         String sanitizeString = input.replace(CUSTOM_DELIMITER_PREFIX+delimiter,"");
         return sanitizeString.replaceAll(NEW_LINE_DELIMITER,delimiter);
     }

     private static int add(String[] numbers) {
         int result = 0;
         for (int i=0;i<numbers.length;i++){
                int number = parseNumber(numbers[i]);
                result += number;
         }
         return result;
     }

     private static int parseNumber(String number){
         try{
             return Integer.valueOf(number);
         }catch (NumberFormatException e){
             return 0;
         }
     }
 }

}
