package science.monke.api.billing

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.stereotype.Component
import science.monke.spring.properties.BillingAWSProperties
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest
import javax.annotation.PostConstruct

@Component
class BillingSQSListener(
    @Qualifier("billing-thread-pool-task-scheduler")
    private val threadPoolTaskScheduler: ThreadPoolTaskScheduler,
    @Qualifier("billing-sqs-client")
    private val sqsClient: SqsClient,
    private val billingAWSProperties: BillingAWSProperties
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostConstruct
    fun initBillingSqsThreads() {
        for (i in billingAWSProperties.sqs.poolSize downTo 1) {
            threadPoolTaskScheduler.scheduleWithFixedDelay(poolMessages(), 1)
        }
    }

    fun poolMessages(): Runnable {
        return Runnable {
            val queueUrl: String = billingAWSProperties.sqs.queueUrl

            logger.debug("Polling messages from queue: $queueUrl")

            val receiveMessage = sqsClient.receiveMessage(
                ReceiveMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .maxNumberOfMessages(10)
                    .waitTimeSeconds(20)
                    .build()
            )
            receiveMessage.messages().forEach { message ->
                logger.info("Received message: ${message.body()}")
                val deleteMessageRequest =
                    DeleteMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .receiptHandle(message.receiptHandle())
                        .build()
                sqsClient.deleteMessage(deleteMessageRequest)
                logger.info("Deleted message: ${message.messageId()}")
            }
        }
    }
}