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

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx) {
		jboolean isCopy;
		jfloat *elems = env->GetFloatArrayElements(x, &isCopy);
		return (jfloat) cblas_scasum(n, elems + (begx >> 1), incx);
}

		JNIEnv *env, jobject, jint n, jfloat real, jfloat imag, jfloatArray x, jint begx, jint incx,
		jfloatArray y, jint begy, jint incy) {
		jboolean xisCopy;
		jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
		jboolean yisCopy;
		jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
		float alpha[2];
		alpha[0] = real;
		alpha[1] = imag;
		cblas_caxpy(n, alpha, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx, jfloatArray y, jint begy,
		jint incy) {
		jboolean xisCopy;
		jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
		jboolean yisCopy;
		jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
		cblas_ccopy(n, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx, jfloatArray y, jint begy,
		jint incy, jfloatArray rslt) {
		jboolean xisCopy;
		jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
		jboolean yisCopy;
		jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
		jboolean risCopy;
		jfloat *relems = env->GetFloatArrayElements(rslt, &risCopy);
		cblas_cdotu_sub(n, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy, relems);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx, jfloatArray y, jint begy,
		jint incy, jfloatArray rslt) {
		jboolean xisCopy;
		jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
		jboolean yisCopy;
		jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
		jboolean risCopy;
		jfloat *relems = env->GetFloatArrayElements(rslt, &risCopy);
		cblas_cdotc_sub(n, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy, relems);
}

			JNIEnv *env, jobject, jint n, jfloat real, jfloat imag, jfloatArray x, jint begx, jint incx,
			jfloatArray y, jint begy, jint incy) {
			jboolean xisCopy;
			jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
			float alpha[2];
			alpha[0] = real;
			alpha[1] = imag;
			cblas_cscal(n, alpha, xelems + (begx >> 1), incx);
}

			JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx, jfloatArray y, jint begy,
			jint incy) {
			jboolean xisCopy;
			jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
			jboolean yisCopy;
			jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
			cblas_cswap(n, xelems + (begx >> 1), incx, yelems + (begy >> 1), incy);

}
