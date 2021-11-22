import moment from 'moment';
import NumberFormat from 'react-number-format';

const ReadingRow = props => {
  const { sensor, createdAt, temperature, humidity } = props.reading;

  return (
    <tr>
      <td>{sensor.name}</td>
      <td>{moment(createdAt).format('lll')}</td>
      <td><NumberFormat
        value={temperature}
        displayType={"text"}
        decimalScale={1}
        fixedDecimalScale={true}
        suffix="&deg;" /></td>
      <td><NumberFormat
        value={humidity}
        displayType={"text"}
        decimalScale={1}
        fixedDecimalScale={true}
        suffix="%" /></td>
    </tr>
  );
};

export default ReadingRow;
