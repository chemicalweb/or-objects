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

package com.opsresearch.orobjects.lib.geom.rect2;

import com.opsresearch.orobjects.lib.real.matrix.VectorI;

public class Point extends Rect2 implements PointI, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private double _x, _y;

	public Point(double x, double y) {
		_x = x;
		_y = y;
	}

	public Point(PointI point) {
		_x = point.x();
		_y = point.y();
	}

	public Point(VectorI coordinates) {
		_x = coordinates.elementAt(0);
		_y = coordinates.elementAt(1);
	}

	public Point(double[] coordinates) {
		_x = coordinates[0];
		_y = coordinates[1];
	}

	public RangeI range() {
		return new Range(this, this);
	}

	public PointI centroid() {
		return this;
	}

	public PointI nearestPointTo(PointI point) {
		return this;
	}

	public double x() {
		return _x;
	}

	public double y() {
		return _y;
	}

	public double getCoordinate(int i) {
		return i == 0 ? _x : _y;
	}

	public int hashCode() {
		long l = Double.doubleToLongBits(_x) + Double.doubleToLongBits(_y);
		return (int) (l ^ (l >> 32));
	}

	public static boolean equals(PointI a, PointI b, double accuracy) {
		if (Math.abs(a.x() - b.x()) > accuracy)
			return false;
		if (Math.abs(a.y() - b.y()) > accuracy)
			return false;
		return true;
	}

	public boolean equals(Object o) {
		if (!(o instanceof PointI))
			return false;
		return equals(this, (PointI) o, coordinateSystem().getAccuracy());
	}

	public String toString() {
		return "rect2.Point(" + "x = " + _x + ", y = " + _y + ")";
	}

	public double getDirectionTo(com.opsresearch.orobjects.lib.geom.PointI point) {
		PointI p = (PointI) point;
		return Math.atan2(p.y() - _y, p.x() - _x);
	}

	public double directionTo(PointI point) {
		return Math.atan2(point.y() - _y, point.x() - _x);
	}

	public double distanceProxyTo(PointI point) {
		double dx = _x - ((PointI) point).x();
		double dy = _y - ((PointI) point).y();
		return dx * dx + dy * dy;
	}

	public double distanceTo(PointI point) {
		double dx = _x - ((PointI) point).x();
		double dy = _y - ((PointI) point).y();
		return Math.sqrt(dx * dx + dy * dy);
	}
}
