package br.com.gazintech.plugin.spring_utils.api.annotations

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RequestMapping


/**
 * Created by IntelliJ IDEA.<br/>
 * User: luan-gazin<br/>
 * Date: 04/05/25<br/>
 * Time: 22:42<br/>
 * To change this template use File | Settings | File Templates.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Repeatable
@RequestMapping("/v1")
annotation class ApiV1(
    @get:AliasFor("attribute") val value: String = "",
    @get:AliasFor("value") val attribute: String = "",
)


