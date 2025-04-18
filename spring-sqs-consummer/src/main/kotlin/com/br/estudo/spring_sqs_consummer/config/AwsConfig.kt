package com.br.estudo.spring_sqs_consummer.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.net.URI

@Configuration
class AwsConfig {

    @Value("\${aws.queue.url}")
    private lateinit  var queueUrl: String

    @Bean
    fun sqsClient():SqsAsyncClient{
        return SqsAsyncClient.builder()
            .endpointOverride(URI.create(queueUrl))
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider
                .create(AwsBasicCredentials.create("teste","teste" )))
            .build()
    }
}