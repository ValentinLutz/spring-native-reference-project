package science.monke.api.billing

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import science.monke.spring.properties.BillingAWSProperties
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.model.PublishRequest
import software.amazon.awssdk.services.sns.model.PublishResponse

@Component
class BillingSNSPublisher(
    @Qualifier("billing-sns-client")
    private val snsClient: SnsClient,
    private val billingAWSProperties: BillingAWSProperties
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun publishMessages(message: String, amount: Int) {
        for (i in amount downTo 1) {
            publishMessage(message)
        }
    }

    fun publishMessage(message: String) {
        val topicArn: String = billingAWSProperties.sns.topicArn

        logger.debug("Publish messages to topic: $topicArn")

        val publishRequest = PublishRequest.builder()
            .topicArn(topicArn)
            .message(message)
            .build()
        val publishResponse: PublishResponse = snsClient.publish(publishRequest)
        logger.info("Published message with id ${publishResponse.messageId()} to topic: $topicArn")
    }
}