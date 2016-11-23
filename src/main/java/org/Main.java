package org;

import edu.ucsb.cs56.projects.scrapers.ucsb_curriculum.*;

public class Main { // implicit: public class Main extends object
    public static void main(String [] args){
        try{
	    UCSBCurriculumSearch uccs = new UCSBCurriculumSearch();
	    uccs.loadCourses("CMPSC", "20154", "Undergraduate");
	    java.util.ArrayList<UCSBLecture> lectures =
		uccs.getLectures();
            System.out.println("We good");
	    System.out.println("lectures=" + lectures);
        }
        catch(Exception E){
            System.out.println(":-(");
        }
    }
}
