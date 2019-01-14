import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";

class AddNews extends Component {
  state = {
    title: "",
    context: "",
    alertType: "",
    message: " "
  };
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
        <h4 className="mb-3">Add new news</h4>

        <form>
          <div className="row">
            <div className="col-md-12 mb-3">
              <label htmlFor="title">Title</label>
              <input
                type="text"
                className="form-control"
                id="title"
                value={this.state.title}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-12 mb-3">
              <label>Content</label>
              <textarea
                type="text"
                id="context"
                value={this.state.context}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
                className="form-control"
              />
            </div>
          </div>
          <div className="row">
            <button
              className="btn btn-primary m-2"
              onClick={e => this.props.history.push("/app")}
            >
              SHOW ALL MESSAGES
            </button>
            <button className="btn btn-success m-2" onClick={this.addNews}>
              ADD NEWS
            </button>
            <div className={"alert m-2 alert-" + this.state.alertType}>
              {this.state.message}
            </div>
          </div>
        </form>
      </div>
    );
  }

  addNews = () => {
    const request = {
      loginData: this.props.session,
      news: this.state,
      newsId: null
    };

    console.log(request);

    Axios.post(this.props.apiHost + "/news/add", request)
      .then((res, req) =>
        this.setState({
          alertType: "success",
          message: "News added! Now you can add more."
        })
      )
      .catch(err => console.log(err));
  };
}

export default withRouter(AddNews);
