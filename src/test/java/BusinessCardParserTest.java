/**
 * JUnit testing for BusinessCardParser
 */

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertTrue;


public class BusinessCardParserTest {

    /**
     * These tests will see if the BusinessCardParser's output is equal to the expected result.
     */
    @Test
    public void testSample1() {

        try {
            File file = new File("src/test/testFiles/test1.txt");
            String document = FileUtils.readFileToString(file, "UTF-8");
            ContactInfo contact = BusinessCardParser.getContactInfo(document);
            String result = contact.toString();

            String expected = "Name: Mike Smith" + System.getProperty("line.separator") +
                    "Phone: 4105551234" + System.getProperty("line.separator") +
                    "Email: msmith@asymmetrik.com";

            assertTrue(result.equals(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSample2() {

        try {
            File file = new File("src/test/testFiles/test2.txt");
            String document = FileUtils.readFileToString(file, "UTF-8");
            ContactInfo contact = BusinessCardParser.getContactInfo(document);
            String result = contact.toString();

            String expected = "Name: Lisa Haung" + System.getProperty("line.separator") +
                    "Phone: 4105551234" + System.getProperty("line.separator") +
                    "Email: lisa.haung@foobartech.com";

            assertTrue(result.equals(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSample3() {

        try {
            File file = new File("src/test/testFiles/test3.txt");
            String document = FileUtils.readFileToString(file, "UTF-8");
            ContactInfo contact = BusinessCardParser.getContactInfo(document);
            String result = contact.toString();

            String expected = "Name: Arthur Wilson" + System.getProperty("line.separator") +
                    "Phone: 17035551259" + System.getProperty("line.separator") +
                    "Email: awilson@abctech.com";

            assertTrue(result.equals(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
