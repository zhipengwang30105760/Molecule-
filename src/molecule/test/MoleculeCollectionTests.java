// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Zhipeng Wang Kise Sone
// RESOURCES: We figure out all the thing by ourselves
package molecule.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import molecule.Molecule;
import molecule.MoleculeCollection;

public class MoleculeCollectionTests 
{

	@Test
	public void testSpecificConstructorWithNull()
	{
		MoleculeCollection a = new MoleculeCollection(null);
	}
	@Test
	public void testSpecificConstructorWithValidLink()
	{
		LinkedList<Molecule> list = new LinkedList<Molecule>();
		Molecule e = new Molecule("h2o");
		list.add(e);
		MoleculeCollection a = new MoleculeCollection(list);
	}
	@Test
	public void testDefaultConstructor() 
	{
		MoleculeCollection a = new MoleculeCollection();
	}
	
	@Test
	public void testAddMolecule()
	{
		MoleculeCollection a = new MoleculeCollection();
		Molecule b = new Molecule("h2o");
		a.addMolecule(1, b);
		a.addMolecule(0, null);
		a.addMolecule(-1, b);
		a.addMolecule(1, b);
	}
	
	@Test
	public void testMoleculeWeights()
	{
		MoleculeCollection a = new MoleculeCollection();
		Molecule b = new Molecule("h2o");
		a.addMolecule(1, b);
		assertEquals(18, a.moleculeWeights());
	}
	
	@Test
	public void testGetMoleculeList()
	{
		MoleculeCollection a = new MoleculeCollection();
		Molecule b = new Molecule("h2o");
		a.addMolecule(1, b);
		LinkedList<Molecule> copy = new LinkedList<Molecule>();
		copy.add(b);
		a.getMoleculeList();
		// don't know how to assertEquals(copy, a.getMoleculeList());
	}
	
	@Test
	public void testSort()
	{
		MoleculeCollection a = new MoleculeCollection();
		Molecule b = new Molecule("h2o");
		Molecule c = new Molecule("h2");
		Molecule d = new Molecule("co2");

		a.addMolecule(1, b);
		a.addMolecule(2, c);
		a.addMolecule(3, d);

		a.sort();
	}
	
	@Test
	public void testChangeSequence()
	{
		MoleculeCollection a = new MoleculeCollection();
		Molecule b = new Molecule("h2o");
		a.addMolecule(1, b);
		
		a.changeSequence(0, "h2");
	}

}
