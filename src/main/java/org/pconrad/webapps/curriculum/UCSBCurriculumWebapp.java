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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	get("/lookup/majorcode/result",
	    (rq, rs) ->
	    {
		Map model = new HashMap();
		String majorCodeAsString = rq.queryParams("major_code"); // get value from form
		int majorCodeAsInt = 0;
		try {
		    majorCodeAsInt = Integer.parseInt(majorCodeAsString);
		} catch (NumberFormatException nfe) {
		    model.put("error","The majorcode entered was invalid:" + majorCodeAsString);
		    return new ModelAndView(model, "lookup.majorcode.result.mustache");
		}
		    
		ArrayList<String> majors
		    = new ArrayList<String>();

		majors.add("CMPSC");
		majors.add("MATH");

		model.put("major_code",majorCodeAsString);

		model.put("majors",majors);
		
		return new ModelAndView(model, "lookup.majorcode.result.mustache");
	    },
	    new MustacheTemplateEngine()
	    );
    }
}
