import ReadingRow from './ReadingRow';
import classes from './ReadingTable.module.css';
import { Col, Form, Button, Row, Table, InputGroup } from 'react-bootstrap';
import { useQuery, gql } from '@apollo/client';
import { Fragment, useEffect, useState } from 'react';
import moment from 'moment';
import { SyncIcon } from '@primer/octicons-react';

const QUERY_SENSORS = gql`{
  readings {
    readingId createdAt temperature humidity sensor {
      sensorId name
    }
  }
}`

const ReadingTable = () => {
  const { loading, error, data } = useQuery(QUERY_SENSORS);
  const [isNow, setNow] = useState(true);
  const [clock, setClock] = useState(moment().add(15, 'm'));
  const dateValue = clock.format('YYYY-MM-DD');
  const hourValue = clock.hour() % 12;
  const minValue = Math.floor(clock.minute() / 15);
  const xiiValue = Math.floor(clock.hour() / 12);

  useEffect(() => {
    const interval = setInterval(() => {
      isNow && setClock(moment().add(15, 'm'));
    }, 5000);
    return () => clearInterval(interval);
  }, [isNow]);

  if (loading) return 'Loading...';

  if (error) return `Error: ${error.message}`;

  const handleDateChange = event => {
    const value = moment(event.target.value);
    setClock(current => moment(current).year(value.year()).month(value.month()).day(value.day()));
    setNow(false);
  };

  const handleHourChange = event => {
    setClock(current => {
      const offset = current.hour() < 12 ? 0 : 12;
      return moment(current).hour(event.target.value + offset);
    });
    setNow(false);
  };

  const handleMinChange = event => {
    setClock(current => moment(current).minute(event.target.value * 15));
    setNow(false);
  };

  const handleXiiChange = event => {
    setClock(current => {
      const offset = parseInt(event.target.value) ? 12 : 0;
      return moment(current).hour(current.hour() % 12 + offset);
    });
    setNow(false);
  };

  const handleNowClick = event => {
    setClock(moment().add(15, 'm'));
    setNow(true);
  }

  return (
    <Fragment>
      <Form as={Row}>
        <Form.Group as={Col} md={3}>
          <Form.Control type="date" value={dateValue} onChange={handleDateChange} />
        </Form.Group>
        <Form.Group as={Col} md={6}>
          <InputGroup>
            <Form.Select value={hourValue} onChange={handleHourChange}>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="0">12</option>
            </Form.Select>
            <Form.Select value={minValue} onChange={handleMinChange}>
              <option value="0">:00</option>
              <option value="1">:15</option>
              <option value="2">:30</option>
              <option value="3">:45</option>
            </Form.Select>
            <Form.Select value={xiiValue} onChange={handleXiiChange}>
              <option value="0">AM</option>
              <option value="1">PM</option>
            </Form.Select>
            <Button variant="secondary" onClick={handleNowClick} disabled={isNow}>
              <SyncIcon size={16} />
            </Button>
          </InputGroup>
        </Form.Group>
        <Form.Group as={Col} md={3}>
          <Form.Select>
            <option value='0'>1 minute</option>
            <option value='1'>5 minutes</option>
            <option value='2'>10 minutes</option>
            <option value='3'>15 minutes</option>
            <option value='4'>30 minutes</option>
            <option value='5'>1 hour</option>
            <option value='6'>3 hours</option>
            <option value='7'>1 day</option>
          </Form.Select>
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
    </Fragment >);
}

export default ReadingTable;