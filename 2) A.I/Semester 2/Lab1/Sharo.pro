/*****************************************************************************

		Copyright (c) My Company

 Project:  SHARO
 FileName: SHARO.PRO
 Purpose: No description
 Written by: Visual Prolog
 Comments:
******************************************************************************/

include "sharo.inc"

domains
    x = Integer.
predicates
    nondeterm sum(x, x, x, x, x).
    nondeterm fact(Integer, Integer).
    nondeterm sum_even(Integer, Integer, Integer, Integer, Integer, Integer).
clauses
    sum(X, A, 0, 0,Z):- Z = A + X. 
    sum(X, A, 1, 0,Z):- Z = A.
    sum(X, A, 1, 1,Z):- Z = A + X. 
    sum(X, A, 0, 1,Z):- Z = A.

    sum_even(End, End, EAc, OAc, EResult, OResult):- EResult = EAc, OResult = OAc.
    sum_even(StartX, End, EAc, OAc, EResult, OResult):-
        M = StartX mod 2,
        sum(StartX, EAc, M, 0, NewEAc),
        sum(StartX, OAc, M, 1, NewOAc),
        NewX = StartX + 1,
        sum_even(NewX, End, NewEAc, NewOAc, EResult, OResult).
goal
    % fact(5, R).
    sum_even(1, 5, 0, 0, E, O).


