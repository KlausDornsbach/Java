package engine;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class FileManagement {
	DataManagement dm = new DataManagement();
	//DataBase db;
	File file;
	Scanner scan;
	Formatter f;
	double[] array;
	
	@SuppressWarnings("resource")
	public void openFile(String address, DataBase db) throws FileNotFoundException{
		try {
										//instantiating DataBase for file manager
			File file = new File("rawFiles\\"+address+".csv");			//parent folder; address of .csv that we are analyzing
			scan = new Scanner(file).useDelimiter(",|\\n");	//setting delimiter for csv file
   
			jump(6, scan);									//jumping first line of csv
		
			for(int i = 0; i<100; i++) {//putting values to the arrays at database
				for(int j = 0; j<6; j++) {
					if(j==0) {
						db.addTimeStamp(i, scan.next());
					}else if(j==4) {
						db.addClose(i, scan.next());
					}else {
						scan.next();
					}
				}
			}
			fillArrays(db);//filling bollinger arrays and mobile average
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*public DataBase getDataBase() {
		return db;
	}*/
	
	public boolean fileExists() {
		return file.exists();
	}
	
	public void closeFile(){
		if(f != null){
			f.close();
		}
	}
	public void jump(int n, Scanner s) {
		for(int i = 0; i<n; i++) {
			s.next();
		}
	}
	
	public void setArray(int a, DataBase db) {//this method fills the variable Array array with the 20 data positions i want to analyze
		for(int i = 0; i<20; i++) {
			array[i]=Double.parseDouble(db.getClose()[a+i]);//converts from string to double
			
		}
		/*
		for(int k = 0; k<20; k++) {
			System.out.println(array[k]);
		}
		System.out.println("-----------");*/
	}
	
	public void fillArrays(DataBase db) {
		array = new double[20];
		for(int i = 79; i>=0; i--) {
			setArray(i, db);
			db.addMediaMovel(i, dm.calculaMedia(array));
			db.addDesvioPadrao(i, dm.calculaDesvioPadrao(array));
			db.addBollingerInferior(i, dm.calculaBollingerInferior(db.getMediaMovel()[i], db.getDesvioPadrao()[i]));
			db.addBollingerSuperior(i, dm.calculaBollingerSuperior(db.getMediaMovel()[i], db.getDesvioPadrao()[i]));
			/*System.out.println(dm.calculaMedia(array));
			System.out.println(dm.calculaDesvioPadrao(array));
			System.out.println(dm.calculaBollingerInferior(db.getMediaMovel()[i], db.getDesvioPadrao()[i]));
			System.out.println(dm.calculaBollingerSuperior(db.getMediaMovel()[i], db.getDesvioPadrao()[i]));*/
		}
	}
	
}