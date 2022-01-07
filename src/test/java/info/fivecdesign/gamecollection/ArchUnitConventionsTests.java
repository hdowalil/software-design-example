package info.fivecdesign.gamecollection;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Generator;
import info.fivecdesign.gamecollection.portal.GameConsole;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "info.fivecdesign.gamecollection")
public class ArchUnitConventionsTests {

	@ArchTest
	public final static ArchRule generatorNamePrefix = classes().that().implement(Generator.class)
						.should().haveSimpleNameStartingWith("Generator");

	@ArchTest
	public final static ArchRule consoleNamePrefix = classes().that().implement(GameConsole.class)
						.should().haveSimpleNameStartingWith("Console");

}
