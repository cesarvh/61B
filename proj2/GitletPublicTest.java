import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

/**
 * Class that provides JUnit tests for Gitlet, as well as a couple of utility
 * methods.
 * 
 * @author Joseph Moghadam
 * 
 *         Some code adapted from StackOverflow:
 * 
 *         http://stackoverflow.com/questions
 *         /779519/delete-files-recursively-in-java
 * 
 *         http://stackoverflow.com/questions/326390/how-to-create-a-java-string
 *         -from-the-contents-of-a-file
 * 
 *         http://stackoverflow.com/questions/1119385/junit-test-for-system-out-
 *         println
 * 
 */
public class GitletPublicTest {
    private static final String GITLET_DIR = ".gitlet/";
    private static final String TESTING_DIR = "test_files/";

    /* matches either unix/mac or windows line separators */
    private static final String LINE_SEPARATOR = "\r\n|[\r\n]";

    /**
     * Deletes existing gitlet system, resets the folder that stores files used
     * in testing.
     * 
     * This method runs before every @Test method. This is important to enforce
     * that all tests are independent and do not interact with one another.
     */
    @Before
    public void setUp() {
        File f = new File(GITLET_DIR);
        if (f.exists()) {
            recursiveDelete(f);
        }
        f = new File(TESTING_DIR);
        if (f.exists()) {
            recursiveDelete(f);
        }
        f.mkdirs();
    }

    /**
     * Tests that init creates a .gitlet directory. Does NOT test that init
     * creates an initial commit, which is the other functionality of init.
     */
    @Test
    public void testBasicInitialize() {
        gitlet("init");
        File f = new File(GITLET_DIR);
        assertTrue(f.exists());
    }

    /**
     * Tests that checking out a file name will restore the version of the file
     * from the previous commit. Involves init, add, commit, and checkout.
     */
    @Test
    public void testBasicCheckout() {
        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("init");
        gitlet("add", wugFileName);
        gitlet("commit", "added wug");
        writeFile(wugFileName, "This is not a wug.");
        gitlet("checkout", wugFileName);
        assertEquals(wugText, getText(wugFileName));
    }

