package org.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.consumer.SqsConsumer
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.net.URI


fun main() = runBlocking{
    val sqs = SqsAsyncClient.builder()
        .endpointOverride(URI.create("https://localhost.localstack.cloud:4566"))
        .region(Region.US_EAST_1)
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(
            "test","test")))
        .build()

    val consumer = SqsConsumer(sqs)
    consumer.start()
    delay(3000L)
    consumer.stop()
}