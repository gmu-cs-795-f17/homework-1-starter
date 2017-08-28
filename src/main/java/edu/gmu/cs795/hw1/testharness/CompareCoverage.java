package edu.gmu.cs795.hw1.testharness;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class CompareCoverage {
	public static void main(String[] args) {
		File coverageFile = new File("covered.txt");
		if (!coverageFile.exists())
			throw new AssertionError(
					"Could not open covered.txt file, should exist at " + coverageFile.getAbsolutePath());
		File unCoverageFile = new File("uncovered.txt");
		if (!unCoverageFile.exists())
			throw new AssertionError(
					"Could not open uncovered.txt file, should exist at " + unCoverageFile.getAbsolutePath());
		HashSet<String> coveredFromRun = new HashSet<>();
		HashSet<String> shouldBeCovered = new HashSet<>();
		HashSet<String> unCoveredFromRun = new HashSet<>();
		HashSet<String> shouldBeUnCovered = new HashSet<>();

		try {
			boolean err = false;
			Scanner s = new Scanner(coverageFile);
			while (s.hasNextLine()) {
				coveredFromRun.add(s.nextLine());
			}
			s.close();
			s = new Scanner(unCoverageFile);
			while (s.hasNextLine()) {
				unCoveredFromRun.add(s.nextLine());
			}
			s.close();
			s = new Scanner(CompareCoverage.class.getResourceAsStream("/integration-test/covered.txt"));
			while (s.hasNextLine()) {
				shouldBeCovered.add(s.nextLine());
			}
			s.close();
			s = new Scanner(CompareCoverage.class.getResourceAsStream("/integration-test/uncovered.txt"));
			while (s.hasNextLine()) {
				shouldBeUnCovered.add(s.nextLine());
			}
			s.close();
			
			HashSet<String> extraCovered = new HashSet<>(coveredFromRun);
			extraCovered.removeAll(shouldBeCovered);
			if(extraCovered.size() > 0){
				System.err.println("Eroneous coverage (not specified in src/main/resources/integration-test/covered.txt):");
				for(String l : extraCovered)
					System.err.println("\t"+l);
				err = true;
			}
			HashSet<String> extraUnCovered = new HashSet<>(unCoveredFromRun);
			extraUnCovered.removeAll(shouldBeUnCovered);
			if(extraUnCovered.size() > 0){
				System.err.println("Eroneous non-coverage (not specified in src/main/resources/integration-test/uncovered.txt):");
				for(String l : extraUnCovered)
					System.err.println("\t"+l);
				err = true;
			}
			
			HashSet<String> notCovered = new HashSet<>(shouldBeCovered);
			notCovered.removeAll(coveredFromRun);
			if(notCovered.size() > 0){
				System.err.println("Missed coverage:");
				for(String l : notCovered)
					System.err.println("\t"+l);
				err = true;
			}
			HashSet<String> notUnCovered = new HashSet<>(shouldBeUnCovered);
			notUnCovered.removeAll(shouldBeUnCovered);
			if(notUnCovered.size() > 0){
				System.err.println("Missed non-coverage:");
				for(String l : notUnCovered)
					System.err.println("\t"+l);
				err = true;
			}
			if(err)
				throw new AssertionError("The covered.txt and uncovered.txt files don't match their intended contents!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
