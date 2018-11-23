library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity PSC is
  port (
    clk, S: in std_logic;
    input : in std_logic_vector(15 downto 0);
    out0  : out std_logic := '1';
    out1  : out std_logic := '1'
  );
end entity;


architecture v1 of PSC is
  signal counter : unsigned(5 downto 0) := (others => '0');
  signal data    : std_logic_vector(15 downto 0);
  signal out0_intern : std_logic;
  signal out1_intern : std_logic;
begin
  --use interal signals
  out0 <= out0_intern;
  out1 <= out1_intern;
  process(clk)
  begin
    if rising_edge(clk) then
      if counter = 0 then
        if S = '0' then
          --read data
          data <= input;
          out0_intern <= '1';
        end if;
    --protocol:
      elsif counter = 2 or counter = 3 then
        out0_intern <= '0';
      elsif counter >= 4 and counter <= 11 then
        --read from right part of input array (offset 4)
        out0_intern <= data(to_integer(counter-4));
    --protocol:
      elsif counter = 12 or counter = 13 then
        out0_intern <= '0';
      elsif counter = 14 or counter = 15 then
        out0_intern <= '1';
      elsif counter = 16 or counter = 17 then
        out1_intern <= '1';
      elsif counter = 18 or counter = 19 then
        out1_intern <= '0';
      elsif counter >= 20 and counter <= 27 then
        --read from right part of input array (offset 12)
        out1_intern <= data(to_integer(counter-12));
    --protocol:
      elsif counter = 28 or counter = 29 then
        out1_intern <= '0';
      elsif counter = 30 or counter = 31 then
        out1_intern <= '1';
      end if;

      if counter /= 0 or (counter = 0 and S = '0') then
        --wait for unblocking; blocking during operation is ignored
        counter <= counter + 1;
      end if;

     if counter = 32 then
       --reset counter to continue with next dataset
	   counter <= "000000";
     end if;

    end if;
  end process;
end architecture;
