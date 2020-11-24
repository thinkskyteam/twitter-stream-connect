package com.thinksky.kafka.twitter.stream.connect;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetVersion {
    static final Logger log = LoggerFactory.getLogger(TwitterSourceTask.class);

    public static String generate(Class<?> clazz) {
        String result;

        try {
            result = GetVersion.class.getPackage().getImplementationVersion();

            if (Strings.isNullOrEmpty(result)) {
                result = "0.0.0";
            }
        } catch (Exception ex) {
            log.error("Exception thrown while getting error", ex);
            result = "0.0.0";
        }
        return result;
    }
}
