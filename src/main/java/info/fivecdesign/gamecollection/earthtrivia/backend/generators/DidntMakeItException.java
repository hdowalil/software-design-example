package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

/**
 * 
 * we always try to generate questions where it is always possible that no suitable question was generated.
 * in that case we just continue generating another one and skip that particular attempt!
 *
 */
public class DidntMakeItException extends RuntimeException {

	private static final long serialVersionUID = -6246027082249039556L;
}
