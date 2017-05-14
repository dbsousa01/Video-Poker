package doublebonus_10_7;

import group18.Hand;

/**
 * Interface used to force the creation of getStrategy to classes that implement it.
 */
public interface Strategy {
	
	/**
	 * Function that tells the player which cards he should hold.
	 * @param hand
	 * @return indices of the cards the player should hole (indexed to 1).
	 */
	String getStrategy(Hand hand);

}
