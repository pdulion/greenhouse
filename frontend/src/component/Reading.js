import classes from './Reading.module.css';
import NumberFormat from 'react-number-format';

export const UploadApp = props => {
  return <h1>Hello!</h1>
}

const Reading = (props) => {
  const reading = props.reading;

  return (
    <tr>
      <td>{reading.sensor.name}</td>
      <td>{reading.createdAt.toLocaleString()}</td>
      <td className={classes.numeric}><NumberFormat
        value={reading.temperature}
        displayType={"text"}
        decimalScale={1}
        fixedDecimalScale={true}
        suffix="&deg;" /></td>
      <td className={classes.numeric}><NumberFormat
        value={reading.humidity}
        displayType={"text"}
        decimalScale={1}
        fixedDecimalScale={true}
        suffix="%" /></td>
    </tr>
  );
}

export default Reading
