import queue.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestSorts {


	@Test
    public void PartitionTestID() {
        UserList list = new UserList();

        list.add(new User(0, 10));
        list.add(new User(1, 0));
        list.add(new User(2, 10));
        list.add(new User(3, 11));
        list.add(new User(4, 15));
		list.add(new User(5, 23));
		list.add(new User(6, 10));
		list.add(new User(7, 1));
		list.add(new User(8, 12));
 		list.add(new User(9, 33));     
		list.add(new User(10, 10));
		list.add(new User(11, 21));
        list.add(new User(12, 0));
        list.add(new User(13, 15));
        list.add(new User(14, 4));
		list.add(new User(15, 13));
		list.add(new User(16, 4));
		list.add(new User(17, 505));
		list.add(new User(18, 10));
 		list.add(new User(19, 102));     
		list.add(new User(20, 201));
        CatenableQueue<User> less = new CatenableQueue<User>();
        CatenableQueue<User> equal = new CatenableQueue<User>();
        CatenableQueue<User> greater = new CatenableQueue<User>();

        /* pivot on user 1 by id */
        list.partition("id", list.userQueue, 9, less, equal, greater);
        assertEquals(9, less.size());
        assertEquals(1, equal.size());
        assertEquals(11, greater.size());
        assertEquals(new User(0, 10), less.front());
        assertEquals(new User(9, 33), equal.front());
        assertEquals(new User(10, 10), greater.front());

    }

    @Test
    public void PartitionTestPages() {
    	UserList pagesList = new UserList();

    	pagesList.add(new User(0, 20));
        pagesList.add(new User(1, 0));
        pagesList.add(new User(2, 10));
        pagesList.add(new User(3, 11));
        pagesList.add(new User(4, 15));
		pagesList.add(new User(5, 23));
		pagesList.add(new User(6, 10));
		pagesList.add(new User(7, 1));
		pagesList.add(new User(8, 12));
 		pagesList.add(new User(9, 33));     
		pagesList.add(new User(10, 10));
		pagesList.add(new User(11, 21));
        pagesList.add(new User(12, 0));
        pagesList.add(new User(13, 15));
        pagesList.add(new User(14, 4));
		pagesList.add(new User(15, 3));
		pagesList.add(new User(16, 4));
		pagesList.add(new User(17, 505));
		pagesList.add(new User(18, 10));
 		pagesList.add(new User(19, 102));     
		pagesList.add(new User(20, 201));
		CatenableQueue<User> less = new CatenableQueue<User>();
        CatenableQueue<User> equal = new CatenableQueue<User>();
        CatenableQueue<User> greater = new CatenableQueue<User>();

        /* pivot on user 1 by id */
        pagesList.partition("pages", pagesList.userQueue, 10, less, equal, greater);
        assertEquals(6, less.size());
        assertEquals(4, equal.size());
        assertEquals(11, greater.size());
        assertEquals(new User(1, 0), less.front());
        assertEquals(new User(2, 10), equal.front());
        assertEquals(new User(0, 20), greater.front());
    }

    @Test
    public void mergeQueuesTest() {
    	CatenableQueue<User> q1 = new CatenableQueue<User>();
    	
 
    	q1.enqueue(new User(0, 7));
        q1.enqueue(new User(1, 10));
        q1.enqueue(new User(2, 12));
        q1.enqueue(new User(3, 12));
        q1.enqueue(new User(4, 12));
		q1.enqueue(new User(5, 12));
		q1.enqueue(new User(6, 21));
		q1.enqueue(new User(7, 22));
		q1.enqueue(new User(8, 34));
 		q1.enqueue(new User(9, 39));

		CatenableQueue<User> q2 = new CatenableQueue<User>();
		q2.enqueue(new User(0, 45));
		q2.enqueue(new User(1, 53));
        q2.enqueue(new User(2, 62));
        q2.enqueue(new User(3, 74));
        q2.enqueue(new User(4, 86));
		q2.enqueue(new User(5, 103));
		q2.enqueue(new User(6, 118));
		q2.enqueue(new User(7, 129));
		q2.enqueue(new User(8, 148));
 		q2.enqueue(new User(9, 153));     

		CatenableQueue<User> merged = UserList.mergeTwoQueues("pages", q1, q2);

		System.out.println(merged.toString());
	}





	public static void main(String[] args) {
		jh61b.junit.textui.runClasses(TestSorts.class);
	}
}