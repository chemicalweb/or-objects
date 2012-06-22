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


#include <cblas.h>
#include "com_opsresearch_orobjects_lib_blas_opencl_BLAS1.h"

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx) {
		jboolean isCopy;
		jdouble *elems = env->GetDoubleArrayElements(x, &isCopy);
		return (jdouble) cblas_dzasum(n, elems + (begx >> 1), incx);
}

		JNIEnv *env, jobject, jint n, jdouble real, jdouble imag, jdoubleArray x, jint begx, jint incx,
		jdoubleArray y, jint begy, jint incy) {
		jboolean xisCopy;
		jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
		jboolean yisCopy;
		jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
		double alpha[2];
		alpha[0] = real;
		alpha[1] = imag;
		cblas_zaxpy(n, alpha, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx, jdoubleArray y, jint begy,
		jint incy) {
		jboolean xisCopy;
		jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
		jboolean yisCopy;
		jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
		cblas_zcopy(n, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx, jdoubleArray y, jint begy,
		jint incy, jdoubleArray rslt) {
		jboolean xisCopy;
		jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
		jboolean yisCopy;
		jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
		jboolean risCopy;
		jdouble *relems = env->GetDoubleArrayElements(rslt, &risCopy);
		cblas_zdotu_sub(n, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy, relems);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx, jdoubleArray y, jint begy,
		jint incy, jdoubleArray rslt) {
		jboolean xisCopy;
		jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
		jboolean yisCopy;
		jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
		jboolean risCopy;
		jdouble *relems = env->GetDoubleArrayElements(rslt, &risCopy);
		cblas_zdotc_sub(n, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy, relems);
}

			JNIEnv *env, jobject, jint n, jdouble real, jdouble imag, jdoubleArray x, jint begx, jint incx,
			jdoubleArray y, jint begy, jint incy) {
			jboolean xisCopy;
			jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
			double alpha[2];
			alpha[0] = real;
			alpha[1] = imag;
			cblas_zscal(n, alpha, xelems + (begx >> 1), incx);
}

			JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx, jdoubleArray y, jint begy,
			jint incy) {
			jboolean xisCopy;
			jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
			jboolean yisCopy;
			jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
			cblas_zswap(n, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy);

}
