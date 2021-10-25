package com.dulion.greenhouse.graphql;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import com.coxautodev.graphql.tools.SchemaParserOptions;
import com.dulion.greenhouse.entity.Initializer;
import com.dulion.greenhouse.entity.Reading;
import com.dulion.greenhouse.entity.ReadingRepository;
import com.dulion.greenhouse.entity.Sensor;
import com.dulion.greenhouse.entity.SensorRepository;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GraphQLProvider {

  private static final Logger LOG = LoggerFactory.getLogger(GraphQLProvider.class);

  private GraphQL graphQL;

  @Autowired
  private SensorRepository sensors;

  @Autowired
  private ReadingRepository readings;

  @Autowired
  private Initializer initializer;

  @Bean
  public GraphQL graphQL() {
    return graphQL;
  }

  @Bean
  public SchemaParserOptions schemaParserOptions(){
    return SchemaParserOptions.newOptions().objectMapperConfigurer((mapper, context) -> {
      mapper.registerModule(new JavaTimeModule());
    }).build();
  }

  @PostConstruct
  public void init() throws IOException {
    URL url = Resources.getResource("schema.graphql");
    TypeDefinitionRegistry registry = new SchemaParser().parse(Resources.toString(url, Charsets.UTF_8));
    graphQL = newGraphQL(new SchemaGenerator().makeExecutableSchema(registry, buildWiring())).build();
    initializer.insertData();
  }

  private RuntimeWiring buildWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type(newTypeWiring("Query")
            .dataFetcher("sensors", this::fetchSensors)
            .dataFetcher("readings", this::fetchReadings)
            .dataFetcher("sensorById", this::fetchSensorById)
            .dataFetcher("readingById", this::fetchReadingById))
        .type(newTypeWiring("Reading")
            .dataFetcher("sensor", this::fetchSensorFromReading))
        .type(newTypeWiring("Sensor")
            .dataFetcher("readings", this::fetchReadingsFromSensor))
        .build();
  }

  private Iterable<Sensor> fetchSensors(DataFetchingEnvironment dfe) {
    LOG.info("SensorsFetcher");
    return sensors.findAll();
  }

  private Iterable<Reading> fetchReadings(DataFetchingEnvironment dfe) {
    LOG.info("ReadingsFetcher");
    return readings.findAll();
  }

  private Optional<Sensor> fetchSensorById(DataFetchingEnvironment dfe) {
    LOG.info("SensorByIdFetcher");
    String id = dfe.getArgument("sensorId");
    return sensors.findById(UUID.fromString(id));
  }

  private Optional<Reading> fetchReadingById(DataFetchingEnvironment dfe) {
    LOG.info("ReadingByIdFetcher");
    String id = dfe.getArgument("readingId");
    return readings.findById(UUID.fromString(id));
  }

  private Iterable<Reading> fetchReadingsFromSensor(DataFetchingEnvironment dfe) {
    LOG.info("ChildReadingFetcher");
    Sensor sensor = dfe.getSource();
    return readings.findBySensorId(sensor.getSensorId());
  }

  private Optional<Sensor> fetchSensorFromReading(DataFetchingEnvironment dfe) {
    LOG.info("ChildSensorFetcher");
    Reading reading = dfe.getSource();
    return sensors.findById(reading.getSensorId());
  }
}
