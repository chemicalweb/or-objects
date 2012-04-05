/* ***** BEGIN LICENSE BLOCK *****
 * 
 * Copyright (C) 2012 OpsResearch LLC (a Delaware company)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License, version 3,
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * ***** END LICENSE BLOCK ***** */

package com.opsresearch.orobjects.lib.random;


public class MultiplicativeCongruential implements RandomI, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long _seed;
	private static final long _msk = 0x7FFFFFFFL;
	private static final long _mult = 16807;

	public MultiplicativeCongruential() {
		setSeed(System.currentTimeMillis());
	}

	public MultiplicativeCongruential(long seed) {
		setSeed(seed);
	}

	public void setSeed(long seed) {
		_seed = (seed ^ _mult) & _msk;
	}

	public double nextDouble() {
		return (((next() << 22) & 0x001FFFFFF8000000L) + (next() >>> 4)) / (double) (1L << 53);
	}

	public int sizeOfBits() {
		return 31;
	}

	public long next() {
		return _seed = (_mult * _seed) & _msk;
	}

}
