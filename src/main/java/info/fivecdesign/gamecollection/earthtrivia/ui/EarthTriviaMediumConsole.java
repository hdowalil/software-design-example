package info.fivecdesign.gamecollection.earthtrivia.ui;

import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Difficulty;

public class EarthTriviaMediumConsole extends EarthTriviaConsoleBase {

	public EarthTriviaMediumConsole() {
		super(Difficulty.MEDIUM);
	}
	
	@Override
	public String getName() {
		return super.getName() + " Difficulty: Medium";
	}
	
}
