package team.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import team.MessageApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { MessageApplication.class })
public class CucumberSpingConfiguration {}
