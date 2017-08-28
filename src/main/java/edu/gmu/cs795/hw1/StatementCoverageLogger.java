package edu.gmu.cs795.hw1;

public class StatementCoverageLogger {
	static {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				dump();
			}
		}));
	}

	public static void dump() {
	}
}