package at.allianz.restserver.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
class VendingMachineApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new VendingMachineApplication() //
				.configure(new SpringApplicationBuilder(VendingMachineApplication.class)) //
				.run(args);
	}

	// http://localhost:8080/vending/api/v2/api-docs
	// http://localhost:8080/vending/api/swagger-ui.html
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.apiInfo(apiInfo()) //
				.select() //
				.apis(RequestHandlerSelectors.any()) //
				.paths(PathSelectors.any()) //
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder() //
				.title("Spring REST Sample with Swagger") //
				.description("Spring REST Sample with Swagger") //
				.version("1.0") //
				.build();
	}

}