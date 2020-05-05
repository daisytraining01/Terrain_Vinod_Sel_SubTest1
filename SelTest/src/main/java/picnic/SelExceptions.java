package picnic;



import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelExceptions {
	public static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "src/main/java/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://google.com");
	try {
		try {
		driver.switchTo().window("hey");
	}
		catch(NoSuchWindowException e) {
			System.out.println("**No Such Window Exception handled**");
			System.out.println(e);
			
		}
   try {
	 driver.switchTo().frame("hello");
   }
	catch(NoSuchFrameException e) {
		System.out.println("**No such frame exception handled**");
		System.out.println(e);
		
	}
  try {
	 driver.switchTo().alert().accept();
   }
  catch(NoAlertPresentException e) {
	  System.out.println("**No alert present exception handled**");
	 System.out.println(e);
  }
  
  try {
	  driver.findElement(By.name("fakename")).click();
	}
  catch(Exception e) {
	  
	  System.out.println("**No such element exception handled**");
		 System.out.println(e); 
  }
  
	}
		
  catch(Exception e) {
			
		}
	driver.close();
}
	
	
	}