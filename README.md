# cs56-webapps-curriculum

A demo of:

* a [SparkJava webapp](http://pconrad-webapps.github.io/topics/sparkjava/),
* loaded with data from the https://github.com/UCSB-CS56-Projects/cs56-scrapers-ucsb-curriculum

# Requires Java 1.8, and Maven (`mvn` command)

To build, use `mvn package`

To run, use `java -jar target/cs56-webapps-curriculum-1.0-SNAPSHOT.jar`

Demo on Heroku at:  https://cs56-webapps-curriculum.herokuapp.com/


# See also: 

* documentation for <https://github.com/pconrad-webapps/sparkjava-corgis-graduates-demo>

F16 final remarks:

Dear future legacy workers,

The current webapps curriculum is the initial model for the webapp with very limited functionality that runs on Heroku. It currently runs on a scraper that was provided by scrapers-ucsb-curriculum. We used the getters from the scraper to get the Title, Capacity, Instructor, LectureDays, and Enroll Code. We added the columns in the html code to display it in our webapp. Note that some getters from the scraper are stubs, so they won't display anything until they are fixed in the scraper.(ITS NOT A PROBLEM DUE TO THIS PROJECT IT'S THE OTHER PROJECT). MAKE SURE YOU WORK WELL AND COMMUNICATE WITH THE SCRAPER GROUP. 



