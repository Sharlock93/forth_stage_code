domains
    x = integer.
predicates
    add(x, x, x).
    mul(x, x, x).
    sub(x, x, x).
    nondeterm prog(X).
    nondeterm welcome().
clauses
    add(X, Y, Z):- Z = X+Y.
    mul(X, Y, Z):- Z = X*Y.
    sub(X, Y, Z):- Z = X-Y.

    prog(1):- 
        write("Enter first number"), readInt(X),
        write("Enter second number"), readInt(Y),
        add(X, Y, R), write("Result: "), write(R), nl. 

    prog(2):- 
        write("Enter first number: "), readInt(X),
        write("Enter second number: "), readInt(Y),
        mul(X, Y, R), write("Result: "), write(R), nl. 
    prog(3):- 
        write("Enter first number: "), readInt(X),
        write("Enter second number: "), readInt(Y),
        sub(X, Y, R), write("Result: "), write(R), nl. 

    prog(X):- write("Wrong Number "), write(X), nl.

    welcome():-
        write("Welcome to the program"), nl,
        write("press 1 to add"), nl,
        write("press 2 to sub"), nl,
        write("press 3 to mul"), nl,
        write("press 0 to quit"), nl,
        write("Enter a number: "), 
        readInt(X),
        X <> 0,
        prog(X), nl,
        welcome(). 
goal
welcome().

