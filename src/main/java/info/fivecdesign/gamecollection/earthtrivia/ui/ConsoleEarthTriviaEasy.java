package info.fivecdesign.gamecollection.earthtrivia.ui;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.Difficulty;

public class ConsoleEarthTriviaEasy extends ConsoleEarthTriviaBase {

	public ConsoleEarthTriviaEasy() {
		super(Difficulty.EASY);
	}

	@Override
	public String getName() {
		return super.getName() + " Difficulty: Easy";
	}
	
}
