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

package com.opsresearch.orobjects.lib.complex.matrix;

import com.opsresearch.orobjects.lib.complex.ComplexI;

public class DenseVector extends ArrayVector {
	private static final long serialVersionUID = 1L;

	public DenseVector() {
	}

	public DenseVector(int size) {
		super(size);
	}

	public DenseVector(int size, ComplexI fill) {
		super(size, fill);
	}

	public DenseVector(int size, int capacity) {
		super(size, capacity);
	}

	public DenseVector(double[] array) {
		super(array);
	}

	public DenseVector(double[] real, double[] imag) {
		super(real, imag);
	}

	public DenseVector(VectorI vector) {
		super(vector);
	}

}
