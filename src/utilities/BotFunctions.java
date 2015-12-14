package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BotFunctions {
	private WebDriver driver;
	private WebDriverWait wait;
	
	/**
	 * Creates a new BotFunctions
	 * @param webDriver the webDriver to run the functions on
	 */
	public BotFunctions(WebDriver webDriver){
		driver = webDriver;
		wait = new WebDriverWait(driver, 2);
	}
	
	public boolean elementPresent(By by){
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * Basically calls driver.get(url). Sends the driver to the given url.
	 */
	public void get(String url){
		driver.get(url);
	}
	
	/**
	 * Selects an element in a table. There are a bunch of these on student center.
	 * If you cannot find the element, see if there are more pages on the table
	 * 
	 * @param clickElements the elements to select (usually the round circles)
	 * @param valueElements the elements with values to read through (such as dicussion number)
	 * @param value the value to match
	 * @return true if element is found, else false
	 */
	
	/**
	 * Selects an element in a table. There are a bunch of tables on student center.
	 * If you cannot find the element, see if there are more pages in the table
	 * 
	 * @param tableElement the element representing the table with the values
	 * @param clickElement the element to select (usually the round circle)
	 * @param valueElement the element with value to match (such as dicussion number)
	 * @param value the value to match
	 * @return true if element is found, else false
	 */
	public boolean clickElementInTable(By tableElement, By clickElement, 
			By valueElement, String value){
		return clickElementInTable(driver.findElement(tableElement), 
				clickElement, valueElement, value);
	}
	
	/**
	 * Overloaded version of clickElementInTable, where you are able to 
	 * input directly the element that represents the table
	 * 
	 * @param table the element representing the table with the values
	 * @param clickElement the element to select (usually the round circle)
	 * @param valueElement the element with value to match (such as dicussion number)
	 * @param value the value to match
	 * @return true if element is found, else false
	 */
	public boolean clickElementInTable(WebElement table, By clickElement, 
			By valueElement, String value){
		wait.until(ExpectedConditions.visibilityOf(table));
		
		List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
		for(WebElement row: rows){
			try{
				WebElement clickElem = row.findElement(clickElement); //find element to click
				
				//element to click found in this row, try to match value
				for(WebElement e: row.findElements(valueElement)){
					if(e.getText().equals(value)){ //found
						click(clickElem);
						return true;
					}
				}
			} catch (NoSuchElementException e){
				//No element in this row
			}
		}
		return false;
	}
	
	/**
	 * clicks the given WebElement
	 * @param e the element
	 */
	public void click(WebElement e){
		wait.until(ExpectedConditions.visibilityOf(e));
		e.click();
	}
	
	/**
	 * Clicks on the WebElement found by the given {@code by}
	 * @param by
	 */
	public void click(By by){
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		driver.findElement(by).click();
	}
	
	/**
	 * Sends the given keys to the WebElement found by the given {@code by}
	 */
	public void sendKeys(By by, String keys){
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		driver.findElement(by).sendKeys(keys);;
	}
	
	/**
	 * @return the WebDriver of this functions. Be careful using driver directly
	 */
	public WebDriver getDriver(){
		return driver;
	}
	
	/**
	 * PrintOut all the elements in the list of elements
	 */
	public static void printElements(List<WebElement> webElements){
		for(WebElement e: webElements){
			System.out.println(e.getText());
		}
	}
}
