import Reading from './component/Reading';

function App() {
  const readings = [{
    createdAt: new Date(2021, 10, 31, 17, 58),
    temperature: 76.5,
    humidity: 80.2
  }, {
    createdAt: new Date(2021, 10, 31, 17, 59),
    temperature: 77.5,
    humidity: 80.1
  }, {
    createdAt: new Date(2021, 10, 31, 18, 0),
    temperature: 78.5,
    humidity: 80.0
  }, {
    createdAt: new Date(2021, 10, 31, 18, 1),
    temperature: 79.5,
    humidity: 79.9
  }];
  return (
    <div>
      {readings.map((reading) => (
        <Reading createdAt={reading.createdAt} temperature={reading.temperature} humidity={reading.humidity}></Reading>
      ))}
    </div>
  );
}

export default App;
