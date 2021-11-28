import { SyncIcon } from '@primer/octicons-react';
import moment from 'moment';
import React, { useEffect, useReducer, useState } from 'react';
import { Button, Col, Form, InputGroup, Row } from 'react-bootstrap';

const initialState = {
  datetime: moment().add(15, 'm'),
  interval: 5
};

const reducer = (state, action) => {
  const datetime = moment(state.datetime);
  switch (action.type) {
    case 'datetime':
      return {
        ...state,
        datetime: action.value
      }
    case 'date':
      const value = action.value;
      return {
        ...state,
        datetime: datetime.year(value.year()).month(value.month()).day(value.day())
      }
    case 'hour':
      return {
        ...state,
        datetime: datetime.hour(action.value + (datetime.hour() < 12) ? 0 : 12)
      }
    case 'minute':
      return {
        ...state,
        datetime: datetime.minute(action.value * 15)
      }
    case 'midday':
      return {
        ...state,
        datetime: datetime.hour(datetime.hour() % 12 + parseInt(action.value) ? 12 : 0)
      }
    case 'interval':
      return {
        ...state,
        interval: action.value
      }
    default:
      throw new Error();
  }
};

const ReadingFilter = ({ onChange }) => {
  const [isLatest, setLatest] = useState(true);
  const [{ datetime, interval }, dispatch] = useReducer(reducer, initialState);

  useEffect(() => {
    onChange({datetime, interval});
  }, [datetime, interval, onChange]);

  useEffect(() => {
    const id = setInterval(() => {
      if (isLatest) {
        dispatch({
          type: 'datetime',
          value: moment().add(15, 'm')
        });
      }
    }, 5000);
    return () => clearInterval(id);
  }, [isLatest]);


  const handleDateChange = event => {
    setLatest(false);
    dispatch({
      type: 'date',
      value: moment(event.target.value)
    });
  };

  const handleHourChange = event => {
    setLatest(false);
    dispatch({
      type: 'hour',
      value: parseInt(event.target.value)
    });
  };

  const handleMinChange = event => {
    setLatest(false);
    dispatch({
      type: 'minute',
      value: parseInt(event.target.value)
    });
  };

  const handleMidChange = event => {
    setLatest(false);
    dispatch({
      type: 'midday',
      value: parseInt(event.target.value)
    });
  };

  const handleNowClick = event => {
    setLatest(true);
    dispatch({
      type: 'datetime',
      value: moment().add(15, 'm')
    });
  }

  const handleIntervalChange = event => {
    dispatch({
      type: 'interval',
      value: parseInt(event.target.value)
    });
  }

  const dateValue = datetime.format('YYYY-MM-DD');
  const hourValue = datetime.hour() % 12;
  const minValue = Math.floor(datetime.minute() / 15);
  const midValue = Math.floor(datetime.hour() / 12);
  return (
    <Form as={Row}>
      <Form.Group as={Col} md={3}>
        <Form.Control type='date' value={dateValue} onChange={handleDateChange} />
      </Form.Group>
      <Form.Group as={Col} md={6}>
        <InputGroup>
          <Form.Select value={hourValue} onChange={handleHourChange}>
            <option value='1'>1</option>
            <option value='2'>2</option>
            <option value='3'>3</option>
            <option value='4'>4</option>
            <option value='5'>5</option>
            <option value='6'>6</option>
            <option value='7'>7</option>
            <option value='8'>8</option>
            <option value='9'>9</option>
            <option value='10'>10</option>
            <option value='11'>11</option>
            <option value='0'>12</option>
          </Form.Select>
          <Form.Select value={minValue} onChange={handleMinChange}>
            <option value='0'>:00</option>
            <option value='1'>:15</option>
            <option value='2'>:30</option>
            <option value='3'>:45</option>
          </Form.Select>
          <Form.Select value={midValue} onChange={handleMidChange}>
            <option value='0'>AM</option>
            <option value='1'>PM</option>
          </Form.Select>
          <Button variant='secondary' onClick={handleNowClick} disabled={isLatest}>
            <SyncIcon size={16} />
          </Button>
        </InputGroup>
      </Form.Group>
      <Form.Group as={Col} md={3}>
        <Form.Select value={interval} onChange={handleIntervalChange}>
          <option value='1'>1 minute</option>
          <option value='5'>5 minutes</option>
          <option value='10'>10 minutes</option>
          <option value='15'>15 minutes</option>
          <option value='30'>30 minutes</option>
          <option value='60'>1 hour</option>
          <option value='180'>3 hours</option>
          <option value='1440'>1 day</option>
        </Form.Select>
      </Form.Group>
    </Form>
  )
};

export default React.memo(ReadingFilter);