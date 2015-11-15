domains
    region_number = Integer.
    region_color = String.
    board = Symbol.
predicates
    nondeterm region_colors(region_number, region_color, board).
    nondeterm region_neighbor(region_number, region_number, board).
    nondeterm same_color(region_number, region_number, region_color, board).
    nondeterm same_colors(region_number).
    nondeterm diff_color_board(region_number, region_number).
    
clauses
    region_colors(1, "red", a).
    region_colors(2, "blue", a).
    region_colors(3, "blue", a).
    region_colors(4, "yellow", a).    
    region_colors(5, "blue", a).    

    region_colors(1, "red", b).
    region_colors(2, "blue", b).
    region_colors(3, "red", b).
    region_colors(4, "green", b).    
    region_colors(5, "blue", b).

    region_neighbor(1, 2, _).
    region_neighbor(1, 3, _).
    region_neighbor(1, 5, _).
    region_neighbor(2, 3, _).
    region_neighbor(2, 4, _).
    region_neighbor(3, 4, _).
    region_neighbor(4, 5, _).

    same_color(Region_number1, Region_number2, Color, Board):-
        region_colors(Region_number1, Color, Board),
        region_neighbor(Region_number1, Region_number2, Board),
        region_colors(Region_number2, Color, Board),
        Region_number1 <> Region_number2.
    
    same_colors(Region_one):-
        region_colors(Region_one, _, _), write("Hello: ", Region_one).


    diff_color_board(Region_number1, Region_number2):- 
        region_colors(Region_number1, Color, a),
        region_colors(Region_number2, Color2, b),
        Region_number1 = Region_number2,
        Color <> Color2.
        %Board <> Board2.
goal
same_colors(G).
