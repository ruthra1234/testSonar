/**
 *
 */
package ca.homedepot.util;

import java.util.Date;


/**
 * The Class IntervalComparator.
 *
 * @author RXP8RN9
 */
public class IntervalComparator implements Comparable<IntervalComparator>
{

	/** The value. */
	public Date value;

	/** The type. */
	public PointType type;

	/**
	 * Instantiates a new interval comparator.
	 *
	 * @param value
	 *           the value
	 * @param type
	 *           the type
	 */
	public IntervalComparator(final Date value, final PointType type)
	{
		this.value = value;
		this.type = type;
	}


	@Override
	public int compareTo(final IntervalComparator other)
	{
		if (other.value.equals(this.value))
		{
			final int value = this.type.ordinal() < other.type.ordinal() ? -1 : 1;
			return value;
		}
		else
		{
			return this.value.before(other.value) ? -1 : 1;
		}
	}


	/**
	 * The Enum PointType.
	 */
	public enum PointType
	{

		End, GapEnd, GapStart, Start
	}
}
