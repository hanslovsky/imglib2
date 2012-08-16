package net.imglib2.algorithm.region.localneighborhood.test;

import net.imglib2.Cursor;
import net.imglib2.Interval;
import net.imglib2.algorithm.region.localneighborhood.RectangleNeighborhoodCursor;
import net.imglib2.img.Img;
import net.imglib2.img.ImgFactory;
import net.imglib2.img.list.ListImgFactory;
import net.imglib2.util.Intervals;
import net.imglib2.view.Views;

public class ShowCoordinates2D
{
	public static void main( final String[] args )
	{
		final int n = 2;
		final long[] dimensions = new long[] { 5, 5 };
		final ImgFactory< CoordinateType > f = new ListImgFactory< CoordinateType >();
		final CoordinateType type = new CoordinateType( n );
		final Img< CoordinateType > img = f.create( dimensions, type );
		final Cursor< CoordinateType > c = img.localizingCursor();
		while ( c.hasNext() )
			c.next().setPosition( c );
		// c.reset();
		// while ( c.hasNext() )
		// System.out.println( c.next() );

//		final Point center = new Point( 2l, 2l );
//		final LocalNeighborhood2< CoordinateType > neighborhood = new LocalNeighborhood2< CoordinateType >( img, center );
//		final Cursor< CoordinateType > nc = neighborhood.cursor();
//		while ( nc.hasNext() )
//			System.out.println( nc.next() );

		final Interval span = Intervals.createMinMax( -1, -1, 1, 1 );
		final RectangleNeighborhoodCursor< CoordinateType > n3 = new RectangleNeighborhoodCursor< CoordinateType >( Views.interval( img, Intervals.expand( img, -1 ) ), span );
		while ( n3.hasNext() )
		{
			for ( final CoordinateType t : n3.next() )
				System.out.println( t );
			System.out.println( "-----" );
		}
	}
}
