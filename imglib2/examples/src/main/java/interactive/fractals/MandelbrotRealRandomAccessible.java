/**
 * Copyright (c) 2009--2012, ImgLib2 developers
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.  Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials
 * provided with the distribution.  Neither the name of the Fiji project nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package interactive.fractals;

import net.imglib2.RealInterval;
import net.imglib2.RealPoint;
import net.imglib2.RealRandomAccess;
import net.imglib2.RealRandomAccessible;
import net.imglib2.type.numeric.integer.LongType;

/**
 * A RealRandomAccess that procedurally generates values (iteration count)
 * for the mandelbrot set.
 *
 * @author Tobias Pietzsch
 */
public class MandelbrotRealRandomAccessible implements RealRandomAccessible< LongType >
{
	final protected LongType t;
	long maxIterations;

	public MandelbrotRealRandomAccessible()
	{
		t = new LongType();
		maxIterations = 50;
	}
	
	public MandelbrotRealRandomAccessible( final long maxIterations )
	{
		t = new LongType();
		this.maxIterations = maxIterations;
	}
	
	public void setMaxIterations( final long maxIterations )
	{
		this.maxIterations = maxIterations;
	}
	
	public class MandelbrotRealRandomAccess extends RealPoint implements RealRandomAccess< LongType >
	{
		final LongType t;

		public MandelbrotRealRandomAccess()
		{
			super( 2 ); // number of dimensions is 2
			t = new LongType();
		}

		final private long mandelbrot( final double re0, final double im0, final long maxIterations )
		{
			double re = re0;
			double im = im0;
			long i = 0;
			for ( ; i < maxIterations; ++i )
			{
				final double squre = re * re;
				final double squim = im * im;
				if ( squre + squim > 4 )
					break;
				im = 2 * re * im + im0;
				re = squre - squim + re0;
			}
			return i;
		}

		@Override
		public LongType get()
		{
			t.set( mandelbrot( position[ 0 ], position[ 1 ], maxIterations ) );
			return t;
		}

		@Override
		public MandelbrotRealRandomAccess copyRealRandomAccess()
		{
			return copy();
		}

		@Override
		public MandelbrotRealRandomAccess copy()
		{
			final MandelbrotRealRandomAccess a = new MandelbrotRealRandomAccess();
			a.setPosition( this );
			return a;
		}
	}

	@Override
	public int numDimensions()
	{
		return 2;
	}

	@Override
	public MandelbrotRealRandomAccess realRandomAccess()
	{
		return new MandelbrotRealRandomAccess();
	}

	@Override
	public MandelbrotRealRandomAccess realRandomAccess( final RealInterval interval )
	{
		return realRandomAccess();
	}
}