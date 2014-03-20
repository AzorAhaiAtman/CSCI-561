% Define predicate functions

next_to(X,Y,List) :- iright(X,Y,List).
next_to(X,Y,List) :- iright(Y,X,List).
iright(L,R,[L|[R|_]]).
iright(L,R,[_|Rest]) :- iright(L,R,Rest).

% member(X,[X|Y]).
% member(X,[H|T]) :- member(X,T).

myprogram(Students) :- =(Students,
			
			[[_,_,_,_,_],							
			 [_,_,_,_,_],
			 [_,_,_,_,_],
			 [_,_,_,_,_],
			 [_,_,_,_,_],
			 [_,jamaica,_,_,_]]),						% 4. The student who comes from Jamaica is the last one in the waiting list

			member([emma,cuba,_,_,maria],Students),				% 1. Emma comes from Cuba and buys fruit from seller Maria
			
			member([_,_,badminton,coconut,_],Students),			% 2. The student who like to eat Coconut also likes to play Badminton
		
			iright([jacob,_,_,blackberry,_],[_,_,_,_,harris],Students),	% 3. Jacob likes to eat Blackberry and in the waiting list he(Jacob) is in 
											%    front of the student who buys fruit from seller harris			

			member([olivia,belgium,marathon,_,_],Students),			% 5. Olivia likes to run the Marathon in her spare time, and she comes from Belgium

			member([_,australia,_,_,jones],Students),			% 6. The student who buys fruit from seller Jones, comes from Australia
			
			member([_,_,shooting,_,harris],Students),			% 7. The student, who has a hobby of Shooting buys fruit from seller Harris
			
			member([noah,_,swimming,_,_],Students),				% 8. Noah has a hobby of Swimming

			member([_,_,H1,mango,lisa],Students),				% 9. The student, who loves to eat Mango and buys them from seller Lisa, never plays Badminton or Volleyball in his 
			(H1 \= badminton; H1 \= volleyball),				%    or her spare time

			member([ethan,_,diving,cherry,_],Students),			% 10. Ethan has a hobby of Diving and likes to eat Cherry

			member([_,morocco,_,grapes,_],Students),			% 11. The student who comes from Morocco likes to eat Grapes
			
			next_to([chloe,_,_,_,_],[jacob,_,_,_,_],Students),		% 12. Chloe and Jacob are next to eachother in the waiting list	
			
			next_to([chloe,_,_,_,_],[_,_,_,coconut,_],Students),		% 13. Chloe is also next to the student who likes to eat Coconut
		
			member([jacob,_,_,_,moore],Students),				% 14. Jacob buys fruit from seller Moore

			iright([_,chile,_,_,_],[_,_,shooting,_,_],Students),		% 15. The student, who has a hobby of Shooting, is behind the student who comes from Chile in the waiting list
		
			member([noah,_,_,pineapple,_],Students),			% 16. Noah likes to eat Pineapple
			
		 	iright([_,australia,_,_,_],[_,_,_,mango,_],Students),		% 17. The student who likes to eat Mango is behind the student who comes from Australia in the waiting list

			iright([_,_,_,_,_],[emma,_,_,_,_],Students),
			iright([_,_,_,_,_],[jacob,_,_,_,_],Students),			% 18. Both Emma and Jacob are not in front of the waiting list

			member([_,_,_,_,sharon],Students),
			member([_,_,volleyball,_,_],Students).

