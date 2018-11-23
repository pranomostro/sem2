ERA-Praktikum, Projekt 1: arcsin in Assembler
=============================================

**Ungenauigkeiten-Disclaimer: siehe unten**

Das Programm bekommt über ein C-Rahmenprogramm eine Zahl und liefert mittels Lookup-Table einen möglichst genauen Wert des Arkus-Sinus der Zahl zurück.

Abhängigkeiten
--------------

* Version-Control-System: [subversion](https://subversion.apache.org/) (Version 1.9.3)
* Netwide Assembler: [nasm](https://www.nasm.us/) (Version 2.11.08)
* C-Compiler: [gcc](https://gcc.gnu.org/) (Version 5.4.0)
* Build-Organisationstool: [make](https://www.gnu.org/software/make/) (Version 4.1)

Die jeweiligen Versionsnummern zeigen die Versionen, mit denen das Programm getestet wurde, andere Versionen sollten also ebenso funktionieren.  
Getestet wurde auf Ubuntu 16.04 32-Bit.

Installation
------------

1. Ordner aus SVN klonen `svn checkout svn://vmbode1.informatik.tu-muenchen.de/erapraktikum`
2. In den Ordner wechseln `cd erapraktikum/ss18-g42/Projekt1/Implementierung`
3. Kompilieren `make`

Ausführen des Programms
-----------------------

Mit `./bin/arcsin [input]` wird für den input der entsprechende Wert berechnet

Ausführen von Tests
-------------------

`make testrun` ausführen: Tests und benötigte Dateien werden kompiliert und ausgeführt.  
Getestet werden nun:

* Zufällige Werte
* Ungültige Werte
* Randbereiche (**siehe Ungenauigkeiten**)
* Die benötigte Zeit

Die Ergebnisse des Assembler-Programms werden dann mit denen der Bibliotheksfunktion verglichen und die Resultate ausgegeben.

Hinzufügen von Tests
--------------------

1. Zum Hinzufügen von Tests die Datei "src/c/tests.c" bearbeiten:
    * In das Array `test_value` in Zeile 11 Wertepaare hinzufügen
        * In der Form `{[Eingabewert], [erwarteter Ausgabewert]}`
        * Beispielsweise  `{[0], [0]}`
    * *Alternativ:* Für komplexere Tests der `main`-Methode eine Routine hinzufügen
2. `make clean`, `make` und `make testrun` aufrufen, um die Tests zu starten

Ungenauigkeiten
---------------

Vor allem in den Randbereichen werden die Ergebnisse des Programmes ungenauer.  
Dies hat primär folgende Gründe:

* Floating-Point-Fehler können die Eingabe leicht verändern
* In den Randbereichen ändert sich die Funktionswerte sehr stark, dies wirkt sich auf die Präzision der Interpolation aus

Tests in den entsprechenden Bereichen geben daher vor allem in den Randbereichen eine Warnung aus, wenn eine das berechnete Ergebnis nicht dem Library-Ergebnis entspricht. Dieses Verhalten ist somit erwartet.

Befehle
-------

    compile:    make
    debug:      make debug (Kompilierung mit Debug-Symbolen in Binärdateien)
    run:        ./bin/arcsin [input]
    test:       make testrun
    cleanup:    make clean

Mitwirkende
-----------

* Projektleitung: Adrian Regenfuß
* Verantwortlicher Vortrag: Korbinian Stein
* Verantwortlicher Dokumentation: Till Müller