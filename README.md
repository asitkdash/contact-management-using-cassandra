# contact-management-using-cassandra
contact management using java and cassandra

#copying data from csv file to cassandra db : data is separated by commas
--------------------------------------------------------------------------
#Example:
Data in csv file:employee.csv
--------------------------------
3,California,sid,6572588830,30000
4,California,shubham,6572588831,40000
5,California,chinmay,6572588832,50000
Command
--------
copy employee (emp_id, emp_city, emp_name, emp_phone, emp_sal) from 'D:/employee.csv';

#After creating a Maven project in Eclipse, below contents need to be added to POM.xml file
-------------------------------------------------------------------------------------------
 <!-- https://mvnrepository.com/artifact/com.datastax.cassandra/cassandra-driver-core -->
<dependency>
    <groupId>com.datastax.cassandra</groupId>
    <artifactId>cassandra-driver-core</artifactId>
    <version>3.3.0</version>
</dependency>
