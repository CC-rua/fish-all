package com.fish.config.load.yaml;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class YamlConfigAutoReader {
    public static YamlConfigAutoReader INSTANCE = new YamlConfigAutoReader();

    public void autoReadYml() {
        try {
            //获取该路径下所有类
            Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages("com.fish"));
            //获取继承的所有类
            Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(AutoLoadYmlConfig.class);

            for (Class<?> clazz : classSet) {
                //是否继承自 YamlConfigReader
                if (!YamlConfigReader.class.isAssignableFrom(clazz)) {
                    continue;
                }
                AutoLoadYmlConfig clazzAnnotation = clazz.getAnnotation(AutoLoadYmlConfig.class);
                if (clazzAnnotation == null) {
                    continue;
                }
                String configPath = clazzAnnotation.value();
                // 获取无参数构造函数
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                // 创建类的实例
                YamlConfigReader instance = (YamlConfigReader) constructor.newInstance();
                //获取clazz的静态实例
                instance = instance.createInstance();
                instance.loadYamlConfig(configPath);
                for (Field declaredField : clazz.getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    AutoLoadYmlConfig annotation = declaredField.getAnnotation(AutoLoadYmlConfig.class);
                    if (annotation == null) {
                        continue;
                    }
                    String paramPath = annotation.value();

                    Object parameterValue = instance.getParameterValue(paramPath);
                    if (parameterValue == null) {
                        continue;
                    }
                    declaredField.set(instance, parameterValue);
                    log.info("[yaml load]   {} === {}", paramPath, parameterValue);
                }

            }
        } catch (Exception ex) {
            log.error("YamlConfigAutoReader.init()", ex);
        }
    }
}
