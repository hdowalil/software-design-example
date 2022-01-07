package info.fivecdesign.gamecollection.earthtrivia.ui;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.Difficulty;

public class ConsoleEarthTriviaMedium extends ConsoleEarthTriviaBase {

	public ConsoleEarthTriviaMedium() {
		super(Difficulty.MEDIUM);
	}
	
	@Override
	public String getName() {
		return super.getName() + " Difficulty: Medium";
	}
	
}
