package info.fivecdesign.gamecollection.portal;

import java.io.PrintStream;
import java.util.Scanner;

public interface GameConsole {
	
	public int run(PrintStream out, Scanner in, Portal portal);
	
	public String getName();

}
