import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";
import { nsend } from "q";

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
          <button
            className="btn btn-success m-2"
            onClick={e => this.props.history.push("/news/add")}
          >
            Add new
          </button>
        </div>
        <h3 className="mb-3">Recent news:</h3>
        <div className="row">
          {this.state.newsList.map(news => (
            <div className="blog-main col-md-12">
              <div className="blog-post">
                <h2 className="blog-post-title">{news.title}</h2>
                <p className="blog-post-meta">
                  {"created by " +
                    news.person.name +
                    " " +
                    news.person.surname +
                    " on " +
                    news.createdDate}
                </p>
                <p className="border-bottom alert alert-primary">
                  {news.contetnt}
                </p>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }

  componentDidMount() {
    console.log(this.props.apiHost + "/news/list", this.props.session);
    Axios.post(this.props.apiHost + "/news/list", this.props.session)
      .then((res, req) => {
        const newsNew = res.data;
        console.log(newsNew);
        this.setState({ newsList: newsNew });
      })
      .catch(err => console.log(err));
  }
}

export default withRouter(ShowNews);
