package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Collection;
import java.util.HashMap;

public class NGramMap {
    private TimeSeries<Long> yearlyCounts = new TimeSeries<Long>();
            //Maps Year --> Total count
    private HashMap<Integer, YearlyRecord> yearToWords =
        new HashMap<Integer, YearlyRecord>();
            //maps YEar --> Words --> Frequency
    private HashMap<String, TimeSeries<Integer>> wordsToYears = 
        new HashMap<String, TimeSeries<Integer>>();
            //maps word --> Year --> Frequency

    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {
        In wordIn = new In(wordsFilename);
        In countsIn = new In(countsFilename);
        String wordLine;

        while (wordIn.hasNextLine()) {
            wordLine = wordIn.readLine();
            // wordLine = wordLine.substring(0, wordLine.length() - 1);
            String[] rawWords = wordLine.split("\\s+");
            String w = rawWords[0];
            Integer y = Integer.parseInt(rawWords[1]);
            Integer f = Integer.parseInt(rawWords[2]);

            if (!yearToWords.containsKey(y)) {
                YearlyRecord yearlyVals = new YearlyRecord();
                yearlyVals.put(w, f);
                yearToWords.put(y, yearlyVals);
            }

            if (!wordsToYears.containsKey(w)) {
                TimeSeries<Integer> wordVals = new TimeSeries<Integer>();
                wordVals.put(y, (Integer) f);
                wordsToYears.put(w, wordVals);
            }

            if (wordsToYears.containsKey(w)) {
                wordsToYears.get(w).put(y, f);
            } else { 
                yearToWords.get(y).put(w, (Integer) f);
            }
        }
    

        while (countsIn.hasNextLine()) {
            String countLine = countsIn.readLine();
            String[] rawCount = countLine.split(",");
            Integer yr = Integer.parseInt(rawCount[0]);
            Long yrFreq = Long.parseLong(rawCount[1]);

            yearlyCounts.put(yr, yrFreq);
        }   




    }
    
    /** Returns the absolute count of WORD in the given YEAR. If the word
      * did not appear in the given year, return 0. */
    public int countInYear(String word, int year) { //change
        if (!wordsToYears.containsKey(word) || !wordsToYears.get(word).containsKey(year)) {
            return 0;
        } else { 
            return wordsToYears.get(word).get(year).intValue();
        }    
    }

    /** Returns a defensive copy of the YearlyRecord of WORD. */
    public YearlyRecord getRecord(int year) {
        YearlyRecord copyRecord = new YearlyRecord();
        TimeSeries<Integer> recordPointer = new TimeSeries<Integer>();
        for (String key : wordsToYears.keySet()) {
            recordPointer = wordsToYears.get(key);
            if (recordPointer.containsKey(year)) {
                copyRecord.put(key, recordPointer.get(year));
            }
        }

        return copyRecord;
    }

    /** Returns the total Integer of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory() {
        return yearlyCounts;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear) {

        TimeSeries<Integer> boundedHistory = new TimeSeries<Integer>();
        if (wordsToYears.containsKey(word)) { 
            TimeSeries<Integer> temporary = wordsToYears.get(word);
            for (Integer year : temporary.keySet()) {
                if (year >= startYear && year <= endYear) {
                    boundedHistory.put(year, temporary.get(year));
                }
            }
        }

        return boundedHistory;
    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word) {
        TimeSeries<Integer> unboundedHistory = new TimeSeries<Integer>();

        for (String w : wordsToYears.keySet()) {
            if (w.equals(word)) {
                TimeSeries<Integer> temp = wordsToYears.get(w);
                for (Integer yrCount : temp.keySet()) {
                    unboundedHistory.put(yrCount, temp.get(yrCount));
                }
            }
        }
        return unboundedHistory;
    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear) {
        // TimeSeries<Double> boundedWeight = new TimeSeries<Double>();
            //use yearly counts??
        
        TimeSeries<Double> boundedWeight = new TimeSeries<Double>();
        TimeSeries<Double> division = new TimeSeries<Double>();
        if (wordsToYears.containsKey(word)) {
            TimeSeries temporary = wordsToYears.get(word);
            division = temporary.dividedBy(yearlyCounts);
            for (Integer year : division.keySet()) {
                if (year >= startYear && year <= endYear) { 
                    boundedWeight.put(year, division.get(year));
                }
            }
        }
        return boundedWeight;
    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word) {
        TimeSeries<Double> ans = countHistory(word).dividedBy(totalCountHistory());
        return ans;
    }

    /** Provides the summed relative frequency of all WORDS between
      * STARTYEAR and ENDYEAR. If a word does not exist, ignore it rather
      * than throwing an exception. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words, 
                                                    int startYear, int endYear) {
        TimeSeries<Double> boundedWeightHistory = summedWeightHistory(words);
        TimeSeries<Double> addBound = 
            new TimeSeries<Double>(boundedWeightHistory, startYear, endYear);
        return addBound;

    }
    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        TimeSeries<Double> unboundWeightSum = new TimeSeries<Double>();
        for (String w : words) {
            unboundWeightSum = unboundWeightSum.plus(weightHistory(w));
        }
        return unboundWeightSum;
    }

    /** Provides processed history of all words between STARTYEAR and ENDYEAR as processed
      * by YRP. */
    public TimeSeries<Double> processedHistory(int startYear, int endYear,
                                               YearlyRecordProcessor yrp) {
        // TimeSeries<Double> pHistory = new TimeSeries<Double>();
        // System.out.println(yearlyCounts.entrySet());
        TimeSeries<Double> pHistory = new TimeSeries<Double>();
        Collection<Number> years = yearlyCounts.years();
        for (Number yr : years) { 
            if (yr.intValue() >= startYear && yr.intValue() <= endYear) {
                YearlyRecord yearR = getRecord((Integer) yr);
                Double processed = yrp.process(yearR);
                pHistory.put(yr.intValue(), processed);
            }
        }
        System.out.println(pHistory.entrySet());
        return pHistory;


    }

    /** Provides processed history of all words ever as processed by YRP. */
    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
        TimeSeries<Double> processUnbound = new TimeSeries<Double>();
        for (Integer yr : yearToWords.keySet()) {
            processUnbound.put(yr, yrp.process(yearToWords.get(yr)));
        }

        return processUnbound;

    } 
}
