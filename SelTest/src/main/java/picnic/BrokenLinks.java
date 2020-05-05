package picnic;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {
	private static WebDriver driver = null;
	
	public static boolean flag = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String homePage = "http://demo.guru99.com/test/newtours/register.php";
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;

		System.setProperty("webdriver.chrome.driver", "src/main/java/driver/chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(homePage);

		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> iter = links.iterator();
		Iterator<WebElement> iter1 = links.iterator();

		// To print list of links in the Website
		System.out.println("List of links in the website");
		while (iter.hasNext()) {
			url = iter.next().getAttribute("href");
			System.out.println(url);
		}

		//To check if there are any broken links
		while (iter1.hasNext()) {

			url = iter1.next().getAttribute("href");

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			//skips link that belongs to different domain
			if(!url.startsWith(homePage)){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
					 flag = true;
				} 	

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(flag==false) {
		System.out.println("**There are no broken links in this website! Try another website that contains Broken links!**");
		}
		driver.quit();

	}
}
