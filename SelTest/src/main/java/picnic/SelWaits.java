package picnic;




import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelWaits {
	private static final TimeUnit SECONDS = null;
	public static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/main/java/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/newtours/register.php ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //implicit wait
		
		driver.findElement(By.name("firstName")).sendKeys("vinod");
		driver.findElement(By.name("lastName")).sendKeys("vinod");
		driver.findElement(By.name("phone")).sendKeys("1234567891");
		driver.findElement(By.id("userName")).sendKeys("vinod@hotmail.com");
		driver.findElement(By.name("address1")).sendKeys("address123");
		driver.findElement(By.name("city")).sendKeys("city");
		driver.findElement(By.name("state")).sendKeys("state");	
		driver.findElement(By.name("postalCode")).sendKeys("");
		Select sel = new Select(driver.findElement(By.name("country")));
		sel.selectByValue("AUSTRALIA");
		
		//Explicit wait
		WebElement  username;
		WebDriverWait wait=new WebDriverWait(driver, 20);
		username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[13]/td[2]/input")));
		username.sendKeys("vinod");
	
		driver.findElement(By.name("password")).sendKeys("vin123");
		driver.findElement(By.name("confirmPassword")).sendKeys("vin123");
		
//		//Fluent wait
//		Wait<WebDriver> fw = new FluentWait<WebDriver>(driver)
//			       .withTimeout(30, SECONDS)
//			       .pollingEvery(5, SECONDS)
//			       .ignoring(Exception.class);
		
		Wait fw = new FluentWait<WebDriver>(driver)
		.withTimeout(50, TimeUnit.SECONDS)
		.pollingEvery(3, TimeUnit.SECONDS)
		.ignoring(Exception.class);
		
		 
		 WebElement submitbtn = (WebElement) fw.until(new Function<WebDriver, WebElement>() {
		     public WebElement apply(WebDriver driver) {
		       return driver.findElement(By.name("submit"));
		     }
		   });
		 
		 submitbtn.click();
		 driver.close();
	}

}
