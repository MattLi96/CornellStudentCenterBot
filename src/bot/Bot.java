package bot;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import schedule.Schedule;
import schedule.section.*;
import utilities.BotFunctions;

/**
 * The overall bot. Does the login, then calls the schedule
 */
public class Bot {
	BotFunctions f;
	Schedule s;
	String sem;

	public Bot(BotFunctions functions, String semester){
		f = functions;
		sem = semester;
		s = new Schedule();
	}

	/**
	 * Adds the class to this bot's schedule. Does not add to student center schedule yet.
	 * @param cn class number
	 */
	public void addClass(int cn){
		s.addSection(new LectureOnly(f, cn));
	}
	
	/**
	 * Adds the class to this bot's schedule. Does not add to student center schedule yet.
	 * Note this should work in lab + lecture only classes too.
	 * 
	 * @param cn class number
	 * @param dn dicussion number
	 */
	public void addClass(int cn, int dn){
		s.addSection(new LectureAndDiscussion(f, cn, dn));
	}
	
	/**
	 * Adds the class to this bot's schedule. Does not add to student center schedule yet.
	 * @param cn class number
	 * @param dn discussion number
	 * @param ln lab number
	 */
	public void addClass(int cn, int dn, int ln){
		s.addSection(new LectDiscAndLab(f, cn, dn, ln));
	}

	/**
	 * Runs the bots, adds all the classes in the bot schedule.
	 * Requires all classes added properly. If they weren't, there 
	 * are no guarantees about what the bot will do
	 * 
	 * to the student center schedule
	 * @param netID student's net ID
	 * @param password student center password
	 */
	public void run(String netID, String password){
		goToWebsite();
		logIn(netID, password);
		try{
			chooseSemester(sem);
		} catch (NoSuchElementException e){
			System.out.println("Did not prompt for semester");
		}
		addClasses(s);
	}

	/**
	 * Goes to the student center website
	 */
	private void goToWebsite(){
		f.get("http://www.studentcenter.cornell.edu");
	}

	/**
	 * Logs in to the given account
	 * @param netID netID of the accound
	 * @param password password of the account
	 */
	private void logIn(String netID, String password){
		f.sendKeys(By.id("netid"), netID);
		f.sendKeys(By.id("password"), password);
		f.click(By.name("Submit"));
	}

	/**
	 * Chooses the appropriate semester
	 * @param semester the semester as a String
	 */
	private void chooseSemester(String semester){
		//clicks add class
		f.click(By.id("DERIVED_SSS_SCL_LINK_ADD_ENRL"));

		//clicks the session
		f.clickElementInTable(By.id("SSR_DUMMY_RECV1$scroll$0"),
				By.name("SSR_DUMMY_RECV1$sels$0"), 
				By.className("PSLEVEL2GRIDROW"), 
				semester);


		//clicks continue
		f.click(By.name("DERIVED_SSS_SCT_SSR_PB_GO"));
	}

	/**
	 * Adds the classes into the schedule.
	 * Specifically, the bot refreshes until it is able to add classes, then begins scheduling.
	 */
	private void addClasses(Schedule schedule){
		while(true){
			try{
				f.getDriver().findElement(By.id("DERIVED_REGFRM1_CLASS_NBR$42$"));
				break;
			} catch (NoSuchElementException e){
				f.getDriver().navigate().refresh();
			}
		}
		schedule.botSchedule();
	}
	
	/**
	 * Checksout from homepage
	 */
	public void checkout(){
		f.click(By.id("DERIVED_REGFRM1_LINK_ADD_ENRL"));
		f.click(By.id("DERIVED_REGFRM1_SSR_PB_SUBMIT"));
	}
}
