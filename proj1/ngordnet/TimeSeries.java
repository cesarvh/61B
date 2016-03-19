package ngordnet;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeSet;



public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {    
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
    }

    /** Returns the years in which this time series is valid. Doesn't really
      * need to be a NavigableSet. This is a private method and you don't have 
      * to implement it if you don't want to. */
    private NavigableSet<Integer> validYears(int startYear, int endYear) {
        TreeSet<Integer> dummy = new TreeSet<Integer>();
        return dummy;
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR. 
     * inclusive of both end points. */
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear) {   
        super(ts.subMap(startYear, true, endYear, true));
    }

    /** Creates a copy of TS. */
    public TimeSeries(TimeSeries<T> ts) {
        super(ts);

    }

    /** Returns the quotient of this time series divided by the relevant value in ts.
      * If ts is missing a key in this time series, return an IllegalArgumentException. */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> divideSeries = new TimeSeries<Double>();
        for (Integer yr : this.keySet()) {
            if (!ts.containsKey(yr)) {
                throw new IllegalArgumentException("Can't divide by 0");
            } else { 
                Double ans = this.get(yr).doubleValue() / ts.get(yr).doubleValue();
                divideSeries.put(yr, ans); 
            }
        }
        return divideSeries;
    }

    /** Returns the sum of this time series with the given ts. The result is a 
      * a Double time series (for simplicity). */
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> plusSeries = new TimeSeries<Double>();

        for (Integer yr : this.keySet()) {
            if (ts.containsKey(yr)) {
                Double ans = this.get(yr).doubleValue() + ts.get(yr).doubleValue();
                // plusSeries.put(yr, valueThis + valueTs);
                plusSeries.put(yr, ans);
            } else {
                plusSeries.put(yr, this.get(yr).doubleValue());
            }
        }
        for (Integer yr2 : ts.keySet()) {
            if (!this.containsKey(yr2)) {
                Double ans2 = ts.get(yr2).doubleValue();
                plusSeries.put(yr2, ans2);
            }
        }
        return plusSeries;
    }

    /** Returns all years for this time series (in any order). */
    public Collection<Number> years() {
        ArrayList<Number> yearsArray = new ArrayList<Number>();
        for (Number year : this.keySet()) {
            // System.out.println(year);
            yearsArray.add(year);
        }
        return yearsArray;
    }

    /** Returns all data for this time series in any order*/
    public Collection<Number> data() {
        ArrayList<Number> dataArray = new ArrayList<Number>();
        for (Number datum : this.keySet()) {
            dataArray.add(this.get(datum));
        }
        return dataArray;
    }
}
