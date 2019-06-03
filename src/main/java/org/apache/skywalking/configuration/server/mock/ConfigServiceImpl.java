package org.apache.skywalking.configuration.server.mock;

import io.grpc.stub.StreamObserver;
import java.util.*;
import org.apache.skywalking.oap.server.configuration.service.*;

public class ConfigServiceImpl extends ConfigurationServiceGrpc.ConfigurationServiceImplBase {
    private Map<String, String> configMap = new HashMap<>();

    @Override public void call(ConfigurationRequest request, StreamObserver<ConfigurationResponse> responseObserver) {
        ConfigurationResponse.Builder builder = ConfigurationResponse.newBuilder();
        configMap.forEach((key, value) -> {
            builder.addConfigTable(Config.newBuilder().setName(key).setValue(value).build());
        });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
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
