import java.awt.FlowLayout;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FileUploading extends JFrame {
	public static void main(String[] args) {
		new FileUploading();
	}

	private JButton upload;
	private JButton download;
	private BufferedOutputStream bs;

	public FileUploading() {
		super.setVisible(true);
		super.setTitle("DB파일 관리 시스템");

		super.setLayout(new FlowLayout());

		upload = new JButton("업로드");
		download = new JButton("다운로드");

		super.add(upload);
		super.add(download);

		super.setSize(500, 500);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);

		download.addActionListener(e -> {
			bfTxtOutput();
		});

		upload.addActionListener(e -> {
			bfTxtInput();
		});

	}

	private void bfTxtOutput() {
		try {
			bs = new BufferedOutputStream(new FileOutputStream("D:/JSwingProject/Db_FileUploading_Project/Output.txt"));
			String str = "테스트 텍스트 입니다.";
			bs.write(str.getBytes());

			bs.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void bfTxtInput() {
		try {
			// 바이트 단위로 파일읽기
//			String filePath = "D:/JSwingProject/Db_FileUploading_Project/Output.txt"; // 대상 파일
//			FileInputStream fileStream = null; // 파일 스트림

			FileInputStream fileStream = new FileInputStream("D:/JSwingProject/Db_FileUploading_Project/Output.txt");// 파일 스트림 생성
			// 버퍼 선언
			byte[] readBuffer = new byte[fileStream.available()]; // 현재 읽을수 있는 바이트수를 반환한다.
			while (fileStream.read(readBuffer) != -1); // 한번에 읽고 
			
			System.out.println(new String(readBuffer)); // 출력

			fileStream.close(); // 스트림 닫기
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void txtOutput() {
		try {
			OutputStream output = new FileOutputStream("D:/JSwingProject/Db_FileUploading_Project/Output.txt");

			String str = "중복 아웃풋 입니다.";

			byte[] by = str.getBytes();

			output.write(by);

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	private void txtInput() {
		File file = new File("D:/JSwingProject/Db_FileUploading_Project/Output.txt");

		try {
			FileReader file_reader = new FileReader(file);
			int cur = 0;
			while ((cur = file_reader.read()) != -1) {
				System.out.print((char) cur);
			}

			file_reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
