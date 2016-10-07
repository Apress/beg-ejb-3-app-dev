
Chapter03-PersistenceSamples
----------------------------

Before running the examples for this chapter, please read the Readme.txt file in
Chapter01-GettingStarted.

To run the examples in this chapter:

1) Go to the EjbModule directory and run the default ANT target.  This will build,
assemble, and deploy an EJB module containing an embedded persistence unit.  It will
also execute a SQL script to drop and create the tables required by this chapter's
entities.

2) Go to the Client directory and run the default ANT target.  This will build and
execute a standalone Java class that will lookup, from outside the Java EE container, 
the session bean defined in our EJB module and perform some operations on the entities
in this chapter's persistence unit.