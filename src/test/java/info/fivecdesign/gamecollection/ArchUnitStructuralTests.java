package info.fivecdesign.gamecollection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

public class ArchUnitStructuralTests {

	static JavaClasses importedClasses = null;

	@BeforeAll
	public static void importClasses() {
		importedClasses = new ClassFileImporter().importPackages("info.fivecdesign.gamecollection");
		System.out.println("importing...");
	}

	@DisplayName("Testing that the different slices (basically the different games) are not dependending on oeach other in a cyclic way")
	@Test
	public void slicesFreeOfCycles() {
		SlicesRuleDefinition.slices().matching("info.fivecdesign.gamecollection.(*)..").should().beFreeOfCycles()
				.check(importedClasses);
	}

	@DisplayName("Testing the wanted dependencies of the rather complex Earthtrivia Backend")
	@Test
	public void testEarthTriviaBackendDependencies() {
		ArchRuleDefinition.noClasses().that()
				.resideInAPackage("info.fivecdesign.gamecollection.earthtrivia.backend.generators").should()
				.accessClassesThat().resideInAPackage("info.fivecdesign.gamecollection.earthtrivia.backend")
				.check(importedClasses);
		ArchRuleDefinition.noClasses().that()
				.resideInAPackage("info.fivecdesign.gamecollection.earthtrivia.backend.info").should()
				.accessClassesThat().resideInAPackage("info.fivecdesign.gamecollection.earthtrivia.backend")
				.check(importedClasses);
		;
		ArchRuleDefinition.noClasses().that()
				.resideInAPackage("info.fivecdesign.gamecollection.earthtrivia.backend.info").should()
				.accessClassesThat().resideInAPackage("info.fivecdesign.gamecollection.earthtrivia.backend.generators")
				.check(importedClasses);
		;
	}

	@DisplayName("UI Package in Slices should not be accessed by Backend Packages")
	@Test
	public void testUiBackendDependency() {
		ArchRuleDefinition.noClasses().that().resideInAPackage("info.fivecdesign.gamecollection.*.backend..").should()
				.accessClassesThat().resideInAPackage("info.fivecdesign.gamecollection.*.ui..").check(importedClasses);
	}

}
