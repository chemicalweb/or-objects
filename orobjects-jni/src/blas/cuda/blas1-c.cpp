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

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx) {
	jboolean isCopy;
	cuComplex *elems = (cuComplex*) env->GetFloatArrayElements(x, &isCopy);
	return (jfloat) cublasScasum(n, elems + begx, incx);
}

		JNIEnv *env, jobject, jint n, jfloat real, jfloat imag, jfloatArray x,
		jint begx, jint incx, jfloatArray y, jint begy, jint incy) {
	jboolean xisCopy;
	cuComplex *xelems = (cuComplex*) env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	cuComplex *yelems = (cuComplex*) env->GetFloatArrayElements(y, &yisCopy);
	cuComplex alpha;
	alpha.x = real;
	alpha.y = imag;
	cublasCaxpy(n, alpha, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx,
		jfloatArray y, jint begy, jint incy) {
	jboolean xisCopy;
	cuComplex *xelems = (cuComplex*) env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	cuComplex *yelems = (cuComplex*) env->GetFloatArrayElements(y, &yisCopy);
	cublasCcopy(n, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx,
		jfloatArray y, jint begy, jint incy, jfloatArray rslt) {
	jboolean xisCopy;
	cuComplex *xelems = (cuComplex*) env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	cuComplex *yelems = (cuComplex*) env->GetFloatArrayElements(y, &yisCopy);
	jboolean risCopy;
	jfloat *relems = env->GetFloatArrayElements(rslt, &risCopy);
	cuComplex val = cublasCdotu(n, xelems + begx, incx, yelems + begy, incy);
	relems[0] = val.x;
	relems[1] = val.y;
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx,
		jfloatArray y, jint begy, jint incy, jfloatArray rslt) {
	jboolean xisCopy;
	cuComplex *xelems = (cuComplex*) env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	cuComplex *yelems = (cuComplex*) env->GetFloatArrayElements(y, &yisCopy);
	jboolean risCopy;
	jfloat *relems = env->GetFloatArrayElements(rslt, &risCopy);
	cuComplex val = cublasCdotc(n, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy);
	relems[0] = val.x;
	relems[1] = val.y;
}

		JNIEnv *env, jobject, jint n, jfloat real, jfloat imag, jfloatArray x,
		jint begx, jint incx, jfloatArray y, jint begy, jint incy) {
	jboolean xisCopy;
	cuComplex *xelems = (cuComplex*) env->GetFloatArrayElements(x, &xisCopy);
	cuComplex alpha;
	alpha.x = real;
	alpha.y = imag;
	cublasCscal(n, alpha, xelems + begx, incx);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx,
		jfloatArray y, jint begy, jint incy) {
	jboolean xisCopy;
	cuComplex *xelems = (cuComplex*) env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	cuComplex *yelems = (cuComplex*) env->GetFloatArrayElements(y, &yisCopy);
	cublasCswap(n, xelems + begx, incx, yelems + begy, incy);

}
