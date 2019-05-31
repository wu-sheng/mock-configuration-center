package org.apache.skywalking.configuration.server.mock;

import io.grpc.stub.StreamObserver;
import java.util.*;
import org.apache.skywalking.oap.server.configuration.service.*;

public class ConfigServiceImpl extends ConfigurationServiceGrpc.ConfigurationServiceImplBase {
    private Map<String, String> configMap = new HashMap<>();

    @Override public void call(ConfigurationRequest request, StreamObserver<ConfigurationResponse> responseObserver) {
        super.call(request, responseObserver);
    }

    public void put(String key, String value) {
        configMap.put(key, value);
    }

    public String get(String key) {
        return configMap.get(key);
    }

    public String list() {
        return configMap.toString();
    }
}
