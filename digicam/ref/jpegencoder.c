#include "ReadBmp_aux.h"

#include "read.h"
#include "dct.h"
#include "quantize.h"
#include "zigzag.h"
#include "huffencode.h"

#include "digicam.h"

int main() {
	unsigned int iter;
	int dctin[64];
	int dctout[64];
	int quantizeout[64];
	int zigzagout[64];

        // preallocate memory for global variable
        unsigned char ScanBuffer[IMG_HEIGHT_MDU*8][IMG_WIDTH_MDU*8];


	ReadBmp(ScanBuffer);

	for (iter = 0; iter < IMG_BLOCKS; iter++)
	{
		readblock(ScanBuffer, dctin);
		dct(dctin, dctout);
		quantize(dctout,quantizeout);
		zigzag(quantizeout, zigzagout);
		huffencode(zigzagout);
	}

	return 0;
}
