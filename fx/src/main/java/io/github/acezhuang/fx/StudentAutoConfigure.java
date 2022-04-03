package io.github.acezhuang.fx;

import io.github.acezhuang.fx.props.StudentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(StudentConfiguration.class)
@EnableConfigurationProperties(StudentProperties.class)
@ConditionalOnProperty(prefix = "spring.student", value = "enable", matchIfMissing = false)
public class StudentAutoConfigure {

    @Autowired
    StudentProperties studentProperties;
    @Autowired
    StudentConfiguration studentConfiguration;

    @Bean
    public Student createStudent(){
        return Student.create(studentProperties.getId(), "name1");
    }

    @Bean
    public Klass createKlass(){
        return new Klass();
    }
}
