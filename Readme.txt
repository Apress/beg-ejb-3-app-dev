
Beginning EJB 3 Application Development:  From Novice to Professional
---------------------------------------------------------------------

Welcome to the 'Beginning EJB 3 Application Development:  From Novice to Professional'
example code area.  This section is organized by chapter, and lists the examples cited
in the book text.  All supporting ANT and SQL scripts are provided for each chapter.

Before proceeding, please read the 'Getting Started' section at the end of Chapter 1
of the book.  This section describes how to install and configure the Glassfish
application server (Sun's reference implementation for Java EE 5, dowloadable for 
free) and how to install and configure an Oracle database (also downloadable for 
free) required to run the samples.

Because the nature of the material differs between chapters, the ANT targets vary,
but are generally organized as follows:

1) A Readme.txt file is provided in each chapter's root directory to explain how
to execute the ANT scripts for that chapter, and how to run any additional tasks
outside of ANT, such as entering an URL from a web browser.

2) Each chapter will either have a top-level build.xml file, or will have separate
build.xml files in each sub-folder of the chapter's root directory.  Typically, we
expect you will execute 'ant all' (or simply 'ant', since we generally set the "all" 
target to be the default) to execute all necessary tasks for the examples in that 
chapter.

3) The build.properties file in the (Chapter1-GettingStarted) chapter defines the
properties common to all other ANT scripts.  This includes jdbc and glassfish 
configuration properies, such as user names and passwords.  It is recommended that
you open this file and update it to reflect your configuration before running any
ANT scripts in this example area.

4) A single database account may be used for all of the examples across all chapters,
with the exception of Chapter09-PerformanceSamples.  This chapter requires a second
account, which may be created through an ANT target provided in that chapter's build.xml
file.

5) To create the common database account, you may execute the runsql.sql script found
in the Chapter07-IntegratedSamples/PersistenceUnit/sqlscripts directory through
Oracle SQLPlus, or you may run the ANT target 'DropAndCreateDatabaseUser' found in
the build.xml file in this (Chapter01-GettingStarted) directory.

6) In general, the examples in each chapter may be run in isolation of each other.
Any chapter whose examples require the output from another chapter's examples will 
describe those dependencies in its Readme.txt file.

A note about the JDeveloper files mixed in among the samples:

You may also see a number of files specific to Oracle JDeveloper scattered throughout
this samples area.  We built these samples using JDeveloper 10.1.3.1, a forthcoming
release.  Once JDeveloper 10.1.3.1 becomes generally available, we will revisit
this source area and ensure that all examples can be easily built, deployed, and
executed from within the JDeveloper IDE as well as using ANT from the command line.

Errata:

The information in this Readme.txt, and all other Readme.txt files in this directory
tree, supercede any steps described in the book itself.  Due to updates to the 
glassfish and toplink-essentials code, changes may be required in these samples.  We
will try to keep them up-to-date, but welcome any comments from our readers.  Should
you run into difficulties, please let us know so we can add further explanations in
these Readme.txt files, or fix any problems encountered in the sources.

Thanks, and enjoy!

Jon and Raghu
