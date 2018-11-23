ERA-Praktikum, Projekt 2: Parallel-Seriell-Konverter in VHDL
============================================================

Dieser in VHDL implementierte Baustein ist ein Parallel-Seriell-Konverter, der
nach einem vorgegebenen Protokoll zuvor parallel eingegebene Daten seriell
über zwei Leitungen ausgibt.

Abhängigkeiten
--------------

* Version-Control-System: [subversion](https://subversion.apache.org/) (Version 1.9.3)
* VHDL-Kompiler: [ghdl](http://ghdl.free.fr/) (Version 0.29)
* Build-Organisationstool: [make](https://www.gnu.org/software/make/) (Version 4.1)
* Zum Anzeigen der Wellenformen nach der Simulation: [gtkwave](http://gtkwave.sourceforge.net/) (Version 3.3.66)

Die jeweiligen Versionsnummern zeigen die Versionen, mit denen das Programm getestet wurde, andere Versionen sollten also ebenso funktionieren.  
Getestet wurde auf Ubuntu 16.04 32-Bit.

Installation
------------

1. Ordner aus SVN klonen `svn checkout svn://vmbode1.informatik.tu-muenchen.de/erapraktikum`
2. In den Ordner wechseln `cd erapraktikum/ss18-g42/Projekt2/Implementierung`
3. Kompilieren `make`

Ausführen von Tests
-------------------

1. "psc_tb.vhd" bearbeiten und in den Zeilen 27 bis 29, sowie 42 bis 44 einen Test einkommentieren (standardmäßig ist Test 1 ausgewählt).

    **Nach ändern des Tests muss erneut `make` aufgerufen werden, damit die Bausteine mit den neuen Einstellungen erzeugt werden.**

2. `make run` aufrufen. Die Simulation wird nun gestartet und der Test läuft durch.

    _Sollte es Abweichungen zwischen dem erwarteten und dem tatsächlichen Ergebnis geben, so werden diese während der Simulation in der Konsole ausgegeben._

3. GTKWave öffnet sich nun mit den erzeugten Wellenformen.
    * In den ersten 16 Takten soll **expected** mit **out0** übereinstimmen
    * In den Takten 17 bis 32 soll **expected** mit **out1** übereinstimmen

Hinzufügen von Tests
--------------------

Zum Hinzufügen von Tests die Datei "psc_tb.vhd" bearbeiten:

1. Alle existierenden Tests in den Zeilen 27 bis 29 und 42 bis 44 auskommentieren
2. In Zeile 45 die Eingangsdaten dem `std_logic_vector input` der Länge 16 Bit zuweisen
    * In der Form: `input <= "BBBBBBBBAAAAAAAA";`
    * Beispielsweise: `input <= "1111111101100011";`
3. In Zeile 30 die erwarteten Ausgangsdaten als `std_logic_vector expected` der Länge 32 Bit definieren
    * In der Form: `signal expected : std_logic_vector(31 downto 0) := "1100BBBBBBBB00111100AAAAAAAA0011";`
    * Beispielsweise: `signal expected : std_logic_vector(31 downto 0) := "11001111111100111100011000110011";`
4. `make` zum erneuten Kompilieren aufrufen
5. `make run` zum Starten der Simulation aufrufen


Befehle
-------

    compile:    make
    run:        make run
    cleanup:    make clean
    
Mitwirkende
-----------
* Projektleitung: Till Müller
* Verantwortlicher Vortrag: Adrian Regenfuß
* Verantwortlicher Dokumentation: Korbinian Stein