import { ApolloClient, InMemoryCache, ApolloProvider, } from "@apollo/client";
import { useCallback, useState } from "react";
import { Container } from 'react-bootstrap';
import ReadingFilter from "./component/reading/ReadingFilter";
import ReadingTable from './component/reading/ReadingTable';

const client = new ApolloClient({
  uri: 'http://localhost:8080/graphql',
  cache: new InMemoryCache()
});

const App = () => {
  const [criteria, setCriteria] = useState({ datetime: null, interval: 0 });
  const handleFilterChange = useCallback(setCriteria, [setCriteria]);

  return (
    <ApolloProvider client={client}>
      <Container md={{ span: 10, offset: 1 }}>
        <ReadingFilter onChange={handleFilterChange} />
        <ReadingTable datetime={criteria.datetime} interval={criteria.interval} />
      </Container>
    </ApolloProvider>
  );
}

export default App;
