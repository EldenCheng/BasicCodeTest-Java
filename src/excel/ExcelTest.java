package excel;
import java.io.*;
import java.util.logging.Level;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.google.common.collect.ImmutableList.*;


public class ExcelTest {
	public static void main(String[] args) {
		try {
            File excel = new File("./TestCase//Test.xlsx");
            FileInputStream fis = new FileInputStream(excel); 
            XSSFWorkbook book = new XSSFWorkbook(fis); 
            XSSFSheet sheet = book.getSheetAt(0);
            //System.out.println(sheet.getLastRowNum());
            Row row = sheet.getRow(1);
            Cell cell =row.getCell(1);
            //System.out.println(cell.getStringCellValue());
            String URL = cell.getStringCellValue();
            row = sheet.getRow(1);
            cell = row.getCell(2);
            String Keyword = cell.getStringCellValue();
            book.close();
            fis.close();

            //System.setProperty("webdriver.gecko.driver", "geckodriver.bat");
            GeckoDriverService gecko = new GeckoDriverService(new File("./WebDrivers/geckodriver.exe"), 4444, of("--log=fatal"), ImmutableMap.of());
            gecko.sendOutputTo(new FileOutputStream("gecko_log.txt"));
            gecko.start();

            FirefoxOptions opts = new FirefoxOptions().setLogLevel(FirefoxDriverLogLevel.FATAL);
            //DesiredCapabilities capabilities = opts.addTo(DesiredCapabilities.firefox());
            //capabilities.setCapability("marionette", true);

            WebDriver my_dr = new FirefoxDriver(gecko, opts);
    		my_dr.get(URL);
    		Thread.sleep(1000);
    		my_dr.findElement(By.id("kw")).sendKeys(Keyword);
    		Thread.sleep(1000);
    		my_dr.findElement(By.id("su")).click();
    		Thread.sleep(1000);
    		System.out.println(my_dr.getTitle());
    		Thread.sleep(1000);
    		my_dr.quit();


		} catch (FileNotFoundException fe) {
            fe.printStackTrace();
	      }
		 catch (IOException ie) {
	            ie.printStackTrace();
	        }
		catch (InterruptedException e) { 
            // Restore the interrupted status
            Thread.currentThread().interrupt();
        }

    }
}
