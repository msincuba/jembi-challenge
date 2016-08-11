# Jembi Refactoring Challenge for Java Developers

Well done for making it this far through Jembi's recruitment process!
We are sure that you would agree with us that it doesn't really make sense to hire developers without getting them to work with some code.

## Background

Jembi's goal is to improve public health in under resourced environments through the development and support of eHealth systems. For more information on what we do, see [Jembi's Website](http://www.jembi.org).

We, as developers, often find ourselves working with many disparate and offline systems.
This generally makes centralised and consistent data storage rare.

Therefore, a common problem that we have to deal with is data synchronisation and patient matching.
Patient matching is the process of identifying patients using multiple, possibly different, elements of patient information.

We use this process to reference patient records and reduce duplication.

## Requirements

* Read a remote json file containing patient records. This file may contain duplicate records and inconsistent data.  
* Process the patient records using patient matching to reduce duplicates.  
* Save the remaining filtered patient records to a sql database.  

## Scenario (Fictional)

A previous intern took a stab at solving the requirements by writing an app using Java, [Gradle](https://gradle.org), a [SQLite](http://www.sqlite.org) database and the [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc) library.

## Challenge

As we all know, students are very enthusiastic. This is great, but they generally don't have a good understanding of what robust production code looks like or what it takes to maintain it.

Your job is to take this ugly, untested and buggy code and refactor it into robust and maintainable production code.

**NB** This code was deliberately written badly and is not the quality of code that Jembi Developers normally produce or are expected to produce.

## Do's and don'ts

* Complete this challenge on your **OWN**. There are multiple solutions to this problem and we want to see yours.
* **KISS** Keep things simple
* **_DO NOT_** invent additional requirements or design a solution to solve every hypothetical scenario. Just design a solution to the presented challenge.
* **_DO NOT_** spend days on this challenge. We are only expecting this to take you a few hours so if find yourself spending days on this challenge, you might want to start wondering whether you are not over engineering your solution.
* You **_DO NOT NEED TO_** research and optimise the patient matching algorithm, it was deliberately kept simple. But you **_MAY_** do this if you so wish.
* We chose [SQLite](http://www.sqlite.org) for this challenge for the following reasons (Please **_DO NOT_** change the database): 
    * It's simple (SQL is standard that we expect everyone to know)
    * Consistency (We want all submissions to be comparable)
    * It's Embeddable (We didn't want you to have to install any additional tools)
* We chose [Gradle](https://gradle.org) as our build tool for this challenge for the following reasons (Please **_DO NOT_** change the build tool):
    * XML sucks (Sorry Maven)
    * It's awesome (If you are interested read up on some of it's cool features: groovy dsl, wrapper, incremental builds and the daemon)
    * The [documentation](https://docs.gradle.org/current/userguide.html) is great
    * It has great IDE support
    * It's embeddable (Gradle Wrapper)
* You **_MAY_** use any libraries that you would like to use to solve the problem. You can add your dependencies to `build.gradle` in the root or the project. See [Gradle Dependency Management Basic](https://docs.gradle.org/current/userguide/artifact_dependencies_tutorial.html) for help on including your dependencies.
* You **_SHOULD NOT_** have to install any additional tooling other than the libraries that you want to use, as described above. 
* Fork our [Github repository](https://github.com/jembi/challenge-java)
* **_DO NOT_** submit pull requests back to our repository
* **_Please_** keep this code and your solution private. Do not share it with anyone outside of the Jembi organization. 
* **_DO_** ask any questions that you may have about the problem.

## Install, Build, Run and Test

* Fork our [Github repository](https://github.com/jembi/challenge-java)
* Run the command `./gradlew build` on unix systems or `gradlew build` on windows systems, which will:
    * Download gradle (first time only)
    * Download application dependencies (first time only)
    * Compile
* To run the application use the command `./gradlew run` on unix systems and `gradlew run` on windows systems.
* To run tests use the command `./gradlew test` on unix systems and `gradlew test` on windows systems.
