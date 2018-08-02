package cn.habitdiary.form;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("cn.habitdiary.form.dao")
@ImportResource(locations={"classpath:kaptcha.xml"})
public class FormApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormApplication.class, args);
	}
}
