package PDFReader.PDFReader;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.google.j2objc.annotations.Weak;

public class PDFReaderTest {
	public static WebDriver driver;
	
	@Test
	public void readPDF() throws Exception {
		String pdfurl = "http://www.orimi.com/pdf-test.pdf";
		
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(pdfurl);
		
		String url = driver.getCurrentUrl();
		
		URL URL = new URL(url);
		
		InputStream is = URL.openStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		
		PDDocument document = null;
		
		document = PDDocument.load(bis);
		
		String pdfcontent = new PDFTextStripper().getText(document);
		
		System.out.println(pdfcontent);
		
		
		if(pdfcontent.contains("Canada"))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("Fail");
			
			
		}
		
		Assert.assertTrue(pdfcontent.contains("Canada"));
		Assert.assertTrue(pdfcontent.contains("Box"));
		
	
	}
	
	@AfterTest
	public void AfterRun() {
		driver.close();
		
	}

}
