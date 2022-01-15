package science.monke.mq.billing.boundary

import org.springframework.jms.annotation.JmsListener

class BillingListenerMQ {

    @JmsListener(destination = "")
    fun onMessage() {
    }
}
