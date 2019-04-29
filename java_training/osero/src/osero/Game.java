package osero;

import java.util.Scanner;

public class Game {
	
	public int[][]board = new int[8][8];
	public boolean isEnd = false;
	
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
		int tarX = setX;
		int tarY = setY;
		boolean canAiteKomaReversi = false;
		
		//tate
		tarX = setX + 1;
		while(tarX < 7) {
			if (board[tarX][setY] == aite.getStatusCode()) {
				if (tarX == setX+1) {
					canAiteKomaReversi = true;
				}
			} else if (board[tarX][setY] == zibun.getStatusCode() && canAiteKomaReversi) {
				return true;
			}
			tarX++;
		}
		canAiteKomaReversi = false;
		tarX = setX - 1 ;
		while (tarX > 0) {
			if (board[tarX][setY] == aite.getStatusCode()) {
				if (tarX == setX-1) {
					canAiteKomaReversi = true;
				}
			} else if (board[tarX][setY] == zibun.getStatusCode() && canAiteKomaReversi) {
				return true;
			}
			tarX--;
		}
		//yoko
		canAiteKomaReversi = false;
		tarY = setY + 1;
		while(tarY < 7) {
			if (board[setX][tarY] == aite.getStatusCode()) {
				if (tarY == setY+1) {
					canAiteKomaReversi = true;
				}
			} else if (board[setX][tarY] == zibun.getStatusCode() && canAiteKomaReversi) {
				return true;
			}
			tarY++;
		}
		canAiteKomaReversi = false;
		tarY = setY - 1;
		while (tarY > 0) {
			if (board[setX][tarY] == aite.getStatusCode()) {
				if (tarY == setY-1) {
					canAiteKomaReversi = true;
				}
			} else if (board[setX][tarY] == zibun.getStatusCode() && canAiteKomaReversi) {
				return true;
			}
			tarY--;
		}
		
		//naname
		canAiteKomaReversi = false;
		tarY = setY + 1;
		tarX = setX + 1;
		while (tarY<7 && tarX<7) {
			if (board[tarX][tarY] == aite.getStatusCode()) {
				if (tarX == setX+1 && tarY == setY+1) {
					canAiteKomaReversi = true;
				}
			} else if (board[tarX][tarY] == zibun.getStatusCode() && canAiteKomaReversi) {
				return true;
			}
			tarY++;
			tarX++;
		}
		canAiteKomaReversi = false;
		tarY = setY - 1;
		tarX = setX - 1;
		while (tarY>0 && tarX>0) {
			if (board[tarX][tarY] == aite.getStatusCode()) {
				if (tarX == setX-1 && tarY == setY-1) {
					canAiteKomaReversi = true;
				}
			} else if (board[tarX][tarY] == zibun.getStatusCode() && canAiteKomaReversi) {
				return true;
			}
			tarY--;
			tarX--;
		}
		
		return false;
	}

	public void doReversi(int setX, int setY, Status zibun, Status aite) {
		int tarX = setX;
		int tarY = setY;
		boolean isFinishThisLine = false;
		
		//tate
		tarX = setX + 1;
		while(!isFinishThisLine) {
			if (board[tarX][setY] == aite.getStatusCode()) {
				board[tarX][setY] = zibun.getStatusCode();
			} else if (tarX == 7 || board[tarX][setY] == zibun.getStatusCode()) {
				isFinishThisLine = true;
			} else {
				break;
			}
			tarX++;
		}
		isFinishThisLine = false;
		tarX = setX - 1 ;
		while (!isFinishThisLine) {
			if (board[tarX][setY] == aite.getStatusCode()) {
				board[tarX][setY] = zibun.getStatusCode();
			} else if (tarX == 0 || board[tarX][setY] == zibun.getStatusCode()) {
				isFinishThisLine = true;
			} else {
				break;
			}
			tarX--;
		}
		//yoko
		isFinishThisLine = false;
		tarY = setY + 1;
		while(!isFinishThisLine) {
			if (board[setX][tarY] == aite.getStatusCode()) {
				board[setX][tarY] = zibun.getStatusCode();
			} else if (tarY == 7 || board[setX][tarY] == zibun.getStatusCode()) {
				isFinishThisLine = true;
			} else {
				break;
			}
			tarY++;
		}
		isFinishThisLine = false;
		tarY = setY - 1;
		while (!isFinishThisLine) {
			if (board[setX][tarY] == aite.getStatusCode()) {
				board[setX][tarY] = zibun.getStatusCode();
			} else if (tarY == 0 || board[setX][tarY] == zibun.getStatusCode()) {
				isFinishThisLine = true;
			} else {
				break;
			}
			tarY--;
		}
		
		//naname
		isFinishThisLine = false;
		tarY = setY + 1;
		tarX = setX + 1;
		while (!isFinishThisLine) {
			if (board[tarX][tarY] == aite.getStatusCode()) {
				board[tarX][tarY] = zibun.getStatusCode();
			} else if (tarY == 7 || tarX == 7 || board[tarX][tarY] == zibun.getStatusCode()) {
				isFinishThisLine = true;
			} else {
				break;
			}
			tarY++;
			tarX++;
		}
		isFinishThisLine = false;
		tarY = setY - 1;
		tarX = setX - 1;
		while (!isFinishThisLine) {
			if (board[tarX][tarY] == aite.getStatusCode()) {
				board[tarX][tarY] = zibun.getStatusCode();
			} else if (tarX == 0 || tarY == 0 || board[tarX][tarY] == zibun.getStatusCode()) {
				isFinishThisLine = true;
			} else {
				break;
			}
			tarY--;
			tarX--;
		}
	}
	
}
