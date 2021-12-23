#!/usr/bin/env bash

SHIPPING_OWNER='000000000000'
SHIPPING_TOPIC="${SHIPPING_TOPIC:-shipping-dev-topic}"
SHIPPING_QUEUES="${SHIPPING_QUEUES:-shipping-dev-queue}"

# Create SNS topic
awslocal sns create-topic \
  --name $SHIPPING_TOPIC

# Create array from queues
IFS=',' read -ra queues <<<$SHIPPING_QUEUES

# Create SQS queues
for queue in ${queues[@]}; do
  awslocal sqs create-queue \
    --queue-name $queue
done

# Subscribe SQS queues to SNS topic
for queue in ${queues[@]}; do
  awslocal sns subscribe \
    --topic-arn "arn:aws:sns:$DEFAULT_REGION:$SHIPPING_OWNER:$SHIPPING_TOPIC" \
    --protocol sqs \
    --notification-endpoint "https://$HOSTNAME:$EDGE_PORT/$SHIPPING_OWNER/$queue" \
    --attributes RawMessageDelivery=true
done
