package com.fish.config.load.yaml;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class YamlConfigReader {
    private Map<String, Object> paramMap;

    public YamlConfigReader() {
        paramMap = new HashMap<>();
    }

    public abstract YamlConfigReader createInstance();

    public boolean loadYamlConfig(String yamlFilePath) {
        log.info("loadYamlConfig path:{}", yamlFilePath);
        InputStream inputStream = this.getClass().getResourceAsStream(yamlFilePath);
        Yaml yaml = new Yaml();
        paramMap = yaml.load(inputStream);
        return true;
    }

    @SuppressWarnings("unchecked")
    public Object getParameterValue(String parameterPath) {
        String[] pathSegments = parameterPath.split("\\.");

        if (paramMap != null) {
            Map<String, Object> currentData = paramMap;
            for (String pathSegment : pathSegments) {
                if (currentData.containsKey(pathSegment)) {
                    Object o = currentData.get(pathSegment);
                    if (o instanceof Map) {
                        currentData = (Map<String, Object>) o;
                    } else {
                        return o;
                    }

                } else {
                    return null; // 返回 null 表示未找到参数值
                }
            }
            return currentData;
        }

        return null;
    }


    public Integer getAsInteger(String paramName, Integer defaultParam) {
        Object param = getParameterValue(paramName);
        if (param != null) {
            defaultParam = (Integer) param;
        }
        return defaultParam;
    }

    public String getAsString(String paramName, String defaultParam) {
        Object param = getParameterValue(paramName);
        if (param != null) {
            defaultParam = (String) param;
        }
        return defaultParam;
    }

}
