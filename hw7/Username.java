import java.util.HashMap;
import java.util.Random;

public class Username {
    // Potentially useless note: (int) '0' == 48, (int) 'a' == 97
    // a - z --> 97 - 122
    // A - Z --> 65 - 90
    // for commit ;
    // Instance Variables (remember, they should be private!)
    public String myUserName;
    private static Random r = null;


    public Username() {
        int randomLength = 0;
        double determiner = Math.random();
        char[] chars = null;

        // Determines length of the future String
        if (determiner >= 0.5) {
            randomLength = 2;
        } 
        if (determiner < 0.5) {
            randomLength = 3;
        }


        int lenIsThree = randomInt(1, 3);
        int legnIsTwo = randomInt(1, 2);
        String uName = null;

        if (randomLength == 2) {
            uName = twoCharConcat(legnIsTwo);

        }
        if (randomLength == 3) {
            uName = threeCharConcat(lenIsThree);
        }

        Username finalUser = new Username(uName);

        this.myUserName = finalUser.myUserName;

    }

    protected String threeCharConcat(int lenIsThree) {
        StringBuilder tempBuilder = new StringBuilder();
        char randomLowerCase = (char) randomInt(97, 122);
        char randomUpperCase = (char) randomInt(65, 90);
        int randomInteger = randomInt(0, 9);

        if (lenIsThree == 1) {
            tempBuilder.append(randomUpperCase);
            tempBuilder.append(randomLowerCase);
            tempBuilder.append(Integer.toString(randomInteger));
            String toReturn = new String(tempBuilder);
            return toReturn;
        }
        else if (lenIsThree == 2) {
            tempBuilder.append(randomLowerCase);
            tempBuilder.append(randomUpperCase);
            tempBuilder.append(Integer.toString(randomInteger));
            String toReturn = new String(tempBuilder);
            return toReturn;
        }

        else {
            tempBuilder.append(randomLowerCase);
            tempBuilder.append(Integer.toString(randomInteger));
            tempBuilder.append(randomUpperCase);
            String toReturn = new String(tempBuilder);
            return toReturn;
        }
    }

    protected String twoCharConcat(int legnIsTwo) {
        StringBuilder tempBuilder2 = new StringBuilder();
        char randomLowerCase = (char) randomInt(97, 122);
        int randomInteger = randomInt(0, 9);
        
        if (legnIsTwo == 1) {
            tempBuilder2.append(randomLowerCase);
            tempBuilder2.append(randomInteger);
            String toReturn2 = new String(tempBuilder2);
            return toReturn2;
        }
        else {
            tempBuilder2.append(randomInteger);
            tempBuilder2.append(randomLowerCase);
            String toReturn2 = new String(tempBuilder2);
            return toReturn2;
        }
    }


    /** Returns a random number uniformly between min and max inclusive
    Taken from HuglifeUtils from lab6.
    Stolen from: http://stackoverflow.com/questions/363681 */
    protected int randomInt(int min, int max) {
        if (r == null)
            r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    public Username(String reqName) {


        if (reqName == null) {
            throw new NullPointerException("Requested username is null!");
        } 

        if (reqName.length() < 2 || reqName.length() > 3) {
            throw new IllegalArgumentException("input must be 2 or 3 chars long.");
        }

        if (!isAlphaNumeric(reqName)) {
            throw new IllegalArgumentException("Your string isnt Alphanumeric!");

        } else {
            this.myUserName = reqName;
        }
    }

    public boolean isAlphaNumeric(String name) {
        int stringCount = 0;
        int numCount = 0;
        boolean alphaOrNo = true;

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            if (!Character.isLetterOrDigit(c)) {
                alphaOrNo = false;
            }
            if (Character.isDigit(c)) {
                numCount += 1;
            }
            if (Character.isLetter(c)) {
                stringCount += 1;
            }
        }

        if (stringCount == 0 || numCount == 0) {
            alphaOrNo = false;
        }

        return alphaOrNo;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Username) {
            Username other = (Username) o;
            return other.myUserName.toLowerCase() == this.myUserName.toLowerCase(); // dont forget case insensitivity
        }
        return false;
    }

    @Override
    public int hashCode() {
        StringBuilder hashBuilder = new StringBuilder();

        if (this.myUserName.length() == 2) {
            for (int i = 0; i < this.myUserName.length(); i+=1) {
                char c = this.myUserName.charAt(i);
                String separator = String.valueOf((int)Math.pow(4, i));
                hashBuilder.append(separator);

                if (Character.isLetter(c)) {
                    int num = (int) c + i;
                    String numStr = String.valueOf(num);
                    hashBuilder.append(numStr);
                } else if (Character.isDigit(c)){
                    hashBuilder.append(c);
                }
            }
        }

        else if (this.myUserName.length() == 3) {
            for (int i = 0; i < this.myUserName.length(); i+=1) {
                char c = this.myUserName.charAt(i);
                String separator = String.valueOf((int) Math.pow(i , 2));
                hashBuilder.append(separator);

                if (Character.isLetter(c)) {
                    int num = (int) c + i;
                    String numStr = String.valueOf(num);
                    hashBuilder.append(numStr);
                } else if (Character.isDigit(c)){
                    hashBuilder.append(c);
                }
            }
        }

        hashBuilder.append('0'); 
        String returnStr = new String(hashBuilder);
        Integer returnInt = Integer.parseInt(returnStr);
        return returnInt;
    }

    public static void main(String[] args) {


    }
}