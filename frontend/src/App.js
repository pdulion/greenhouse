import Reading from './component/Reading';
import {
  ApolloClient,
  InMemoryCache,
  ApolloProvider,
  useQuery,
  gql
} from "@apollo/client";
import { Col, Container, Row } from 'react-bootstrap';

const client = new ApolloClient({
  uri: 'http://localhost:8080/graphql',
  cache: new InMemoryCache()
});

const QUERY_SENSORS = gql`
  query {
    sensors {
      sensorId name heatDegrees coolDegrees dryPercent dryOnSpan dryOffSpan createdAt updatedAt
      readings {
        readingId temperature humidity createdAt 
      }
    }
  }
`
  
const Greenhouse = () => {
  const { loading, error, data } = useQuery(QUERY_SENSORS);

  if (loading) return "Loading...";

  if (error) return `Error: ${error.message}`

  console.log(data);

  const readings = [{
    key: 1,
    createdAt: new Date(2021, 10, 31, 17, 58),
    temperature: 76.5,
    humidity: 80.2
  }, {
    key: 2,
    createdAt: new Date(2021, 10, 31, 17, 59),
    temperature: 77.5,
    humidity: 80.1
  }, {
    key: 3,
    createdAt: new Date(2021, 10, 31, 18, 0),
    temperature: 78.5,
    humidity: 80.0
  }, {
    key: 4,
    createdAt: new Date(2021, 10, 31, 18, 1),
    temperature: 79.5,
    humidity: 79.9
  }];

  return (
    <Container md={{ span: 10, offset: 1 }}>
      {readings.map(reading => <Reading key={reading.key} reading={reading} />)}
    </Container>
  );
}

const App = () => {
  return (
    <ApolloProvider client={client}>
      <Greenhouse />
    </ApolloProvider>
  );
}

export default App;
