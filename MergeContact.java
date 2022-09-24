package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	/*
	 * //Pseudo Code
	 * 
	 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
	 * 
	 * 2. Enter UserName and Password Using Id Locator
	 * 
	 * 3. Click on Login Button using Class Locator
	 * 
	 * 4. Click on CRM/SFA Link
	 * 
	 * 5. Click on contacts Button
	 * 	
	 * 6. Click on Merge Contacts using Xpath Locator
	 * 
	 * 7. Click on Widget of From Contact
	 * 
	 * 8. Click on First Resulting Contact
	 * 
	 * 9. Click on Widget of To Contact
	 * 
	 * 10. Click on Second Resulting Contact
	 * 
	 * 11. Click on Merge button using Xpath Locator
	 * 
	 * 12. Accept the Alert
	 * 
	 * 13. Verify the title of the page
	 */

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

		//1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().window().maximize();

		//2.Enter UserName and Password Using Id Locator

		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Demosalesmanager");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("crmsfa");

		//3. Click on Login Button using Class Locator
		driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();

		//4. Click on CRM/SFA Link
		driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();

		//5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();

		//6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.linkText("Merge Contacts")).click();

		//7. Click on Widget of From Contact
		driver.findElement(By.xpath("//table[contains(@id,'partyIdFrom')]//following-sibling::a")).click();

		//8. Click on First Resulting Contact
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> Lwindows=new ArrayList<String>(windowHandles);
		driver.switchTo().window(Lwindows.get(1));
		driver.findElement(By.xpath("(//div[contains(@class,'partyId')]//a)[2]")).click();

		//9. Click on Widget of To Contact
		driver.switchTo().window(Lwindows.get(0));
		driver.findElement(By.xpath("//table[contains(@id,'partyIdTo')]//following-sibling::a")).click();

		//10. Click on Second Resulting Contact
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> Lwindows1=new ArrayList<String>(windowHandles1);
		driver.switchTo().window(Lwindows1.get(1));
		driver.findElement(By.xpath("(//div[contains(@class,'partyId')]//a)[3]")).click();

		//11. Click on Merge button using Xpath Locator
		driver.switchTo().window(Lwindows1.get(0));
		driver.findElement(By.linkText("Merge")).click();

		//12. Accept the Alert
		driver.switchTo().alert().accept();

		//13. Verify the title of the page
		String title = driver.getTitle();
		if(title.contains("View Contact")==true) {
			System.out.println("Contacts are merged");
		}else {
			System.out.println("contacts are not merged");
		}
	}

}


