ASM=nasm
AFLAGS=-f elf32
debug: AFLAGS += -DDEBUG -g

CC=cc
CFLAGS=-m32 -lm
debug: CFLAGS += -DDEBUG -g
BIN=./bin
BUILD=./build

PROG=$(BIN)/arcsin $(BIN)/tests $(BIN)/generate_numbers
OBJ=$(BUILD)/rahmenprogramm.o $(BUILD)/routine.o $(BUILD)/tests.o
