package science.monke.api.billing.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import science.monke.spring.properties.BillingAWSProperties
import science.monke.util.aws.SQSConfig
import software.amazon.awssdk.services.sqs.SqsClient

@Configuration
class BillingSQSConfig(
    private val sqsConfig: SQSConfig,
    private val billingAWSProperties: BillingAWSProperties
) {

    @Bean("billing-sqs-client")
    fun billingSQSClient(): SqsClient {
        return sqsConfig.createClient(
            endpointURI = billingAWSProperties.endpoint,
            region = billingAWSProperties.region,
            accessKeyId = billingAWSProperties.accessKeyId,
            secretAccessKey = billingAWSProperties.secretAccessKey
        )
    }
}