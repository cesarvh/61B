package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.*;


public class NgordnetUI {
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("What Synset fie would you like to use?");
        String synsetFile = userInputScanner.nextLine();

        System.out.println("What Hyponyms file would you like to use?");
        String hyponymFile = userInputScanner.nextLine();

        System.out.println("What Counts File would you like to use?");
        String countFile = userInputScanner.nextLine();

        System.out.println("What Words file would you like to use?");
        String wordFile = userInputScanner.nextLine();

        System.out.println("\nBased on your input, the program is running using the following: "
                           + wordFile + ", " + countFile + ", " + synsetFile +
                           ", and " + hyponymFile + ".");



        NGramMap ngm = new NGramMap("./ngrams/" + wordFile, "./ngrams/" + countFile);
        WordNet wn = new WordNet("./wordnet/" + synsetFile, "./wordnet/" + hyponymFile);
        WordLengthProcessor wlp = new WordLengthProcessor();
        int startDate = 1505; 
        int endDate = 2008;               

            while (true) {
                try {
                System.out.print("> ");
                String line = StdIn.readLine();
                String[] rawTokens = line.split(" ");
                String command = rawTokens[0];
                String[] tokens = new String[rawTokens.length - 1];
                System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);

                // catch illegal argument exceptions in count, history and hypohist.
                
                switch (command) {
                    case "quit":
                        return;
                    case "help": 
                        In inhelp = new In("help.txt");
                        String helpStr = inhelp.readAll();
                        System.out.println(helpStr);
                        break;
                    case "range":
                        if (tokens.length < 1){
                            startDate = 1505;
                            endDate = 2008;
                            System.out.println("New range is now " + startDate + " through " + endDate);
                            break;
                        }
                        startDate = Integer.parseInt(tokens[0]); 
                        endDate = Integer.parseInt(tokens[1]);
                        if (startDate < 1505 || endDate > 2008){
                            System.out.println("These date are invalid. Data only avaliable"  
                                + " for years 1505 through 2008");
                            break;
                        }
                        System.out.println("New range is now " + startDate + " through " + endDate);
                        break;
                    case "count":
                        if (tokens.length < 2){
                            System.out.println("Must include a year and word!");
                            break;
                        }

                        Integer yr = Integer.parseInt(tokens[1]);
                        String word = tokens[0];
                        Long count = (long) ngm.countInYear(word, yr);
                        System.out.println(count);
                        break;
                    case "hyponyms":
                        if (tokens.length < 1){
                            System.out.println("Can you not.");
                            break;
                        }
          
                        ArrayList<String> returns = new ArrayList<String>();
                        if (wn.nouns().contains(tokens[0])) {
                            for (String noun : wn.hyponyms(tokens[0])) {
                                returns.add(noun);
                            }
                            System.out.println(returns); 
                            wn.hyponyms(tokens[0]);
                            break;
                        } else {
                            System.out.println("That word doesn't exist!");
                            break;
                        }
                    case "history":
                        if (tokens.length < 1) {
                            System.out.println("Must include a word or set of words");
                            break;
                        }
                        Plotter.plotAllWords(ngm, tokens, startDate, endDate);    
                        break;
                    case "hypohist":
                        if (tokens.length < 1) {
                            System.out.println("Must include a word");
                            break;
                        }
                        Plotter.plotCategoryWeights(ngm, wn, tokens, startDate, endDate);
                        break;
                    case "wordlength":
                        System.out.println("Processing word lengths... Please wait.");
                        Plotter.plotProcessedHistory(ngm, startDate, endDate, wlp); 
                        break;
                    case "zipf":
                        if (tokens.length < 1) {
                            System.out.println("You must include a year.");
                            break;
                        }
                        Integer year = Integer.parseInt(tokens[0]);
                        Plotter.plotZipfsLaw(ngm, year);
                        break;
                    default:
                        System.out.println("This isnt a valid command. The valid commands are:");
                        System.out.println("quit " + "help " + "range " + "count " 
                            + "history "   + "hyponyms " + "hypohist");
                        System.out.println("For help with these commands, type 'help'");
                        break;
                    }
                } 
            catch (Exception e) {
                System.out.println("Bad input. Please type \'help\' for a list of commands.");
            } 
                // return;
            }
        }
    }

