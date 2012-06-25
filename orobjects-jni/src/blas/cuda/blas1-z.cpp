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


#include <cublas.h>
#include "com_opsresearch_orobjects_lib_blas_cuda_BLAS1.h"

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx) {
		jboolean isCopy;
		cuDoubleComplex *elems = (cuDoubleComplex*)env->GetDoubleArrayElements(x, &isCopy);
		return (jdouble) cublasDzasum(n, elems + begx, incx);
}

		JNIEnv *env, jobject, jint n, jdouble real, jdouble imag, jdoubleArray x, jint begx, jint incx,
		jdoubleArray y, jint begy, jint incy) {
		jboolean xisCopy;
		cuDoubleComplex *xelems = (cuDoubleComplex*)env->GetDoubleArrayElements(x, &xisCopy);
		jboolean yisCopy;
		cuDoubleComplex *yelems = (cuDoubleComplex*)env->GetDoubleArrayElements(y, &yisCopy);
		cuDoubleComplex alpha;
		alpha.x = real;
		alpha.y = imag;
		cublasZaxpy(n, alpha, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx, jdoubleArray y, jint begy,
		jint incy) {
		jboolean xisCopy;
		cuDoubleComplex *xelems = (cuDoubleComplex*)env->GetDoubleArrayElements(x, &xisCopy);
		jboolean yisCopy;
		cuDoubleComplex *yelems = (cuDoubleComplex*)env->GetDoubleArrayElements(y, &yisCopy);
		cublasZcopy(n, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx, jdoubleArray y, jint begy,
		jint incy, jdoubleArray rslt) {
		jboolean xisCopy;
		cuDoubleComplex *xelems = (cuDoubleComplex*)env->GetDoubleArrayElements(x, &xisCopy);
		jboolean yisCopy;
		cuDoubleComplex *yelems = (cuDoubleComplex*)env->GetDoubleArrayElements(y, &yisCopy);
		jboolean risCopy;
		jdouble *relems = env->GetDoubleArrayElements(rslt, &risCopy);
		cuDoubleComplex val = cublasZdotu(n, xelems + begx, incx, yelems + begy, incy);
		relems[0] = val.x;
		relems[1] = val.y;
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx, jdoubleArray y, jint begy,
		jint incy, jdoubleArray rslt) {
		jboolean xisCopy;
		cuDoubleComplex *xelems = (cuDoubleComplex*)env->GetDoubleArrayElements(x, &xisCopy);
		jboolean yisCopy;
		cuDoubleComplex *yelems = (cuDoubleComplex*)env->GetDoubleArrayElements(y, &yisCopy);
		jboolean risCopy;
		jdouble *relems = env->GetDoubleArrayElements(rslt, &risCopy);
		cuDoubleComplex val = cublasZdotc(n, xelems + begx, incx, yelems + begy, incy);
		relems[0] = val.x;
		relems[1] = val.y;
}

			JNIEnv *env, jobject, jint n, jdouble real, jdouble imag, jdoubleArray x, jint begx, jint incx,
			jdoubleArray y, jint begy, jint incy) {
			jboolean xisCopy;
			cuDoubleComplex *xelems = (cuDoubleComplex*)env->GetDoubleArrayElements(x, &xisCopy);
			cuDoubleComplex alpha;
			alpha.x = real;
			alpha.y = imag;
			cublasZscal(n, alpha, xelems + begx, incx);
}

			JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx, jdoubleArray y, jint begy,
			jint incy) {
			jboolean xisCopy;
			cuDoubleComplex *xelems = (cuDoubleComplex*)env->GetDoubleArrayElements(x, &xisCopy);
			jboolean yisCopy;
			cuDoubleComplex *yelems = (cuDoubleComplex*)env->GetDoubleArrayElements(y, &yisCopy);
			cublasZswap(n, xelems + begx, incx, yelems + begy, incy);

}
