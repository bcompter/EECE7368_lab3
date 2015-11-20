//
// Monitor
//   -- read bytes from queue and write into file

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sim.sh>
#include "digicam.sh"

import "i_receiver";

behavior Monitor(i_receiver bytes, unsigned long long startTime) 
{
  // initialize file pointer to NULL, changed from 
  // NULL to 0 as init value to avoid compiler limitation
  FILE *f = 0;
  unsigned char buf[2];
  unsigned int nBytes = 0;
  int byteCount = 0;
  unsigned long long stopTime;
  unsigned long long elapsedTime;

  void main(void) 
  {
  	int frames;
  	for (frames = 0; frames < NUM_FRAMES; frames++)
  	{
  		printf("MONITOR::Saving Frame %d...\n", frames);

    buf[0] = 0;
    buf[1] = 1;
    
    	if(!f) 
    	{
      f=fopen("test.jpg","wb");
    }
    	if(!f) 
    	{
      fprintf(stderr, "Cannot open output file %s\n", "test.jpg");
    }
    
    	do
    	{
      nBytes++;

      // receive a single byte (note write it in alternating 
      // location to detect EOF later
      bytes.receive(&buf[nBytes & 1], sizeof(char));
      		//printf("MONITOR::Got a byte, %d!\n", byteCount++);

      // write single byte into file 
      		if (fwrite(&buf[nBytes & 1], sizeof(char),1,f) != 1) 
      		{
        fprintf(stderr, "Error writing output file %s\n", "test.jpg");
        fclose(f);
        exit(1);
      }
      
      // repeat until seing the EOF marker in last two byts 
    }while ( !((buf[nBytes     & 1] == 0xd9 ) &&
               (buf[(nBytes+1) & 1] == 0xff ))); 

    fclose(f);
    f = 0;
    	nBytes = 0;

    	printf ("Encoded JPEG file written successfully!\n");
    
    	// Elapsed Time
    	stopTime = now();
    	elapsedTime = stopTime - startTime;
    	printf("Simulation delay is %lld (%lld, %lld)\n", elapsedTime, startTime, stopTime);
    
    }  // end for each frame
    
    exit(0);
    
  }  // end main(void)
  
};  // end Monitor