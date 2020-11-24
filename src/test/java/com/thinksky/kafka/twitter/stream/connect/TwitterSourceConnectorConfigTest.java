package com.thinksky.kafka.twitter.stream.connect;

import static com.thinksky.kafka.twitter.stream.connect.TwitterSourceConnectorConfig.FILTER_KEYWORDS_CONF;
import static com.thinksky.kafka.twitter.stream.connect.TwitterSourceConnectorConfig.FILTER_USER_IDS_CONF;
import static com.thinksky.kafka.twitter.stream.connect.TwitterSourceConnectorConfig.KAFKA_STATUS_TOPIC_CONF;
import static com.thinksky.kafka.twitter.stream.connect.TwitterSourceConnectorConfig.PROCESS_DELETES_CONF;
import static com.thinksky.kafka.twitter.stream.connect.TwitterSourceConnectorConfig.TWITTER_DEBUG_CONF;
import static com.thinksky.kafka.twitter.stream.connect.TwitterSourceConnectorConfig.TWITTER_OAUTH_ACCESS_TOKEN_CONF;
import static com.thinksky.kafka.twitter.stream.connect.TwitterSourceConnectorConfig.TWITTER_OAUTH_ACCESS_TOKEN_SECRET_CONF;
import static com.thinksky.kafka.twitter.stream.connect.TwitterSourceConnectorConfig.TWITTER_OAUTH_CONSUMER_KEY_CONF;
import static com.thinksky.kafka.twitter.stream.connect.TwitterSourceConnectorConfig.TWITTER_OAUTH_SECRET_KEY_CONF;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TwitterSourceConnectorConfigTest {

    private final ConfigDef configDef = TwitterSourceConnectorConfig.conf();
    private final Map<String, String> baseProps = new HashMap<>();

    @BeforeEach
    void setUp() {
        baseProps.put(TWITTER_DEBUG_CONF, "true");
        baseProps.put(TWITTER_OAUTH_CONSUMER_KEY_CONF, "consumer_key");
        baseProps.put(TWITTER_OAUTH_SECRET_KEY_CONF, "secret_key");
        baseProps.put(TWITTER_OAUTH_ACCESS_TOKEN_CONF, "access_token");
        baseProps.put(TWITTER_OAUTH_ACCESS_TOKEN_SECRET_CONF, "access_token_secret");
        baseProps.put(FILTER_KEYWORDS_CONF, "keyword");
        baseProps.put(FILTER_USER_IDS_CONF, "123");
        baseProps.put(KAFKA_STATUS_TOPIC_CONF, "status_topics");
        baseProps.put(PROCESS_DELETES_CONF, "true");
    }

    @Test
    void initConfigIsValid() {
        boolean validationResult = configDef.validate(baseProps).stream()
            .allMatch(configValue -> configValue.errorMessages().size() == 0);
        Assertions.assertTrue(validationResult);
    }

    @Test
    void conf() {
    }

    @Test
    void configuration() {
    }
}