import React, { Component } from "react";
class Card extends Component {
	render() {
		return (
			<div className="card-wrapper">
				<div id="card-2" className="card card-rotating text-center">
    				      		
					<div className="face front">
						<div className="card-body">
							<a className="rotate-btn float-right" data-card="card-2"><i className="bi bi-gear-fill"></i></a>
							<h4 className="mb-3">{this.props.username}</h4>
							<p className="card-text">`{this.props.nombre} {this.props.apellido}`</p>
						</div>
					</div>
    				    		
					<div className="face back">
						<div className="content text-center">
							<h4 className="card-title font-weight-bold my-4">Acciones <i className="fas fa-times rotate-btn text-muted" data-card="card-2"></i></h4>
							<h5 className="font-weight-bold my-4">{this.props.role}</h5>
    							        
							<ul className="list-unstyled list-inline d-flex justify-content-center flex-wrap mt-4">
								<li className="list-inline-item"><a className="btn btn-success px-4"><i className="bi bi-person-fill-gear"></i></a></li>
								<li className="list-inline-item"><a className="btn btn-tw px-4"><i className="bi bi-person-fill-slash"></i></a></li>
								<li className="list-inline-item">
									<form method="DELETE" action="/usuario/borrar">
										<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
										<button type="submit" className="btn btn-danger px-4"><i className="bi bi-trash-fill"></i></button>
									</form>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		);
	}
}
export default Card;