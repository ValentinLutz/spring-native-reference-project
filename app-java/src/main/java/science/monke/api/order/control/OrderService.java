package science.monke.api.order.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import science.monke.api.order.entity.OrderRequest;
import science.monke.api.order.entity.OrderResponse;
import science.monke.internal.order.control.OrderMapper;
import science.monke.internal.order.entity.OrderEntity;
import science.monke.internal.order.repository.OrderRepository;
import science.monke.internal.workflow.DefaultWorkflow;
import science.monke.internal.workflow.WorkflowObject;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;
  private final DefaultWorkflow defaultWorkflow;

  @Autowired
  public OrderService(
      final OrderRepository orderRepository,
      final OrderMapper orderMapper,
      final DefaultWorkflow defaultWorkflow) {
    this.orderRepository = orderRepository;
    this.orderMapper = orderMapper;
    this.defaultWorkflow = defaultWorkflow;
  }

  public Set<OrderResponse> getOrders() {
    return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
        .map(orderMapper::orderEntityToOrderResponse)
        .collect(Collectors.toSet());
  }

  public OrderResponse postOrders(final OrderRequest orderRequest) {
    final OrderEntity orderEntity = orderMapper.orderRequestToOrderEntity(orderRequest);
    final WorkflowObject workflowObject = WorkflowObject.builder().order(orderEntity).build();
    defaultWorkflow.execute(workflowObject);
    return orderMapper.orderEntityToOrderResponse(orderEntity);
  }

  public Optional<OrderResponse> getOrder(final UUID uuid) {
    return orderRepository.findByOrderId(uuid).map(orderMapper::orderEntityToOrderResponse);
  }
}
