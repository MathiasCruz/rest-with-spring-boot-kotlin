package org.example.config

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.net.URI

class AwsConfig() {

    fun getSqsClient():SqsAsyncClient{
        val sqsBuilder =  SqsAsyncClient.builder()
            .endpointOverride(URI.create("https://localhost.localstack.cloud:4566"))
            .region(Region.US_EAST_1)
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(
                        "test","test")))
            .build()
        return sqsBuilder
    }
}