import React, { Component } from "react";

class ScreenElement extends Component {
  state = {};
  render() {
    return (
      <div className="col-5 m-2 container">
        <div className="text-center row-fluid">
          <img
            src={this.props.img}
            alt=""
            className="center-block "
          />
        </div>
        <div className="row">
          <button
            onClick={this.props.buttonOnClick}
            className="btn m-2 btn-primary btn-block"
          >
            {this.props.buttonValue}
          </button>
        </div>
      </div>
    );
  }
}

export default ScreenElement;
