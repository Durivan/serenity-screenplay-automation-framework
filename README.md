# Automation Framework

## The technologies used are:
* Java
* Cucumber
* Gradle
* Selenium
* Serenity

## Required tool installations

* Java JDK [download](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* Gradle [download](https://gradle.org/install/)
* Git executable [download](https://git-scm.com/downloads)

## Recommended tool installations

* IDE - IntelliJ IDEA Community Edition [download](https://www.jetbrains.com/idea/download)
* Gitflow Workflow [Documentation](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)

## Setting required environment variables

We recommended going the official documentation for necessary setup.

* Java [Documentation](https://java.com/en/download/help/path.html)
* Gradle [Documentation](https://gradle.org/install/)

## Project compilation

It is important to compile the project before making a Pull Request in order to guarantee that the framework is working correctly.

```
gradle compileJava
```
And 
``` 
gradle clean assemble
```

## Run project

Available drivers [firefox, chrome, ie, safari, opera]

* Execute specific test using tags 
``` java
 gradle clean test -D"cucumber.filter.tags=@Smoke"
```

* Execution All tests and report generation - To run all the project tests and generate the Serenity report
``` java
 gradle clean test aggregate
```

## Report
The project is supported by [Serenity](https://serenity-bdd.github.io/theserenitybook/latest/manual-tests.html) for the generation of reports.

The report will available at -> 

``` 
├──AutomationFramework
|   └── target
|       └── site
|           └── serenity
|               └── index.html
```
