package info.fivecdesign.gamecollection.earthtrivia.ui;

import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Difficulty;

public class EarthTriviaHardConsole extends EarthTriviaConsoleBase {

	public EarthTriviaHardConsole() {
		super(Difficulty.HARD);
	}
	
	@Override
	public String getName() {
		return super.getName() + " Difficulty: Hard";
	}
	
}
