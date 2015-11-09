domains
    sex = Symbol.
    name = Symbol.
predicates 
    father(name, name).
    mother(name, name).
    nondeterm gender(name, sex).
    
    nondeterm brother(name, name).
    
    nondeterm cousign(name, name).
clauses
    father(kareem, muhammed).
    father(muhammed, gf).
    father(ahmed, gf).
    father(ali, ahmed).
    father(zahra, ahmed).
    
    mother(ahmed, gm).
    mother(ali, fatima).
    mother(zahra, fatima).
    mother(kareem, layla).
    mother(mohammed, gm).
    
    gender(layla, female).
    gender(muhammed, male).
    gender(kareem, male).
    gender(ahmed, male).
    gender(fatima, female).
    gender(ali, male).
    gender(zahra, female).
    
    brother(Name, Name2):- 
        gender(Name, male),
        gender(Name2, Gender2),
        father(Name, FatherName),
        father(Name2, FatherName),
        Name <> Name2.
    
    cousign(Name, Name2):-
        gender(Name, Gender),
        gender(Name2, Gender2),
        father(Name, FatherName),
        father(Name2, FatherName2),
        brother(FatherName, FatherName2).
        
goal
brother(Z, H).