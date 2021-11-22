import ReadingRow from './ReadingRow';
import classes from './ReadingTable.module.css';
import { Col, Form, Row, Table } from 'react-bootstrap';
import { useQuery, gql } from '@apollo/client';
import { Fragment } from 'react';

const QUERY_SENSORS = gql`{
  readings {
    readingId createdAt temperature humidity sensor {
      sensorId name
    }
  }
}`

const ReadingTable = () => {
  const { loading, error, data } = useQuery(QUERY_SENSORS);

  if (loading) return 'Loading...';

  if (error) return `Error: ${error.message}`

  return (
    <Fragment>
      <Form>
        <Form.Group as={Row}>
          <Form.Label column sm="2">Date</Form.Label>
          <Col sm="10">
            <Form.Control type="date" />
          </Col>
        </Form.Group>
        <Form.Group as={Row}>
          <Form.Label column sm="2">Interval</Form.Label>
          <Col sm="10">
            <Form.Select>
              <option value='0'>1 minute</option>
              <option value='1'>5 minutes</option>
              <option value='1'>10 minutes</option>
              <option value='1'>15 minutes</option>
              <option value='2'>30 minutes</option>
              <option value='3'>1 hour</option>
              <option value='3'>3 hours</option>
              <option value='4'>1 day</option>
            </Form.Select>
          </Col>
        </Form.Group>
      </Form>
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
      </Table>
    </Fragment>);
}

export default ReadingTable;