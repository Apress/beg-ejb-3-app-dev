
Chapter08-TransactionSamples
----------------------------

Before running the examples for this chapter, please read the Readme.txt file in
Chapter01-GettingStarted.

To run the examples in this chapter:

1) Go to the XactionExamples directory and run the default ANT target.  

This will build, assemble, and deploy a Java EE application (EAR file) consisting 
of an EJB module from source in this chapter, and a persistence unit created for 
the Chapter07-IntegratedSamples examples.

2) Go to the Client directory and run the default ANT target.  

This will build and execute two standalone Java classes that will lookup, from 
outside the Java EE container, the BMT and CMT session beans defined in our 
XactionSamples EJB module, and perform some operations on the entities in this 
chapter's persistence unit.  These operations demonstrate how you can control the 
transactional behavior of session beans.

Errata:

An apparent issue with the Glassfish app server gives rise to an exception when
executing the Run-BMTClient and the Run-CMTClient ant targets in succession, like
this:

  ant
  ant all
  ant Run-BMTClient Run-CMTClient
  ant Run-CMTClient Run-BMTClient

When these targets are run from separate ant sessions, the exception does not 
occur:

  ant Run-BMTClient
  ant Run-CMTClient

This issue will hopefully be resolved in a future release of Glassfish and/or
toplink-essentials.