package org.pipservices.commons.commands;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.pipservices.commons.errors.ApplicationException;
import org.pipservices.commons.run.IExecutable;
import org.pipservices.commons.run.Parameters;

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
	public void testExecute(){
		Map<Integer, Object> map = new HashMap<Integer, Object>();
	       map.put(8, "title 8");
	       map.put(11, "title 11");
	    Parameters param = new Parameters(map); 
		try {
			assertEquals(command.execute("a", param), 0);
		} catch (ApplicationException e) {

		}
		
		try {
			command.execute("wrongId", param);
		} catch (ApplicationException e) {
			assertEquals("Test error", e.getMessage());
		}
		
	}
}
