package science.monke.mq.billing.boundary;

import org.springframework.jms.annotation.JmsListener;

public class BillingListenerMQ {

  @JmsListener(destination = "")
  void onMessage() {}
}
