// COURSE: CSCI1620
// TERM: Spring 2019
//
// NAME: Zhipeng Wang Kise Sone
// RESOURCES: We figure out all the thing by ourselves
package molecule;

import java.util.EmptyStackException;
import java.util.Stack;

import molecule.exceptions.InvalidAtomException;
import molecule.exceptions.InvalidSequenceException;

/**
 * Objects of the Molecule class represent a single chemical molecule made 
 * up of any number of hydrogen, carbon, and oxygen atoms. 
 * The classprovides functionality to compute the atomic weight of the molecule.
 * @author zhipengwang@unomaha.edu ksone@unomaha.edu
 *
 */
public class Molecule implements Comparable<Molecule>, Cloneable
{
	/**
	 * The weight for Hydrogen.
	 */
	private static final int H = 1;
	/**
	 * The weight for Carbon.
	 */
	private static final int C = 12;
	/**
	 * The weight for Oxygen.
	 */
	private static final int O = 16;
	/**
	 * The sequence that we read.
	 */
	private String sequence;
	/**
	 * The total weight of molecule.
	 */
	private int weight;
	/**
	 * The stack that used to check whether a input string is valid or not.
	 */
	private Stack<Integer>  stack;
	/**
	 * Creates a new Molecule made up of the H, C, and O atoms in the configuration specified by sequenceIn.
	 * @param sequenceIn The sequence of atoms for this Molecule.
	 * @throws InvalidAtomException if any non C, H, O atom exists in sequenceIn
	 * @throws InvalidSequenceException if unmatched parentheses exist in sequenceIn or 
	 * incoming sequence is null or empty String.
	 */
	public Molecule(String sequenceIn)
	{
		setSequence(sequenceIn);
	}
	/**
	 * Updates the sequence of atoms represented by this Molecule.
	 * @param sequenceIn  The new molecular sequence to be used for this Molecule.
	 * @throws InvalidAtomException if any non C, H, O atom exists in sequenceIn
	 * @throws InvalidSequenceException if unmatched parentheses exist in sequenceIn or 
	 * incoming sequence is null or empty String.
	 */
	public void setSequence(String sequenceIn)
	{
		stack = new Stack<Integer>();
		checkInavlidAtom(sequenceIn);
		checkInvalidSequence(sequenceIn);
		if (stack.size() != 1)
		{
			throw new InvalidSequenceException();
		}
		sequence = sequenceIn;
		weight = stack.peek();
	}
	
