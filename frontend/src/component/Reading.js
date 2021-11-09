import './Reading.css';
import NumberFormat from 'react-number-format';

const Reading = (props) => {
  const reading = props.reading;

  return (
    <div className="reading">
      <div>{reading.createdAt.toLocaleString()}</div>
      <div><NumberFormat
        value={reading.temperature}
        displayType={"text"}
        decimalScale={1}
        fixedDecimalScale={true}
        suffix="&deg;" /></div>
      <div><NumberFormat
        value={reading.humidity}
        displayType={"text"}
        decimalScale={1}
        fixedDecimalScale={true}
        suffix="%" /></div>
    </div>
  );
}

export default Reading
