package org.pconrad;

import edu.ucsb.cs56.projects.scrapers.ucsb_curriculum.*;

public class Main { // implicit: public class Main extends object
    public static void main(String [] args){
        try{
	    UCSBCurriculumSearch uccs = new UCSBCurriculumSearch();
	    uccs.loadCourses("CMPSC", "20154", "Undergraduate");
	    System.out.println("We good");
	    java.util.ArrayList<UCSBLecture> lectures =
		uccs.getLectures();
        }
        catch(Exception E){
            System.out.println(":-(");
        }
    }
}
