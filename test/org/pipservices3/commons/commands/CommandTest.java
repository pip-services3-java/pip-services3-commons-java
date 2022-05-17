package org.pipservices3.commons.commands;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.commons.run.IExecutable;
import org.pipservices3.commons.run.Parameters;

public class CommandTest {
	private Command command;
	
	@Before
	public void setCommand() {
		IExecutable interfaceEx = new IExecutable() { 
			public Object execute(String correlationId, Parameters parameters)
					throws ApplicationException {
				if (correlationId.equals("wrongId")) 
					throw new ApplicationException(null, null, null, "Test error");
				
				return 0;
            }
		};
		command = new Command("name", null, interfaceEx);
	}
	
	@Test
	public void testCommand() {
		try {
			command = new Command(null, null, null);
		} catch (NullPointerException ex) {
			assertEquals("Command name is not set", ex.getMessage());
		}
		
		try {
			command = new Command("name", null, null);
		} catch (NullPointerException ex) {
			assertEquals("Command function is not set", ex.getMessage());
		}
	}
	
	@Test
	public void testGetName() {
		assertEquals("name", command.getName());
	}
	
	@Test
	public void testExecute() throws ApplicationException {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
	       map.put(8, "title 8");
	       map.put(11, "title 11");
	    Parameters param = new Parameters(map);

		assertEquals(command.execute("a", param), 0);

		try {
			command.execute("wrongId", param);
		} catch (ApplicationException e) {
			assertEquals("Test error", e.getMessage());
		}
		
	}
}
