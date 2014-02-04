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

package net.imglib2.ops.pointset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.imglib2.ops.condition.Condition;
import net.imglib2.ops.condition.RangeCondition;

import org.junit.Test;

public class ConditionalPointSetTest {

	@Test
	public void test() {
		PointSet hv = new HyperVolumePointSet(new long[]{15,15});
		Condition<long[]> cond = new RangeCondition(0, 1, 10, 1);
		PointSet ps = new ConditionalPointSet(hv, cond);
		
		assertEquals(150, ps.size());
		
		assertEquals(1, ps.min(0));
		assertEquals(0, ps.min(1));
		assertEquals(10, ps.max(0));
		assertEquals(14, ps.max(1));
		
		assertEquals(1, ps.realMin(0), 0);
		assertEquals(0, ps.realMin(1), 0);
		assertEquals(10, ps.realMax(0), 0);
		assertEquals(14, ps.realMax(1), 0);
		
		assertEquals(10, ps.dimension(0));
		assertEquals(15, ps.dimension(1));
	
		assertFalse(ps.includes(new long[]{0,5}));
		assertTrue(ps.includes(new long[]{1,5}));
		assertTrue(ps.includes(new long[]{2,5}));
		assertTrue(ps.includes(new long[]{9,10}));
		assertTrue(ps.includes(new long[]{10,10}));
		assertFalse(ps.includes(new long[]{11,10}));
		assertTrue(ps.includes(new long[]{8,14}));
		assertFalse(ps.includes(new long[]{8,15}));
		
		ps.translate(new long[]{1,2});

		assertEquals(1, ps.min(0));
		assertEquals(2, ps.min(1));
		assertEquals(10, ps.max(0));
		assertEquals(16, ps.max(1));
		
		assertEquals(1, ps.realMin(0), 0);
		assertEquals(2, ps.realMin(1), 0);
		assertEquals(10, ps.realMax(0), 0);
		assertEquals(16, ps.realMax(1), 0);
	}

}
