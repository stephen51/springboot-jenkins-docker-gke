package com.stephen.jenkinsdockerkubernetesgke;

import com.stephen.jenkinsdockerkubernetesgke.controller.TestController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JenkinsDockerKubernetesGkeApplicationTests {

	@InjectMocks
	TestController testController;

	@Test
	void contextLoads() {
		Assert.assertEquals("welcome to Jenkins,Docker,Kubernetes and GKE",testController.welcome()); ;
	}

}
