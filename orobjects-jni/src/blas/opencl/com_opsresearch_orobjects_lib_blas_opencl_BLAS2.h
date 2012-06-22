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


#include <jni.h>


#ifndef _Included_com_opsresearch_orobjects_lib_blas_opencl_BLAS2
#define _Included_com_opsresearch_orobjects_lib_blas_opencl_BLAS2
#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_dgemvNative
  (JNIEnv *, jobject, jint, jint, jdouble, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jdouble, jdoubleArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_dgerNative
  (JNIEnv *, jobject, jint, jint, jdouble, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_sgemvNative
  (JNIEnv *, jobject, jint, jint, jfloat, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jfloat, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_sgerNative
  (JNIEnv *, jobject, jint, jint, jfloat, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_zgemvNative
  (JNIEnv *, jobject, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_zgeruNative
  (JNIEnv *, jobject, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_zgercNative
  (JNIEnv *, jobject, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_cgemvNative
  (JNIEnv *, jobject, jint, jint, jfloat, jfloat, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jfloat, jfloat, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_cgeruNative
  (JNIEnv *, jobject, jint, jint, jfloat, jfloat, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_opencl_BLAS2_cgercNative
  (JNIEnv *, jobject, jint, jint, jfloat, jfloat, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
