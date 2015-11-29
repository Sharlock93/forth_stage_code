predicates
    nondeterm max(integer, integer).
clauses
    max(X, Y):- X > Y, write(X), nl.
    max(X, Y):- X < Y, write(Y), nl.
    max(_, _):- write("="), nl.
goal
    max(3, 2).
