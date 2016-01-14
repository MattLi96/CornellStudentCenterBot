package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilities.BotFunctions;
import bot.Bot;

public class MainNoGui {
	
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		BotFunctions bf = new BotFunctions(driver);
		Bot bot = new Bot(bf, "Fall 2015"); //Replace with correct semester
		
		//Replace these classes with your classes
		//bot.addClass(11509, 11511); //cs 3110
		//bot.addClass(12019, 12022); //engrd 2700
		//bot.addClass(6060, 6066); 	//math 2930
		//bot.addClass(12257); 		//cs 4410 (os)
		//bot.addClass(12643); 		//cs 4780 (ml)
		//bot.addClass(11906);		//cs 4320 (db)
		//bot.addClass(6105); 		//math 4700
		//bot.addClass(11405, 17190); //orie 3300
		
		bot.run("netid", Encrypter.decrypt()); //Replace with your netid
		bot.checkout();
	}
}
