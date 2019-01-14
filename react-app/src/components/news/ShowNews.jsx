import React, { Component } from "react";

class ShowNews extends Component {
  state = { newsList: [] };
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
        <h4 className="mb-3">Recent news:</h4>
        {this.state.newsList.map(news => (
          <div className="col col-md-6">
            <h3>{news.title}</h3>
          </div>
        ))}
      </div>
    );
  }

  componentDidMount() {}
}

export default ShowNews;
