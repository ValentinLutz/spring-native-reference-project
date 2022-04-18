package science.monke.spring.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import software.amazon.awssdk.regions.Region
import java.net.URI

@ConstructorBinding
@ConfigurationProperties(prefix = "custom.billing.aws")
data class BillingAWSProperties(
    val accessKeyId: String,
    val secretAccessKey: String,
    val region: Region,
    val endpoint: URI?,
    val proxy: URI?,
    val sqs: SQS,
    val sns: SNS
) {
    data class SQS(
        val poolSize: Int,
        val queueUrl: String,
        val iamRole: String
    )

    data class SNS(
        val topicArn: String,
        val iamRole: String,
    )
}