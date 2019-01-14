import React, { Component } from "react";
import { withRouter } from "react-router";

import Axios from "axios";
import ShowAllChildrenTable from "./ShowAllChildrenTable";

class ShowAllParent extends Component {
  state = {
    children: []
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
        <h1>Childrens</h1>
        <ShowAllChildrenTable
          children={this.state.children}
          goTo={this.goToPage}
          apiHost={this.props.apiHost}
          session={this.props.session}
          onDelete={this.deletePerson}
        />
      </div>
    );
  }

  goToPage = path => {
    this.props.history.push(path);
  };

  deletePerson = deleteId => {
    const children = this.state.children.filter(child => child.id !== deleteId);
    this.setState({ children });
  };

  componentDidMount() {
    Axios.post(this.props.apiHost + "/child/list", this.props.session)
      .then(resp => {
        const children = resp.data;
        this.setState({ children });
      })
      .catch(err => console.log(err));
  }
}

export default withRouter(ShowAllParent);
