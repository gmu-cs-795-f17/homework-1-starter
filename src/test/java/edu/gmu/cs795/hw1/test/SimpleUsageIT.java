package edu.gmu.cs795.hw1.test;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import edu.gmu.cs795.hw1.StatementCoverageLogger;
import edu.gmu.cs795.hw1.test.testedclasses.SampleClass;

public class SimpleUsageIT {
	@Test
	public void testSomeTest() throws Exception {
		new SampleClass().foo();
	}
}
