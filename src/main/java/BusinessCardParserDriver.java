/**
 * Takes business card text as an input file and outputs the name, phone number, and email address in a neat format.
 */

import java.io.File;


public class BusinessCardParserDriver {

    public static void main(String[] args) {

        // Takes text file containing business card information, one argument is required
        if (args.length < 1) {
            System.out.println("Error: One argument is necessary.");
            System.out.println("Proper Usage: java -jar BusinessCardParser.jar <input file>");
            System.out.println("Example: java -jar BusinessCardParser.jar text.txt");
            System.exit(0);
        }
        File file = new File(args[0]);
        // Passes File and extracts contact information
        System.out.println(BusinessCardParser.getContactInfo(file));
    }

}