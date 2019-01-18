import React, { Component } from "react";
import { withRouter } from "react-router";

class ClassroomAdd extends Component {
  state = {};
  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e => this.props.history.push("/app")}
          >
            Return
          </button>
        </div>
        <h4 className="mb-3">Add new classroom</h4>
        <form className="needs-validation" />
      </div>
    );
  }
}

export default withRouter(ClassroomAdd);
