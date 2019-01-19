import React, { Component } from "react";
import { withRouter } from "react-router";
import "./../css/ScreenElement.css";

class ScreenElement extends Component {
  state = {};
  render() {
    return (
      <div className="col-5 m-2 container">
        <div className="text-center row-fluid">
          <img
            src="/img/person.png"
            alt=""
            className="center-block img-fluid screen-img"
          />
          <h3>PERSON</h3>
        </div>
        <div className="row">
          <button
            className="btn btn-primary m-1 btn-block"
            onClick={() => this.props.history.push("/person/add")}
          >
            ADD
          </button>
          <div
            className="btn-group d-flex"
            role="group"
            aria-label="Basic example"
          >
            <button
              type="button"
              onClick={() => this.props.history.push("/parents/show")}
              className="btn btn-primary"
            >
              PARENTS
            </button>
            <button
              type="button"
              onClick={() => this.props.history.push("/teachers/show")}
              className="btn btn-primary"
            >
              TEACHERS
            </button>
            <button
              type="button"
              onClick={() => this.props.history.push("/admins/show")}
              className="btn btn-primary"
            >
              ADMINS
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(ScreenElement);
