package schedule.section;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;

import utilities.BotFunctions;

public class LectureAndDiscussion extends Section {
	int dn; //dicussion number
	
	public LectureAndDiscussion(BotFunctions functions, int classNumber, int discussionNumber) {
		super(functions, classNumber);
		dn = discussionNumber;
	}

	@Override
	public void specificBotSection() {
		BotFunctions f = getFunctions();
		
		//assigns clickelem to one of the possibilities
		By clickelem = null;
		if(f.elementPresent(By.name("SSR_CLS_TBL_R1$sels$0"))){
			clickelem = By.name("SSR_CLS_TBL_R1$sels$0");
		} else if(f.elementPresent(By.name("SSR_CLS_TBL_RE$sels$0"))) {
			clickelem = By.name("SSR_CLS_TBL_RE$sels$0");
		} else {
			throw new NoSuchElementException("Could not find clickable element");
		}
		
		//assigns nextElem to one of the possiblities
		By nextElem = null;
		if(f.elementPresent(By.name("SSR_CLS_TBL_R1$fdown$img$0"))){
			nextElem = By.name("SSR_CLS_TBL_R1$fdown$img$0");
		} else if(f.elementPresent(By.name("SSR_CLS_TBL_RE$fdown$img$0"))) {
			nextElem = By.name("SSR_CLS_TBL_RE$fdown$img$0");
		} else {
			throw new NoSuchElementException("Could not find clickable element");
		}
		
		while(!f.clickElementInTable(By.className("PSLEVEL1GRID"), 
				clickelem,
				By.className("PSEDITBOX_DISPONLY"), 
				""+dn))
		{
			f.click(nextElem);
		}
	}
}
