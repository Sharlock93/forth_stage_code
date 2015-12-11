#define FREEGLUT_STATIC
#include <iostream>
#include <sh_inc\GL\freeglut.h>
// #include <GL\glew.h>

#define 
g++ -v main.cpp -lfreeglut_static -lopengl32 -lwinmm -lgdi32 -lglu32

int main(int argc, char *argv[])
{
    glutInit(&argc, argv);
    return 0;
}
