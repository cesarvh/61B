import java.util.AbstractList;

public class ArrayList61B<Fuckboy> extends AbstractList<Fuckboy>{
	private Fuckboy[] items;
	private int size;

	/* *
	 * Constructor for the ArrayList61B class. 
	 * Initializes the size of the internal array to be initialCapacity
	 * and throw an IllegalArgumentException if  initialCapacity is less than 1
	 */
	public ArrayList61B(int initialCapacity){
	//Fuckboy[] A = (Fuckboy[]) new Object[10];
		if (initialCapacity < 1){
			throw new IllegalArgumentException("Can't have array of 0 or less items!");
		} else{
			items = (Fuckboy[]) new Object[initialCapacity];
			size = 0;
		}
	}

	/**
	 * This constructor should initialize the size of the internal array to 1 */
	public ArrayList61B(){
		this(1);
	}

	/** 
	 * @return the ith element in the list. 
	 * @throw IllegalArgumentException if i is < 0 or >= size
	 */
	public Fuckboy get(int i){
		if (i < 0 || i >= size){
			throw new IllegalArgumentException("No can do, amigo");
		} else {
			return items[i];
		}
	}

	/**
	 * adds item to the end of the list, resizing the internal array if necessary
	 * @return true, always.
	 */
	public boolean add(Fuckboy item){

		if (size == items.length){
			Fuckboy[] newArr = (Fuckboy[]) new Object[size * 2];
			System.arraycopy(items, 0, newArr, 0, size);
			items = newArr;
			items[size] = item;			
		}
		items[size] = item;
		size = size + 1;
		return true;

	}



	/* @returns number of elems in the list. Not necessarily same as 
	 * number of elems in the internal array 
	 */
	public int size(){
		return this.size;
	}


}