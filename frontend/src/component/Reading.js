import './Reading.css';

function Reading(props) {
  return (
    <div className="reading">
      <div>{props.createdAt.toLocaleString()}</div>
      <div>Temperature: {props.temperature}&deg;</div>
      <div>Humidity: {props.humidity}%</div>
    </div>
  );
}

export default Reading
