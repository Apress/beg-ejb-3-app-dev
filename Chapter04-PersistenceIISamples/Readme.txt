
Chapter04-PersistenceIISamples
------------------------------

Before running the examples for this chapter, please read the Readme.txt file in
Chapter01-GettingStarted.

To run the examples in this chapter:

1) Go to each sub-directory of the Chapter04-PersistenceIISamples directory and 
run the default ANT target.  

This will build a persistence unit composed of entities related by inheritance and 
reference, along with a simple Java client to exercise their behavior.  It will 
also execute a SQL script to drop and create the tables required by the entities 
in that example's persistence unit.  The final target will run the client and
send the output to the command prompt.

Each sub-directory for this chapter defines a persistence unit containing 
entities (or non-entity classes) with the same API, but which differ in their 
persistence mapping metadata:

* Embedded:  Address is mapped as an embedded non-entity class

* Joined: Entities in the hierarcy are mapped using the JOINED inheritance strategy
  
* MappedSuperclass:  The Person class is mapped as a non-entity mapped superclass

* SingleTable:  Entities in the hierarcy are mapped using the SINGLE_TABLE 
  inheritance strategy
  
* TablePerClass:  Entities in the hierarcy are mapped using the TABLE_PER_CLASS* 
  inheritance strategy.  (*Note: this strategy is not mandated by the JPA spec,
  and is not fully implemented by the reference implementation at the time of
  writing).