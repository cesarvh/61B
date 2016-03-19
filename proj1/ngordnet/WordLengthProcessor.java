package ngordnet;


public class WordLengthProcessor implements YearlyRecordProcessor {

    public double process(YearlyRecord yearlyRecord) {
		double pointer = 0;
		double total = 0;
    	for (String w : yearlyRecord.words()){
    		pointer += w.length() * yearlyRecord.count(w);
    		total += yearlyRecord.count(w);
    	}
    	return (pointer / total);
    }
}
