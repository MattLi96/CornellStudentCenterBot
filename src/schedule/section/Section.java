package schedule.section;

import org.openqa.selenium.By;

import utilities.BotFunctions;

/**
 * A generic section type
 * 
 * Note: A section is defined as equal if it has the same class number, 
 * regardless of if the other numbers are not equal. This is because
 * student center will not allow you to add multiple sections with same 
 * class number
 */
public abstract class Section {
	private BotFunctions functions;
	private int cn; //class number

	public Section(BotFunctions functions, int classNumber){
		this.functions = functions;
		cn = classNumber;
	}

	/**
	 * Adds the section with the given class number
	 */
	public void botSection(){
		functions.sendKeys(By.id("DERIVED_REGFRM1_CLASS_NBR$42$"), ""+cn);
		functions.click(By.name("DERIVED_REGFRM1_SSR_PB_ADDTOLIST2$44$"));
		specificBotSection();
		
		//While can't input next class, click next
		while(!functions.elementPresent(By.id("DERIVED_REGFRM1_CLASS_NBR$42$"))){
			functions.click(By.id("DERIVED_CLS_DTL_NEXT_PB"));
		}
	}

	/**
	 * @return the class number of this section
	 */
	public int classNumber(){
		return cn;
	}

	/**
	 * Gets the BotFunctions of this section. Used for the subclasses.
	 */
	protected BotFunctions getFunctions(){
		return functions;
	}

	/**
	 * Each different type of section has specifics required. The section class
	 * inputs the given class number and clicks submit. The other clicks that a required
	 * to add the section and return to main page are specified by each subclass.
	 */
	protected abstract void specificBotSection();

	public boolean equals(Object section){
		if(section instanceof Section){
			return cn == ((Section) section).cn;
		}
		return false;
	}
}
