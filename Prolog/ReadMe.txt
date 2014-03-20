Name: Pramodh Aravindan
USC ID: 9930-7510-26
Email: aravinda@usc.edu

Instructions on how to compile the program
-------------------------------------------------

1. Both files students1.pl and students2.pl were programmed and tested on GNU Prolog 1.3.0
2. Lists in prolog were used to represent the students' data.
3. Both the prolog files can be compiled with the following command

  consult(students1).
  consult(students2).  

4. The following query can be typed in to check the entire solution provided by the program 

   myprogram(X). 

Part 1:
--------

a) How many solutions are consistent with the clues? 

Query
-------

| ?- myprogram(X).

Output
--------

X = [[noah,australia,swimming,pineapple,jones],[olivia,belgium,marathon,mango,lisa],[jacob,chile,volleyball,blackberry,moore],[chloe,morocco,shooting,grapes,harris],[emma,cuba,badminton,coconut,maria],[ethan,jamaica,diving,cherry,sharon]] ? a

X = [[noah,australia,swimming,pineapple,jones],[olivia,belgium,marathon,mango,lisa],[jacob,chile,volleyball,blackberry,moore],[chloe,morocco,shooting,grapes,harris],[emma,cuba,badminton,coconut,maria],[ethan,jamaica,diving,cherry,sharon]]

X = [[ethan,australia,diving,cherry,jones],[olivia,belgium,marathon,mango,lisa],[jacob,chile,volleyball,blackberry,moore],[chloe,morocco,shooting,grapes,harris],[emma,cuba,badminton,coconut,maria],[noah,jamaica,swimming,pineapple,sharon]]

X = [[ethan,australia,diving,cherry,jones],[olivia,belgium,marathon,mango,lisa],[jacob,chile,volleyball,blackberry,moore],[chloe,morocco,shooting,grapes,harris],[emma,cuba,badminton,coconut,maria],[noah,jamaica,swimming,pineapple,sharon]]

b) Who is the second student in the waiting list?

Query
--------

| ?- myprogram([_,[X|_]|_]).

Output
---------

X = olivia ? a

X = olivia

X = olivia

X = olivia

Part 2
--------

c) What is the order of the students in the waiting list?

Query
-------

| ?- myprogram([[A|_],[B|_],[C|_],[D|_],[E|_],[F|_]]).

Output
---------

A = ethan
B = olivia
C = emma
D = jacob
E = chloe
F = noah ? a

A = ethan
B = olivia
C = emma
D = jacob
E = chloe
F = noah

A = ethan
B = olivia
C = emma
D = jacob
E = chloe
F = noah

A = ethan
B = olivia
C = emma
D = jacob
E = chloe
F = noah

d) Which Amazon seller does Noah buy fruit from?

Query
-------
//Copy and paste the entire 6 lines as a single query

myprogram([[noah,_,_,_,A]|_]);
myprogram([[_,_,_,_,_],[noah,_,_,_,B]|_]);
myprogram([[_,_,_,_,_],[_,_,_,_,_],[noah,_,_,_,C]|_]);
myprogram([[_,_,_,_,_],[_,_,_,_,_],[_,_,_,_,_],[noah,_,_,_,D]|_]);
myprogram([[_,_,_,_,_],[_,_,_,_,_],[_,_,_,_,_],[_,_,_,_,_],[noah,_,_,_,E]|_]);
myprogram([[_,_,_,_,_],[_,_,_,_,_],[_,_,_,_,_],[_,_,_,_,_],[_,_,_,_,_],[noah,_,_,_,F]]). 

Output
--------

F = sharon ? a

F = sharon

F = sharon

F = sharon


