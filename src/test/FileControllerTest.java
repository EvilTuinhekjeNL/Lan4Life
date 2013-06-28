package test;

import controller.FileController;
import controller.ScoreController;

public class FileControllerTest {
	public static void main(String[] args) {
		FileController fileC = FileController.getInstance();
		ScoreController.getInstance().newGame();
		fileC.makeL4Lfile();
	}
}
