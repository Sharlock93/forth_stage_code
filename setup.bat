@echo off
echo %1


goto %1


:OS
pushd "1) O.S"
call "setup.bat" %2
popd "1) O.S"
goto End 

:AI
pushd "2) A.I"
"setup.bat"
popd "2) A.I"
goto End

:DS
pushd "5) D.S" 
"setup.bat" %2
popd
goto End 

:AD
pushd "6) A.D"   
"setup.bat" %2
popd
goto End 


:End
echo "End has been reached."
