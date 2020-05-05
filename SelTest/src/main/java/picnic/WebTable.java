package picnic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTable {
	public static WebDriver driver;
	
	 
	public static void writeexcel() throws IOException {
		// String [][] tableval = null;
		System.setProperty("webdriver.chrome.driver", "src/main/java/driver/chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/sql/sql_syntax.asp");
		
		List<WebElement> rownum =driver.findElements(By.xpath("/html/body/div[6]/div[1]/div[1]/div[3]/table/tbody/tr"));
		System.out.println("No of Rows in the Table:"+rownum.size());
		List <WebElement> colnum = driver.findElements(By.xpath("/html/body/div[6]/div[1]/div[1]/div[3]/table/tbody/tr[1]/th"));
		System.out.println("No of Coloumns in the Table:"+colnum.size());
		
		String [][] tableval = new String [rownum.size()][colnum.size()];
       for(int i =1 ; i <= rownum.size() ; i++ ){
           for(int j =1 ; j <= colnum.size() ; j++ ) {
               if(i == 1) {
                   //Get header value
               	tableval[i - 1][j - 1] = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[3]/table/tbody/tr["+i+"]/th["+j+"]")).getText();
                   //System.out.println( driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[3]/table/tbody/tr["+i+"]/th["+j+"]")).getText());
               }
               else{
                   //get table data values
               	tableval[i-1][j-1] =driver.findElement(By.xpath(".//table/tbody/tr["+i+"]/td["+j+"]")).getText();
                   //System.out.println(driver.findElement(By.xpath(".//table/tbody/tr["+i+"]/td["+j+"]")).getText());
               }
           }
           }
       XSSFWorkbook workbook = new XSSFWorkbook();
       XSSFSheet sheet = workbook.createSheet("Tabledata");

       int rn = 0;
       System.out.println("Writing in  excel");

       for (Object[] datatype : tableval) {
           Row row = sheet.createRow(rn++);
           int cn = 0;
           for (Object field : datatype) {
               Cell cell = row.createCell(cn++);
               if (field instanceof String) {
                   cell.setCellValue((String) field);
               } else if (field instanceof Integer) {
                   cell.setCellValue((Integer) field);
               }
           }
       }

       try {
           FileOutputStream outputStream = new FileOutputStream("src/main/java/driver/webtable.xlsx");
           workbook.write(outputStream);
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }finally {
           workbook.close();
       }

       System.out.println("Done");
		
	}
	
	public static void main(String[] args) throws IOException {
	
		writeexcel();
		driver.close();
    }
	

	}		
	

	

	


