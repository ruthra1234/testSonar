/**
 *
 */
package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import test.IntervalComparator.PointType;




/**
 * The Class IntervalProcessor.
 *
 * @author RXP8RN9
 */
public class IntervalProcessor
{

	/**
	 * Inits the queue.
	 *
	 * @param interval
	 *           the interval
	 * @param remove
	 *           the remove
	 * @return the list
	 */
	public static List<IntervalComparator> initQueue(final List<Interval> interval, final List<Interval> remove)
	{
		// annotate all points and put them in a list
		final List<IntervalComparator> queue = new ArrayList<>();
		for (final Interval i : interval)
		{
			queue.add(new IntervalComparator(i.start, PointType.Start));
			queue.add(new IntervalComparator(i.end, PointType.End));
		}
		for (final Interval i : remove)
		{
			queue.add(new IntervalComparator(i.start, PointType.GapStart));
			queue.add(new IntervalComparator(i.end, PointType.GapEnd));
		}

		// sort the queue
		Collections.sort(queue);

		return queue;
	}

	/**
	 * Do sweep.
	 *
	 * @param queue
	 *           the queue
	 * @return the list
	 */
	public static List<Interval> doSweep(final List<IntervalComparator> queue)
	{
		final List<Interval> result = new ArrayList<>();

		// iterate over the queue
		boolean isInterval = false; 
		boolean isGap = false;
		Date intervalStart = null;
		for (final IntervalComparator point : queue)
		{
			switch (point.type)
			{
				case Start:
					if (!isGap)
					{
						intervalStart = point.value;
					}
					isInterval = true;
					break;
				case End:
					if (!isGap)
					{
						result.add(new Interval(intervalStart, point.value));
					}
					isInterval = false;
					break;
				case GapStart:
					if (isInterval)
					{
						result.add(new Interval(intervalStart, point.value));
					}
					isGap = true;
					break;
				case GapEnd:
					if (isInterval)
					{
						intervalStart = point.value;
					}
					isGap = false;
					break;
			}
		}

		return result;
	}
}
