/**
 * Contact information, which consists of a name, a phone number, and an email address
 */
public class ContactInfo {

    private String name;
    private String phone;
    private String email;

    /**
     * Contact information
     *
     * @param name  of person
     * @param phone of person
     * @param email of person
     */
    ContactInfo(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * @return the full name of the individual (eg. John Smith, Susan Malick)
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the phone number formatted as a sequence of digits
     */
    public String getPhoneNumber() {
        return this.phone;
    }

    /**
     * @return the email address
     */
    public String getEmailAddress() {
        return this.email;
    }

    /**
     * @return all contact information in a neat format
     */
    public String toString() {
        return ("Name: " + this.name + System.getProperty("line.separator") +
                "Phone: " + this.phone + System.getProperty("line.separator") +
                "Email: " + this.email);
    }
}
