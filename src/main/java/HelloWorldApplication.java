/**
 * Created by geoHeil on 17.01.15.
 */

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;

import java.io.IOException;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
        swaggerDropwizard.onInitialize(bootstrap);
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {


        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(new SampleResource());
        try {
            swaggerDropwizard.onRun(configuration, environment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
