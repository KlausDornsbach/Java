package gui;

import java.awt.Canvas;
import java.lang.Math;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import engine.Acao;
import engine.DataBase;

public class GraphManager extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Acao a;
	public DataBase db; 			//import database to know the boundaries of the graphs
	public final int XCte = 16;
	public final int YCte = 40;
	public int SCREENHEIGHT;
	public final Color bgColor = new Color(31, 31, 31);
	public int scalar;
	public double scalar2;
	
	public GraphManager(Acao a, int scalar) {			//we instantiate with information about how should we scale the gharph
		db = a.getDataBase();
		this.a = a;
		SCREENHEIGHT = (int) Math.round(db.getMaiorBlSup()*100-db.getMenorBlnf()*100);
		this.setBounds(0, 0, 79*XCte/scalar, 800/scalar);		
		this.setBackground(bgColor);
		this.scalar = scalar;
		scalar2 = (double) SCREENHEIGHT/600;
		System.out.println(SCREENHEIGHT);
		
	}
	@Override
	public void paint(Graphics g) {
		for(int i = 79; i>1; i--) {
			g.setColor(Color.RED);					//draw mobile media
			/*System.out.println((79-i)*15);
			System.out.println(4*YCte - (int) ((db.getMediaMovel()[i]-db.getMenorMedia())/4*YCte));
			System.out.println((80-i)*15);
			System.out.println(4*YCte - (int) (db.getMediaMovel()[i-1]-db.getMenorMedia()/4*YCte));
			*/
			//System.out.println((int) ((db.getMediaMovel()[i]-db.getMenorMedia())/4*YCte));
			g.drawLine((int) ((79-i)*XCte/scalar), (int) ((Math.round(SCREENHEIGHT-(db.getMediaMovel()[i]*100-db.getMenorBlnf()*100))/scalar)/scalar2),(int) ((80-i)*XCte/scalar), (int) (((Math.round(SCREENHEIGHT-(db.getMediaMovel()[i-1]*100-db.getMenorBlnf()*100))/scalar))/scalar2));//drawing lines in the graphic
		//System.out.println("\nx1: "+(int) ((79-i)*XCte/scalar)+"\ny1: "+(int) (Math.round((SCREENHEIGHT-(db.getMediaMovel()[i]*100-db.getMenorBlnf()*100)))/scalar/scalar2)+"\nx2: "+(int) ((80-i)*XCte/scalar)+"\ny2: "+(int) ((SCREENHEIGHT-Math.round((db.getMediaMovel()[i-1]*100-db.getMenorBlnf()*100)))/scalar/scalar2));
			g.setColor(Color.BLUE);					//draw inferior and superior bollinger bands
			g.drawLine((int) ((79-i)*XCte/scalar), (int) ((Math.round(SCREENHEIGHT-(db.getBollingerSuperior()[i]*100-db.getMenorBlnf()*100))/scalar)/scalar2), (int) ((80-i)*XCte/scalar), (int) (((Math.round(SCREENHEIGHT-(db.getBollingerSuperior()[i-1]*100-db.getMenorBlnf()*100))/scalar))/scalar2));//drawing lines in the graphic
			g.drawLine((int) ((79-i)*XCte/scalar), (int) ((Math.round(SCREENHEIGHT-(db.getBollingerInferior()[i]*100-db.getMenorBlnf()*100))/scalar)/scalar2), (int) ((80-i)*XCte/scalar), (int) (((Math.round(SCREENHEIGHT-(db.getBollingerInferior()[i-1]*100-db.getMenorBlnf()*100))/scalar))/scalar2));//drawing lines in the graphic
			g.setColor(Color.GREEN);
		//System.out.println("escalar(dependendo da quantidade de stocks): "+scalar+"\nescalar 2(dependendo da diferenca entre bollinger superior e inferior): "+scalar2);
			g.drawLine((int)(((79-i)*XCte)/scalar), (int) ((Math.round(SCREENHEIGHT-(Double.parseDouble(db.getClose()[i])*100 - db.getMenorBlnf()*100))/scalar)/scalar2), (int)((80-i)*XCte/scalar),(int) ((Math.round(SCREENHEIGHT-(Double.parseDouble(db.getClose()[i-1])*100 - db.getMenorBlnf()*100))/scalar)/scalar2));
			g.setColor(Color.WHITE);
			
			g.drawString(a.getNome(), ((79*XCte/2)/scalar)-10, 30);
			
			/*int unidade = (int) (SCREENHEIGHT*scalar*scalar2);
			int inicio = (int) (unidade*(db.getMaiorBlSup()-(Math.floor((db.getMaiorBlSup())))));
			int var2 = inicio;
			for(int k =(int) (db.getMaiorBlSup()); k>db.getMenorBlnf(); k--) {
				g.drawString(Integer.toString(k), 20/scalar, var2);
				var2+=unidade;
				//System.out.println(db.getMaiorBlSup());
				//System.out.println(db.getMenorBlnf());
				
				
			}*/
			
		//	g.drawString( (int) ((Math.round(SCREENHEIGHT-(Double.parseDouble(db.getClose()[i]) - db.getMenorBlnf()))/scalar)/scalar2))//numeros
			g.drawString("DATE: "+db.getTimeStamp()[0], ((79*XCte)/scalar)-250, 30);//data
		//	g.drawString(), x, y);//compra e vende
			
			/*if(Double.parseDouble(db.getClose()[i])>Double.parseDouble(db.getClose()[i-1])) {
				g.drawLine((int)(((80-i)*XCte)/scalar), (int) ((Math.round(SCREENHEIGHT-(Double.parseDouble(db.getClose()[i])*100 - db.getMenorBlnf()*100))/scalar)/scalar2), (int)((80-i)*XCte/scalar),(int) ((Math.round(SCREENHEIGHT-(Double.parseDouble(db.getClose()[i])*100 - db.getMenorBlnf()*100))/scalar)/scalar2) - (int) ((Math.round(SCREENHEIGHT-(Double.parseDouble(db.getClose()[i-1])*100 + db.getMenorBlnf()*100))/scalar)/scalar2));
			}*/
			//(int) ((SCREENHEIGHT-(db.getMediaMovel()[i]*100-db.getMenorBlnf()*100))/scalar/scalar2)
		}
	}
}
