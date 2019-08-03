// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Zhipeng Wang Kise Sone
// RESOURCES: We figure out all the thing by ourselves
package molecule.test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import molecule.Molecule;
import molecule.exceptions.InvalidAtomException;
import molecule.exceptions.InvalidSequenceException;

public class MoleculeTests 
{

	@Test
	public void testDefaultConstructor() 
	{
		Molecule a = new Molecule("h2o");
		assertEquals("h2o", a.getSequence());
		assertEquals(18, a.getWeight());
		assertEquals("h2o                      : 18", a.toString());
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testSetSequence()
	{
		Molecule a = new Molecule("h2o");
		a.setSequence("hcooh");
	}
	
	@Test
	public void testSetSequenceInvalidAtom()
	{
		thrown.expect(InvalidAtomException.class);
		Molecule a = new Molecule("b");
	}
	
	@Test
	public void testSetSequenceExtraOpenP()
	{
		thrown.expect(InvalidSequenceException.class);
		Molecule a = new Molecule("(h2");
	}
	
	@Test
	public void testSetSequenceExtraCloseP()
	{
		thrown.expect(InvalidSequenceException.class);
		Molecule a = new Molecule("h2)");
	}
	
	@Test
	public void testSetSequenceEmpty()
	{
		thrown.expect(InvalidSequenceException.class);
		Molecule a = new Molecule("");
	}
	
	@Test
	public void testAtomWeight()
	{
		assertEquals(1, Molecule.atomWeight('h'));
		assertEquals(16, Molecule.atomWeight('o'));
		assertEquals(12, Molecule.atomWeight('c'));


	}
	@Test
	public void testAtomWeightInvalid()
	{
		thrown.expect(InvalidAtomException.class);
		Molecule.atomWeight('B');
	}
	
	@Test
	public void testCompareTo()
	{
		Molecule a = new Molecule("h2o");
		Molecule b = new Molecule("h2o");
		
		assertEquals(0, a.compareTo(b));
	}
	
	@Test
	public void testClone()
	{
		Molecule a = new Molecule("h2o");
		Object b = a.clone();
		
		// Don't know how to cause classnotsupported exception
//		Molecule b = new Molecule("h");
//		b.clone();
	}
}
