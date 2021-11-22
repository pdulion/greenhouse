import { ApolloClient, InMemoryCache, ApolloProvider, } from "@apollo/client";
import { Container } from 'react-bootstrap';
import ReadingTable from './component/reading/ReadingTable';

const client = new ApolloClient({
  uri: 'http://localhost:8080/graphql',
  cache: new InMemoryCache()
});

const App = () => {
  return (
    <ApolloProvider client={client}>
      <Container md={{ span: 10, offset: 1 }}>
        <ReadingTable />
      </Container>
    </ApolloProvider>
  );
}

export default App;
