/*inal Submission :) 5:00pm

 * @author Cesar Villalobos
 */
public class Piece{
	private boolean waterFire;
	private Board board;
	private int xC, yC, curX, curY;
	private String kind;
	private boolean crowned = false;
	private boolean capturer = false;
	private Piece oldStorage = null;
	private Piece newStorage = null;

	public Piece(boolean isFire, Board b, int x, int y, String type){
		/* Constructor for a piece. 
		 * isFire determiners whether a piece is fire or water
		 * b represents the board that the pieces are on. 
		 * x and y is the starting position
		 * type represent the type of the piece (pawn, bomb, shield, king)
		 */
		waterFire = isFire;
		b = board;
		xC = x;
		yC = y;
		kind = type;
	}

	public boolean isFire(){
		/*returns wether a piece is fire or water*/
		if (waterFire == true){
			return true;
		}
		else{
			return false;
		}
	}

	public int side(){

		if (waterFire == true){
			return 0;
		}
		else{
			return 1;
		}

	}

	public boolean isKing(){
		/*returens wether or not a piece has been crowned*/
		if ((yC == 0) && (waterFire == false)){
			crowned = true;
			return crowned;
		}
		else if ((yC == 7) && (waterFire == true)){
			crowned = true;
			return crowned;
		}
		return false;
	}

	public boolean isBomb(){
		/*returns wether a piece is a bomb piece*/
		if (kind == "bomb"){
			return true;
		}
		return false;

	}

	public boolean isShield(){
		/*returns wether or not the piece is a shield piece*/
		if (kind == "shield"){
			return true;
		}
		return false;

	}

	public void move(int x, int y){
		/* Assumes this Piece's movement from its current position
		 * to (x, y) is valid. Moves the piece to (x, y), capturing
		 * any intermediate piece if applicable. This will be a 
		 * difficult method to write.
		 */
		int x1 = yC;
		int y1 = xC;
		int x2 = x;
		int y2 = y;

		int dY = y2-y1 ;
		int dX = x2 -x1 ;

		int midpointx = (x1 + x2) / 2;
		int midpointy = (y1 + y2) / 2;
		// int slope = Math.abs(dY / dX); 
		//store piece, remove, switch the values.


		if ((Math.abs(x2 - x1) == 2) && (Math.abs(y2 - y1) == 2)) {
			if (board.pieceAt(x1, y1).isBomb()){
				board.place(board.pieceAt(x1, y1), x2, y2);

				bombCapturing(x2, y2);
				board.remove(x1, y1);
				board.remove(x2, y2);
			}

			else if (board.pieceAt(midpointx, midpointy) != null){
				capturer = true;

				board.remove(midpointx, midpointy);
				board.place(board.pieceAt(x1, y1), x2, y2);
				if ((board.pieceAt(x2, y2).isFire()) && (y2 == 7)) {
					board.pieceAt(x2,y2).king();
				}
				else if (board.pieceAt(x2,y2).isFire() == false && (y2 == 0)){
					board.pieceAt(x2, y2).king();
				}
			}

		}
		if (((dX == 1) || (dX == -1))  && (dY == 1)){
				if (board.pieceAt(x2, y2) == null){

			// board.remove(xC, yC);
				board.place(board.pieceAt(x1,y1) , x, y);
			}
		}
		xC = x;
		yC = y;
	}

	private void king(){
		crowned = true;
	}
	public boolean hasCaptured(){
		return capturer;
	}


	private void regularCapturing(){

	}


	private void bombCapturing(int x, int y){
		Piece currentBomb = board.pieceAt(x,y);
		Piece topRight = board.pieceAt(x+1, y+1);
		Piece topLeft = board.pieceAt(x-1, y+1);
		Piece bottomLeft = board.pieceAt(x-1, y-1);
		Piece bottomRight = board.pieceAt(x+1, y-1);

		if (currentBomb.isBomb()) {
			// if (currentBomb == placeAt(0, 0)){
			// 	board.remove(x+1, y+1);
			// }
			// if (currentBomb == placeAt(7, 7))

			if (currentBomb.hasCaptured()){
				if (topLeft.isShield() ==false){
					board.remove(x-1, y+1);
				}
				if (topRight.isShield() == false){
					board.remove(x+1, y+1);
				}
				if (bottomRight.isShield() == false){
					board.remove(x+1, y-1);
				}
				if (bottomLeft.isShield() == false){
					board.remove(x-1, y-1);
				}
			}
		}
	}
	



	public void doneCapturing(){
		/*Called at the end of each turn on the Piece that moved. 
		 * Makes sure the piece's hasCaptured() returns to false
		 */
		// piece.hasCaptured();

	} 


}