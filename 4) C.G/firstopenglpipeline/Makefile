HDR_DIR = header C:\dev\lib\include
HEADER = $(addprefix -I,$(HDR_DIR))
LIB_DIR = C:\dev\lib\freeglut\lib\x64
LIB = $(addprefix -L, $(LIB_DIR))
LIB_NAMES = freeglut_static opengl32 winmm gdi32 glu32
LIBRARY = $(addprefix -l,$(LIB_NAMES))
CCOPTION = Wall std=gnu++11 D__USE_MINGW_ANSI_STDIO=1
FLAGS = $(addprefix -,$(CCOPTION))
CC = g++
OBJ_DIR = obj
OBJ = main.o 
BUILD_DIR = build
SRC_DIR = S:\Test\firstopenglpipeline\src

$(BUILD_DIR)\firstopenglpipeline.exe: $(addprefix $(OBJ_DIR)\,$(OBJ)) 
	 $(CC) $(FLAGS) $(addprefix $(OBJ_DIR)\,$(OBJ)) -o $(BUILD_DIR)\firstopenglpipeline.exe $(LIB) $(LIBRARY)

$(OBJ_DIR)\main.o: $(SRC_DIR)\main.cpp 
	 $(CC) $(FLAGS) -c $(SRC_DIR)\main.cpp -o $(OBJ_DIR)\main.o $(HEADER)


clean: 
	del /Q $(OBJ_DIR)\*.o
	del /Q $(BUILD_DIR)\*.exe
