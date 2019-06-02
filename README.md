# Accounts


-------------Requirements----------------
Design and implement a RESTful API (including data model and the backing implementation) for
money transfers between accounts.
Explicit requirements:

1 Assume the API is invoked by multiple systems and services on behalf of end users.

2 keep it simple and avoid heavy frameworks.

3. The datastore should run in-memory for the sake of this test.
3. The final result should be executable as a standalone program (should not require a pre-installed container/server).
7. Demonstrate with tests that the API works as expected.

----------- Implementation assumptions----------
Used servlets to implement the REST apis
Servlet annotation used to avoid file references

Basic code structure-
 (1) packages --Acc,Beans,operation,util .  
    (a)Acc -> this has 5 URL mapped servlet classes. Urls are account, addmoney, getbalance, transfer , withdrawmoney
    Note*direct post data JSON parsing used and not implemented REST standardised namings for URLs- This would save time as we dont need many librarys to get the data from the user
    (b)Beans->Bbasic beans - Bank account and Customer
             Account has the ReentrantLock for each account
            Account has balance and Customer as attributes
            Account has internal mehods for credit debit
   (c) operation -> Bank,MoneyTransfer,AccountTeransfer. 
            Bank holds the DATA structure for saving the inmemory data
            Bank holds accounts
            Bank has various operations- get balance, addaccount, withdrawmoney, addmoney and transfer
            MoneyTransfer and AccountTransfer are not used from web
    (d)UTIL-> basic utils
          ->FActory makse sure only one bank is created
     

 (2) Junit testing- cases- Here individual functions are tested- Add account, add money, transfer money and get balance
    Cases-> Junit test cases for each type of operation

--Endpoints
(1)
Create customer - http://localhost:8080/acc/account
Type -POST
Data-Json 
{"name":"Amrit","address":"Bangalore"}


(2)
Addmoney- http://localhost:8080/acc/addmoney
Type -POST
Data-Json 
{"to":"1002", "amount":"8000"}


(3)
Transfer- http://localhost:8080/acc/transfer
Type -POST
Data-Json 
{"from":"1000","to":"1001", "amount":"100"}

(4)
WithdrawMoney- http://localhost:8080/acc/withdrawmoney
Type -POST
Data-Json 
{"from":"1000", "amount":"50"}



------------TESTING--------------------
POSTMAN testing link
https://www.getpostman.com/collections/b58c4281353ed6cc9654

Nodejs code for End to end parallel request testing - is added in the repository as webtestcases.js.js

Response from testing (attached as results.txt suggests that , this works for multiple parallel requests).
The threads wait for upto 2seconds and if lock is not obtained it responds with try again. Only successful locked threads can read/write the account


--results are in results.txt
--logs are in logs.txt
