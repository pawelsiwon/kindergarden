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
            src={this.props.img}
            alt=""
            className="center-block img-fluid screen-img"
          />
          <h3>{this.props.screenTitle}</h3>
        </div>
        <div className="row">
          {this.props.buttons.map(btn => (
            <button
              className="btn btn-primary m-1 btn-block"
              onClick={() => this.props.history.push(btn.path)}
            >
              {btn.title}
            </button>
          ))}
        </div>
      </div>
    );
  }
}

export default withRouter(ScreenElement);
