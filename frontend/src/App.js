import Reading from './component/Reading';
import {
  ApolloClient,
  InMemoryCache,
  ApolloProvider,
  useQuery,
  gql
} from "@apollo/client";

const client = new ApolloClient({
  uri: 'http://localhost:8080/graphql',
  cache: new InMemoryCache()
});

function App() {
  client.query({
    query: gql`
      query {
        sensorById(sensorId: "e56e12d5-52ca-49a1-a7f6-4392325d960d") {
          sensorId name heatDegrees coolDegrees dryPercent dryOnSpan dryOffSpan createdAt updatedAt
          readings {
            readingId temperature humidity createdAt 
          }
        }
      }`
  }).then(result => console.log(result));

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
    <div>
      {readings.map(reading => <Reading key={reading.key} reading={reading} />)}
    </div>
  );
}

export default App;
