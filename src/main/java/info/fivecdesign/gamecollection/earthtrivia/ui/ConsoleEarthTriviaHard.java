package info.fivecdesign.gamecollection.earthtrivia.ui;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.Difficulty;

public class ConsoleEarthTriviaHard extends ConsoleEarthTriviaBase {

	public ConsoleEarthTriviaHard() {
		super(Difficulty.HARD);
	}
	
	@Override
	public String getName() {
		return super.getName() + " Difficulty: Hard";
	}
	
}
