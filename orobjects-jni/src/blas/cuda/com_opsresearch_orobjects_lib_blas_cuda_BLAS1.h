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


#ifndef _Included_com_opsresearch_orobjects_lib_blas_cuda_BLAS1
#define _Included_com_opsresearch_orobjects_lib_blas_cuda_BLAS1
#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT jdouble JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_dasumNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_daxpyNative
  (JNIEnv *, jobject, jint, jdouble, jdoubleArray, jint, jint, jdoubleArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_dcopyNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint);

JNIEXPORT jdouble JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_ddotNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_drotNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jdouble, jdouble);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_dscalNative
  (JNIEnv *, jobject, jint, jdouble, jdoubleArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_dswapNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint);

JNIEXPORT jdouble JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_dnrm2Native
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint);

JNIEXPORT jint JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_idamaxNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint);

JNIEXPORT jfloat JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_sasumNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_saxpyNative
  (JNIEnv *, jobject, jint, jfloat, jfloatArray, jint, jint, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_scopyNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint, jfloatArray, jint, jint);

JNIEXPORT jfloat JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_sdotNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_srotNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloat, jfloat);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_sscalNative
  (JNIEnv *, jobject, jint, jfloat, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_sswapNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint, jfloatArray, jint, jint);

JNIEXPORT jfloat JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_snrm2Native
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint);

JNIEXPORT jint JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_isamaxNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint);

JNIEXPORT jdouble JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_dzasumNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_zaxpyNative
  (JNIEnv *, jobject, jint, jdouble, jdouble, jdoubleArray, jint, jint, jdoubleArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_zcopyNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint);

JNIEXPORT jobject JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_zdotuNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject);

JNIEXPORT jobject JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_zdotcNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint, jobject);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_zscalNative
  (JNIEnv *, jobject, jint, jdouble, jdouble, jdoubleArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_zswapNative
  (JNIEnv *, jobject, jint, jdoubleArray, jint, jint, jdoubleArray, jint, jint);

JNIEXPORT jfloat JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_scasumNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_caxpyNative
  (JNIEnv *, jobject, jint, jfloat, jfloat, jfloatArray, jint, jint, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_ccopyNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_cdotuNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_cdotcNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint, jfloatArray, jint, jint, jfloatArray);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_cscalNative
  (JNIEnv *, jobject, jint, jfloat, jfloat, jfloatArray, jint, jint);

JNIEXPORT void JNICALL Java_com_opsresearch_orobjects_lib_blas_cuda_BLAS1_cswapNative
  (JNIEnv *, jobject, jint, jfloatArray, jint, jint, jfloatArray, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
