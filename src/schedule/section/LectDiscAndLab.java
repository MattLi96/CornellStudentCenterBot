package schedule.section;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.BotFunctions;

/**
 * lecture, dicussion, and lab
 * @author Matthew Li
 *
 */
public class LectDiscAndLab extends Section{
	int dn; //dicussion number
	int ln; //lab number
	
	public LectDiscAndLab(BotFunctions functions, int classNumber, int dicussionNumber,
			int labNumber) {
		super(functions, classNumber);
		dn = dicussionNumber;
		ln = labNumber;
	}

	@Override
	protected void specificBotSection() {
		BotFunctions f = getFunctions();
		WebDriver d = f.getDriver(); //Used to find the element of the table, since multiple
		List<WebElement> tables = d.findElements(By.className("PSLEVEL1GRID"));
		
		while(!f.clickElementInTable(tables.get(0), 
				By.name("SSR_CLS_TBL_R1$sels$0"), 
				By.className("PSEDITBOX_DISPONLY"), 
				""+dn))
		{
			f.click(By.name("SSR_CLS_TBL_R1$fdown$img$0"));
			tables = d.findElements(By.className("PSLEVEL1GRID"));
		}
		
		while(!f.clickElementInTable(tables.get(1), 
				By.name("SSR_CLS_TBL_R2$sels$0"), 
				By.className("PSEDITBOX_DISPONLY"), 
				""+ln))
		{
			f.click(By.name("SSR_CLS_TBL_R2$fdown$img$0"));
			tables = d.findElements(By.className("PSLEVEL1GRID"));
		}
	}

}
