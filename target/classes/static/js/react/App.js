import React, { Component, Fragment } from "react";
//import Card from "./Card";
class App extends Component {
	render() {
		return (
			<Fragment>
				<Card username="Ekaitz10" nombre="Ekaitz" apellido="Soto" role="Admin" id="1"></Card>
				<Card username="iriM" nombre="Unai" apellido="iriarte" role="User" id="2"></Card>
				<Card username="Asiscs" nombre="Asis" apellido="Caballero" role="User" id="3"></Card>
			</Fragment>
		);
	}
}
export default App;