#!/usr/bin/env bash


targetjar=cs56-foobar-ucsb-curriculum
targetjarversion=2.0
targetjarfile=cs56-foobar-ucsb-curriculum-2.0.jar

function mvn_deploy {
    mvn clean -U     
    mvn deploy:deploy-file \
	-Durl=file:`pwd`/repo -Dfile="$targetjarfile" \
         -DgroupId=edu.ucsb.cs56.projects \
	 -DartifactId="$targetjar" -Dpackaging=jar \
	 -Dversion=$targetjarversion   
    mvn -U initialize
}

function main {


    if [[ ! -f "$targetjarfile" ]]; then
	echo "The needed jar, $targetjarfile, does not exist"
	exit 1
    fi

    mvn_deploy 
}

[ "$#" -ne 0 ] && ( usage && exit 1 ) || main "$1"
