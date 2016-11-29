import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
/**
 * 
 * @author Stas Rudevitsky
 * 
 * This project finds  all  sub folders in directory,and converts the html files to txt files.

 * 
 * Download next Jar files:
 * http://commons.apache.org/proper/commons-io/
 * https://jsoup.org/download
 * 
 *  project's properties---> Java Build Path ---> Libraries tab ---> Add External Jars
 *
 */


public class Converter {


	public static void main(String[] args)  {

		File currentDir = new File("/Users/stas/Desktop/ig");  //change to your path

		htmlTotxtFiles(currentDir);
	}



	public static void htmlTotxtFiles(File dir) {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				htmlTotxtFiles(file);
			} else {

				if (!file.getName().toString().equals(".DS_Store")){				
					
					htmlToText(file);

				}

			}
		}
	}


	public static void htmlToText(File file){
		try {
			String str = FileUtils.readFileToString(file, "UTF-8"); //Html file to String
			String page=Jsoup.parse(str).text();	//Remove Html Tags

			//			Save to new File

			try {

				//BufferedWriter(new FileWriter(path+".txt")); file.getName().replace("html", "")+"txt")
				BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsolutePath().replace("html", "txt")));
				out.write(page);  
				out.close();
			}
			catch (IOException e)
			{
				System.out.println("Exception ");

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}


