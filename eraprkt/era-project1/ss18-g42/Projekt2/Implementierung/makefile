include config.mk

compile: clean psc psc_tb

run: $(BIN)
	ghdl -r psc_tb --stop-time=3500000ns --wave=psc_tb.ghw
	gtkwave psc_tb.ghw psc.gtkw

psc: $(OBJ)
	ghdl -e $@

psc_tb: $(OBJ)
	ghdl -e $@

psc.o: psc.vhd
	ghdl -a psc.vhd

psc_tb.o: psc_tb.vhd
	ghdl -a psc_tb.vhd

clean:
	rm -f $(OBJ) $(BIN) e~psc.o e~psc_tb.o work-obj93.cf

.PHONY: run clean
