package group18;

import doublebonus_10_7.*;
/**
 * Class that implements the interface strategy and calls all other implementations, which are
 * the rules of the perfect plays. It returns what the player should do to achieve the 
 * perfect play. All methods below are calls to each strategy implementation in what the
 * player should do
 */
public enum Advice implements Strategy{
	ROYAL_FLUSH
	{
		public String getStrategy(Hand hand)
		{
			return RoyalFlush.getStrategy(hand);
		}
	},
	STRAIGHT_FLUSH
	{
		public String getStrategy(Hand hand)
		{
			return StraightFlush.getStrategy(hand);
		}
	},
	FOUR_OF_A_KIND
	{
		public String getStrategy(Hand hand)
		{
			return FourofaKind.getStrategy(hand);
		}
	},
	FOUR_TO_A_ROYAL_FLUSH
	{
		public String getStrategy(Hand hand)
		{
			return FourtoRoyalFlush.getStrategy(hand);
		}
	},
	THREE_ACES
	{
		public String getStrategy(Hand hand)
		{
			return ThreeAces.getStrategy(hand);
		}
	},
	STRAIGHT
	{
		public String getStrategy(Hand hand)
		{
			return Straight.getStrategy(hand);
		}
	},
	FLUSH
	{
		public String getStrategy(Hand hand)
		{
			return Flush.getStrategy(hand);
		}
	},
	FULL_HOUSE
	{
		public String getStrategy(Hand hand)
		{
			return FullHouse.getStrategy(hand);
		}
	},
	THREE_OF_A_KIND
	{
		public String getStrategy(Hand hand)
		{
			return ThreeofaKind.getStrategy(hand);
		}
	},
	FOUR_TO_A_STRAIGHT_FLUSH
	{
		public String getStrategy(Hand hand)
		{
			return FourtoStraightFlush.getStrategy(hand);
		}
	},
	TWO_PAIR
	{
		public String getStrategy(Hand hand)
		{
			return TwoPair.getStrategy(hand);
		}
	},
	HIGH_PAIR
	{
		public String getStrategy(Hand hand)
		{
			return HighPair.getStrategy(hand);
		}
	},
	FOUR_TO_A_FLUSH
	{
		public String getStrategy(Hand hand)
		{
			return FourtoFlush.getStrategy(hand);
		}
	},
	THREE_TO_A_ROYAL_FLUSH
	{
		public String getStrategy(Hand hand)
		{
			return ThreetoRoyalFlush.getStrategy(hand);
		}
	},
	FOUR_TO_AN_OUTSIDE_STRAIGHT
	{
		public String getStrategy(Hand hand)
		{
			return FourtoOutsideStraight.getStrategy(hand);
		}
	},
	LOW_PAIR
	{
		public String getStrategy(Hand hand)
		{
			return Pair.getStrategy(hand);
		}
	},
	AKQJ_UNSUITED
	{
		public String getStrategy(Hand hand)
		{
			return AKQJ_Unsuited.getStrategy(hand);
		}
	},
	THREE_TO_A_STRAIGHT_FLUSH_TYPE_1
	{
		public String getStrategy(Hand hand){
			return ThreetoStraightFlush_type1.getStrategy(hand);
		}
	},
	FOUR_TO_AN_INSIDE_STRAIGHT_WITH_THREE_HIGH_CARDS
	{
		public String getStrategy(Hand hand)
		{
			return FourtoInsideStraight_3HighCards.getStrategy(hand);
		}
	},
	QJ_SUITED
	{
		public String getStrategy(Hand hand)
		{
			return QJ_Suited.getStrategy(hand);
		}
	},
	THREE_TO_A_FLUSH_WITH_TWO_HIGH_CARDS
	{
		public String getStrategy(Hand hand)
		{
			return ThreetoFlush_2HighCards.getStrategy(hand);
		}
	},
	TWO_SUITED_HIGH_CARDS
	{
		public String getStrategy(Hand hand)
		{
			return TwoSuitedHighCards.getStrategy(hand);
		}
	},
	FOUR_TO_AN_INSIDE_STRAIGHT_WITH_TWO_HIGH_CARDS
	{
		public String getStrategy(Hand hand)
		{
			return FourtoInsideStraight_2HighCards.getStrategy(hand);
		}
	},
	THREE_TO_A_STRAIGHT_FLUSH_TYPE_2
	{
		public String getStrategy(Hand hand)
		{
			return ThreetoStraightFlush_type2.getStrategy(hand);
		}
	},
	FOUR_TO_AN_INSIDE_STRAIGHT_WITH_ONE_HIGH_CARD
	{
		public String getStrategy(Hand hand)
		{
			return FourtoInsideStraight_1HighCard.getStrategy(hand);
		}
	},
	KQJ_UNSUITED
	{
		public String getStrategy(Hand hand)
		{
			return KQJ_Unsuited.getStrategy(hand);
		}
	},
	JT_SUITED
	{
		public String getStrategy(Hand hand)
		{
			return JT_Suited.getStrategy(hand);
		}
	},
	QJ_UNSUITED
	{
		public String getStrategy(Hand hand)
		{
			return QJ_Unsuited.getStrategy(hand);
		}
	},
	THREE_TO_A_FLUSH_WITH_ONE_HIGH_CARD
	{
		public String getStrategy(Hand hand)
		{
			return ThreetoFlush_1HighCard.getStrategy(hand);
		}
	},
	QT_SUITED
	{
		public String getStrategy(Hand hand)
		{
			return QT_Suited.getStrategy(hand);
		}
	},
	THREE_TO_A_STRAIGHT_FLUSH_TYPE_3
	{
		public String getStrategy(Hand hand)
		{
			return ThreetoStraightFlush.getStrategy(hand);
		}
	},
	KQ_UNSUITED
	{
		public String getStrategy(Hand hand)
		{
			return KQ_Unsuited.getStrategy(hand);
		}
	},
	KJ_UNSUITED
	{
		public String getStrategy(Hand hand)
		{
			return KJ_Unsuited.getStrategy(hand);
		}
	},
	ACE
	{
		public String getStrategy(Hand hand)
		{
			return Ace.getStrategy(hand);
		}
	},
	KT_SUITED
	{
		public String getStrategy(Hand hand)
		{
			return KT_Suited.getStrategy(hand);
		}
	},
	KING
	{
		public String getStrategy(Hand hand)
		{
			return King.getStrategy(hand);
		}
	},
	QUEEN
	{
		public String getStrategy(Hand hand)
		{
			return Queen.getStrategy(hand);
		}
	},
	JACK
	{
		public String getStrategy(Hand hand)
		{
			return Jack.getStrategy(hand);
		}
	},
	FOUR_TO_AN_INSIDE_STRAIGHT_WITH_NO_HIGH_CARDS
	{
		public String getStrategy(Hand hand)
		{
			return FourtoInsideStraight.getStrategy(hand);
		}
	},
	THREE_TO_A_FLUSH_WITH_NO_HIGH_CARDS
	{
		public String getStrategy(Hand hand)
		{
			return ThreetoFlush.getStrategy(hand);
		}
	}
}