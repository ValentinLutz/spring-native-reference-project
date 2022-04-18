package science.monke.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import science.monke.spring.properties.BillingAWSProperties


@Configuration
class ThreadPoolTaskSchedulerSqsListenerConfig(
    private val billingAWSProperties: BillingAWSProperties
) {
    @Bean("billing-thread-pool-task-scheduler")
    fun billingThreadPoolTaskScheduler(): ThreadPoolTaskScheduler {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()
        threadPoolTaskScheduler.poolSize = billingAWSProperties.sqs.poolSize
        threadPoolTaskScheduler.setThreadNamePrefix("billing-sqs-")
        threadPoolTaskScheduler.initialize()
        return threadPoolTaskScheduler
    }
}