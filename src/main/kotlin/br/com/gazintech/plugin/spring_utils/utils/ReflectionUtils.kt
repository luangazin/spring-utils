package br.com.gazintech.plugin.spring_utils.utils

/**
 * Created by IntelliJ IDEA.<br/>
 * User: luan-gazin<br/>
 * Date: 03/05/25<br/>
 * Time: 02:07<br/>
 * To change this template use File | Settings | File Templates.
 */
class ReflectionUtils {
    companion object {
        fun getAnnotationValue(
            clazz: Class<*>, annotationClass: Class<out Annotation>, propertyName: String
        ): Any? {
            val annotation = clazz.getAnnotation(annotationClass)
            return if (annotation != null) {
                val method = annotationClass.getDeclaredMethod(propertyName)
                method.invoke(annotation)
            } else {
                null
            }
        }
    }

}