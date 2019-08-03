// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Zhipeng Wang Kise Sone
// RESOURCES: We figure out all the thing by ourselves
package molecule;


import java.util.Iterator;
import java.util.LinkedList;

import molecule.exceptions.InvalidAtomException;
import molecule.exceptions.InvalidSequenceException;

/**
 * A collection of Molecules maintained in a LinkedList. 
 * Allows for adding, sorting, and updating Molecules in the collection. 
 * A List of the Molecules can also be retrieved; 
 * the returned list is a deep copy of the list held in the MoleculeCollection.
 * @author zhipengwang@unomaha.edu ksone@unomaha.edu
 *
 */
public class MoleculeCollection 
{
	/**
	 * The list that store molecule.
	 */
	private LinkedList<Molecule> moleculeList;
	/**
	 * Creates a new MoleculeCollection containing no Molecules yet.
	 */
	public MoleculeCollection()
	{
		moleculeList = new LinkedList<Molecule>();
	}
	/**
	 * Creates a new MoleculeCollection based upon an existing list of Molecules. 
	 * The newly created MoleculeCollection will store a deep 
	 * copy of the data in moleculeList Into enforce encapsulation.
	 * If the passed reference is null, the created MoleculeCollection will be empty. 
	 * @param moleculeListIn LinkedList of Molecules used to create a new MoleculeCollection.
	 */
	public MoleculeCollection(LinkedList<Molecule> moleculeListIn)
	{
		moleculeList = new LinkedList<Molecule>();
		
		if (moleculeListIn != null)
		{	
			Iterator<Molecule> itr = moleculeListIn.iterator();
			while (itr.hasNext())
			{
				moleculeList.add(new Molecule(itr.next().getSequence()));
			}
		}
	}
	/**
	 * Adds a copy of a given Molecule to this MoleculeCollection at the given index.
	 * Future external changes to the original Molecule will not impact values in the collection. If add is null, 
	 * this MoleculeCollection is unchanged. 
	 * If the given index is out of range, the Molecule will be added to the end of the Collection
	 * @param index The index in which to add the Molecule
	 * @param add Molecule to be added to the Collection
	 */
	public void addMolecule(int index, Molecule add)
	{
		if (add == null)
		{
			int a = 1;
		}
		if (add != null 
				&& (index < moleculeList.size() && index >= 0))
		{
			Molecule target = new Molecule(add.getSequence());
			moleculeList.add(index, target);
		}
		else if (add != null 
				&& (index >= moleculeList.size() || index < 0))
		{
			Molecule target = new Molecule(add.getSequence());
			moleculeList.add(target);
		}
	}
	/**
	 * Sums the weights of all Molecules in the MoleculeCollection.
	 * @return The sum of all weights in the collection
	 */
	public int moleculeWeights()
	{
		Iterator<Molecule> itr = moleculeList.iterator();
		int sum = 0;
		while (itr.hasNext())
		{
			sum += itr.next().getWeight();
		}
		return sum;
		
	}
	/**
	 * Generates and returns a deep copy of a list containing all of the Molecules in this MoleculeCollection. 
	 * Modifying the returned list will not impact the contents of this MoleculeCollection in any way.
	 * @return Deep copy of the Molecules
	 */
	public LinkedList<Molecule> getMoleculeList()
	{
		LinkedList<Molecule> copy = new LinkedList<Molecule>();
		Iterator<Molecule> itr = moleculeList.iterator();
		while (itr.hasNext())
		{
			copy.add(new Molecule(itr.next().getSequence()));
		}
		return copy;
	}
	/**
	 * Reorders the MoleculeCollection based on atomic weight. Molecules with the same weights 
	 * should appear in their original order of insertion relative to 
	 * one another(ie., sort() is a stable sorting algorithm).
	 */
	public void sort()
	{
		Molecule insert;
		for (int next = 1; next < moleculeList.size(); next++)
		{
			insert = moleculeList.get(next);
			int moveItem = next;
			while (moveItem > 0 && moleculeList.get(moveItem - 1).compareTo(insert) > 0)
			{
				moleculeList.set(moveItem, moleculeList.get(moveItem - 1));
				moveItem--;
			}
			moleculeList.set(moveItem, insert);
		}
	}
	/**
	 * Changes the sequence of a Molecule in the collection at the specified index. 
	 * This does not create a new Molecule, rather modifies an existing Molecule. 
	 * @param index Location of the Molecule to update
	 * @param newSequence New sequence of the specified Molecule
	 * @throws InvalidAtomException Thrown if the new sequence is invalid due to unknown atom
	 * @throws InvalidSequenceException Thrown if the new sequence is invalid due to format
	 */
	public void changeSequence(int index, String newSequence) throws InvalidAtomException, InvalidSequenceException
	{
		//not sure the definition.
		moleculeList.get(index).setSequence(newSequence);
	}
} 
