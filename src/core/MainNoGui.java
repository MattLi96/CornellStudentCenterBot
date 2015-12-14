package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import schedule.Schedule;
import schedule.section.LectureOnly;
import utilities.BotFunctions;
import bot.Bot;

public class MainNoGui {
	
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		BotFunctions bf = new BotFunctions(driver);
		Bot bot = new Bot(bf, "Fall 2015");
		
		//bot.addClass(11509, 11511); //cs 3110
		//bot.addClass(12019, 12022); //engrd 2700
		//bot.addClass(6060, 6066); 	//math 2930
		//bot.addClass(12257); 		//cs 4410 (os)
		//bot.addClass(12643); 		//cs 4780 (ml)
		//bot.addClass(11906);		//cs 4320 (db)
		//bot.addClass(6105); 		//math 4700
		//bot.addClass(11405, 17190); //orie 3300
		
		bot.run("netid", Encrypter.decrypt());
		bot.checkout();
	}
}
