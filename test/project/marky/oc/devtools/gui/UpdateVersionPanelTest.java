package project.marky.oc.devtools.gui;

import static org.junit.Assert.*;

import org.junit.Test;

public class UpdateVersionPanelTest
{
	@Test
	public void testValidVersionSingleDigit()
	{
		assertTrue(UpdateVersionPanel.isValidVersion("1"));
	}

	@Test
	public void testValidVersionDoubleDigit()
	{
		assertTrue(UpdateVersionPanel.isValidVersion("12"));
	}

	@Test
	public void testValidVersionMultiDigit()
	{
		assertTrue(UpdateVersionPanel.isValidVersion("1,0"));
	}

	@Test
	public void testValidVersionMultiDigit2()
	{
		assertTrue(UpdateVersionPanel.isValidVersion("1,10"));
	}

	@Test
	public void testValidVersionMultiDigit3()
	{
		assertTrue(UpdateVersionPanel.isValidVersion("123,45,6,7"));
	}

	@Test
	public void testInValidVersionWord()
	{
		assertFalse(UpdateVersionPanel.isValidVersion("1,O"));
	}

	@Test
	public void testInValidVersionDot()
	{
		assertFalse(UpdateVersionPanel.isValidVersion("1.2"));
	}

	@Test
	public void testInValidVersionMulticomma()
	{
		assertFalse(UpdateVersionPanel.isValidVersion("1,,2"));
	}
}
