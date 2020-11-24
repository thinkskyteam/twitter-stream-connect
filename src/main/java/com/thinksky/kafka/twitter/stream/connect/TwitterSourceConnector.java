package com.thinksky.kafka.twitter.stream.connect;

import com.github.jcustenborder.kafka.connect.utils.config.Description;
import com.github.jcustenborder.kafka.connect.utils.config.Title;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Title("Twitter")
@Description("This Twitter Source connector is used to pull data from Twitter in realtime.")
public class TwitterSourceConnector extends SourceConnector {
    private static final Logger log = LoggerFactory.getLogger(TwitterSourceConnector.class);
    Map<String, String> settings;
    private TwitterSourceConnectorConfig config;

    @Override
    public String version() {
        return GetVersion.generate(this.getClass());
    }

    @Override
    public void start(Map<String, String> map) {
        this.config = new TwitterSourceConnectorConfig(map);
        this.settings = map;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return TwitterSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        Preconditions.checkState(maxTasks > 0, "MaxTasks must be greater than 0");
        final int tasks = Math.min(maxTasks, this.config.filterKeywords.size());


        Multimap<Integer, String> taskToKeywords = ArrayListMultimap.create();
        int index = 0;
        for (String keyword : this.config.filterKeywords) {
            final int taskID = index % tasks;
            taskToKeywords.put(taskID, keyword);
            index++;
        }
        final List<Map<String, String>> taskConfigs = new ArrayList<>(tasks);

        for (Integer taskID : taskToKeywords.keySet()) {
            Collection<String> keywords = taskToKeywords.get(taskID);
            Map<String, String> taskSettings = new LinkedHashMap<>(this.settings);
            taskSettings
                .put(TwitterSourceConnectorConfig.FILTER_KEYWORDS_CONF,
                    Joiner.on(',').join(keywords));
            taskConfigs.add(taskSettings);
        }

        return taskConfigs;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        return TwitterSourceConnectorConfig.conf();
    }
}
