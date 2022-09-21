package team.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import team.RoomApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { RoomApplication.class })
public class CucumberSpingConfiguration {}
