include config.mk

all: directories $(PROG)

debug: directories $(PROG)

directories: $(BUILD) $(BIN)

$(BIN):
	@mkdir -p bin
$(BUILD):
	@mkdir -p build

$(BIN)/arcsin: $(BUILD)/rahmenprogramm.o $(BUILD)/routine.o
	$(CC) -o $@ $(BUILD)/rahmenprogramm.o $(BUILD)/routine.o $(CFLAGS) 

$(BIN)/tests: $(BUILD)/tests.o $(BUILD)/routine.o
	$(CC) -o $@ $(BUILD)/tests.o $(BUILD)/routine.o $(CFLAGS)

$(BUILD)/routine.o: src/asm/routine.nasm
	$(ASM) $(AFLAGS) -o $@ $<

$(BUILD)/rahmenprogramm.o: src/c/rahmenprogramm.c
	$(CC) -c $< -o $@ $(CFLAGS)

$(BUILD)/tests.o: src/c/tests.c
	$(CC) -c $< -o $@ $(CFLAGS)

$(BIN)/generate_numbers: src/c/generate_numbers.c
	$(CC) $< -o $@ $(CFLAGS)

clean:
	rm -rf $(BIN) $(BUILD)

testrun: $(BIN)/tests
	./$(BIN)/tests

.PHONY: all clean install uninstall testrun
