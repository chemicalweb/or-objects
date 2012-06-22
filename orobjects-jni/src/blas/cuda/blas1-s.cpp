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
#include "com_opsresearch_orobjects_lib_blas_cuda_BLAS1.h"

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx) {
	jboolean isCopy;
	jfloat *elems = env->GetFloatArrayElements(x, &isCopy);
	return (jfloat) cblas_sasum(n, elems + begx, incx);
}

		JNIEnv *env, jobject, jint n, jfloat alpha, jfloatArray x, jint begx,
		jint incx, jfloatArray y, jint begy, jint incy) {
	jboolean xisCopy;
	jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
	cblas_saxpy(n, alpha, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx,
		jfloatArray y, jint begy, jint incy) {
	jboolean xisCopy;
	jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
	cblas_scopy(n, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx,
		jfloatArray y, jint begy, jint incy) {
	jboolean xisCopy;
	jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
	return (jfloat) cblas_sdot(n, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx,
		jfloatArray y, jint begy, jint incy, jfloat cos, jfloat sin) {
	jboolean xisCopy;
	jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
	cblas_srot(n, xelems + begx, incx, yelems + begy, incy, cos, sin);

}

		JNIEnv *env, jobject, jint n, jfloat alpha, jfloatArray x, jint begx,
		jint incx) {
	jboolean isCopy;
	jfloat *elems = env->GetFloatArrayElements(x, &isCopy);
	cblas_sscal(n, alpha, elems + begx, incx);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx,
		jfloatArray y, jint begy, jint incy) {
	jboolean xisCopy;
	jfloat *xelems = env->GetFloatArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jfloat *yelems = env->GetFloatArrayElements(y, &yisCopy);
	cblas_sswap(n, xelems + begx, incx, yelems + begy, incy);

}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx) {
		jboolean isCopy;
		jfloat *elems = env->GetFloatArrayElements(x, &isCopy);
		return (jfloat) cblas_snrm2(n, elems + begx, incx);
}

		JNIEnv *env, jobject, jint n, jfloatArray x, jint begx, jint incx) {
		jboolean isCopy;
		jfloat *elems = env->GetFloatArrayElements(x, &isCopy);
		return cblas_isamax(n, elems + begx, incx);
}

