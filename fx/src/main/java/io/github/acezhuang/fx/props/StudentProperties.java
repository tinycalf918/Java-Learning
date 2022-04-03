package io.github.acezhuang.fx.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "student")
public class StudentProperties {
    private String id = "s1";

}
