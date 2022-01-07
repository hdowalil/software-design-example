package info.fivecdesign.gamecollection;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Generator;
import info.fivecdesign.gamecollection.portal.GameConsole;

@AnalyzeClasses(packages = "info.fivecdesign.gamecollection")
public class ArchUnitConventionsTests {

	@ArchTest
	public final static ArchRule generatorNamePrefix = classes().that().implement(Generator.class)
						.should().haveSimpleNameStartingWith("Generator");

	@ArchTest
	public final static ArchRule consoleNamePrefix = classes().that().implement(GameConsole.class)
						.should().haveSimpleNameStartingWith("Console");
	
	private static ArchCondition<JavaClass> hasMethodNext =
		    new ArchCondition<JavaClass>("has a method called next") {
		        @Override
		        public void check(JavaClass item, ConditionEvents events) {
		    		boolean found = false;
		    		for (JavaMethod method : item.getAllMethods()) {
		    			if (method.getName().equals("next")) {
		    				found = true;
		    			}
		    		}
		    		if (!found) {
		    			events.add(SimpleConditionEvent.violated(item, "Class does not have a next method!"));
		    		}
		        }
		    };
		    
	@ArchTest
	public final static ArchRule selectorsHaveNextMethod = classes().that().haveSimpleNameEndingWith("Selector").should(hasMethodNext);

}
