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

import java.util.Iterator;

import com.opsresearch.orobjects.lib.complex.Complex;
import com.opsresearch.orobjects.lib.complex.ComplexI;
import com.opsresearch.orobjects.lib.util.Array;
import com.opsresearch.orobjects.lib.util.ComplexArray;

public class RowMajorMatrix extends ContiguousMatrix {
	private static final long serialVersionUID = 1L;

	public RowMajorMatrix(int sizeOfRows, int sizeOfColumns) {
		super(sizeOfRows, sizeOfColumns, sizeOfRows, sizeOfColumns);
	}

	public RowMajorMatrix(int sizeOfRows, int sizeOfColumns, int capacityOfRows, int capacityOfColumns) {
		super(sizeOfRows, sizeOfColumns, capacityOfRows, capacityOfColumns);
	}

	public RowMajorMatrix(double[][] array) {
		super(array);
		for (int i = 0, k = 0; i < _sizeOfRows; i++, k += _capacityOfColumns) {
			double[] row = array[i];
			if (row == null)
				continue;
			ComplexArray.copy(row.length / 2, _values, k, 1, row, 0, 1);
		}
	}

	public RowMajorMatrix(double[][] real, double[][] imag) {
		super(real, imag);
		int incr = 2 * _capacityOfColumns;
		for (int i = 0, k = 0; i < _sizeOfRows; i++, k += incr) {
			double[] rrow = real == null ? null : real[i];
			double[] irow = imag == null ? null : imag[i];
			if (rrow != null)
				Array.copy(rrow.length, _values, k, 2, rrow, 0, 1);
			if (irow != null)
				Array.copy(irow.length, _values, k + 1, 2, irow, 0, 1);
		}
	}

	public RowMajorMatrix(MatrixI matrix) {
		super(matrix);
		for (Iterator<MatrixElementI> e = matrix.elements(); e.hasNext();) {
			MatrixElementI elem = e.next();
			setElementAt(elem.getRowIndex(), elem.getColumnIndex(), elem.getValue());
		}
	}

	public RowMajorMatrix(VectorI vector) {
		super(vector);
	}

	public int getOffset(int row, int column) {
		if (row < 0 || row >= _sizeOfRows)
			throw new IndexOutOfBoundsException("Row=" + row + ", sizeOfRows=" + _sizeOfRows);
		if (column < 0 || column >= _sizeOfColumns)
			throw new IndexOutOfBoundsException("Column=" + column + ", sizeOfColumns=" + _sizeOfColumns);
		return row * _capacityOfColumns + column;
	}

	public int getRowIncrement() {
		return _capacityOfColumns;
	}

	public int getColumnIncrement() {
		return 1;
	}

	public boolean isRowMajor() {
		return true;
	}

	public boolean isColumnMajor() {
		return false;
	}

	public void setSize(int sizeOfRows, int sizeOfColumns) {
		Complex zero = new Complex();
		if (sizeOfRows > _capacityOfRows || sizeOfColumns > _capacityOfColumns)
			setCapacity(sizeOfRows, sizeOfColumns);
		for (int i = _sizeOfRows, k = _sizeOfRows * _capacityOfColumns; i < sizeOfRows; i++, k += _capacityOfColumns)
			ComplexArray.copy(sizeOfColumns, _values, k, 1, zero);
		if (sizeOfColumns > _sizeOfColumns) {
			int n = sizeOfColumns - _sizeOfColumns;
			for (int i = 0, k = _sizeOfColumns; i < _sizeOfRows; i++, k += _capacityOfColumns)
				ComplexArray.copy(n, _values, k, 1, zero);
		}
		_sizeOfRows = sizeOfRows;
		_sizeOfColumns = sizeOfColumns;
	}

