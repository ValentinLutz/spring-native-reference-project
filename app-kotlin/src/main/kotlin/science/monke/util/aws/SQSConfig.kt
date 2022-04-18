package science.monke.util.aws

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

@Configuration
class SQSConfig {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun createClient(endpointURI: URI?, region: Region, accessKeyId: String, secretAccessKey: String): SqsClient {
        logger.info("Creating SQS client with endpoint: $endpointURI, region: $region")
        
        val sqsClientBuilder = SqsClient.builder()
            .region(region)
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(
                        accessKeyId, secretAccessKey
                    )
                )
            )

        if (endpointURI != null) {
            sqsClientBuilder.endpointOverride(endpointURI)
        }

        return sqsClientBuilder.build()
    }
}