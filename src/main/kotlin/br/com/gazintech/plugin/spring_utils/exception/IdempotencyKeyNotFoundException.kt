package br.com.gazintech.plugin.spring_utils.exception

/**
 * Created by IntelliJ IDEA.<br/>
 * User: luan-gazin<br/>
 * Date: 01/05/25<br/>
 * Time: 21:57<br/>
 * To change this template use File | Settings | File Templates.
 */
class IdempotencyKeyNotFoundException: RuntimeException {
    constructor() : super("Idempotency key not found")
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}