package science.monke.mq.billing

import org.springframework.jms.annotation.JmsListener

class BillingListenerMQ {

    @JmsListener(destination = "")
    fun onMessage() {
    }
}
