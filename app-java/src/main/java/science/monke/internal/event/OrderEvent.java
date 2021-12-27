package science.monke.internal.event;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import science.monke.internal.order.entity.OrderEntity;
import science.monke.internal.order.repository.OrderItemRepository;
import science.monke.internal.order.repository.OrderRepository;
import science.monke.internal.workflow.WorkflowObject;

@Log4j2
@Component
public class OrderEvent implements Event {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  @Autowired
  public OrderEvent(
      final OrderRepository orderRepository, final OrderItemRepository orderItemRepository) {
    this.orderRepository = orderRepository;
    this.orderItemRepository = orderItemRepository;
  }

  @Override
  public EventName getName() {
    return EventName.ORDER_EVENT;
  }

  @Override
  @Transactional
  public WorkflowObject execute(final WorkflowObject workflowObject) {
    final OrderEntity order = workflowObject.getOrder();
    orderRepository.save(order);
    orderItemRepository.saveAll(order.getOrderItems());

    return workflowObject;
  }
}
