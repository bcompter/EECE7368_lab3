#ifndef Read_h
#define Read_h

#include "digicam.h"

void readblock(unsigned char [IMG_HEIGHT_MDU*8][IMG_WIDTH_MDU*8], 
               int block[64]);


#endif