	public void setCapacity(int capacityOfRows, int capacityOfColumns) {
		if (capacityOfRows <= _capacityOfRows && capacityOfColumns <= _capacityOfColumns)
			return;
		double[] values = new double[capacityOfRows * capacityOfColumns];
		for (int i = 0, k = 0, l = 0; i < _sizeOfRows; i++, k += _capacityOfColumns, l += capacityOfColumns)
			ComplexArray.copy(_sizeOfColumns, values, l, 1, _values, k, 1);
		_values = values;
		_capacityOfRows = capacityOfRows;
		_capacityOfColumns = capacityOfColumns;
	}

	public void addRow(VectorI vector) {
		int i = _sizeOfRows;
		setSize(_sizeOfRows + 1, _sizeOfColumns);
		if (vector == null)
			return;
		if (vector instanceof DenseVector) {
			DenseVector vect = (DenseVector) vector;
			int n = Math.min(vector.size(), _sizeOfColumns);
			ComplexArray.copy(n, _values, i * _capacityOfColumns, 1, vect._values, 0, 1);
		} else {
			for (Iterator<VectorElementI> e = vector.elements(); e.hasNext();) {
				VectorElementI elem = e.next();
				int j = elem.getIndex();
				if (j < _sizeOfColumns)
					setElementAt(i, j, elem.getValue());
			}
		}
	}

	public void addColumn(VectorI vector) {
		int j = _sizeOfColumns;
		setSize(_sizeOfRows, _sizeOfColumns + 1);
		if (vector == null)
			return;
		if (vector instanceof DenseVector) {
			DenseVector vect = (DenseVector) vector;
			int n = Math.min(vector.size(), _sizeOfRows);
			ComplexArray.copy(n, _values, _sizeOfColumns - 1, _capacityOfRows, vect._values, 0, 1);
		} else {
			for (Iterator<VectorElementI> e = vector.elements(); e.hasNext();) {
				VectorElementI elem = e.next();
				int i = elem.getIndex();
				if (i < _sizeOfRows)
					setElementAt(i, j, elem.getValue());
			}
		}
	}

	public void setElementAt(int row, int column, ComplexI value) {
		if (row < 0 || row >= _sizeOfRows)
			throw new IndexOutOfBoundsException("Row=" + row + ", sizeOfRows=" + _sizeOfRows);
		if (column < 0 || column >= _sizeOfColumns)
			throw new IndexOutOfBoundsException("Column=" + column + ", sizeOfColumns=" + _sizeOfColumns);
		int i = 2 * (column + _capacityOfColumns * row);
		_values[i] = value.getReal();
		_values[i + 1] = value.getImag();
	}

	public Complex elementAt(int row, int column) {
		if (row < 0 || row >= _sizeOfRows)
			throw new IndexOutOfBoundsException("Row=" + row + ", sizeOfRows=" + _sizeOfRows);
		if (column < 0 || column >= _sizeOfColumns)
			throw new IndexOutOfBoundsException("Column=" + column + ", sizeOfColumns=" + _sizeOfColumns);
		int i = 2 * (column + _capacityOfColumns * row);
		return new Complex(_values[i], _values[i + 1]);
	}

	public Complex elementAt(int row, int column, Complex results) {
		if (row < 0 || row >= _sizeOfRows)
			throw new IndexOutOfBoundsException("Row=" + row + ", sizeOfRows=" + _sizeOfRows);
		if (column < 0 || column >= _sizeOfColumns)
			throw new IndexOutOfBoundsException("Column=" + column + ", sizeOfColumns=" + _sizeOfColumns);
		int i = 2 * (column + _capacityOfColumns * row);
		if (results == null)
			return new Complex(_values[i], _values[i + 1]);
		results.real = _values[i];
		results.imag = _values[i + 1];
		return results;
	}

	public double[][] getArray() {
		double[][] array = new double[_sizeOfRows][_sizeOfColumns * 2];
		for (int i = 0, k = 0; i < array.length; i++, k += _capacityOfColumns) {
			double[] row = array[i];
			ComplexArray.copy(_sizeOfColumns, row, 0, 1, _values, k, 1);
		}
		return array;
	}

