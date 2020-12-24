package engine;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class APIConnector {
	public void getFileFromAPI(String name) {						//String "name" is gonna be file we create from api's data
		System.out.println(name);
		StringBuffer stringBuffer = new StringBuffer();				//declare and instantiate string buffer
	    byte[] data = null;											//declare variable data (later filled with contents from api)

	    DecimalFormat decimalFormat = new DecimalFormat("###.###");	//decimal format

	    File file = new File("rawFiles\\"+name+".csv");							//file name is parameter we give to the method

	    try {
	        URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + name + "&interval=5min&apikey=23145&datatype=csv"); //api url
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();//connector (makes http request)

	        System.out.println("Connected");

	        InputStream inputStream = connection.getInputStream();					//getting data from web

	        long read_start = System.nanoTime();									//calculate time we need to finish process

	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

	        int i;

	        while ((i = reader.read()) != -1) {
	            char c = (char) i;
	            if(c == '\n') {
	                stringBuffer.append("\n");
	            }else {
	                stringBuffer.append(String.valueOf(c));
	            }
	        }

	        reader.close();

	        long read_end = System.nanoTime();

	        System.out.println("Finished reading response in " + decimalFormat.format((read_end - read_start)/Math.pow(10,9)) + " seconds");


	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException b) {
	        b.printStackTrace();
	    }

	    finally {
	        data = stringBuffer.toString().getBytes();
	    }
	    
        try (FileOutputStream fop = new FileOutputStream(file)) {
        	 
            if (!file.exists()) {
                file.createNewFile();
            }
 
            System.out.println("Initializing write.....");
 
            long now = System.nanoTime();
 
            fop.write(data);
            fop.flush();
            fop.close();
 
            System.out.println("Finished writing CSV in " + decimalFormat.format((System.nanoTime() - now)/Math.pow(10,6)) + " milliseconds!");
 
 
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        System.gc();
	}
}
