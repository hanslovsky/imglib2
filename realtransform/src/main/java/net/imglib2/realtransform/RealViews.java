/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2014 Stephan Preibisch, Tobias Pietzsch, Barry DeZonia,
 * Stephan Saalfeld, Albert Cardona, Curtis Rueden, Christian Dietz, Jean-Yves
 * Tinevez, Johannes Schindelin, Lee Kamentsky, Larry Lindsey, Grant Harris,
 * Mark Hiner, Aivar Grislis, Martin Horn, Nick Perry, Michael Zinsmaier,
 * Steffen Jaensch, Jan Funke, Mark Longair, and Dimiter Prodanov.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imglib2.realtransform;

import net.imglib2.RandomAccessible;
import net.imglib2.RealRandomAccessible;

/**
 * Convenience factory methods for
 * {@link RealRandomAccessible RealRandomAccessibles} transformed in real
 * coordinate space by
 * {@link InvertibleRealTransform InvertibleRealTransforms}.
 *
 * @author Stephan Saalfeld <saalfeld@mpi-cbg.de>
 */
public class RealViews
{
	/**
	 * See a {@link RealRandomAccessible} as transformed by an
	 * {@link InvertibleRealTransform}.  The {@link InvertibleRealTransform} is
	 * interpreted according to the natural understanding that the source is
	 * transformed by it.  E.g. a positive translation of dimension <em>x</em>
	 * would shift the source to the right.  Therefore, the samples need to be
	 * generated by the inverse of the {@link InvertibleRealTransform}.  Here,
	 * the inverse is realized by {@link InverseRealTransform}.  That way,
	 * changing the state of the {@link InvertibleRealTransform} will
	 * immediately change the state of the view.
	 * 
	 * @param source the {@link RealRandomAccessible} to be transformed
	 * @param transform the {@link InvertibleRealTransform} transforming source
	 * 
	 * @return {@link RealTransformRealRandomAccessible} representing the
	 *   transformed source 
	 */
	public static < T > RealTransformRealRandomAccessible< T, InverseRealTransform > transformReal( final RealRandomAccessible< T > source, final InvertibleRealTransform transform )
	{
		return new RealTransformRealRandomAccessible< T, InverseRealTransform >( source, new InverseRealTransform( transform ) );
	}
	
	/**
	 * See a {@link RealRandomAccessible} as a {@link RandomAccessible}
	 * transformed by an {@link InvertibleRealTransform}.  The
	 * {@link InvertibleRealTransform} is interpreted according to the natural
	 * understanding that the source is transformed by it.  E.g. a positive
	 * translation of dimension <em>x</em> would shift the source to the right.
	 * Therefore, the samples need to be generated by the inverse of the
	 * {@link InvertibleRealTransform}.  Here, the inverse is realized by
	 * {@link InverseRealTransform}.  That way, changing the state of the
	 * {@link InvertibleRealTransform} will immediately change the state of the
	 * view.
	 * 
	 * @param source the {@link RealRandomAccessible} to be transformed
	 * @param transform the {@link InvertibleRealTransform} transforming source
	 * 
	 * @return {@link RealTransformRandomAccessible} representing the
	 *   transformed source 
	 */
	public static < T > RealTransformRandomAccessible< T, InverseRealTransform > transform( final RealRandomAccessible< T > source, final InvertibleRealTransform transform )
	{
		return new RealTransformRandomAccessible< T, InverseRealTransform >( source, new InverseRealTransform( transform ) );
	}
	
	/**
	 * See a {@link RealRandomAccessible} as transformed by an
	 * {@link AffineGet}.  The {@link AffineGet} is interpreted according to
	 * the natural understanding that the source is transformed by it.  E.g. a
	 * positive translation of dimension <em>x</em> would shift the source to
	 * the right.  Therefore, the samples need to be generated by the inverse
	 * of the {@link AffineGet}.  Here, the {@link AffineGet} is inverted using
	 * it's {@link AffineGet#inverse()} method that is expected to generate an
	 * inverse that changes with the original transformation accordingly.  That
	 * way, changing the state of the {@link AffineGet} will immediately change
	 * the state of the view.
	 * 
	 * @param source the {@link RealRandomAccessible} to be transformed
	 * @param transform the {@link AffineGet} transforming source
	 * 
	 * @return {@link AffineRealRandomAccessible} representing the
	 *   transformed source 
	 */
	public static < T > AffineRealRandomAccessible< T, AffineGet > affineReal( final RealRandomAccessible< T > source, final AffineGet affine )
	{
		return new AffineRealRandomAccessible< T, AffineGet >( source, affine.inverse() );
	}
	
	/**
	 * See a {@link RealRandomAccessible} as a {@link RandomAccessible}
	 * transformed by an {@link AffineGet}.  The {@link AffineGet} is
	 * interpreted according to the natural understanding that the source is
	 * transformed by it.  E.g. a positive translation of dimension <em>x</em>
	 * would shift the source to the right.  Therefore, the samples need to be
	 * generated by the inverse of the {@link AffineGet}.  Here, the
	 * {@link AffineGet} is inverted using it's {@link AffineGet#inverse()}
	 * method that is expected to generate and inverse that changes with the
	 * original transformation accordingly.  That way, changing the state of
	 * the {@link AffineGet} will immediately change the state of the view.
	 * 
	 * @param source the {@link RealRandomAccessible} to be transformed
	 * @param transform the {@link AffineGet} transforming source
	 * 
	 * @return {@link AffineRandomAccessible} representing the
	 *   transformed source 
	 */
	public static < T > AffineRandomAccessible< T, AffineGet > affine( final RealRandomAccessible< T > source, final AffineGet affine )
	{
		return new AffineRandomAccessible< T, AffineGet >( source, affine.inverse() );
	}
	
	/**
	 * See a {@link RealRandomAccessible} as transformed by an
	 * {@link AffineGet} that is expected to be constant and thus can be
	 * replaced by an appropriate copy.  The {@link AffineGet} is interpreted
	 * according to the natural understanding that the source is transformed by
	 * it.  E.g. a positive translation of dimension <em>x</em> would shift the
	 * source to the right.  Therefore, the samples need to be generated by the
	 * inverse of the {@link AffineGet}.  It is not specified whether changing
	 * the state of the {@link AffineGet} will or will not change the state of
	 * the view.  Instead, the view may use an optimized alternative for doing
	 * the actual coordinate transfer. 
	 * 
	 * @param source the {@link RealRandomAccessible} to be transformed
	 * @param transform the {@link AffineGet} transforming source
	 * 
	 * @return {@link RandomAccessible} representing the
	 *   transformed source 
	 */
	public static < T > RandomAccessible< T > constantAffine( final RealRandomAccessible< T > source, final AffineGet affine )
	{
		/** 
		 * TODO If source is a ConstantAffineRealRandomAccessible (not yet
		 * implemented), then reduce the affine smartly (combine them into the
		 * least complex common ancestor).  If the affine ends up
		 * being the identity, return a
		 * {@link RandomAccessibleOnRealRandomAccessible} instead.
		 */
		return new ConstantAffineRandomAccessible< T, AffineGet >( source, affine.inverse() );
	}
	
}
