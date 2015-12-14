package schedule.section;

import org.openqa.selenium.By;

import utilities.BotFunctions;

public class LectureOnly extends Section {
	public LectureOnly(BotFunctions functions, int classNumber) {
		super(functions, classNumber);
	}

	public void specificBotSection() {
		getFunctions().click(By.id("DERIVED_CLS_DTL_NEXT_PB"));
	}
}
