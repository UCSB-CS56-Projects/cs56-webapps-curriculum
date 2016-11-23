/*
 * Copyright 2014
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.pconrad.webapps.curriculum;


import edu.ucsb.cs56.projects.scrapers.ucsb_curriculum.UCSBCurriculumSearch;
import edu.ucsb.cs56.projects.scrapers.ucsb_curriculum.UCSBLecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.PrintWriter;
import java.io.StringWriter;


import org.pconrad.webapps.sparkjava.MustacheTemplateEngine;

import spark.Spark;
import spark.ModelAndView;

import static spark.Spark.get;



/**
 UCSBCurriculumWebapp

 @author Phill Conrad https://github.com/pconrad
 @author James Yang
 @author Arturo Milanes

 */

public class UCSBCurriculumWebapp {
    
    public static void main(String[] args) {
	
	Spark.staticFileLocation("/static");
	
	try {
	    Spark.port(Integer.valueOf(System.getenv("PORT"))); // needed for Heroku
	} catch (Exception e) {
	    System.err.println("NOTICE: using default port. \n" +
			       "Define PORT env variable to override");
	}

	final Map nullMap = new HashMap();

        get("/",
	    (rq, rs) ->
	    new ModelAndView(nullMap, "home.mustache"),
	    new MustacheTemplateEngine());
	
        get("/lookup/majorcode",
	    (rq, rs) ->
	    new ModelAndView(nullMap, "lookup.majorcode.mustache"), new MustacheTemplateEngine());

	get("/lookup/CMPSC",
	    (rq, rs) -> {
		Map model = new HashMap();
		String majorCodeAsString = "CMPSC";
		System.out.println("majorCodeAsString=" + majorCodeAsString);
		model.put("major_code",majorCodeAsString);
		
		try {
		    UCSBCurriculumSearch uccs = new UCSBCurriculumSearch();
		    uccs.loadCourses(majorCodeAsString,
				     "20171",
				     "Undergraduate");
		    ArrayList<UCSBLecture> lectures =
			uccs.getLectures();
		    System.out.println("lectures=" + lectures);

		    model.put("lectures",lectures);		    
		    
		} catch (Exception ex) {
		    ex.printStackTrace();
		    StringWriter sw = new StringWriter();
		    ex.printStackTrace(new PrintWriter(sw));
		    String exceptionAsString = sw.toString();		    
		    model.put("error",exceptionAsString);
		}
		return new ModelAndView(model,
					"lookup.majorcode.result.mustache");
	    },
	    new MustacheTemplateEngine()
		);
	
	
		
	
	
	get("/lookup/majorcode/result",
	    (rq, rs) ->
	    {
		Map model = new HashMap();
		String majorCodeAsString = rq.queryParams("major_code"); // get value from form
		System.out.println("majorCodeAsString=" + majorCodeAsString);
		model.put("major_code",majorCodeAsString);
		
		try {
		    UCSBCurriculumSearch uccs = new UCSBCurriculumSearch();
		    uccs.loadCourses(majorCodeAsString,
				     "20171",
				     "Undergraduate");
		    ArrayList<UCSBLecture> lectures =
			uccs.getLectures();
		    System.out.println("lectures=" + lectures);

		    model.put("lectures",lectures);		    
		    
		} catch (Exception ex) {
		    ex.printStackTrace();
		    StringWriter sw = new StringWriter();
		    ex.printStackTrace(new PrintWriter(sw));
		    String exceptionAsString = sw.toString();		    
		    model.put("error",exceptionAsString);
		}
	
		return new ModelAndView(model,
					"lookup.majorcode.result.mustache");
	    },
	    new MustacheTemplateEngine()
	    );
    }
}
