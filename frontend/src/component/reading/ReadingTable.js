import ReadingRow from "./ReadingRow";
import classes from './ReadingTable.module.css';
import { Table } from 'react-bootstrap';
import { useQuery, gql } from "@apollo/client";

const QUERY_SENSORS = gql`{
  readings {
    readingId createdAt temperature humidity sensor {
      sensorId name
    }
  }
}`

const ReadingTable = () => {
  const { loading, error, data } = useQuery(QUERY_SENSORS);

  if (loading) return "Loading...";

  if (error) return `Error: ${error.message}`

  return (
    <Table striped bordered className={classes.readings}>
      <thead>
        <tr>
          <th>Sensor</th>
          <th>Date/Time</th>
          <th>Temperature</th>
          <th>Humidity</th>
        </tr>
      </thead>
      <tbody>
        {data.readings.map(reading => <ReadingRow key={reading.readingId} reading={reading} />)}
      </tbody>
    </Table>);
}

export default ReadingTable;