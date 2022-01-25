import React from "react";
import PropTypes from 'prop-types'
const ComponenteApp = (props) => {
    return (
    <>
        <p>La suma de { props.num1} y {props.num2} es: {props.num1 + props.num2}</p>
    </>
    );
}
ComponenteApp.propTypes = {
    info: PropTypes.number.isRequired
}
export default ComponenteApp;
