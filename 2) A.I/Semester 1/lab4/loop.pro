predicates
    nondeterm loop(integer).
clauses
    loop(1):- 
        write("Bye"), nl.
    loop(X):-
        NewValue = X-1,
        write("X: "), write(X),
        write(" NV: "), write(NewValue), nl,
        loop(NewValue).
goal
loop(5).
