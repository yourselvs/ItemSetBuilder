package me.yourselvs.tests;

import org.junit.Test;

import me.yourselvs.Program;
import net.rithms.riot.api.RiotApiException;

import static org.junit.Assert.assertEquals;

public class TestItemData {
	@Test
	public void test() {
		try {
			Program.main(null);
		} catch (RiotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
