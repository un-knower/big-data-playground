package com.codiply.bdpg.kafkastreams

import java.util.Properties

import com.codiply.bdpg.constants.KafkaCluster
import com.codiply.bdpg.constants.KafkaCluster.Topics
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}

object NumberMultiplier {
  val applicationId = "number-multiplier-app"

  def main(args: Array[String]): Unit = {
    val builder = new StreamsBuilder()

    val numbers = builder.stream[String, String](Topics.RandomNumbers)
    numbers.mapValues { v => (v.toInt * 10).toString }.to(Topics.MultipliedRandomNumbers)

    val streams = new KafkaStreams(builder.build, buildConfig)
    streams.start()
  }

  def buildConfig(): Properties = {
    val properties = new Properties()
    properties.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId)
    properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaCluster.BROKERS.mkString(","))
    properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass)
    properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass)
    properties
  }
}
