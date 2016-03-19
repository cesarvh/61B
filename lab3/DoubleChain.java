public class DoubleChain {
	
	private DNode head;
	public int size = 0;
	

	public DoubleChain(double val) {
		/* your code here. */
		size = 1;
		head = new DNode(head, val, head);
	}

	public DNode getFront() {
		return head;
	}

	/** Returns the last item in the DoubleChain. */		
	public DNode getBack() {
		/* your code here */
		DNode n = head;
		if (n != null){
			while (n.next != null){
			n = n.next;
			}
		}
		return n;
	}
		// if (n.next == null){
		// 	return n;
		//}
		//else{
		
		//	}
		//return n;
	//	}
	//}
	
	/** Adds D to the front of the DoubleChain. */	
	public void insertFront(double d) {




	DNode dFront = new DNode(null, d, head);
	head.prev = dFront;

	head = dFront;
	// head.next = newD;

	}
	
	/** Adds D to the back of the DoubleChain. */	
	public void insertBack(double d) {
		/* your code here */
      DNode n = head;
      while (n.next != null){
         n = n.next;}
      
	   DNode dBack = new DNode(n, d, null);
      
      n.next = dBack;
	}
	
	/** Removes the last item in the DoubleChain and returns it. 
	  * This is an extra challenge problem. */
	public DNode deleteBack() {
		/* your code here */
	

		// return getBack();
		return null;

	}
	
	/** Returns a string representation of the DoubleChain. 
	  * This is an extra challenge problem. */
	public String toString() {
		/* your code here */		
		return null;
	}

	public static class DNode {
		public DNode prev;
		public DNode next;
		public double val;
		
		private DNode(double val) {
			this(null, val, null);
		}
		
		private DNode(DNode prev, double val, DNode next) {
			this.prev = prev;
			this.val = val;
			this.next =next;
		}
	}
	
}