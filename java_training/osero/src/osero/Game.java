package osero;

import java.util.Scanner;

public class Game {
	
	public int[][]board = new int[8][8];
	public boolean isEnd = false;
	public int BOARD_SIZE_X_TO = 7;
	public int BOARD_SIZE_Y_TO = 7;
	public int BOARD_SIZE_X_FROM = 0;
	public int BOARD_SIZE_Y_FROM = 0;
	
	public enum AddSt {
		Xadd,
		Yadd,
		XandYadd;
	}
	
	public boolean run() {
		board = init();
		
		while(!isEnd) {
			turn(Status.BLACK);
			turn(Status.WHITE);
			isEnd = isEnd();
		}
		
		return true;
	}
	
	
	public int[][] init() {		
		for(int x=0; x<board.length; x++) {
			for (int y=0; y<board[x].length; y++) {
				board[x][y] =  Status.NONE.getStatusCode();
			}
		}
		
		board[3][4] = Status.BLACK.getStatusCode();
		board[4][3] = Status.BLACK.getStatusCode();
		board[3][3] = Status.WHITE.getStatusCode();
		board[4][4] = Status.WHITE.getStatusCode();
		
		show();
		
		return board;
	}
	
	public boolean isEnd() {
		for (int x=0; x<board.length; x++) {
			for(int y=0; y<board[x].length; y++) {
				if (board[x][y] == Status.NONE.getStatusCode()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void show() {
		
		int xIndex = 2;
		
		for(int x=0; x<board.length; x++) {
			if (x == 0) {
				System.out.print(" ");
			} else {
				System.out.print(xIndex);
				xIndex++;
			}
			
			for (int y=0; y<board[x].length; y++) {
					
					if (x == 0 && y==0) {
						System.out.print(" ");
						System.out.print("1");
						System.out.print(" ");
						System.out.print("2");
						System.out.print(" ");
						System.out.print("3");
						System.out.print(" ");
						System.out.print("4");
						System.out.print(" ");
						System.out.print("5");
						System.out.print(" ");
						System.out.print("6");
						System.out.print(" ");
						System.out.print("7");
						System.out.print(" ");
						System.out.print("8");
						System.out.print(" ");
						System.out.println();
						System.out.print("1");
					}
					
					switch(board[x][y]) {
					case 1://BKACK
						System.out.print("○");
						break;
					case 2://WHITE
						System.out.print("●");
						break;
					case 0:
						System.out.print("・");
						break;
					default:
						throw new IllegalStateException();
				}
			}
			System.out.println();
		}
	}
	
	public void turn(Status zibun) {
		
		int setX = 10;
		int setY = 10;
		Status aite = Status.NONE;
		String message = "NullPointer";
		if (Status.BLACK.getStatusCode() == zibun.getStatusCode()) {
			message = "黒番";
			aite = Status.WHITE;
		} else {
			message = "白番";
			aite = Status.BLACK;
		}
		
		System.out.println(message + "です。");
		Scanner scanner = new Scanner(System.in);
		boolean isFix = false;
		while (!isFix) {
			System.out.print("駒を置く縦位置を指定してください。＞");
			setX = Integer.parseInt(scanner.nextLine()) -1;
			System.out.print("駒を置く横位置を指定してください。＞");
			setY = Integer.parseInt(scanner.nextLine()) -1;
			isFix = canSet(setX,setY,zibun, aite);
			if (!isFix) {
				System.out.println("その位置にはすでに駒があるか、リバーシできません。");
			} else {
				doReversi(setX, setY, zibun, aite);
			}
		}
		board[setX][setY] = zibun.getStatusCode();
		
		show();
	}
	
	public boolean canSet(int setX, int setY, Status zibun, Status aite) {
		
		if (setX<0 || setY<0 || setX>7 || setY>7) {
			return false;
		}
		
		
		if (setX>7 && setY>7 ) {
			return false;
		}
	
		switch (board[setX][setY]) {
		case 1:
			return false;
		case 2:
			return false;
		case 0:
			return canSetByRule(setX, setY, zibun, aite);
		default:
			throw new IllegalArgumentException();
		}
		
	}
	
	public boolean canSetByRule(int setX, int setY, Status zibun, Status aite) {
		
		//tate
		if (canSetMatrix(setX,setY,zibun,aite,"+","")) {
			return true;
		}
		if (canSetMatrix(setX,setY,zibun,aite,"-","")) {
			return true;
		}
		
		//yoko
		if (canSetMatrix(setX,setY,zibun,aite,"","+")) {
			return true;
		}
		if (canSetMatrix(setX,setY,zibun,aite,"","-")) {
			return true;
		}
		
		//naname
		if (canSetMatrix(setX,setY,zibun,aite,"+","+")) {
			return true;
		}
		if(canSetMatrix(setX,setY,zibun,aite,"-","-")) {
			return true;
		}
		if (canSetMatrix(setX,setY,zibun,aite,"+","-")) {
			return false;
		}
		if (canSetMatrix(setX,setY,zibun,aite,"-","+")) {
			return false;
		}
		//default
		return false;
	}
	
	public boolean canSetMatrix(int setX, int setY, Status zibun, Status aite, String addOrDecX, String addOrDecY) {

		boolean maybeCanReversi = false;
		for (int x = setX ,y = setY; 
				(("+".equals(addOrDecX) || "+".equals(addOrDecY)) && x<=7 && y<=7) 
						|| (("-".equals(addOrDecX) || "-".equals(addOrDecY)) && x>=0 && y>=0); 
				x=addXY(x, addOrDecX), y=addXY(y, addOrDecY)) {
			
			int targetSt = board[x][y];
			if (targetSt == zibun.getStatusCode()) {
				if (maybeCanReversi) {
					return true;
				} else {
					return false;
				}
			} else if (targetSt == aite.getStatusCode()) {
				maybeCanReversi = true;
			}
		}
		
		return false;
		
	}
	
	private int addXY(int tarInt, String addOrDec) {
		if ("+".equals(addOrDec)) {
			return tarInt+1;
		} else if ("-".equals(addOrDec)) {
			return tarInt-1;
		} else {
			return tarInt;
		}
	}

	public void doReversi(int setX, int setY, Status zibun, Status aite) {
		
		boolean tateP = canSetMatrix(setX,setY,zibun,aite,"+","");
		boolean tateM = canSetMatrix(setX,setY,zibun,aite,"-","");
		boolean yokoP = canSetMatrix(setX,setY,zibun,aite,"","+");
		boolean yokoM = canSetMatrix(setX,setY,zibun,aite,"","-");
		boolean nanaP = canSetMatrix(setX,setY,zibun,aite,"+","+");
		boolean nanaM = canSetMatrix(setX,setY,zibun,aite,"-","-");
		boolean nanaPR = canSetMatrix(setX,setY,zibun,aite,"+","-");
		boolean nanaMR = canSetMatrix(setX,setY,zibun,aite,"-","+");
		
		if (tateP) {
			execute(setX,setY,zibun,aite,"+","");
		}
		if (tateM) {
			execute(setX,setY,zibun,aite,"-","");
		}
		if (yokoP) {
			execute(setX,setY,zibun,aite,"","+");
		}
		if (yokoM) {
			execute(setX,setY,zibun,aite,"","-");
		}
		if (nanaP) {
			execute(setX,setY,zibun,aite,"+","+");
		}
		if (nanaM) {
			execute(setX,setY,zibun,aite,"-","-");
		}
		if (nanaPR) {
			execute(setX,setY,zibun,aite,"+","-");
		}
		if (nanaMR) {
			execute(setX,setY,zibun,aite,"-","+");
		}
	}
	
	public void execute(int setX, int setY, Status zibun, Status aite, String addOrDecX, String addOrDecY) {
		board[setX][setY] = zibun.getStatusCode();
		for (int x = setX ,y = setY; 
				(("+".equals(addOrDecX) || "+".equals(addOrDecY)) && x<7 && y<7) 
						|| (("-".equals(addOrDecX) || "-".equals(addOrDecY)) && x>0 && y>0); 
				x=addXY(x, addOrDecX), y=addXY(y, addOrDecY)) {
			if (board[x][y] == aite.getStatusCode()) {
				board[x][y] = zibun.getStatusCode();
			}
		}
	}
	
}
