package com.cbapps.duveln.connection;

import android.support.annotation.NonNull;

/**
 * @author CoenB95
 */

public class Card  implements Comparable<Card> {
	public CardNumber Number;
	public CardShape Shape;

	public static Card Koekoek = new Card(CardNumber.King, CardShape.Clubs);

	public Card(CardNumber number, CardShape shape) {
		Number = number;
		Shape = shape;
	}

	@Override
	public int compareTo(@NonNull Card other) {
		return Number.compareTo(other.Number);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Card))
			return false;
		Card other = (Card) obj;
		return Number == other.Number;
	}

	@Override
	public int hashCode() {
		int result = Number != null ? Number.hashCode() : 0;
		result = 31 * result + (Shape != null ? Shape.hashCode() : 0);
		return result;
	}

	public String GetImageName() {
		StringBuilder builder = new StringBuilder();
		builder.append(GetNumberString()).append("_of_").append(GetShapeString()).append(".png");
		return builder.toString().toLowerCase();
	}

	public String GetNumberString() {
		switch (Number) {
			case Of2:
				return "2";
			case Of3:
				return "3";
			case Of4:
				return "4";
			case Of5:
				return "5";
			case Of6:
				return "6";
			case Of7:
				return "7";
			case Of8:
				return "8";
			case Of9:
				return "9";
			case Of10:
				return "10";
			case Jack:
				return "Jack";
			case Queen:
				return "Queen";
			case King:
				return "King";
			case Ace:
				return "Ace";
			default:
				return "";
		}
	}

	public String GetShapeString() {
		return Shape.toString();
	}

	public String toString() {
		return "{GetNumberString()} of {GetShapeString()}";
	}

	public enum CardNumber {
		Ace,Of2,Of3,Of4,Of5,Of6,Of7,Of8,Of9,Of10,Jack,Queen,King
	}

	public enum CardShape {
		Clubs,Diamonds,Hearts,Spades
	}
}

