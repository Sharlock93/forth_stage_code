#define FREEGLUT_STATIC
#include <iostream>
#include <math.h>
#include <GL\glut.h>

using namespace std;

struct color {
    float r;
    float g;
    float b;
};

void draw_rect(float left, float right, float top, float bottom, color c = {1, 1, 1}) {
    glColor3f(c.r, c.g, c.b);
    glBegin(GL_POLYGON);
        glVertex2f(left, top);
        glVertex2f(right, top);
        glVertex2f(right, bottom);
        glVertex2f(left, bottom);
    glEnd();
}

void draw_circle(float Xc, float Yc, int R) {
    glColor3f(1.0, 1.0, 1.0);
    GLdouble x, y;
    glBegin(GL_LINE_LOOP);
    for (x = -R; x <= R; x++)
    {
        y = sqrt(R * R - (x * x));
        glVertex2i(Xc + x, Yc + y); // The upper half
        glVertex2i(Xc + x, Yc - y); // the lower half
    }
    glEnd();
}

void render() {
    glPushMatrix();
    glViewport(0, 0, 250, 250);
    glClearColor(0, 0, 0, 1);
    glClear(GL_COLOR_BUFFER_BIT); 
    gluOrtho2D(-10, 10, -10, 10); // set the window dimension
    draw_circle(400, 300, 50);
    draw_rect(-10, 10, 10, -10, {0, 0, 1});
    draw_rect(-5, 5, 5, -10, {0, 1, 0});
    glPopMatrix();
    glFlush();

    glPushMatrix();
    glViewport(250, 0, 250, 250);
    gluOrtho2D(-50, 50, -50, 50); // set the window dimension
    draw_circle(400, 300, 50);
    draw_rect(-10, 10, 10, -10, {0, 0, 1});
    draw_rect(-5, 5, 5, -10, {0, 1, 0});

    glPopMatrix();

    glFlush();

    glPushMatrix();
    glViewport(0, 250, 250, 250);
    gluOrtho2D(-50, 50, -50, 50); // set the window dimension
    draw_circle(400, 300, 50);
    draw_rect(-10, 10, 10, -10, {0, 0, 1});
    draw_rect(-5, 5, 5, -10, {0, 1, 0});
    glPopMatrix(); 
    glFlush();

    glPushMatrix();
    glViewport(250, 250, 250, 250);
    gluOrtho2D(-20, 20, -20, 20); // set the window dimension
    draw_circle(400, 300, 50);
    draw_rect(-10, 10, 10, -10, {0, 0, 1});
    draw_rect(-5, 5, 5, -10, {0, 1, 0});
    glPopMatrix();
    glFlush();
}

void Init()
{
    glClearColor(1.0, 1.0, 1.0, 0); // set the background color (white)
    glColor3f(0.0, 0.0, 0.0); // set the foreground color (black)
    // gluOrtho2D(-10, 10, -10, 10); // set the window dimension
}

int main( int argc, char* args[] )
{
	glutInit( &argc, args );
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize( 500, 500 );
	glutCreateWindow( "OpenGL" );

	glutDisplayFunc( render );
    Init();
	glutMainLoop();

	return 0;
}
