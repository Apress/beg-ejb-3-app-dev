
Chapter10-MigrationExamples
---------------------------

Before running the examples for this chapter, please read the Readme.txt file in
Chapter01-GettingStarted.

To run the examples in this chapter:

1) Go to the EJB21_MigrationExamples directory and run the default ANT target.  

This will build the EJB 2.1 migration example.  We don't go beyond this because
our interest here is in how to migrate this to EJB 3.0.

2) Go to the EJB30_MigrationExamples directory and run the default ANT target.  

This will build, deploy, and execute the examples from the EJB 2.1 sample that
have been migrated to EJB 3.0.  We run two separate clients against the resulting
EJBs and entities, one against the session bean running inside the EJB container,
and the other against the session bean repurposed as an ordinary POJO running
outside the Java EE container altogether.