    /**
     * Tests that log prints out commit messages in the right order. Involves
     * init, add, commit, and log.
     */
    @Test
    public void testBasicLog() {
        gitlet("init");
        String commitMessage1 = "initial commit";

        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("add", wugFileName);
        String commitMessage2 = "added wug";
        gitlet("commit", commitMessage2);

        String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));
    }

    /**
     * Convenience method for calling Gitlet's main. Anything that is printed
     * out during this call to main will NOT actually be printed out, but will
     * instead be returned as a string from this method.
     * 
     * Prepares a 'yes' answer on System.in so as to automatically pass through
     * dangerous commands.
     * 
     * The '...' syntax allows you to pass in an arbitrary number of String
     * arguments, which are packaged into a String[].
     */
    private static String gitlet(String... args) {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        ByteArrayOutputStream printingResults = new ByteArrayOutputStream();
        try {
            /*
             * Below we change System.out, so that when you call
             * System.out.println(), it won't print to the screen, but will
             * instead be added to the printingResults object.
             */
            System.setOut(new PrintStream(printingResults));

            /*
             * Prepares the answer "yes" on System.In, to pretend as if a user
             * will type "yes". You won't be able to take user input during this
             * time.
             */
            String answer = "yes";
            InputStream is = new ByteArrayInputStream(answer.getBytes());
            System.setIn(is);

            /* Calls the main method using the input arguments. */
            Gitlet.main(args); //look for me

        } finally {
            /*
             * Restores System.out and System.in (So you can print normally and
             * take user input normally again).
             */
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
        return printingResults.toString();
    }

    /**
     * Returns the text from a standard text file (won't work with special
     * characters).
     */
    private static String getText(String fileName) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(fileName));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Creates a new file with the given fileName and gives it the text
     * fileText.
     */
    private static void createFile(String fileName, String fileText) {
        File f = new File(fileName);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeFile(fileName, fileText);
    }

    /**
     * Replaces all text in the existing file with the given text.
     */
    private static void writeFile(String fileName, String fileText) {
        FileWriter fw = null;
        try {
            File f = new File(fileName);
            fw = new FileWriter(f, false);
            fw.write(fileText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes the file and all files inside it, if it is a directory.
     */
    private static void recursiveDelete(File d) {
        if (d.isDirectory()) {
            for (File f : d.listFiles()) {
                recursiveDelete(f);
            }
        }
        d.delete();
    }



    @Test
    public void testBranchCheckout() {
        String frenchFileName =  TESTING_DIR + "french.txt";
        String spanishFileName = TESTING_DIR + "spanish.txt";
        String englishFileName = TESTING_DIR + "english.txt";
        String italianFileName =  TESTING_DIR + "italan.txt";
        String frenchText = "french";
        String spanishText = "spanish";
        String englishText = "english";
        String italianText = "italian";
        String frenchText2 = "french2";
        String spanishText2 = "spanish2";
        String englishText2 = "english2";
        String italianText2 = "italian2";


        createFile(frenchFileName, frenchText);
        createFile(spanishFileName, spanishText);
        createFile(italianFileName, italianText);
        createFile(englishFileName, englishText);

        gitlet("init");
        gitlet("add", frenchFileName);
        gitlet("add", spanishFileName);
        gitlet("add", englishFileName);
        gitlet("commit", "c1");

        writeFile(englishFileName,englishText2);
        gitlet("add", englishFileName);
        gitlet("branch", "italianBranch");
        gitlet("commit", "c2");
    
        writeFile(spanishFileName, spanishText2);
        writeFile(frenchFileName, frenchText2);
        gitlet("add", frenchFileName);
        gitlet("commit", "c3");

        gitlet("checkout", "italianBranch");
        gitlet("add", italianFileName);
        gitlet("commit", "c4");
        writeFile(italianFileName, italianText2);
        gitlet("add", italianFileName);
        gitlet("commit", "c5");
        gitlet("checkout", "master");

        assertEquals(frenchText2, getText(frenchFileName));
        assertEquals(englishText2, getText(englishFileName));
        assertEquals(italianText2, getText(italianFileName));
        assertEquals(spanishText, getText(spanishFileName));
    }

    @Test
    public void testReset() {
        String frenchFileName =  TESTING_DIR + "french.txt";
        String spanishFileName = TESTING_DIR + "spanish.txt";
        String englishFileName = TESTING_DIR + "english.txt";
        String italianFileName =  TESTING_DIR + "italan.txt";
        String frenchText = "french";
        String spanishText = "spanish";
        String englishText = "english";
        String italianText = "italian";
        String frenchText2 = "french2";
        String spanishText2 = "spanish2";
        String englishText2 = "english2";
        String italianText2 = "italian2";


        createFile(frenchFileName, frenchText);
        createFile(spanishFileName, spanishText);
        createFile(italianFileName, italianText);
        createFile(englishFileName, englishText);

        gitlet("init");
        gitlet("add", frenchFileName);
        gitlet("add", spanishFileName);
        gitlet("add", englishFileName);
        gitlet("commit", "c1");

        writeFile(englishFileName,englishText2);
        gitlet("add", englishFileName);
        gitlet("branch", "italianBranch");
        gitlet("commit", "c2");
    
        writeFile(spanishFileName, spanishText2);
        writeFile(frenchFileName, frenchText2);
        gitlet("add", frenchFileName);
        gitlet("commit", "c3");

        gitlet("checkout", "italianBranch");
        gitlet("add", italianFileName);
        gitlet("commit", "c4");
        writeFile(italianFileName, italianText2);
        gitlet("add", italianFileName);
        gitlet("commit", "c5");

        gitlet("reset", "1");
        assertEquals(frenchText, getText(frenchFileName));
        assertEquals(spanishText, getText(spanishFileName));
        assertEquals(englishText, getText(englishFileName));


    }

    @Test
    public void testRebaseCornerCases() {
        String rugFileName = TESTING_DIR + "rug.txt";
        String bugFileName = TESTING_DIR + "bug.txt";
        String rugText1 = "rug1";
        String bugText = "bug";
        String rugText2 = "rug2";
        String rugText3 = "rug3";
        createFile(rugFileName, rugText1);
        gitlet("init");
        gitlet("branch", "ignore");
        gitlet("add", rugFileName);
        gitlet("commit", "c1"); // Master has rug1
        gitlet("branch", "trevor"); // Branch off and checkout Trevor
        gitlet("checkout", "trevor"); 
        writeFile(rugFileName, rugText2);
        createFile(bugFileName, bugText);
        gitlet("add", rugFileName);
        gitlet("add", bugFileName);
        gitlet("commit", "c2"); // Trevor has rug2q

        // 1
        System.out.println(gitlet("rebase", "master"));
        assertEquals(rugText2, getText(rugFileName)); // ok 
        System.out.println(gitlet("checkout", "master"));
        assertEquals(rugText1, getText(rugFileName)); // ok
        System.out.println(gitlet("checkout", "trevor"));
        assertEquals(rugText2, getText(rugFileName)); //  ok (even with new)

        // 2
        gitlet("checkout", "master");
        System.out.println(gitlet("rebase", "trevor")); // Master should now point to same commit as Trevor
        assertEquals(rugText2, getText(rugFileName)); // ok 
        writeFile(rugFileName, rugText3);
        gitlet("add", rugFileName);
        gitlet("commit", "c3");
        gitlet("checkout", "ignore");
        writeFile(bugFileName, "should be overwritten by checkout master");
        gitlet("checkout", "master");
        assertEquals(bugText, getText(bugFileName)); //  ok
        assertEquals(rugText3, getText(rugFileName)); // not ok ...
        gitlet("checkout", "trevor");
        assertEquals(rugText2, getText(rugFileName)); // not ok
        
    }

    @Test
    public void rebaseTest() {
        gitlet("init");
        String bulbaFile = TESTING_DIR + "bulbasaur.txt";
        String bulaText = "bulbasaur";
        createFile(bulbaFile, bulaText);
        String squirtleFile = TESTING_DIR + "squirtle.txt";
        String squirtleText = "squirtle";
        createFile(squirtleFile, squirtleText);
        String charFile = TESTING_DIR + "charmander.txt";
        String charText = "charmander";
        createFile(charFile, charText);
        String pikaFile = TESTING_DIR + "pikachu.txt";
        String pikaText = "pikachu";
        createFile(pikaFile, pikaText);
        gitlet("add", squirtleFile);
        gitlet("add", bulbaFile);
        gitlet("add", pikaFile);
        String commit1 = "Added squirtle,bulbasaur,pikachu";
        gitlet("commit", commit1);
        gitlet("branch", "starters");
        System.out.println(gitlet("checkout", "starters"));
        gitlet("add", charFile);
        String venosaurText = "venosaur";
        writeFile(bulbaFile, venosaurText);
        String wartortleText = "wartortle";
        writeFile(squirtleFile, wartortleText);
        gitlet("add", bulbaFile);
        gitlet("add", squirtleFile);
        String raichuText = "raichu";
        writeFile(pikaFile, raichuText);
        gitlet("add", pikaFile);
        String b1Message = "evolved squirtle, bulbasaur, and caught charmander";
        gitlet("commit", b1Message);
        System.out.println(gitlet("checkout", "master"));
        gitlet("rm", pikaFile);
        System.out.println(getText(squirtleFile));
        String blastoiseMaster = "blastoise";
        writeFile(squirtleFile, blastoiseMaster);
        gitlet("add", squirtleFile);
        System.out.println(getText(squirtleFile));
        String commitMessageP = "Changed squirtle and removed pikachu";
        gitlet("commit", commitMessageP);
        gitlet("checkout", "master");

        System.out.println(gitlet("rebase", "starters"));
        assertEquals(blastoiseMaster, getText(squirtleFile)); 
        assertEquals(venosaurText, getText(bulbaFile));  // passinng
        assertEquals(raichuText, getText(pikaFile));  // not passing
        assertEquals(charText, getText(charFile));
    }

    @Test
    public void mergeTest() {
        gitlet("init");
        String dogFile = TESTING_DIR + "dog.txt";
        String dogText = "woof";
        createFile(dogFile, dogText);
        String catFile = TESTING_DIR + "cat.txt";
        String catText = "meow";
        createFile(catFile, catText);
        String cowFile = TESTING_DIR + "cow.txt";
        String cowText = "moo";
        createFile(cowFile, cowText);
        String mouseFile = TESTING_DIR + "mouse.txt";
        String mouseText = "squeak";
        createFile(mouseFile, mouseText);
        gitlet("add", catFile);
        gitlet("add", dogFile);
        gitlet("add", mouseFile);
        String commit1 = "Added cat,dog,mouse";
        gitlet("commit", commit1);
        gitlet("branch", "branch1");
        gitlet("checkout", "branch1");
        gitlet("add", cowFile);
        String dogB1Text = "b1 dog";
        writeFile(dogFile, dogB1Text);
        String catB1Text = "b1 cat";
        writeFile(catFile, catB1Text);
        gitlet("add", dogFile);
        gitlet("add", catFile);
        String mouseB1Text = "b1 mouse";
        writeFile(mouseFile, mouseB1Text);
        gitlet("add", mouseFile);
        String b1Message = "Changed cat, dog, and added cow";
        gitlet("commit", b1Message);
        System.out.println(gitlet("checkout", "master"));
        gitlet("rm", mouseFile);
        System.out.println(getText(catFile));
        String masterCatText = "master cat";
        writeFile(catFile, masterCatText);
        gitlet("add", catFile);
        System.out.println(getText(catFile));
        String masterCommit = "Changed cat and removed mouse";
        gitlet("commit", masterCommit);
        System.out.println(gitlet("merge", "branch1"));
       

        assertEquals(masterCatText, getText(catFile)); // ok
        assertEquals(dogB1Text, getText(dogFile)); // not ok
        assertEquals(mouseB1Text, getText(mouseFile)); // not ok
        assertEquals(cowText, getText(cowFile)); // ok

    }

    /**
     * Returns an array of commit messages associated with what log has printed
     * out.
     */
    private static String[] extractCommitMessages(String logOutput) {
        String[] logChunks = logOutput.split("====");
        int numMessages = logChunks.length - 1;
        String[] messages = new String[numMessages];
        for (int i = 0; i < numMessages; i++) {
            System.out.println(logChunks[i + 1]);
            String[] logLines = logChunks[i + 1].split(LINE_SEPARATOR);
            messages[i] = logLines[3];
        }
        return messages;
    }

}
