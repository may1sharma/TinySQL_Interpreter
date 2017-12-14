
# TinySQL Interpreter

# Interface: Single User Text Based Iterative Interface.

# Requisite: Java SE 1.8

#How to Run
Steps:
1. Open terminal (/command prompt) and go to "TinySQL_Interpreter" Project location.
2. Run the following commands:
	> javac -target 1.8 -source 1.8 -d ./out/ src/parser/*.java src/storageManager/*.java src/interpreter/*.java -Xlint:unchecked
	> cd out/
	> java -cp . interpreter/Main
3. A text based interface will open with following options:
	Enter 1 for Single Query Input
	Enter 2 for uploading Input File 
	Enter 0 to exit the interface
4. Choose appropriate method and follow the instructions. The interface is iterative which means, 
	after processing the given input it will present the above choices again for the next iteration.
5. Processing the input queries may take a while depending on the input size. Please be patient.
6. The output of individual iteration would be logged inside /out/Result.txt 
	The output file Result.txt is rewritten on each iteration so make a copy if you need to persist the output.
	Note: It is better to open the output file in advanced editors instead of basic Notepad.
	
	
# Developers
Mayank Sharma (mayank.sharma@tamu.edu) 
