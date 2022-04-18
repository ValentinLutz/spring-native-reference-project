package science.monke.api.billing.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import science.monke.spring.properties.BillingAWSProperties
import science.monke.util.aws.SNSConfig
import software.amazon.awssdk.services.sns.SnsClient

@Configuration
class BillingSNSConfig(
    private val snsConfig: SNSConfig,
    private val billingAWSProperties: BillingAWSProperties
) {

    @Bean("billing-sns-client")
    fun billingSNSClient(): SnsClient {
        return snsConfig.createClient(
            endpointURI = billingAWSProperties.endpoint,
            region = billingAWSProperties.region,
            accessKeyId = billingAWSProperties.accessKeyId,
            secretAccessKey = billingAWSProperties.secretAccessKey
        )
    }
}