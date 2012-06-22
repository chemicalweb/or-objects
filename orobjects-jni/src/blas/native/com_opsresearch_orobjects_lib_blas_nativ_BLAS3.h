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


#ifndef _Included_com_opsresearch_orobjects_lib_blas_nativ_BLAS3
#define _Included_com_opsresearch_orobjects_lib_blas_nativ_BLAS3
#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_nativ_BLAS3_dgemmNative
  (JNIEnv *, jobject, jint, jint, jint, jdouble, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jint, jdouble, jdoubleArray, jint, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_nativ_BLAS3_sgemmNative
  (JNIEnv *, jobject, jint, jint, jint, jfloat, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jint, jfloat, jfloatArray, jint, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_nativ_BLAS3_zgemmNative
  (JNIEnv *, jobject, jint, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint, jint, jdoubleArray, jint, jint, jint, jdouble, jdouble, jdoubleArray, jint, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_nativ_BLAS3_cgemmNative
  (JNIEnv *, jobject, jint, jint, jint, jfloat, jfloat, jfloatArray, jint, jint, jint, jfloatArray, jint, jint, jint, jfloat, jfloat, jfloatArray, jint, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
