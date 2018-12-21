/**
 * Takes business card text as input and outputs the name, phone number, and email address in a neat format.
 *
 * Uses Stanford Named Entity Recognizer (NER) library for name parsing.
 * Uses a standardized email regex from https://emailregex.com/
 */

import java.io.File;

import org.apache.commons.io.FileUtils;


public class BusinessCardParser {

    /**
     * Takes in a file, reads it into a String, and passes it on to the logic processing.
     *
     * @param file a text file containing information from a business card; can consist of names, organizations, title, phone number, fax number, email addresses, and etc.
     * @return ContactInfo which contains a name, phone number, and email address.
     */
    public static ContactInfo getContactInfo(File file) {

        String document = "";

        try {
            document = FileUtils.readFileToString(file, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getContactInfo(document);
    }


    /**
     * Takes in a document, splits it by newline, parses each line to determine if it's a name, phone number, or email address.
     * It then returns the name, phone number, and email address in a neat format.
     *
     * @param document a text file containing information from a business card; can consist of names, organizations, title, phone number, fax number, email addresses, and etc.
     * @return ContactInfo which contains a name, phone number, and email address.
     */
    public static ContactInfo getContactInfo(String document) {

        Parser parser = new Parser();
        String name = "";
        String phone = "";
        String email = "";

        // Split the document by newline into an array of lines
        String documentSplit[] = document.split(System.getProperty("line.separator"));

        try {
            for (String line : documentSplit) {

                // Checks for email matches
                if (email.equals("")) {
                    email = parser.parseEmail(line);
                }

                // Checks for phone matches
                if (phone.equals("")) {
                    phone = parser.parsePhone(line);
                }

                // Checks for name matches
                if (name.equals("")) {
                    name = parser.parseName(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ContactInfo contact = new ContactInfo(name, phone, email);
        return contact;
    }


}