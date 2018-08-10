package cn.habitdiary.form;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@SpringBootApplication
@MapperScan("cn.habitdiary.form.dao")
@ImportResource(locations={"classpath:kaptcha.xml"})
@EnableScheduling
public class FormApplication {

	public static void main(String[] args) {
		System.setProperty("mail.mime.splitlongparameters", "false");
		SpringApplication.run(FormApplication.class, args);
	}


}
