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

package com.opsresearch.orobjects.lib.prob;

public class Distributions {

	private BinomialDistributionI _binomialDistribution;
	private ChiSquareDistributionI _chiSquareDistribution;
	private DiscreteUniformDistributionI _discreteUniformDistribution;
	private EmpiricalDistributionI _empiricalDistribution;
	private ExponentialDistributionI _exponentialDistribution;
	private FDistributionI _fDistribution;
	private NormalDistributionI _normalDistribution;
	private PoissonDistributionI _poissonDistribution;
	private StudentsTDistributionI _studentsTDistribution;
	private UniformDistributionI _uniformDistribution;

	public BinomialDistributionI getBinomialDistribution() {
		if (_binomialDistribution == null)
			_binomialDistribution = new BinomialDistribution();
		return _binomialDistribution;
	}

	public void setBinomialDistribution(BinomialDistributionI binomialDistribution) {
		this._binomialDistribution = binomialDistribution;
	}

	public ChiSquareDistributionI getChiSquareDistribution() {
		if (_chiSquareDistribution == null)
			_chiSquareDistribution = new ChiSquareDistribution();
		return _chiSquareDistribution;
	}

	public void setChiSquareDistribution(ChiSquareDistributionI chiSquareDistribution) {
		this._chiSquareDistribution = chiSquareDistribution;
	}

	public DiscreteUniformDistributionI getDiscreteUniformDistribution() {
		if (_discreteUniformDistribution == null)
			_discreteUniformDistribution = new DiscreteUniformDistribution();
		return _discreteUniformDistribution;
	}

	public void setDiscreteUniformDistribution(DiscreteUniformDistributionI discreteUniformDistribution) {
		this._discreteUniformDistribution = discreteUniformDistribution;
	}

	public EmpiricalDistributionI getEmpiricalDistribution() {
		if (_empiricalDistribution == null)
			_empiricalDistribution = new EmpiricalDistribution();
		return _empiricalDistribution;
	}

	public void setEmpiricalDistribution(EmpiricalDistributionI empiricalDistribution) {
		this._empiricalDistribution = empiricalDistribution;
	}

	public ExponentialDistributionI getExponentialDistribution() {
		if (_exponentialDistribution == null)
			_exponentialDistribution = new ExponentialDistribution();
		return _exponentialDistribution;
	}

	public void setExponentialDistribution(ExponentialDistributionI exponentialDistribution) {
		this._exponentialDistribution = exponentialDistribution;
	}

	public FDistributionI getFDistribution() {
		if (_fDistribution == null)
			_fDistribution = new FDistribution();
		return _fDistribution;
	}

	public void setFDistribution(FDistributionI fDistribution) {
		this._fDistribution = fDistribution;
	}

	public NormalDistributionI getNormalDistribution() {
		if (_normalDistribution == null)
			_normalDistribution = new NormalDistribution();
		return _normalDistribution;
	}

	public void setNormalDistribution(NormalDistributionI normalDistribution) {
		this._normalDistribution = normalDistribution;
	}

	public PoissonDistributionI getPoissonDistribution() {
		if (_poissonDistribution == null)
			_poissonDistribution = new PoissonDistribution();
		return _poissonDistribution;
	}

	public void setPoissonDistribution(PoissonDistributionI poissonDistribution) {
		this._poissonDistribution = poissonDistribution;
	}

	public StudentsTDistributionI getStudentsTDistribution() {
		if (_studentsTDistribution == null)
			_studentsTDistribution = new StudentsTDistribution();
		return _studentsTDistribution;
	}

	public void setStudentsTDistribution(StudentsTDistributionI studentsTDistribution) {
		this._studentsTDistribution = studentsTDistribution;
	}

	public UniformDistributionI getUniformDistribution() {
		if (_uniformDistribution == null)
			_uniformDistribution = new UniformDistribution();
		return _uniformDistribution;
	}

	public void setUniformDistribution(UniformDistributionI uniformDistribution) {
		this._uniformDistribution = uniformDistribution;
	}

}