	public Iterator<MatrixElementI> rowElements(int row) {
		if (row < 0 || row >= _sizeOfRows)
			throw new IndexOutOfBoundsException("Row=" + row + ", sizeOfRows=" + _sizeOfRows);
		return new RowEnum(row);
	}

	public Iterator<MatrixElementI> columnElements(int column) {
		if (column < 0 || column >= _sizeOfColumns)
			throw new IndexOutOfBoundsException("Column=" + column + ", sizeOfColumns=" + _sizeOfColumns);
		return new ColumnEnum(column);
	}

	public Iterator<MatrixElementI> elements() {
		return new Enum();
	}

	private class Enum implements Iterator<MatrixElementI>, MatrixElementI, ComplexI {
		private int _row = 0;
		private int _coef = 0;
		private int _column = 0;

		public boolean hasNext() {
			return _row < _sizeOfRows;
		}

		public MatrixElementI next() {
			if (_row >= _sizeOfRows)
				return null;
			_mrow = _row;
			_mcol = _column++;
			_mreal = _values[_coef++];
			_mimag = _values[_coef++];
			if (_column >= _sizeOfColumns) {
				_row++;
				_coef = 2 * _row * _capacityOfColumns;
				_column = 0;
			}
			return this;
		}

		private int _mrow;
		private int _mcol;
		private double _mreal;
		private double _mimag;

		public int getRowIndex() {
			return _mrow;
		}

		public int getColumnIndex() {
			return _mcol;
		}

		public ComplexI getValue() {
			return this;
		}

		public void setValue(ComplexI value) {
			_mreal = value.getReal();
			_mimag = value.getImag();
			setElementAt(_mrow, _mcol, value);
		}

		public double getReal() {
			return _mreal;
		}

		public double getImag() {
			return _mimag;
		}

		@Override
		public void remove() {
		}
	}

	private class RowEnum implements Iterator<MatrixElementI>, MatrixElementI, ComplexI {
		private int _row = 0;
		private int _coef = 0;
		private int _column = 0;

		public RowEnum(int row) {
			_row = row;
			_coef = _row * _capacityOfColumns;
		}

		public boolean hasNext() {
			return _column < _sizeOfColumns;
		}

		public MatrixElementI next() {
			if (_column >= _sizeOfColumns)
				return null;
			_mcol = _column++;
			_mreal = _values[_coef++];
			_mimag = _values[_coef++];
			return this;
		}

		private int _mcol;
		private double _mreal;
		private double _mimag;

		public int getRowIndex() {
			return _row;
		}

		public int getColumnIndex() {
			return _mcol;
		}

		public ComplexI getValue() {
			return this;
		}

		public void setValue(ComplexI value) {
			_mreal = value.getReal();
			_mimag = value.getImag();
			setElementAt(_row, _mcol, value);
		}

		public double getReal() {
			return _mreal;
		}

		public double getImag() {
			return _mimag;
		}

		@Override
		public void remove() {
		}
	}

	class ColumnEnum implements Iterator<MatrixElementI>, MatrixElementI, ComplexI {
		private int _row = 0;
		private int _coef = 0;
		private int _column = 0;

		public ColumnEnum(int column) {
			_coef = _column = column;
		}

		public boolean hasNext() {
			return _row < _sizeOfRows;
		}

		public MatrixElementI next() {
			if (_row >= _sizeOfRows)
				return null;
			_mrow = _row++;
			_mreal = _values[_coef];
			_mimag = _values[_coef];
			_coef += 2 * _capacityOfColumns;
			return this;
		}

		private int _mrow;
		private double _mreal;
		private double _mimag;

		public int getRowIndex() {
			return _mrow;
		}

		public int getColumnIndex() {
			return _column;
		}

		public ComplexI getValue() {
			return this;
		}

		public void setValue(ComplexI value) {
			_mreal = value.getReal();
			_mimag = value.getImag();
			setElementAt(_mrow, _column, value);
		}

		public double getReal() {
			return _mreal;
		}

		public double getImag() {
			return _mimag;
		}

		@Override
		public void remove() {
		}
	}

}
