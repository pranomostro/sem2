library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity psc_tb is
end entity;

architecture behaviour of psc_tb is
	component psc
		port(	clk : in std_logic;
			input : in std_logic_vector(15 downto 0);
			S : in std_logic;
			out0 : out std_logic;
			out1 : out std_logic
		);
	end component;
	signal clk : std_logic := '1';

	for psc_0: psc use entity work.psc;
	signal S, out0, out1 : std_logic;
	signal input : std_logic_vector(15 downto 0);

	--Test can be chosen by uncommenting the first, second or third expected signal
	--and the appropriate input (in line 38 - 40)
	--Test 1 is currently selected

	signal expected : std_logic_vector(31 downto 0) := "11001111111100111100011000110011";
	--signal expected : std_logic_vector(31 downto 0) := "11000110110100111100110001000011";
	--signal expected : std_logic_vector(31 downto 0) := "1100UUUUUUUU00111100UUUUUUUU0011";

	signal tempExpected : std_logic;

	signal waitOnce : std_logic := '1';
	signal done : std_logic := '0';

	signal counter : UNSIGNED(5 downto 0) := "000000";
begin
	psc_0: psc port map(clk => clk, input => input, S => S, out0 => out0, out1 => out1);
	clk <= not clk after 52083 ns;
	--9600 baud

	input <= "1111111101100011";
	--input <= "0110110111000100";
	--input <= "UUUUUUUUUUUUUUUU";

	S <= '0';
	tempExpected <= expected(to_integer(counter)) when counter < 32 else 'U';
	--use tempExpected for assert checks; set to U if counter out of range
	--used for debugging:
	--assert false report "starting test" severity note;
	process(clk)
	begin
		if(rising_edge(clk)) then
			if(waitOnce = '1') then
				waitOnce <= '0';
				--Wait one cycle to get testbench and entity in sync at the start
				--Entity needs one cycle to load values
			else
				if(counter = 32) then
				    --reset
					counter <= "000000";
				elsif(counter < 16) then
				    --print if wrong signal is received
					assert (out0 = tempExpected)
						report "wrong output out0; expected "
						& std_logic'image(tempExpected) & " got: "
						& std_logic'image(out0) & " on counter: "
						& integer'image(to_integer(counter))
						severity error;
				    --only used for debugging
					--assert (out0 /= tempExpected)
					--	report "right output out0; got " & std_logic'image(out0)
					--	& ", expected " & std_logic'image(tempExpected)
					--	& " on counter: " & integer'image(to_integer(counter))
					--	severity note;
					counter <= counter + 1;
				else
				    --print if wrong signal is received
					assert (out1 = tempExpected)
						report "wrong output out1; expected "
						& std_logic'image(tempExpected) & " got: " 
						& std_logic'image(out1) & " on counter: "
						& integer'image(to_integer(counter)) 
						severity error;
				    --only used for debugging
					--assert (out1 /= tempExpected)
					--	report "right output out1; got " & std_logic'image(out1)
					--	& ", expected " & std_logic'image(tempExpected)
					--	& " on counter: " & integer'image(to_integer(counter))
					--	severity note;
					counter <= counter + 1;
				end if;
			end if;
		end if;
	end process;
end architecture;
