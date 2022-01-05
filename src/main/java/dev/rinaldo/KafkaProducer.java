package dev.rinaldo;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;

@ApplicationScoped
public class KafkaProducer implements QuarkusApplication {

    private static final Random RANDOM = new Random();

    @Outgoing("out")
    public Multi<Double> generate() {
        
        return Multi.createFrom()
            .ticks()
            .every(Duration.ofSeconds(10))
            .map(x -> RANDOM.nextDouble());
    }

    @Override
    public int run(String... args) throws Exception {
        Quarkus.waitForExit();
        return 0;
    }
    
}