	/**
	 * A helper method that checks the invalid sequence exception.
	 * @param sequenceIn The new molecular sequence to be used for this Molecule.
	 */
	private void checkInvalidSequence(String sequenceIn) 
	{
		String target = '(' + sequenceIn + ')';
		int i = 0;
		while (i < target.length())
		{
			if (target.charAt(i) == '(')
			{
				helperOpenP(target.charAt(i));
				i++;
			}
			else if (Character.toUpperCase(target.charAt(i)) == 'H' 
					|| Character.toUpperCase(target.charAt(i)) == 'O' 
					|| Character.toUpperCase(target.charAt(i)) == 'C')
			{
				helperChar(target.charAt(i));
				i++;
			}
			else if (Character.isDigit(target.charAt(i)))
			{
				String sum = "";
				int number = 0;
				while (Character.isDigit(target.charAt(i)))
				{
					sum += target.charAt(i);
					i++;
				}
				number = Integer.parseInt(sum);
				int top = stack.pop() * number;
				//System.out.println(top);
				stack.push(top);
				// check whether need to i++
			}
			else
			{
				helperCloseP(target.charAt(i));
				i++;
			}
			
		}	
	}
	/**
	 * A helper method that do some operation when we meet ).
	 * @param charAt character we read from sequence.
	 */
	private void helperCloseP(char charAt) 
	{
		int sum = 0;
		// check later
		/*
		while (stack.peek() != -1)
		{
			sum += stack.pop();
		}
		stack.pop();
		stack.push(sum);
		*/
		
		try 
		{
			while (stack.peek() != -1)
			{
				sum += stack.pop();
			}
		}
		catch (EmptyStackException ese)
		{
			throw new InvalidSequenceException();
		}
		if (sum == 0)
		{
			throw new InvalidSequenceException();
		}
		stack.pop();
		stack.push(sum);
		
		/*
		if (stack.size() != 1)
		{
			throw new InvalidSequenceException();
		}
		*/
	}
	/**
	 * A helper method that do some operation when we meet O, H, or C.
	 * @param charAt character we read from sequence.
	 */
	private void helperChar(char charAt) 
	{
		stack.push(atomWeight(charAt));
	}
	/**
	 * A helper method that do some operation when we meet (.
	 * @param charAt character we read from sequence.
	 */
	private void helperOpenP(char charAt) 
	{
		stack.push(-1);
	}
	/**
	 * A helper method that checks the invalid atom exception.
	 * @param sequenceIn The new molecular sequence to be used for this Molecule.
	 */
	private void checkInavlidAtom(String sequenceIn)
	{
		for (int i = 0; i < sequenceIn.length(); i++)
		{
			if (Character.isAlphabetic(sequenceIn.charAt(i)))
			{
				char hold = Character.toUpperCase(sequenceIn.charAt(i));
				if (hold != 'H' && hold != 'O' && hold != 'C')
				{
					throw new InvalidAtomException(sequenceIn.charAt(i));
				}
			}
		}
	}
	/**
	 * Retrieves a String containing this Molecule's sequence of atoms.
	 * @return Molecular sequence of the Molecule.
	 */
	public String getSequence()
	{
		return sequence;
	}
	/**
	 * Retrieves this Molecule's weight, which is calculated based
	 * on the Molecule'ssequence per the algorithm specified.
	 * @return Weight of the Molecule.
	 */
	public int getWeight()
	{
		return weight;
	}
	/**
	 * Generates and returns a String with the Molecule's sequence and weight. 
	 * @return a string that contains molecule's information.
	 */
	public String toString()
	{
		//check weight 
		return String.format("%-25s: %d", sequence, weight);
	}
	
	/**
	 * Static utility method to return the atomic weight of a given atom.
	 * Supported atoms are Carbon (C), Hydrogen (H), and Oxygen (O), and
	 * the value of the atom parameter corresponds to the 
	 * single letter abbreviation for these atoms (case insensitive). 
	 * Atomic weight are given in their nearest whole number: 
	 * @param atom Character for atom abbreviation
	 * @return Atomic weight of passed atom
	 * @throws InvalidAtomException Thrown if an unsupported atom is passed
	 */
	public static int atomWeight(char atom)
	{
		int atomWeight;
		if (Character.toUpperCase(atom) != 'H' && Character.toUpperCase(atom) != 'C' 
				&& Character.toUpperCase(atom) != 'O')
		{
			throw new InvalidAtomException(atom);
		}
		else if (Character.toUpperCase(atom) == 'H')
		{
			atomWeight = H;
		}
		else if (Character.toUpperCase(atom) == 'C')
		{
			atomWeight = C;
		}
		else
		{
			atomWeight = O;
		}
		return atomWeight;
	}
	/**
	 * Compares this Molecule to a passed Molecule, determining natural order. 
	 * Molecules with the same weight (regardless of sequence) are considered equal. 
	 * All others are ordered relative to the magnitude of their weights.
	 * @param other  Incoming Molecule to be compared with this (local) Molecule.
	 * @return Returns an int less than 0 if the local Molecule is less than the passed Molecule, 
	 * an int greater than 0 if the local Molecule is greater than the passed Molecule, 
	 * and a 0 if the Molecules are equal.
	 */
	@Override
	public int compareTo(Molecule other) 
	{
		Integer a = this.getWeight();
		Integer b = other.getWeight();
		return a.compareTo(b);
	}
	/**
	 * Returns a deep copy of the Molecule. The reference returned should refer to 
	 * a completely separate molecule with no direct or indirect 
	 * aliasing of any instance data in the originalMolecule. 
	 * @return Deep copy of the calling Molecule.
	 */
	public Object clone()
	{
		try
		{
			//ask teacher
			Object copy = new Molecule(((Molecule) super.clone()).getSequence());
			return copy;
		}
		catch (CloneNotSupportedException cnse)
		{
			throw new RuntimeException();
		}
		
	}
	
}
