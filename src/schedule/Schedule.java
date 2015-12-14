package schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import schedule.section.Section;

public class Schedule {
	//priority is defined by the order elements are in classes
	//higher priority is close to the front (index 0), they will be scheduled first
	List<Section> classes;
	
	public Schedule(){
		classes = new ArrayList<Section>();
	}
	
	/**
	 * Adds the section if it is not already in the schedule.
	 * 
	 * A section is defined as equal if it has the same class number, 
	 * regardless of if the other numbers are not equal. This is because
	 * student center will not allow you to add multiple sections with same 
	 * class number
	 *
	 * @param s
	 */
	public synchronized boolean addSection(Section s){
		if(classes.contains(s)) return false;
		classes.add(s);
		return true;
	}
	
	/**
	 * removes the section from the schedule. Requires the section is in the schedule
	 */
	public synchronized void removeSection(Section s){
		classes.remove(s);
	}
	
	/**
	 * Changes priority by 1. Requires that you are able to change the index in given direction. 
	 * @param increase true if increase priority, else false
	 */
	public synchronized void changePriority(Section s, boolean increase){
		int index = classes.indexOf(s);
		
		//check to make sure operation is possible
		if((increase && index == 0) || (!increase && index == classes.size()-1)) return;
		
		//note you minus index to increase priority
		int swapIndex = increase? index - 1: index + 1;
		Collections.swap(classes, index, swapIndex);
	}
	
	/**
	 * adds all the classes in the current schedule
	 */
	public synchronized void botSchedule(){
		for(Section s: classes){
			try{
				s.botSection();
				System.out.println("Done with class number: " + s.classNumber());
			} catch (Exception e){
				System.out.println("Issue with class number: " + s.classNumber());
				e.printStackTrace();
				System.exit(1);;
			}
		}
		System.out.println("Done making schedule");
	}
}
