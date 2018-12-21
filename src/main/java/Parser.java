/**
 * Helper class for the BusinessCardParser to aid in parsing names, phone numbers, and emails.
 *
 * Uses Stanford Named Entity Recognizer (NER) library for name parsing.
 * Uses a standardized email regex from https://emailregex.com/
 */

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;


public class Parser {

    // General Email Regex (RFC 5322 Official Standard) from https://emailregex.com/
    private static final String EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    // Phone Regex; checks to see if the numbers are 10 digit, also allows 11 digit if it starts with a 1 (USA Country Calling Code)
    private static final String PHONE = "^[1][1-9]\\d{9}$|^[1-9]\\d{9}$";
    // Stanford classifier used to help classify Strings when parsing names
    private AbstractSequenceClassifier<CoreLabel> classifier;


    public Parser() {
        // Loads the Stanford classifier used to help classify Strings
        try {
            // Can try different classifiers if one isn't satisfactory
            InputStream stream = BusinessCardParser.class.getClassLoader().getResourceAsStream("english.muc.7class.distsim.crf.ser.gz");
            GZIPInputStream gzip = new GZIPInputStream(stream);
            this.classifier = CRFClassifier.getClassifier(gzip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks to see if the input is a valid email address.
     * Uses General Email Regex (RFC 5322 Official Standard) from https://emailregex.com/
     *
     * @param line any String, in this case, a single line being read from a text document.
     * @return an email address as a String if there is a match,
     * will return an empty character otherwise.
     */
    public String parseEmail(String line) {
        // Sees if the line matches the email regex
        if (line.matches(EMAIL)) {
            return line;
        } else {
            return "";
        }
    }


    /**
     * Checks to see if the input is a USA phone number.  So this means 10 digit numbers, or 11 digits numbers are okay if it starts with a 1.
     *
     * @param line any String, in this case, a single line being read from a text document.
     * @return a phone number as a String if there is a match,
     * will return an empty character otherwise.
     */
    public String parsePhone(String line) {
        // If there is an "f" or "fa" or "fax" anywhere, it is assumed to be a fax number and gets ignored
        if (line.toLowerCase().contains("f") || line.toLowerCase().contains("fa") || line.toLowerCase().contains("fax"))
            return "";

        // Replace all non-digits with an empty character
        line = line.replaceAll("[^\\d]", "");

        // Checks to see if the numbers are 10 digit, also allows 11 digit if it starts with a 1 (USA Country Calling Code)
        if (line.matches(PHONE)) {
            return line;
        } else {
            return "";
        }
    }


    /**
     * Checks to see if the input is possibly a person's name.
     * Uses Stanford Named Entity Recognizer (NER) library for name parsing.
     *
     * @param line any String, in this case, a single line being read from a text document.
     * @return a name as a String if there is a match,
     * will return an empty character otherwise.
     */
    public String parseName(String line) throws Exception {

        // Encloses the String in an identification tag if possible
        String input = this.classifier.classifyWithInlineXML(line);

        // Checks the tags to see if the String is considered a person's name
        if (input.contains("<PERSON>") && input.contains("</PERSON>")) {
            return line;
        } else {
            return "";
        }
    }


}
