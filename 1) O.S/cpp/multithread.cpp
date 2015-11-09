#include <windows.h>
#include <process.h>
#include <stdio.h>

DWORD WINAPI test(LPVOID t) {
    return 0;
}

int main(int argc, char **argv) {
    LPDWORD threadID = 0;
    HANDLE lel = CreateThread( NULL, 0, test, NULL, 0, threadID); 
    WaitForSingleObject(lel, INFINITE);

    return 0;
}
