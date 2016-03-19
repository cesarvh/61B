import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;


public class UsernameBank extends Username {
    private HashSet<String> invalidUsernames = new HashSet<String>();
    private HashMap<String, String> usersAndEmails = new HashMap<String, String>();
    private HashMap<String, Integer> badUsernames = new HashMap<String, Integer>();
    private HashMap<String, Integer> badEmais = new HashMap<String, Integer>();

    // Instance variables (remember, they should be private!)
    // YOUR CODE HERE
 
    public UsernameBank() {
        super();
    }

    public void generateUsername(String username, String email) {
        if (username == null  || email == null) {
            throw new NullPointerException("Must provide user and email");
        }
        if (!isValidUsername(username) || !isValidEmail(email)) {
            throw new IllegalArgumentException("Your username is invalid");
        }
        if (usersAndEmails.containsKey(username)) { 
            throw new IllegalArgumentException("Username already exists");

        } else {
            usersAndEmails.put(username, email);
        }
/*If the username is valid and does not already exist, create the username/email pair and record it in the database.
If the requested username is valid, but already exists in the database, throw an IllegalArgumentException with an appropriate message.
*/
    }

    public String getEmail(String username) {
        if (username == null) {
            throw new NullPointerException("Provided username is blank!");
        }
        if (!isValidUsername(username)) {
            invalidUsernames.add(username);
            return null;
        }
        if (isValidUsername(username) && !usersAndEmails.containsKey(username)) {
            badUsernames.put(username, null); //not taking counts into acount
            return null;
        }  
        String returnEmail = usersAndEmails.get(username);
        return returnEmail;
    }

    public boolean isValidEmail(String email) {
        boolean vEmail = false;
        int atCount = 0;
        int invalidCharCount = 0;

        for (int i = 0; i < email.length(); i += 1) {
            char c = email.charAt(i);
            if (c == '@') {
                atCount += 1;
                invalidCharCount -= 1;
                vEmail = true;
            }
        }
        if (atCount > 1 || invalidCharCount > 0) {
            vEmail = false;
        }
        return vEmail;
    }

    public boolean isValidUsername(String username) {
        if ((username.length() == 2 || username.length() == 3) && isAlphaNumeric(username)) {
            return true;
        } return false;
    }

    public String getUsername(String userEmail)  {
        // YOUR CODE HERE
        return null;
    }

    public Map<String, Integer> getBadEmails() {
        // YOUR CODE HERE
        return null;
    }

    public Map<String, Integer> getBadUsernames() {
        // YOUR CODE HERE
        return null;
    }

    public String suggestUsername() {
        // YOUR CODE HERE
        return null;
    }

    // The answer is somewhere in between 3 and 1000.
    public static final int followUp() {
        // YOUR CODE HERE
        return 0;
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadUsername(String username) {
        // YOUR CODE HERE
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadEmail(String email) {
        // YOUR CODE HERE
    }
}