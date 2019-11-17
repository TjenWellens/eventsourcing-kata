package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class EventStorage {
    private Map<String, List<Event>> streams = new HashMap<>();

    void appendToStream(String streamId, List<Event> events) {
        final List<Event> stream = this.streams.getOrDefault(streamId, new ArrayList<>());
        streams.put(streamId, stream);

        stream.addAll(events);
    }

    List<Event> streamById(String streamId) {
        final List<Event> stream = streams.get(streamId);
        if (stream == null) {
            throw new IllegalStateException();
        }
        return stream;
    }
}
