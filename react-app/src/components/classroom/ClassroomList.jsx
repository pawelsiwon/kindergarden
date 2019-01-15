import React, { Component } from "react";
import { withRouter } from "react-router";

class ClassroomList extends Component {
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
          <button
            className="btn btn-success m-2"
            onClick={e => this.props.history.push("/classroom/add")}
          >
            Add new
          </button>
        </div>
      </div>
    );
  }
}

export default withRouter(ClassroomList);
