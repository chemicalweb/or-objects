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
	jdouble *elems = env->GetDoubleArrayElements(x, &isCopy);
	return (jdouble) cublasDasum(n, elems + begx, incx);
}

		JNIEnv *env, jobject, jint n, jdouble alpha, jdoubleArray x, jint begx,
		jint incx, jdoubleArray y, jint begy, jint incy) {
	jboolean xisCopy;
	jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
	cublasDaxpy(n, alpha, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx,
		jdoubleArray y, jint begy, jint incy) {
	jboolean xisCopy;
	jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
	cublasDcopy(n, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx,
		jdoubleArray y, jint begy, jint incy) {
	jboolean xisCopy;
	jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
	return (jdouble) cublasDdot(n, xelems + begx, incx, yelems + begy, incy);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx,
		jdoubleArray y, jint begy, jint incy, jdouble cos, jdouble sin) {
	jboolean xisCopy;
	jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
	cublasDrot(n, xelems + begx, incx, yelems + begy, incy, cos, sin);

}

		JNIEnv *env, jobject, jint n, jdouble alpha, jdoubleArray x, jint begx,
		jint incx) {
	jboolean isCopy;
	jdouble *elems = env->GetDoubleArrayElements(x, &isCopy);
	cublasDscal(n, alpha, elems + begx, incx);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx,
		jdoubleArray y, jint begy, jint incy) {
	jboolean xisCopy;
	jdouble *xelems = env->GetDoubleArrayElements(x, &xisCopy);
	jboolean yisCopy;
	jdouble *yelems = env->GetDoubleArrayElements(y, &yisCopy);
	cublasDswap(n, xelems + begx, incx, yelems + begy, incy);

}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx) {
		jboolean isCopy;
		jdouble *elems = env->GetDoubleArrayElements(x, &isCopy);
		return (jdouble) cublasDnrm2(n, elems + begx, incx);
}

		JNIEnv *env, jobject, jint n, jdoubleArray x, jint begx, jint incx) {
		jboolean isCopy;
		jdouble *elems = env->GetDoubleArrayElements(x, &isCopy);
		return cublasIdamax(n, elems + begx, incx);
}

