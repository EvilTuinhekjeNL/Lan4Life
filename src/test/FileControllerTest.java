package test;

import controller.FileController;

public class FileControllerTest {
	public static void main(String[] args) {
		FileController fileC = FileController.getInstance();
		fileC.makeL4Lfile();
	}
}
