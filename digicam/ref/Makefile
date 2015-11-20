#
# Simple make file for building JPEG reference code
#

CC = gcc
CMP = diff -s
RM = rm -f

# source files
CFILES = 	jpegencoder.c read.c \
		quantize.c dct.c file.c \
		huffencode.c ReadBmp_aux.c zigzag.c

# header files to depend on 
HFILES =	dct.h file.h huffencode.h read.h \
		quantize.h  ReadBmp_aux.h  zigzag.h

# additional compiler options (enable all warnings)
COPTS = 	-Wall -g

# name of the executable
EXEFILE = 	./jpegencoder

# output file name
TESTFILE =	test.jpg

# reference file name
GOLDFILE =	golden.jpg

# default rule to build target executable
all: $(EXEFILE)

# build everything
$(EXEFILE): 	$(CFILES) $(HFILES)
	$(CC) $(COPTS) -o $(EXEFILE) $(CFILES)

# clean
clean:
	$(RM) *~
	$(RM) $(EXEFILE) $(TESTFILE)

# test whether output file is equal to reference file
test:	all
	$(EXEFILE) && $(CMP) $(TESTFILE) $(GOLDFILE)
