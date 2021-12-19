#!/usr/bin/env bash

BILLING_OWNER='000000000000'
BILLING_TOPIC="${BILLING_TOPIC:-billing-dev-topic}"
BILLING_QUEUES="${BILLING_QUEUES:-billing-dev-queue}"

# Create SNS topic
awslocal sns create-topic \
  --name $BILLING_TOPIC

# Create array from queues
IFS=',' read -ra queues <<<$BILLING_QUEUES

# Create SQS queues
for queue in ${queues[@]}; do
  awslocal sqs create-queue \
    --queue-name $queue
done

# Subscribe SQS queues to SNS topic
for queue in ${queues[@]}; do
  awslocal sns subscribe \
    --topic-arn "arn:aws:sns:$DEFAULT_REGION:$BILLING_OWNER:$BILLING_TOPIC" \
    --protocol sqs \
    --notification-endpoint "https://$HOSTNAME:$EDGE_PORT/$BILLING_OWNER/$queue" \
    --attributes RawMessageDelivery=true
done

# Create user
awslocal iam create-user --user-name test
awslocal iam create-access-key --user-name test
