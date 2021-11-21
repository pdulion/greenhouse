import Reading, {UploadApp} from './component/Reading';
import {
  ApolloClient,
  InMemoryCache,
  ApolloProvider,
  useQuery,
  gql
} from "@apollo/client";
import { Container, Table } from 'react-bootstrap';

const client = new ApolloClient({
  uri: 'http://localhost:8080/graphql',
  cache: new InMemoryCache()
});

const QUERY_SENSORS = gql`{
  readings {
    readingId createdAt temperature humidity sensor {
      sensorId name
    }
  }
}`
  
const Greenhouse = () => {
  const { loading, error, data } = useQuery(QUERY_SENSORS);

  if (loading) return "Loading...";

  if (error) return `Error: ${error.message}`

  console.log(data);

  return (
    <Container md={{ span: 10, offset: 1 }}>
      <Table striped bordered>
        <tbody>
          {data.readings.map(reading => <Reading key={reading.readingId} reading={reading} />)}
        </tbody>
      </Table>
    </Container>
  );
}

const App = () => {
  return (
    <ApolloProvider client={client}>
      <UploadApp />
      <Greenhouse />
    </ApolloProvider>
  );
}

export default App;
